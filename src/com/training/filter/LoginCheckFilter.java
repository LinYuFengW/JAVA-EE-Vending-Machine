package com.training.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.training.model.Account;

public class LoginCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
				
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		System.out.println(session.getId());
		Account account = (Account) session.getAttribute("account");
		if(account != null){
			// 已登入(放行)
			filterChain.doFilter(request, response);
		} else {			
        	String requestURI = req.getRequestURI();
        	String action = req.getParameter("action");
        	String method = req.getMethod();        	
        	// 若是登入請求就放行
            if(requestURI.endsWith("LoginAction.do") && "login".equals(action) 
            		&& "POST".equals(method)) {
            	filterChain.doFilter(request, response);
            }else{
            	// 未登入
    			RequestDispatcher dispatcher = req.getRequestDispatcher("Login.jsp");
    			dispatcher.forward(request, response);
            }
		}		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException { }
	
	@Override
	public void destroy() { }

}
