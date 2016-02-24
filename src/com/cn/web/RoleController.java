package com.cn.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.dao.MenuDao;
import com.cn.model.Menu;
import com.cn.model.Role;
import com.cn.service.MenuService;
import com.cn.service.RoleService;
import com.cn.util.Constants;
import com.cn.util.StringUtil;

/**
 * 角色
 * @author owen
   @date 2014-10-17
 */
@Controller
@RequestMapping(value="/role")
public class RoleController {
	private Logger logger = Logger.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService ;
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 
	 * @param roleName
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add.html")
	public String add(String roleName,HttpServletRequest request){
		logger.debug("roleName:"+roleName);
		System.out.println("roleName="+roleName);
		if(StringUtil.isEmpty(roleName)){
			request.setAttribute("msg","角色名称不能为空！");
		}else if(roleService.hasRole(roleName)){
			request.setAttribute("msg","角色名称已存在！");
		}else{
			roleService.addRole(roleName);
			request.setAttribute("msg","新增角色成功!");
			Constants.reloadRoleList();
		}
		request.setAttribute("list", Constants.getRoleList());
		return "system/role";
	}
	
	/**
	 * 角色列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list.html")
	public String list(HttpServletRequest request){
		List<Role> list = roleService.list();
		request.setAttribute("list",list);
		return "system/role";
	}
	
	/**
	 * 角色编辑
	 * @param request
	 */
	@RequestMapping(value="/roleEdit.html")
	public String edit(String oldRole,String newRole,HttpServletRequest request){
		if(StringUtil.isEmpty(newRole)){
			request.setAttribute("msg","角色名称不能为空！");
		}else if(roleService.hasRole(newRole)){
			request.setAttribute("msg","角色名称已存在！");
		}else{
			boolean rs = roleService.editRole(oldRole,newRole);
			if(rs){
				request.setAttribute("msg","编辑成功！");
				Constants.reloadRoleList();
			}
		}
		request.setAttribute("list", Constants.getRoleList());
		return "system/role";
	}
	
	
	/**
	 * 设置角色权限
	 * @param roleId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/setPerPage.html")
	public String setPerPage(int id,HttpServletRequest request){
		Role role = new Role();
		role.setId(id);
		List<Integer> rmId = roleService.listRoleMenu(id);
		role.setRmId(rmId);
		List<Menu> pm = menuService.list(MenuDao.listParent, new Object[]{});
		for(Menu m:pm){
			List<Menu> sun = menuService.list(MenuDao.listSun, new Object[]{m.getId()});
			m.setSun(sun);
		}
		request.setAttribute("pm", pm);
		request.setAttribute("role", role);
		return "system/roleMenu";
	}
	
	
	@RequestMapping(value="/setPer.html")
	public String setPer(Role role,HttpServletRequest request){
		roleService.editRoleMenu(role.getId(),role.getRmId());
		request.setAttribute("msg", "设置角色菜单权限成功!");
		return "success" ;
	}
	
	
	
	
	
	
	
	
}
