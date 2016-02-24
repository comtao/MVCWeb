package com.cn.model;

public class Model {
	public int index = 1 ;  //行号   
	public int status = -1; //0正常     1冻结
	
	public int pageSize = 1 ; //每页 记录 条数
	
	/**
	 * 页数
	 */
	public int pageNum = 1 ;
	
	/**
	 * 展示需要
	 */
	public String statusStr ;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getStatusStr() {
		statusStr = status + "";
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}

