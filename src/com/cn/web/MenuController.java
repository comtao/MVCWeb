package com.cn.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.dao.MenuDao;
import com.cn.model.Menu;
import com.cn.service.MenuService;



/**
 * 菜单
 */

@Controller
@RequestMapping(value="/menu")
public class MenuController {
	
private Logger logger = Logger.getLogger(MenuController.class);
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="/list.html")
	public String getMenuList(HttpServletRequest request){
		List<Menu> list = menuService.list(MenuDao.list, new Object[]{});
		request.setAttribute("list",list);
		return "system/modular" ;
	}
	
	/**
	 * 新增菜单
	 * @return
	 */
	@RequestMapping(value="/addMenu.html")
	public String addPage(HttpServletRequest request){
		List<Menu> list = menuService.list(MenuDao.listParent, new Object[]{});
		request.setAttribute("pm",list);
		return "system/addMenu" ;
	}
	@RequestMapping(value="/addSave.html")
	public String add(Menu menu,HttpServletRequest request){
		logger.debug("menu name:"+menu.getName());
		menuService.addMenu(new Object[]{
				menu.getName()
				,menu.getDesc()
				,menu.getPath()
				,menu.getParent()
				,menu.getVisible()
				,menu.getOrderNo()
		});
		request.setAttribute("msg", "菜单增加成功!");
		return "success" ;
	}
	
	/**
	 * 编辑菜单
	 */
	@RequestMapping(value="/editMenu.html")
	public String editMenu(int id,HttpServletRequest request){
		Menu m = menuService.getMenuById(id);
		List<Menu> list = menuService.list(MenuDao.listParent, new Object[]{});
		request.setAttribute("pm",list);
		request.setAttribute("menu", m);
		return "system/editMenu";
	}
	
	/**
	 * 菜单编辑提交
	 * @param menu
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editSave.html")
	public String editSave(Menu menu,HttpServletRequest request){
		menuService.editSave(menu);
		request.setAttribute("msg", "修改菜单成功");
		return "success";
	}
	
	
	
	/**
	 * 删除菜单
	 * @return
	 */
	@RequestMapping(value="/delMenu.html")
	public String delMenu(int id,HttpServletRequest request){
		if(menuService.delMenuById(id)){
			List<Menu> list = menuService.list(MenuDao.list, new Object[]{});
			request.setAttribute("list",list);
			return "system/modular";
		}else{
			request.setAttribute("msg", "菜单删除成功");
			return "error";
		}
	}
	
}
