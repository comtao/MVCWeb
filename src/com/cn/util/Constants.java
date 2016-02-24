package com.cn.util;

import java.util.List;

import com.cn.model.Role;
import com.cn.service.RoleService;

public class Constants {
	
private static RoleService  roleService = null ;
	
	/**
	 * 角色列表
	 */
	private static List<Role> roleList = null ;
	
	public static List<Role> getRoleList(){
		if(roleList == null){
			roleList = getRoleService().list();
		}
		return roleList;
	}
	
	public static void reloadRoleList(){
		roleList = getRoleService().list();
	}
	
	public static RoleService getRoleService(){
		if(roleService == null){
			roleService = (RoleService)SpringManagerHelper.getWebApplicationContext().getBean("roleService");
		}
		return roleService;
	}
	
	/**
	 * 页数
	 * @param total
	 * @param pageSize
	 * @return
	 */
	public static int getPageTotal(int total , int pageSize){
		int pageTotal = total/pageSize;
		if(total%pageSize != 0){
			return pageTotal+1 ;
		}
		return pageTotal ;
	}
	
	public static int handlePageNum(int pageNum,int pageTotal){
		if(pageNum > pageTotal){
			if(pageTotal == 0){
				return pageNum;
			}else{
				return pageTotal;
			}
		}else if(pageNum < 1){
			return 1;
		}else{
			return pageNum;
		}
	}
}
