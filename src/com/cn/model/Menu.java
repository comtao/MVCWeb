package com.cn.model;

import java.util.List;

import com.cn.model.Model;

public class Menu extends Model{
    private int id ;    //id
	private String name ; //name
	private String desc ; //
	private String path ;
	private int parent ;
	private int visible ;
	private String orderNo ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	
	private String visibleStr ;

	private List<Menu> sun ;
	
	public String getVisibleStr() {
		visibleStr = "不可见";
		if(visible == 1){
			visibleStr = "可见";
		}
		return visibleStr;
	}

	public void setVisibleStr(String visibleStr) {
		this.visibleStr = visibleStr;
	}

	public List<Menu> getSun() {
		return sun;
	}

	public void setSun(List<Menu> sun) {
		this.sun = sun;
	}
}
