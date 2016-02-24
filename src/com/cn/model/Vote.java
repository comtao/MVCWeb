package com.cn.model;

import java.sql.Timestamp;
import org.springframework.web.multipart.MultipartFile;

public class Vote extends Model {
	private int id;	//投票单ID
	private String title; //投票标题
	private String optionsId; //投票选项ID
	private String[] options;//投票选项
	private String  option;
	private MultipartFile[] file;
	private String[] iconFile;
	private String icon;
	private String startTime;//投票开始时间
	private String endTime; //投票结束时间
	private Timestamp itime; //添加时间
	private String comment; //评论内容
	private int userID; //员工Id
	private String realName;//员工姓名
	private String dpId;//所要投票的部门
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOptionsId() {
		return optionsId;
	}
	public void setOptionsId(String optionsId) {
		this.optionsId = optionsId;
	}
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Timestamp getItime() {
		return itime;
	}
	public void setItime(Timestamp itime) {
		this.itime = itime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
/*	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}*/
	
	public String[] getIconFile() {
		return iconFile;
	}
	public MultipartFile[] getFile() {
		return file;
	}
	public void setFile(MultipartFile[] file) {
		this.file = file;
	}
	public void setIconFile(String[] iconFile) {
		this.iconFile = iconFile;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDpId() {
		return dpId;
	}
	public void setDpId(String dpId) {
		this.dpId = dpId;
	}
	
}
