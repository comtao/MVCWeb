package com.cn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cn.model.Role;


@Repository
public class RoleDao extends JdbcDao{
	/**
	 * 角色名称是否存在
	 */
	public static String hasRoleSql = "select count(1) from t_role where role_name = ?" ;
	
	/**
	 * 新增角色
	 */
	public static String addSql = "insert into t_role (role_name) values (?)" ;
	
	/***
	 * 编辑角色
	 */
	public static final String editSql = "UPDATE t_role SET role_name=? WHERE role_name=? ";

	/**
	 * 角色列表
	 */
	public static String list = "select id,role_name,status,itime from t_role " ;
	public List<Role> list(){
		return jdbcTemplate.query(list,  new RowMapper<Role>(){
			@Override
			public Role mapRow(ResultSet rs, int index) throws SQLException {
				int num = 1+index;
				Role role = new Role();
				role.setIndex(num);
				role.setId(rs.getInt("id"));
				role.setRoleName(rs.getString("role_name"));
				role.setStatus(rs.getInt("status"));
				role.setItime(rs.getTimestamp("itime"));
				return role;
			}
		});
	}
	
	
	/**
	 * 角色菜单列表
	 */
	public static String listRoleMenuSql = "select menu_id from t_role_menu where role_id = ?" ;
	public List<Integer> listRoleMenu(int roleId){
		return jdbcTemplate.query(listRoleMenuSql,new Object[]{roleId}, new RowMapper<Integer>(){
			public Integer mapRow(ResultSet rs, int index) throws SQLException {
				return rs.getInt("menu_id");
			}
		});
	}
	
	
	
	/**
     * 删除角色菜单	
     */
	public static String delRoleMenuSql = "delete from t_role_menu where role_id = ?" ;
	/**
	 * 添加角色菜单
	 */
	public static String addRoleMenuSql = "insert into t_role_menu (role_id , menu_id) values (?,?)" ;
}
