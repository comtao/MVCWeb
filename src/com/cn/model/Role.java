package com.cn.model;

import java.sql.Timestamp;
import java.util.List;


/**
 * 角色
 * @author owen
   @date 2014-10-17
 */
public class Role extends Model{
	private int id;
	private String roleName;
	private Timestamp itime ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Timestamp getItime() {
		return itime;
	}
	public void setItime(Timestamp itime) {
		this.itime = itime;
	}
	
	public String getStatusStr(){
		if(super.status == 0){
			return "正常";
		}else{
			return "冻结";
		}
	}
	
	/**
	 * 角色权限 菜单ID
	 */
	private List<Integer> rmId;

	public List<Integer> getRmId() {
		return rmId;
	}

	public void setRmId(List<Integer> rmId) {
		this.rmId = rmId;
	}
}
