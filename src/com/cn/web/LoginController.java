package com.cn.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.core.CometInit;
import com.cn.model.User;
import com.cn.service.RoleService;
import com.cn.service.UserService;
import com.cn.util.StringUtil;
/**
 * 登陆i
 * @author ll
 *
 */
@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService ;
	
	private Logger logger = Logger.getLogger(UserController.class);
	
	public static final int STATE_LOGINING = 0;//"登录中";
    public static final int STATE_ONLINE = 1;//"在线";
    public static final int STATE_MISSING = 2;//"掉线";
    public static final int STATE_LEAVE = 3;//"离开";
	  
    private CometEngine engine;
	  
	public LoginController() {
	    engine = CometContext.getInstance().getEngine();
	}
	
	//保存在线用户集合，在些比做持久化的用户数据
	public static Map<String, User> onlineUsers = new HashMap<String, User>();
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/login.html")
	public String login(String username,String password,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		if(StringUtil.isEmpty(username)){
			logger.debug("用户名不能为空！");
			request.setAttribute("content", "用户名不能为空！");
			return "login";
		}
		User user = userService.login(new Object[]{username,password});
		if(StringUtil.isEmpty(user.getUserName()) || StringUtil.isEmpty(password) || !password.equals(user.getPassWord())){
			logger.debug("用户名或密码不正确！username："+username+",password:"+password);
			request.setAttribute("content", "用户名或密码不正确！");
			return "login";
		}
		List<Integer> rmId = roleService.listRoleMenu(user.getRoleId());
		user.setRmId(rmId);
		request.getSession().setAttribute("user", user);
		return "main";
	}
	/**
	 * 用户注销 退出
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value="/logout.html")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.getSession().invalidate();
		response.sendRedirect("/");
		return;
	}
	
	
	
	
	
	  
	  
	  
	  
	  
	  
	  
	 
	  @RequestMapping(value="/toChat.html")
	  public String toChat(){
		  return "chat/chatLogin";
	  }
	  
	  @RequestMapping(value="/loginChat.html")
	  public String login(String name,HttpSession session,HttpServletResponse response) throws Exception {
	      User user = onlineUsers.get(name);
	      if (user == null) {
	          //更新状态信息
	          user = new User();
	          user.setUserName(name);
	          onlineUsers.put(name, user);
	      }
	      //设置为正在登录状态
	      user.setChatState(STATE_LOGINING);
	      session.setAttribute("user_key", user);
	      
	      //如果是json方式返回，也可由客户端通过js添加cookie
	      Cookie cookie = new Cookie("userName-chating-client",URLEncoder.encode(name,"utf-8"));
	      cookie.setMaxAge(60*60*24*7);//保留7天  
	      response.addCookie(cookie);
	  
	      //更新在线用户列表
	      ObjectMapper om = new ObjectMapper();
	      engine.sendToAll(CometInit.CHANNEL_USER_INFO, om.writeValueAsString(onlineUsers.values()));
	      
	      //进入主页面
	      return "chat/chating";
	      
	  }
	 
	      
	  @RequestMapping(value="/getState.html")
	  public void getState(HttpSession session,HttpServletResponse response) throws Exception { 
	      User user = (User)session.getAttribute("user_key");
	      ObjectMapper om = new ObjectMapper();
	      if (user != null) {
	          //返回所有的在线用户
	          om.writeValue(response.getWriter(), user);
	      } else {
	          om.writeValue(response.getWriter(), "{code:0}");
	      }
	      
	  }
	  
	  @RequestMapping(value="/onlineUser.html")
	  public void allUsers(HttpServletResponse response) throws Exception {
	      //返回所有的在线用户
	      ObjectMapper om = new ObjectMapper();
	      om.writeValue(response.getWriter(), onlineUsers);
	  }
	      
	  /***
	   * 退出聊天
	   * @param session
	   * @param response
	   * @return
	   * @throws Exception
	   */
	  @RequestMapping(value="/chatOut.html")
	  public String logout(
	          HttpSession session,
	          HttpServletResponse response
	          ) throws Exception {
	      
	      User user = (User)session.getAttribute("user_key");
	      if (user != null) {
	          ObjectMapper om = new ObjectMapper();
	          onlineUsers.get(user.getUserName()).setChatState(STATE_LEAVE);
	          engine.sendToAll(CometInit.CHANNEL_USER_INFO, om.writeValueAsString(onlineUsers.values()));
	      }
	      return "chat/chatLogin";
	  }
	  
	  @RequestMapping(value="/sendMsg.html")
	  public void sendMsg(
	          HttpSession session,String msg,
	          HttpServletResponse response) throws Exception {
	      SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
	      User user = (User)session.getAttribute("user_key");
	      if (user != null) {
	          ObjectMapper om = new ObjectMapper();
	          Map respBody = new HashMap();
	          respBody.put("date", sf.format(new Date()));
	          respBody.put("user", user.getUserName());
	          respBody.put("msg", msg);
	      System.out.println("**><><="+om.writeValueAsString(respBody));
	          engine.sendToAll(CometInit.CHANNEL_USER_CHATING_CONTENT, om.writeValueAsString(respBody));
	      }
	      response.getWriter().print("over");
	  }
}
