package com.training.servlet;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.training.model.Account;
import com.training.model.BuyCommodity;
import com.training.model.Commodity;
import com.training.model.ShoppingCart;
import com.training.service.FrontendService;
import com.training.vo.BackendFormData;
import com.training.vo.FrontendFormData;

public class FrontendAction extends DispatchAction {

	private FrontendService frontendService = FrontendService.getInstance();
	
	//首頁商品&商品查尋
    public ActionForward homePageGoods(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) {
    	
    	HttpSession session = request.getSession();
    	// 判斷是否為登入頁進到商品首頁action=homePageGoods，無分頁值。
    	String page = request.getParameter("pageNo");
    	String pageNo = (page == null) ? "1" : page;
    	// 取得分頁數，給前台分頁判斷是否為當下頁面
    	request.setAttribute("pageNo", pageNo);
		
		// 判斷是否為商品查詢
		String key = request.getParameter("searchKeyword");
		String keyword = (key == null) ? "" : key;
		
		// 設定分頁，每頁6項商品。
		String start = Integer.toString(6 * (Integer.parseInt(pageNo) - 1) + 1);
		String end = Integer.toString(Integer.parseInt(start) + 5);
		
		// 依使用者所輸入的關鍵字取得商品 list
		List<Commodity> commoditys = frontendService.searchCommodity(keyword,start,end);
		request.setAttribute("homePageGoods", commoditys);
						
		// 商品總數
		int totalcommodity = Integer.parseInt(commoditys.get(0).getGoodsSize());
		// 每頁商品數
		int pageCommodityNO = 6;		
		// 總頁數
		int totalPages = totalcommodity % pageCommodityNO == 0 ? totalcommodity / pageCommodityNO : totalcommodity / pageCommodityNO + 1;
				
		List<Integer> totalPagesNO = new ArrayList<>();
		// 所有分頁
		for(int i = 0 ; i < totalPages ; i++){
			totalPagesNO.add(i+1);
		}
		request.setAttribute("totalPagesNO", totalPagesNO);
		
		// 分頁固定三頁
		List<Integer> forEachPageNO = frontendService.pagination(pageNo,totalPages);				
		request.setAttribute("forEachPageNO", forEachPageNO);

		return mapping.findForward("homePageGoodsView");
	}
    
	

    
    //商品購買
    public ActionForward buyGoods(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
		
    	HttpSession session = request.getSession();
		FrontendFormData formData = (FrontendFormData) form;
		
		// 將Struts FrontendFormData 資料複製 
		// 將表單資料轉換儲存資料物件(commons-beanutils-1.8.0.jar)
		BuyCommodity checklistCommodity = new BuyCommodity();
		BeanUtils.copyProperties(checklistCommodity, formData);
		
		List<String> viewList = new ArrayList<>();
		// 取得投入金額
		int money = Integer.parseInt(checklistCommodity.getInputMoney());
		// 餘額
		int finallyMoney = money;
		int finallyBuyNo = 0;
		
		// 購買商品ID&購買數量&購買金額
		String[] goodsID = checklistCommodity.getGoodsID();
		String[] buyQuantity = checklistCommodity.getBuyQuantity();
		String[] goodsPrice = checklistCommodity.getGoodsPrice();
		
		// 購買總金額
		int totalPoodsPrice = 0;
		for(int i = 0 ;i < goodsID.length ; i++){
			totalPoodsPrice += Integer.parseInt(buyQuantity[i]) * Integer.parseInt(goodsPrice[i]);		
		}
		
		// 查看投入金額是否有 >= 購買總金額
		if (money >= totalPoodsPrice) {
			for(int i = 0 ;i < goodsID.length ; i++){
				// 查看購買大於0才進入，否則跑下一件商品。
				if (Integer.parseInt(buyQuantity[i]) > 0) {
					Commodity commoditys = frontendService.buySearchCommodity(goodsID[i]);
					// 商品名稱
					String goodsName = commoditys.getGoodsName();
					// 庫存量
					int quantity = Integer.parseInt(commoditys.getGoodsQuantity());
					// 商品價格
					int price = Integer.parseInt(commoditys.getGoodsPrice());
					// 判斷可購買商品數量
					int buyNo = Integer.parseInt(buyQuantity[i]) <= quantity ? Integer.parseInt(buyQuantity[i]) : quantity;
					
					//更新庫存
					quantity = quantity - buyNo;
					// 餘額
					finallyMoney = finallyMoney - price;			
					Commodity buyCommodity = new Commodity();
					buyCommodity.setGoodsPrice(commoditys.getGoodsPrice());
					buyCommodity.setGoodsQuantity(Integer.toString(quantity));
					buyCommodity.setStatus(commoditys.getStatus());
					buyCommodity.setGoodsName(commoditys.getGoodsName());
					
					//資料庫更新
					boolean modifyResult = frontendService.buyCommodity(buyCommodity);				
					String message = modifyResult ? "商品購買成功！" : "商品購買失敗！";
					System.out.println(message);
					//畫面顯示資訊
					viewList.add(goodsName + " " + price + "*" + buyNo);
					
					// (建立銷售表)獲取購買時間
					Date date = new Date();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String orderDATE = simpleDateFormat.format(date);
					// 購買人，登從入session資料取得
					Account account = (Account) session.getAttribute("account");
					String customerId = account.getId();
					
					Commodity salesTable = new Commodity();
					salesTable.setOrderDATE(orderDATE);
					salesTable.setCustomerId(customerId);
					salesTable.setGoodsID(goodsID[i]);
					salesTable.setGoodsPrice(commoditys.getGoodsPrice());
					salesTable.setGoodsQuantity(Integer.toString(buyNo));
					
					// 銷售表資料庫更新
					boolean addResult = frontendService.salesTable(salesTable);				
					String addmessage = addResult ? "新增購買資料成功！" : "新增購買資料失敗！";
					System.out.println(addmessage);										
				}
			}
			viewList.add(0, "投入金額：" + money);
			viewList.add(1, "購買金額：" + Integer.toString(money - finallyMoney));
			viewList.add(2, "找零金額：" + finallyMoney);
			viewList.stream().forEach(a -> System.out.println(a));
			session.setAttribute("viewList", viewList);
		} else {
			viewList.add("投入金額不足，退額:" + money);
			session.setAttribute("viewList", viewList);
		}

		return mapping.findForward("buyCommodityList");
	}
    
