package com.cn.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class PageTag extends BodyTagSupport{
	private static final long serialVersionUID = 1L;
	
	private int pageNum = 1;      //当前页
	private int pageTotal = 0;    //总页数
	private int totalcount = 0;    //总条数
	private String url = "";   //URL地址
	
	@Override
	public int doEndTag() throws JspException {
	    try {
	    	JspWriter out=pageContext.getOut();
			StringBuffer str = new StringBuffer();
			String path = pageContext.getServletContext().getContextPath();
			
			if(pageTotal<1){
				pageNum = 0 ;
			}
			str.append("<div class='pagination'>")
			   .append("<ul>")
			   .append("<li><a href='javascript:void(0);' onclick='javascript:firstPage(0);'>首页</a></li>")
			   .append("<li><a href='javascript:void(0);' onclick='javascript:firstPage("+(pageNum-1)+");'>上一页</a></li>")
			   .append("<li><a href='javascript:void(0);' onclick='javascript:nextPage("+(pageNum+1)+");'>下一页</a></li>")
			   .append("<li><a href='javascript:void(0);' onclick='javascript:lastPage("+pageTotal+");'>尾页</a></li>")
			   .append("<li><a>共"+pageTotal+"页/第"+pageNum+"页</a></li>")
			   .append("</ul>");
			out.println(str.toString());
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    this.release();
		return EVAL_PAGE;
	}
	
	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public void release() {
		pageNum = 1;
		super.release();
	}
	
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
