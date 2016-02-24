<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  	<base target="_self">
    <title>${Title }</title>
  </head>
  
    <frameset rows="42,*" cols="*" frameborder="no" border="0" framespacing="0">
		<frame src="${ctx}/page/include/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
		<frameset cols="213,*" frameborder="no" border="0" framespacing="0">
		   <frame src="${ctx}/page/include/left.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
		   <frame src="${ctx}/User/index.html" name="mainFrame" id="mainFrame" title="mainFrame" />
		</frameset>
	</frameset>
	<noframes>
		<body>您的浏览器不支持框架！</body>
	</noframes> 
</html>