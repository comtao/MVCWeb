package com.cn.tag;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import com.cn.dao.MenuDao;
import com.cn.model.Menu;
import com.cn.model.User;
import com.cn.service.MenuService;
import com.cn.util.SpringContextUtil;

public class MenuTag extends BodyTagSupport{
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(MenuTag.class);
	
	@Override
	public int doEndTag() throws JspException {
		logger.debug("menu tag start");
		HttpSession session = pageContext.getSession() ;
		String basePath = pageContext.getServletContext().getContextPath();
		
		User user = (User)session.getAttribute("user");
		if(user == null)return SKIP_BODY;
		
		MenuService menuService = (MenuService)SpringContextUtil.getBean("menuService");
		
		List<Menu> pm = menuService.list(MenuDao.listParent, new Object[]{});
		for(Menu m:pm){
			List<Menu> sun = menuService.list(MenuDao.listSun, new Object[]{m.getId()});
			m.setSun(sun);
		}
		/**
		 * 用户角色 菜单权限ID
		 */
		List<Integer> rmId = user.getRmId();
		JspWriter out=pageContext.getOut();
		try {
			/**
			<a href="#dashboard-menu" class="nav-header" data-toggle="collapse">
		    <i class="icon-dashboard"></i>个人办公</a>
		 <ul id="dashboard-menu" class="nav nav-list collapse in">
			<li><a href="${ctx}/User/index.html" target="mainFrame">主页</a></li>
			<li><a href="${ctx}/task/list.html" target="mainFrame">任务</a>
			</li>
			<li><a href="${ctx}/task/approvalList.html" target="mainFrame">审批</a>
			</li>
			<li><a href="${ctx}/News/affiche.html?dpId=${user.dpId}" target="mainFrame">公告</a>
			</li>
		  </ul>
			 **/
			StringBuffer str = new StringBuffer();
			for(Menu menu:pm){
				if(rmId.contains(menu.getId()) && menu.getVisible() != 0){   //1级菜单
					str.append("<a href='#menuRoot"+menu.getId()+"' class='nav-header' data-toggle='collapse'>")
					   .append("<i class='icon-dashboard'></i>"+menu.getName()+"</a>")
					   .append("<ul id='menuRoot"+menu.getId()+"' class='nav nav-list collapse'>");
					for(Menu sunMenu:menu.getSun()){     //2级菜单
						if(rmId.contains(sunMenu.getId()) && sunMenu.getVisible() != 0){
							str.append("<li><a href='"+basePath+sunMenu.getPath()+"' target='mainFrame'>"+sunMenu.getName()+"</a></li>");
						}
					}
					str.append("</ul>");
				}
			}
			logger.debug(str.toString());
			out.println(str.toString());
		}catch(Exception e){
			logger.debug("菜单初始化失败！");
			e.printStackTrace();
		}
		
		
		logger.debug("menu tag end");
		return EVAL_PAGE;
	}
	
	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public void release() {
		super.release();
	}
}
