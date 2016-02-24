package com.cn.util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringManagerHelper {
	
	private static WebApplicationContext springContext;
	
	private static JdbcTemplate jdbcTemplate = null;
	
	public static ApplicationContext getWebApplicationContext(){
		return springContext;
	}
	
	public static void setWebApplicationContext(ServletContext sc) {
		if(springContext == null){
			springContext = WebApplicationContextUtils.getWebApplicationContext(sc);
		}
    }
	
	/*private static ApplicationContext getApplicationContext(){
		if(context == null){
			context = new ClassPathXmlApplicationContext("applicationContext.xml");
		}
		return context;
	}*/
	
	public static JdbcTemplate getJdbc(ServletContext sc){
		if(jdbcTemplate == null){
		//	jdbcTemplate = (JdbcTemplate) getApplicationContext().getBean("jdbcTemplate");
			jdbcTemplate = (JdbcTemplate) getWebApplicationContext().getBean("jdbcTemplate");
		}
		return jdbcTemplate;
	}
	
	

}
