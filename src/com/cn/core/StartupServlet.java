package com.cn.core;

import java.io.IOException;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.util.SpringManagerHelper;


public class StartupServlet extends HttpServlet {

	private static final long serialVersionUID = 1510972382904964644L;

	public StartupServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}
	
	
	public void init() throws ServletException {
		System.out.println(" woshi start up servlet!");
		ServletContext sc = getServletContext();
		SpringManagerHelper.setWebApplicationContext(sc);
		
		
	//	new CometInit();   //test
	}

}
