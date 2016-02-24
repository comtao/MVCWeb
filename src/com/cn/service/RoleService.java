package com.cn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.dao.RoleDao;
import com.cn.model.Role;


@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao ;
	
	/**
	 * 角色名称是否存在
	 * @param roleName
	 * @return
	 */
	public boolean hasRole(String roleName){
		int row = roleDao.queryForInt(RoleDao.hasRoleSql, new Object[]{roleName});
		if(row >0){
			return true ;
		}
		return false ;
	}
	
	/**
	 * 角色列表
	 * @return
	 */
	public List<Role> list(){
		return roleDao.list();
	}

	/**
	 * 增加角色
	 * @param roleName
	 */
	public void addRole(String roleName) {
		roleDao.executeSql(RoleDao.addSql, new Object[]{roleName});
	}

	public boolean editRole(String oldRole,String roleName) {
		return roleDao.executeSql(RoleDao.editSql,new Object[]{roleName,oldRole})>0 ? false:true;
	}
	
	/**
	 * 角色菜单列表
	 * @param roleId
	 * @return
	 */
	public List<Integer> listRoleMenu(int roleId){
		return roleDao.listRoleMenu(roleId);
	}
	
	/**
	 * 编辑角色权限
	 * @param roleId
	 * @param menuIdArr
	 */
	public void editRoleMenu(int roleId,List<Integer> menuIdArr){
		roleDao.executeSql(RoleDao.delRoleMenuSql, new Object[]{roleId});
		if(menuIdArr != null){
			for(Integer menuId:menuIdArr){
				roleDao.executeSql(RoleDao.addRoleMenuSql,new Object[]{roleId,menuId});
			}
		}
	}
	
	
	
	
	
	
}
