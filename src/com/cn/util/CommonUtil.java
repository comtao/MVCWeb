package com.cn.util;

import javax.servlet.http.HttpServletRequest;

import com.cn.model.User;

public class CommonUtil {
	/**
	 * 获取登录信息
	 * @param request
	 * @return
	 */
	/*private static User user;
	
	public static User getUser() {
		return user;
	}
	public static void setUser(User user) {
		CommonUtil.user = user;
	}*/
	
	public static User getUser(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		return user;
	}
}
