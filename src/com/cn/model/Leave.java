package com.cn.model;

import java.sql.Timestamp;

import com.cn.util.DateUtil;

public class Leave  extends Model{
	private int id ;
	private int userId;
	private int levaeStatus;
	private String levaeString;
	private String reason;//内容
	private String start;
	private String end;
	private int diId;
	private String remarks;//备注
	private int status;
	private Timestamp itime;
	private String shortItime;
	private String realName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getLevaeStatus() {
		return levaeStatus;
	}
	public void setLevaeStatus(int levaeStatus) {
		this.levaeStatus = levaeStatus;
	}
	public String getLevaeString() {
		if(levaeStatus==1){
			levaeString="事假";
		}else if(levaeStatus==2){
			levaeString="病假";
		}else if(levaeStatus==3){
			levaeString="婚假";
		}else if(levaeStatus==4){
			levaeString="产假";
		}else if(levaeStatus==5){
			levaeString="丧假";
		}else if(levaeStatus==6){
			levaeString="工伤";
		}else if(levaeStatus==7){
			levaeString="年休假";
		}else{
			levaeString="其他";
		} 
		return levaeString;
	}
	public void setLevaeString(String levaeString) {
		this.levaeString = levaeString;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public int getDiId() {
		return diId;
	}
	public void setDiId(int diId) {
		this.diId = diId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
			shortItime = DateUtil.getDateStr(DateUtil.simple, itime);
		} catch (Exception e) {
			shortItime = "";
		}
		return shortItime;
	}
	public void setShortItime(String shortItime) {
		this.shortItime = shortItime;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
}
