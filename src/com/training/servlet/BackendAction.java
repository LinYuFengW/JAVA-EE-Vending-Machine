package com.training.servlet;

import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.training.model.Commodity;
import com.training.service.BackendService;
import com.training.vo.BackendFormData;

@MultipartConfig
public class BackendAction extends DispatchAction {
	
	private BackendService backendService = BackendService.getInstance();
	
	//商品列表
    public ActionForward queryGoods(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) {
		List<Commodity> commoditys = backendService.queryAllCommodity();
		request.setAttribute("commoditys", commoditys);
		
//		HttpSession session = request.getSession();
//		session.setAttribute("commoditys", commoditys);
//		commoditys.stream().forEach(a -> System.out.println(a));

		return mapping.findForward("commodityListView");
	}
	
    //商品維護作業
    public ActionForward updateGoods(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception{	
    	
    	HttpSession session = request.getSession();
    	// 將表單資料使用 struts ActionForm 方式自動綁定，省去多次由 request getParameter 取表單資料的工作
    	BackendFormData formData = (BackendFormData) form;
    	   	
		// 將Struts BackedActionForm 資料複製 Goods
		// 將表單資料轉換儲存資料物件(commons-beanutils-1.8.0.jar)
    	Commodity commodity = new Commodity();
		BeanUtils.copyProperties(commodity, formData);
		
		//庫存查詢
		String id = commodity.getGoodsID();
//    	List<Commodity> stock = backendService.searchCommodity(id);
    	Commodity stock = backendService.searchCommodity(id);
    	
    	//原庫存+補貨數量
//    	int stockUpdate = Integer.parseInt(commodity.getGoodsQuantity()) + Integer.parseInt(stock.get(0).getGoodsQuantity());
    	int stockUpdate = Integer.parseInt(commodity.getGoodsQuantity()) + Integer.parseInt(stock.getGoodsQuantity());
    	commodity.setGoodsQuantity(Integer.toString(stockUpdate));
		
    	//商品更新
		boolean modifyResult = backendService.modifyCommodity(commodity);
		String modifyResultMessage = modifyResult ? "商品更新成功！" : "商品更新失敗！";
		System.out.println(modifyResultMessage);
		session.setAttribute("modifyResultMessage", modifyResultMessage);
		
		//商品更新後資訊
		commodity.setGoodsName(stock.getGoodsName());
		session.setAttribute("modifyView", commodity);
		
		
		
//		System.out.println("商品更新後資訊");		
//    	Commodity goodsNews = backendService.searchCommodity(id);
//    	session.setAttribute("commoditysList", goodsNews);

		return mapping.findForward("commodityModify");
	}
	
    // 下拉式選單，選商品時，資料顯示在畫面上
    public ActionForward modifyView(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception{
    	
    	HttpSession session = request.getSession();
    	
    	//商品列表
    	List<Commodity> commodityList = backendService.queryAllCommodity();
		request.setAttribute("commoditysList", commodityList);
//		commodityList.stream().forEach(a -> System.out.println(a));

		//下拉式選單，選擇要修改的資料
		String goodsID = request.getParameter("goodsID");
		//檢查是否有更新商品資料
		Commodity updateGoods = (Commodity)session.getAttribute("modifyView");
				
		//判斷(換選項)還是(更新)，這二個都會傳入goodsID
		if(goodsID == null && updateGoods!= null ){
			goodsID = updateGoods.getGoodsID();
			
			JSONObject updateData = JSONObject.fromObject(updateGoods);
			updateData.put("updateMessage", "商品更新成功"); //更新訊息
			updateData.put("updatePrice", updateGoods.getGoodsPrice()); //更新價格
			updateData.put("stockQuantity", updateGoods.getGoodsQuantity()); //庫存
			updateData.put("updateQuantity", "0"); //補貨數量設為0
			
			String status = updateGoods.getStatus().equals("1")? "上架" : "下架";
			updateData.put("updateStatus", status); //商品狀態
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(updateData);
			out.flush();
			out.close();
		}
		
		//商品狀態1跟0改成，上架、下架
		if(goodsID != null && !"".equals(goodsID)){
			//拿到要修改的資料清單
			Commodity checklist = backendService.searchCommodity(goodsID);
			if (checklist.getStatus().equals("1")) {
				checklist.setStatus("上架");
			} else {
				checklist.setStatus("下架");
			}						
			request.setAttribute("modifyView", checklist);
			
			
		}
		
    	return mapping.findForward("commodityModifyView");
    }
    
    //商品新增上架
    public ActionForward addGoods(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception{		
    	
    	HttpSession session = request.getSession();
    	// 將表單資料使用 struts ActionForm 方式自動綁定，省去多次由 request getParameter 取表單資料的工作
    	BackendFormData formData = (BackendFormData) form;
    	
		// 將Struts BackedActionForm 資料複製 Goods
		// 將表單資料轉換儲存資料物件(commons-beanutils-1.8.0.jar)
    	Commodity commodity = new Commodity();
		BeanUtils.copyProperties(commodity, formData);
		
		//上傳檔案，儲存位置 		
		String goodsImgPath = getServlet().getInitParameter("GoodsImgPath");
		String severGoodsImgPath = getServlet().getServletContext().getRealPath(goodsImgPath);
		
//		Part filePart = request.getPart("goodsImage");
//		String fileName = filePart.getSubmittedFileName();
		
		FormFile filePart = commodity.getGoodsImage();
		String fileName = filePart.getFileName();
		
		Path severImgPath = Paths.get(severGoodsImgPath).resolve(fileName);
		try(InputStream fileContent = filePart.getInputStream();){
//			Files.copy(fileContent, severImgPath);
			Files.copy(fileContent, severImgPath, StandardCopyOption.REPLACE_EXISTING);
		}
		
		//取得資料庫商品項目數量+1
//		List<Commodity> commoditys = backendService.queryAllCommodity();
//		String goodsNo = Integer.toString(commoditys.size() + 1 );
		
		//set新的GoodsID
//		commodity.setGoodsID(goodsNo);
		
		//取得圖片名稱set到commodity
		commodity.setImageName(fileName);
		
		//更新資料庫，商品新增上架
		boolean createResult = backendService.createCommodity(commodity);
		String createResultMessage = createResult ? "商品資料新增成功！" : "商品資料新增失敗！";
		System.out.println(createResultMessage);
		session.setAttribute("createResultMessage", createResultMessage);
		
		//網頁導入上傳成功照片
//		response.sendRedirect("DrinksImage/" + fileName);

		return mapping.findForward("commodityCreate");
	}
    
    public ActionForward createView(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception{
    	return mapping.findForward("commodityCreateView");
    }
    
    //銷售報表
    public ActionForward querySalesReport(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) {
		
    	HttpSession session = request.getSession();
    	String startDate = request.getParameter("queryStartDate");
    	String endDate = request.getParameter("queryEndDate");
    	
    	Commodity date = new Commodity();
    	date.setStartDate(startDate);
    	date.setEndDate(endDate);
    	List<Commodity> salesReports = backendService.salesReport(date);
    	
    	session.setAttribute("date", date);
    	session.setAttribute("salesReports", salesReports);
    	
		return mapping.findForward("commoditySaleReport");
	}
    
    public ActionForward SalesReportView(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("commoditySaleReportView");
	}
}
