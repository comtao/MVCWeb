package com.cn.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cn.model.User;
import com.cn.util.StringUtil;

public class WebSessionInterceptor implements HandlerInterceptor{
	
	
	private Logger logger = Logger.getLogger(WebSessionInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		/**
		 * 没有登录 session 过期
		 */
		System.out.println("*************************");
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return false ;
		}
		return true;
	}

}