    public ActionForward buyGoodsView(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	return mapping.findForward("buyCommodityListView");
    }
    
    // 購物車
    public ActionForward shoppingCart(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	HttpSession session = request.getSession();
		
    	// 購買商品ID&購買數量
		String goodsID = request.getParameter("goodsID");
		String buyQuantity = request.getParameter("buyQuantity");
		
		// 從session取得已購買商品
		List<ShoppingCart> shoppingCart = (List<ShoppingCart>)session.getAttribute("shoppingCart");
		shoppingCart = shoppingCart != null ? shoppingCart : new ArrayList<>();
			
		// 判斷購物車是否有相同商品
		ShoppingCart carCommodity = new ShoppingCart(goodsID);
		if (shoppingCart.contains(carCommodity)) {
			carCommodity = shoppingCart.get(shoppingCart.indexOf(carCommodity));
			carCommodity.setBuyQuantity(carCommodity.getBuyQuantity() + Integer.valueOf(buyQuantity));			
		} else {
			Commodity commodity = frontendService.buySearchCommodity(goodsID);
			BeanUtils.copyProperties(carCommodity, commodity);
			carCommodity.setBuyQuantity(Integer.valueOf(buyQuantity));
			shoppingCart.add(carCommodity);
		}
		
		// 相同商品金額合計&所有商品購買總金額&購買商品的件數
		int totalGoodsPrice = 0;
		int number = 0;
		for(ShoppingCart goods : shoppingCart){
			goods.setTotalPrice(Integer.valueOf(goods.getGoodsPrice())*goods.getBuyQuantity());
			totalGoodsPrice += goods.getTotalPrice();
			number += goods.getBuyQuantity();
		}
		
		// 購物車結帳使用
		session.setAttribute("number", number);
		session.setAttribute("shoppingCart", shoppingCart);
		session.setAttribute("totalGoodsPrice", totalGoodsPrice);
		// 購物車畫面呈現使用
		JSONObject carGoods = new JSONObject();
		carGoods.put("number", number); //購物車商品數量
		carGoods.put("shoppingCart", shoppingCart);
		carGoods.put("totalGoodsPrice", totalGoodsPrice); //購物車商品總價
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(carGoods);
		out.flush();
		out.close();		
		
    	return null;
    }
    
    public ActionForward shoppingCartView(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	return mapping.findForward("shoppingCartView");
    }
}
