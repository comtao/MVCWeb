package com.cn.core;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.comet4j.core.CometConnection;
import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;
import org.comet4j.core.event.ConnectEvent;
import org.comet4j.core.event.DropEvent;
import org.comet4j.core.listener.ConnectListener;
import org.comet4j.core.listener.DropListener;
import org.springframework.stereotype.Component;

import com.cn.model.User;
import com.cn.service.CometService;
import com.cn.web.LoginController;

/**
 * Component将对象放入spring 并初始化comet4j
 * 
 * @author owen
 * @date 2014-11-11
 */

@Component
public class CometInit {
	@Resource
	private CometService ms;
	
	public static final String CHANNEL_USER_INFO = "onlineUsers";
	public static final String CHANNEL_USER_CHATING_CONTENT = "user_chating_content";

	public CometInit() {
		CometContext context = CometContext.getInstance();
		CometEngine engine = context.getEngine();
		// 添加连接监听
		engine.addConnectListener(new ConnectListener() {
			@Override
			public boolean handleEvent(ConnectEvent event) {
				CometConnection connection = event.getConn();
				String ip = connection.getClientIp();
				String id = connection.getId();

				HttpServletRequest request = connection.getRequest();
				String userName = getCookieValue(request.getCookies(), "userName-chating-client", "");

				if (userName == null) {
					return true;
				}

				try {
					userName = URLDecoder.decode(userName, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				System.out.println("id是：" + id + "的用户连接服务器，ip是：" + ip + ",用户名是：" + userName);
				User u = LoginController.onlineUsers.get(userName);
				if (u == null) {
					return true;
				}
				u.setChatState(LoginController.STATE_ONLINE);
				u.setChatId(id);
				ObjectMapper om = new ObjectMapper();
				try {
					event.getTarget().sendToAll(CometInit.CHANNEL_USER_INFO,
							om.writeValueAsString(LoginController.onlineUsers.values()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			}
		});

		// 添加离线、断线监听
		engine.addDropListener(new DropListener() {
			@Override
			public boolean handleEvent(DropEvent event) {
				CometConnection connection = event.getConn();
				String ip = connection.getClientIp();
				String id = connection.getId();
				HttpServletRequest request = connection.getRequest();
				String userName = getCookieValue(request.getCookies(),"userName-chating-client", "");

				if (userName == null) {
					return true;
				}

				try {
					userName = URLDecoder.decode(userName, "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("id是：" + id + "的用户断开与服务器的连接，ip是：" + ip+ ",用户名是：" + userName);
				User u = LoginController.onlineUsers.get(userName);
				if (u == null) {
					return true;
				}
				u.setChatState(LoginController.STATE_MISSING);

				ObjectMapper om = new ObjectMapper();
				try {
					event.getTarget().sendToAll(
							CometInit.CHANNEL_USER_INFO,
							om.writeValueAsString(LoginController.onlineUsers.values()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
		});

		// 注册信道
		context.registChannel(CHANNEL_USER_INFO);
		context.registChannel(CHANNEL_USER_CHATING_CONTENT);
	}

	/**
	 * 从cookiek中得到某个 key的取值
	 * 
	 * @param cookies
	 * @param cookieName
	 * @param defaultValue
	 * @return
	 */
	public String getCookieValue(Cookie[] cookies, String cookieName,
			String defaultValue) {
		String result = defaultValue;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookieName.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return result;
	}
}
