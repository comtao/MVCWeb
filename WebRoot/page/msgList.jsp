<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<title>${Title}-新的消息</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" type="text/css" href="${ctx}/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${ctx}/stylesheets/theme.css">
<link rel="stylesheet" href="${ctx}/lib/font-awesome/css/font-awesome.css">

<script src="${ctx}/lib/jquery-1.7.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

<!-- Demo page code -->
<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;}
.brand {
	font-family: georgia, serif;}
.brand .first {
	color: #ccc;
	font-style: italic;}
.brand .second {
	color: #fff;
	font-weight: bold;}
</style>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="../assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="../assets/ico/apple-touch-icon-57-precomposed.png">
</head>

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
	<!--<![endif]-->
	<div class="content">
		<div class="header">
			<h1 class="page-title">消息列表</h1>
		</div>
		<ul class="breadcrumb">
			<li>
			  <a href="${ctx}/User/index.html">主页</a> 
			  <span class="divider">/</span>
			  <li class="active">消息列表</li>
			</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="well">
					<table class="table">
					<!-- 任务申请 开始 -->
					  <c:if test="${not empty approvalTasks}">
					  <thead>
					  <tr>
					    <td colspan=5 align="left">
					      <span class="btn btn-primary">未审批任务</span>
					    </td>
					  </tr>
					  <tr>
					   <th width="10%">任务级别</th>
					   <th width="50%">任务标题</th>
					   <th width="15%">申请人</th>
					   <th width="15%">申请时间</th>
					   <th width="10%">操作</th>
					  </tr>
					  </thead>
					  <c:forEach var="itm" items="${approvalTasks}">
					  <tr>
					    <td>${itm.gradeStr}</td>
					    <td>${itm.title}<img src="${ctx}/images/hot.gif"></td>
					    <td>${itm.realName}</td>
					    <td><fmt:formatDate value="${itm.itime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td><a href="${ctx}/task/approval.html?id=${itm.id}" title="审批任务">审批</a></td>
					  </tr>
					  </c:forEach>
					  </c:if>
					<!-- 任务申请 结束-->
					  <tr><td height="20" colspan=5>&nbsp;</td></tr>
				<!-- 请假申请 -->	  
					  <c:if test="${not empty leaveList}">
						  <tr>
						    <td colspan=5 align="left" style="border:none;">
						      <span class="btn btn-primary">待处理请假申请</span>
						    </td>
						  </tr>
						  	<tr>
							   <th>请假人</th>
							   <th>请假类型</th>
							   <th>开始时间</th>
							   <th>结束时间</th>
							   <th>操作</th>
							  </tr>
							  <c:forEach var="itm" items="${leaveList}">
							  <tr>
							    <td>${itm.real_name}<img src="${ctx}/images/hot.gif"></td>
							    <td>
							    <c:choose>
								 	  <c:when test="${itm.l_status==1}">
									    	事假
									  </c:when>
									  <c:when test="${itm.l_status==2}">
									    	病假
									  </c:when>
									  <c:when test="${itm.l_status==3}">
									    	婚假
									  </c:when>
									  <c:when test="${itm.l_status==4}">
									    	产假
									  </c:when>
									  <c:when test="${itm.l_status==5}">
									    	丧假
									  </c:when>
									  <c:when test="${itm.l_status==6}">
									    	工伤
									  </c:when>
									  <c:when test="${itm.l_status==7}">
									    	年休假
									  </c:when>
									  <c:otherwise>
									    	其他 
									  </c:otherwise>
									</c:choose>
									</td>
									<td>${itm.l_start}</td>
							        <td>${itm.l_end}</td>
							    <td><a href="${ctx}/Leave/leaveApprovaList.html" title="审批">审批</a></td>
							  </tr>
							</c:forEach>
					  </c:if>
				<!-- 请假申请 -->	  	 
					  
					  <c:if test="${not empty newsList}">
						  <tr>
						    <td colspan=5 align="left" style="border:none;">
						      <span class="btn btn-primary">未读公告</span>
						    </td>
						  </tr>
						  	<tr>
							   <th  >编号</th>
							   <th  >标题</th>
							   <th  colspan="2">日期</th>
							   <th  >操作</th>
							  </tr>
								  <c:forEach var="itm" items="${newsList}">
									  <tr>
									    <td>${itm.id}</td>
									    <td>${itm.title}<img src="${ctx}/images/hot.gif"></td>
									    <td colspan="2"><fmt:formatDate value="${itm.itime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									    <td><a href="${ctx}/News/seeNews.html?id=${itm.id}" title="查看">查看</a></td>
									  </tr>
								  </c:forEach>
					  </c:if>
					  
					  <c:if test="${not empty voteList}">
						  <tr>
						    <td colspan=5 align="left" style="border:none;">
						      <span class="btn btn-primary">待参与投票</span>
						    </td>
						  </tr>
						  	<tr>
							   <th >编号</th>
							   <th colspan="3">标题</th>
							   <th>操作</th>
							  </tr>
							  <c:forEach var="itm" items="${voteList}">
							  <tr>
							    <td>${itm.id}</td>
							    <td colspan="3">${itm.title}<img src="${ctx}/images/hot.gif"></td>
							    <td><a href="${ctx}/Vote/vote.html?id=${itm.id}&optionId=${itm.optionId}" title="投票">投票</a></td>
							  </tr>
							</c:forEach>
					  </c:if>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx}/lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
</body>
</html>


