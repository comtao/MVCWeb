package com.cn.model;

import java.sql.Timestamp;

import com.cn.util.DateUtil;

/**
 * 
 * @author ll 公告信息
 * 
 */
public class News extends Model {
	private int id;
	private String title;
	private String dpId;
	private String content;
	private int status;
	private Timestamp itime;
	private String shortTime;
	private String datetime;

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

	public String getDpId() {
		return dpId;
	}

	public void setDpId(String dpId) {
		this.dpId = dpId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getShortTime() {
		try {
			shortTime = DateUtil.getDateStr(DateUtil.simple, itime);
		} catch (Exception e) {
			shortTime = "";
		}
		return shortTime;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	private String dpName;

	public String getDpName() {
		return dpName;
	}

	public void setDpName(String dpName) {
		this.dpName = dpName;
	}

	private int readInt;

	public int getReadInt() {
		return readInt;
	}

	public void setReadInt(int readInt) {
		this.readInt = readInt;
	}

	 
}
