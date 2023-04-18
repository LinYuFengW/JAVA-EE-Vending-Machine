package com.training.servlet;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
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
import com.training.model.CheckoutForm;
import com.training.model.Commodity;
import com.training.model.ShoppingCart;
import com.training.service.FrontendService;
import com.training.vo.CheckoutFormData;
import com.training.vo.BackendFormData;
import com.training.vo.FrontendFormData;

public class CheckoutAction extends DispatchAction {

	 private FrontendService frontendService = FrontendService.getInstance();
		 	 			 
	//購物車資料填寫
    public ActionForward checkout(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
		
    	HttpSession session = request.getSession();
    	CheckoutFormData formData = (CheckoutFormData) form;
		
		// 將Struts CheckoutFormData 資料複製 
		// 將表單資料轉換儲存資料物件(commons-beanutils-1.8.0.jar)
    	CheckoutForm checkoutForm = new CheckoutForm();
		BeanUtils.copyProperties(checkoutForm, formData);
		
    	// 從session取得購物車商品
		List<ShoppingCart> cartList = (List<ShoppingCart>)session.getAttribute("shoppingCart");
		List<String> viewList = new ArrayList<>();
		
		for(int i = 0 ;i < cartList.size() ; i++){
			// 從session取得商品ID
			String goodsID = cartList.get(i).getGoodsID();
			// 從session取得購買商品數量
			String buyQuantity = String.valueOf(cartList.get(i).getBuyQuantity());
			// 從資料庫取資料，確定庫存量&價格
			Commodity commoditys = frontendService.buySearchCommodity(goodsID);
			// 商品名稱
			String goodsName = commoditys.getGoodsName();
			// 庫存量
			int quantity = Integer.parseInt(commoditys.getGoodsQuantity());
			// 商品價格
			int price = Integer.parseInt(commoditys.getGoodsPrice());
			// 判斷可購買商品數量
			int buyNo = Integer.parseInt(buyQuantity) <= quantity ? Integer.parseInt(buyQuantity) : quantity;
			
			//更新庫存
			quantity = quantity - buyNo;
						
			Commodity buyCommodity = new Commodity();
			buyCommodity.setGoodsPrice(commoditys.getGoodsPrice());
			buyCommodity.setGoodsQuantity(Integer.toString(quantity));
			buyCommodity.setStatus(commoditys.getStatus());
			buyCommodity.setGoodsName(commoditys.getGoodsName());
			
			//資料庫更新
			boolean modifyResult = frontendService.buyCommodity(buyCommodity);				
			String message = modifyResult ? "商品購買成功！" : "商品購買失敗！";
			System.out.println(message);
			
			
			// (建立銷售表)獲取購買時間
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String orderDATE = simpleDateFormat.format(date);
			// 購買人，登從入session資料取得
			Account account = (Account) session.getAttribute("account");
			String customerId = account.getId();
			String shipmentDate = request.getParameter("shipmentDate");
			
			// 地址有資料，做字串串接。
			if(checkoutForm.getArea().equals("")){
				checkoutForm.setAddress("");
			}else {
				checkoutForm.setCompleteAddress(checkoutForm.getArea() + checkoutForm.getAreaList() + checkoutForm.getAddress());
			}
			
			// 超商門市有資料，做字串串接。
			if(checkoutForm.getShopArea().equals("")){
				checkoutForm.setShopArea("");
			}else {
				checkoutForm.setSupermarket(checkoutForm.getShopArea() + "-" + checkoutForm.getStrongholdArea());
			}
			
			// 信用卡有資料，做字串串接。
			if(checkoutForm.getCardNo().equals("")){
				checkoutForm.setCardNo("");
			}else {
				checkoutForm.setCreditCard(checkoutForm.getCardNo() + "-" + checkoutForm.getMonth() + "-" + checkoutForm.getYears() + "-" + checkoutForm.getCode());
			}
			
			checkoutForm.setOrderDATE(orderDATE);
			checkoutForm.setCustomerId(customerId);
			checkoutForm.setGoodsID(goodsID);
			checkoutForm.setGoodsPrice(commoditys.getGoodsPrice());
			checkoutForm.setGoodsQuantity(Integer.toString(buyNo));						
			checkoutForm.setStringShipmentDate(shipmentDate);
									
			// 銷售表資料庫更新
			boolean addResult = frontendService.checkoutFormTable(checkoutForm);				
			String addmessage = addResult ? "新增購買資料成功！" : "新增購買資料失敗！";
			System.out.println(addmessage);										
		}
		// 購買完成頁，收貨地址畫面使用。
		session.setAttribute("transactionComplete", checkoutForm);
		// 結帳後清除session購物車商品，復制一份給結帳完成頁使用。
		session.setAttribute("endShoppingCart", session.getAttribute("shoppingCart"));
    	session.setAttribute("endTotalPoodsPrice", session.getAttribute("totalGoodsPrice"));
    	session.removeAttribute("number");
		session.removeAttribute("shoppingCart");
		session.removeAttribute("totalPoodsPrice");
					
		return mapping.findForward("checkout");
	}
    
    public ActionForward transactionComplete(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	return mapping.findForward("transactionCompleteView");
    }

    
}
