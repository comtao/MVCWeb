package com.cn.model;

import java.sql.Timestamp;

import com.cn.util.DateUtil;

/**
 * 部门
 * @author ll
 *
 */
public class Department {
 private int id;
 private String dpName;
 private int status;
 private Timestamp itime;
 private String shortItime;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDpName() {
	return dpName;
}
public void setDpName(String dpName) {
	this.dpName = dpName;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public Timestamp getItime() {
	return itime;
}
public void setItime(Timestamp itime) {
	this.itime = itime;
}
public String getShortItime() {
	try {
		shortItime=DateUtil.getDateStr(DateUtil.simple, itime);
	} catch (Exception e) {
		shortItime="";
	}
	return shortItime;
}
public void setShortItime(String shortItime) {
	this.shortItime = shortItime;
}
 
 
}
