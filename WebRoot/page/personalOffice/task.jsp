<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@ taglib uri="/page" prefix="page" %>
<html lang="en">
  <head>
    <title>${Title }-任务</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="${ctx}/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/stylesheets/theme.css">
    <link rel="stylesheet" href="${ctx}/lib/font-awesome/css/font-awesome.css">
    <script src="${ctx}/lib/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="${ctx}/js/work/page.js" type="text/javascript"></script>
    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;}
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;}
        .brand .second {
            color: #fff;
            font-weight: bold; }
    </style>
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
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
            <h1 class="page-title">任务</h1>
        </div>
		<ul class="breadcrumb">
			<li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span>
			</li>
			<li class="active">处理中任务</li>
		</ul>
		<div class="container-fluid">
            <div class="row-fluid">
                    
<div class="btn-toolbar">
    <button class="btn btn-primary" onclick="location='${ctx}/task/add.html'"><i class="icon-plus"></i>发起任务</button>
    <button class="btn" onclick="location='${ctx}/task/finishList.html'">已完成任务</button>
<!-- <button class="btn">已提交</button>
    <button class="btn">处理</button> -->
  <div class="btn-group">
  </div>
</div>
<div class="well">
    <table class="table">
      <thead>
        <tr>
          <th width="5%">任务id</th>
          <th width="30%">任务标题</th>
          <th width="10%">任务等级</th>
          <th width="5%">发起人</th>
          <th width="12%">发起时间</th>
          <th width="7%">状态</th>
          <th width="6%">操作</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var="itm" items="${list}">
         <tr>
           <td>${itm.id}</td>
           <td>${itm.title}</td>
           <td>
           <c:choose>
             <c:when test="${itm.grade==1}">
                <c:out value="较急"/> 
             </c:when>
             <c:when test="${itm.grade==2}">
                <c:out value="加急"/> 
             </c:when>
             <c:otherwise>
                <c:out value="一般"/> 
             </c:otherwise>
             </c:choose>
           </td>
           <td>${itm.realName}</td>
           <td><fmt:formatDate value="${itm.itime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
           <td>
            <c:choose>
              <c:when test="${itm.status==-1}">
                <font style="color:red">审核失败</font>
              </c:when>
              <c:when test="${itm.status==1}">
                                        审核中
              </c:when>
              <c:when test="${itm.status==2}">
                                        执行中
              </c:when>
              <c:otherwise>
                                        待审核               
              </c:otherwise>
            </c:choose>
           </td>
           <td>
             <%-- <a href="${ctx}/task/taskDetail.html?taskId=${itm.id}"><i class="icon-pencil"></i></a> --%>
             <a href="${ctx}/task/allocating.html?id=${itm.id}">查看</a>
             <c:if test="${(itm.status==-1 || itm.status==0) && task.userId==itm.userId}">
               |<a href="${ctx}/task/delTask.html?id=${itm.id}">删除</a>
             </c:if>
           </td>
         </tr>
      </c:forEach>
      </tbody>
    </table>
</div>
 <form action="${ctx}/task/list.html" id="searchForm" method="post" style="display:none;">
  <input type="hidden" id="pageNum" name="pageNum" value="${task.pageNum}" />
 </form>
<page:page pageNum="${task.pageNum}" pageTotal="${pageTotal}"></page:page>
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


