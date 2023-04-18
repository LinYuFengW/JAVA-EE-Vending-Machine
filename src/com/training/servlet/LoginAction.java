package com.training.servlet;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.training.dao.BackendDao;
import com.training.model.Account;

public class LoginAction extends DispatchAction {
	
	private BackendDao backendDao = BackendDao.getInstance();
	
	/**
	 * info:這是負責"登入"的action method
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward login(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 登入請求
    	ActionForward actFwd = null;
    	HttpSession session = request.getSession();
    	String inputID = request.getParameter("id");
        String inputPwd = request.getParameter("pwd");
        // Step2:依使用者所輸入的帳戶名稱取得 Member
        Account account = backendDao.queryAccountById(inputID);
        // 登入畫面要呈現的動態文字
        String loginMsg = null;
    	if(account != null) {
    		// Step3:取得帳戶後進行帳號、密碼比對
    		String id = account.getId();    		
    		String pwd = account.getPwd();
    		if(id.equals(inputID) && pwd.equals(inputPwd)) {
    			loginMsg = account.getName() + " 先生/小姐您好!";
    			// 將account存入session scope 以供LoginCheckFilter之後使用!
    			session.setAttribute("account", account);
    			
    			// 獲取登入時間
    			// EX:'2021-09-05 16:58:30'
				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String orderDATE = simpleDateFormat.format(date);
					
				account.setLoginDate(orderDATE);
				
				// 登入時間更新
				boolean addResult = backendDao.updateLoginTime(account);			
				String addmessage = addResult ? "新增登入時間成功！" : "新增登入時間失敗！";
				System.out.println(addmessage);
				
    			
    			actFwd = mapping.findForward("success");        			
    		} else {
                // Step4:帳號、密碼錯誤,轉向到 "/BankLogin.html" 要求重新登入.
    			loginMsg = "帳號或密碼錯誤";
    			actFwd = mapping.findForward("fail");
    		}
    	} else {
            // Step5:無此帳戶名稱,轉向到 "/BankLogin.html" 要求重新登入.
    		loginMsg = "無此帳戶名稱,請重新輸入!";        		
    		actFwd = mapping.findForward("fail");
    	}
    	// 登入畫面要呈現的動態文字
    	session.setAttribute("loginMsg", loginMsg);
    	return actFwd;
    }
    
    /**
     * info:這是負責"登出"的action method
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward logout(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	// 登出請求
    	HttpSession session = request.getSession();    	
    	session.setAttribute("loginMsg", "謝謝您的光臨!");		
		
		// 獲取該網頁的最後一次訪問登出時間
    	Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String orderDATE = simpleDateFormat.format(date);
		
		Account account = (Account) session.getAttribute("account");
		account.setLogoutDate(orderDATE);
		
		// 登出時間更新
		boolean addResult = backendDao.updateLogoutTime(account);			
		String addmessage = addResult ? "新增登出時間成功！" : "新增登出時間失敗！";
		System.out.println(addmessage);
		
		// 刪除session裡的會員資料
		session.removeAttribute("account");	
		
		// 刪除session裡的現場購買資料
		session.removeAttribute("viewList");	
		
		// 刪除session裡的購物車資料
		session.removeAttribute("number");
		session.removeAttribute("shoppingCart");
		session.removeAttribute("totalGoodsPrice");
    	
    	return mapping.findForward("fail");
    }
}
