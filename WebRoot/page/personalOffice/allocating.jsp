<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@include file="/common/taglibs.jsp" %>
<% SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   String now = format.format(new Date()); %>
<html lang="en">
  <head>
    <title>${Title}-任务详情</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" type="text/css" href="${ctx}/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/stylesheets/theme.css">
    <link rel="stylesheet" href="${ctx}/lib/font-awesome/css/font-awesome.css">
    <script src="${ctx}/lib/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="${ctx}/js/work/task.js" type="text/javascript"></script>
    <script src="${ctx}/js/common/util.js" type="text/javascript"></script>
    <script src="${ctx}/js/calendar/WdatePicker.js" type="text/javascript"></script>
    <script src="${ctx}/js/divFlow/jquery.ui.flow.js" type="text/javascript"></script>
    <script src="${ctx}/js/divFlow/wz_jsgraphics.js" type="text/javascript"></script>
    <!-- Demo page code -->
    <style type="text/css">
    #receiveUserId label {display:inline;margin-right:10px; }
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;}
        .brand .second {
            color: #fff;
            font-weight: bold;}
        .hover{background: green;color: black;}
	.before{
		padding:5px;width:170px;height:90px;border:1px solid black;
		font-size:9pt;
		color:#000000;
		font-weight:bolder;
		float: left;
		cursor:hand;overflow-y:auto;overflow-x:auto;}
	.taskCommon{background:#C0C0C0;} /*未完成任务  但未超过时限*/
	.taskFinsh{background:#70DBDB;}    /*规定时间完成*/
	.taskOverTime{background:#CC3299;}   /* 规定时间未完成 */
	.taskStart{background:#D9D919;}
	.tipBox{padding:7px 20px;font-size:12px;font-weight:bold;margin-left:7px;}
	
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
            <h1 class="page-title">任务详情</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span></li>
            <li class="active">任务详情</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid"> 
            <div class="btn-toolbar">
			   <span class="btn btn-primary" style="cursor:default;">
			         任务详情
			   </span>
		    </div>
            <div class="well">
               <table class="table">
                 <thead>
                   <tr>
                    <td width="20%">任务标题：</td>
                    <td width="90%">${tDetail.title}</td>
                   </tr>
                 </thead>
                 <tbody>
                   <tr> 
                     <td>任务级别：</td>
                     <td>
                      <c:choose>
                       <c:when test="${tDetail.grade == 2}">
                        <font style="color:red;">加急</font>
                       </c:when>
                       <c:when test="${tDetail.grade == 1}">
                                                               较急
                       </c:when>
                       <c:otherwise>
                                                                一般
                       </c:otherwise>
                      </c:choose>
                     </td>
                   </tr>
                   <tr>
                    <td>发起人：</td>
                    <td>${tDetail.dpName}-${tDetail.realName}</td>
                   </tr>
                   <tr>
                    <td>任务描述：</td>
                    <td>${tDetail.content}</td>
                   </tr>
                   <tr>
                    <td>任务状态：</td> 
                    <td>
                      <c:choose>
                        <c:when test="${tDetail.status == -1}">
                          <font style="color:red;">审核失败</font>
                        </c:when>
                        <c:when test="${tDetail.status == 2}">
                          <font>执行中</font>
                        </c:when>
                        <c:when test="${tDetail.status == 0}">
                                                                    待审核
                        </c:when>
                        <c:when test="${tDetail.status == 3}">
                                                                   完成
                        </c:when>
                        <c:otherwise>审核中</c:otherwise>
                      </c:choose>
                    </td>
                   </tr>
                   <c:if test="${!empty tDetail.attachment}">
                   <tr> 
                    <td>附件：</td>
                    <td><a href="${tDetail.attachment}">${tDetail.attachment}</a></td>
                   </tr>
                   </c:if>
                 </tbody>
               </table>
            </div>
<div class="modal role hide fade" id="addTaskWbs" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">增加分任务</h3>
	</div>
	<form id="allocatingAddForm" name="form_data" method="post" class="form-horizontal" action="${ctx}/task/allocatingAdd.html" onsubmit="return addFlowCheck()">
	  <input type="hidden" name="taskId" value="${taskId}">
	  <input type="hidden" id="id" name="id" value="">
	  <input type="hidden" id="flag" name="flag" value="100">
	  <input type="hidden" id="rUserId" name="userIds" value="">
	<div class="modal-body">
			<table class="table table-bordered">
				<tbody>
					<!-- <tr>
					  <th>后置任务id</th>
					  <td><input type="text" name="nextId" style="width:100px;" value="-1"/></td>
					</tr> -->
					<tr>
					  <th width="100px">实施人部门*</th>
					  <td>
					    <select name="userDpId" id="receiveDpId" OnChange="changeDp('${ctx}')">
					     
					    </select>
					  </td>
					</tr>
					<tr>
					  <th>实施人*</th>
					  <td id="receiveUserId">
					   &nbsp;
					    <!-- <select id="receiveUserId" name="userId">
					    </select> -->
					  </td>
					</tr>
					<tr>
					  <th>任务描述*</th>
					  <td>
					    <textarea name="describe" style="height:100px;width:500px;max-width:500px;"></textarea>
					  </td>
					</tr>
					<tr>
					 <th>任务开始时间*</th>
					 <td><input type="text" readonly name="startTime" value="<%=now%>" onclick="WdatePicker()" width="100" ></td>
					</tr>
					<tr>
					 <th>任务截至时间*</th>
					 <td><input type="text" readonly name="endTime" value="<%=now%>" onclick="WdatePicker()" width="100"></td>
					</tr>
				</tbody>
			</table>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
		<button id="submitForm" class="btn btn-danger" data-dismiss="modal">提交</button>
	</div>
	</form>
</div>

<!-- 流程图 -->
<%-- <c:if test="${isAllot}"> --%>
<div class="btn-toolbar" style="display:none;">
	 <a data-toggle="modal" id="aEditDiv" role="button" href="#addTaskWbs" class="btn btn-primary">
	   <i class="icon-save"></i>添加分任务
	 </a>
     <div class="btn-group">
     </div>
</div>
<div class="btn-toolbar">
<%-- </c:if> --%>
   <span class="btn btn-primary" style="cursor:default;">
           任务流程
   </span>
   <div style="float:right;">
     <span class="tipBox taskCommon">待完成</span>
     <span class="tipBox taskStart">任务开始</span>
     <span class="tipBox taskOverTime">任务超时</span>
     <span class="tipBox taskFinsh">任务完成</span>
   </div>
   
   
</div>
<div id="spanBefore">
    <c:forEach var="itm" items="${list}" varStatus="status">
      <div ${status.index==0?"begin='-1'":""} title="执行人：${itm.realName}" userId="${itm.userIds}" id="${itm.id}" next="${itm.nextId}"
          class="before ${itm.status==0 ?'taskFinsh':itm.flag==5?'taskOverTime':itm.start==0?'taskStart':'taskCommon'}">
        <div>
         <span>
          <a onclick="startTask('${ctx}','${itm.id}','${itm.userIds}')" href="javascript:void(0);">开始</a>|
          <a onclick="finshTask('${ctx}','${itm.id}','${itm.userIds}')" href="javascript:void(0);">完成</a>|
          <a onclick="handTask('${ctx}','${itm.id}','${itm.userIds}')" href="javascript:void(0);">下派</a>|
          <a onclick="deleteTask('${ctx}','${itm.id}','${taskId}')" href="javascript:void(0);">删除</a>
         <%--  (${itm.status==0 ?'已完成':itm.flag==5?'任务超时':itm.start==0?'开启':'待完成'}) --%>
         </span>
        </div>             
                      开始：<fmt:formatDate value="${itm.startTime}" pattern="yyyy-MM-dd HH:00:00"/><br/>
                      截止：<fmt:formatDate value="${itm.endTime}" pattern="yyyy-MM-dd HH:00:00"/><br/>
        ${itm.describe}<br/>
      </div>
    </c:forEach>
</div>
<div style="padding:30px;border:1px dotted black;overflow-y:auto;">
   <div style="position:relative; width:300px; height:300px;" id="draw"></div>
</div>


<div class="well" style="margin-top:30px;">
   <h4>备注：</h4>
   <c:forEach var="cm" items="${comment}">
     <p>
       <b style="color:#1874CD;">${cm.realName}：</b>
        ${cm.comment}<br>
       <font color="#6E6E6E"><fmt:formatDate value="${cm.itime}" pattern="yyyy-MM-dd HH:mm:ss"/></font>
     </p>
   </c:forEach>
</div>
<script type="text/javascript">
	var createFlow = function(){
		$("#spanBefore").flow('${ctx}',{hover:function(){
			$(this).addClass("hover");
		},remove:function(){
			$(this).removeClass("hover");
		}/*, click:function(){
			taskExcute('${ctx}',$(this).attr("id"),$(this).attr("userId"));
		} */});
	};
</script>
 </div>
        </div>
    </div>
    <script src="${ctx}/lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
            $("#submitForm").click(function(){
               $("#allocatingAddForm").submit();
            });
            createFlow();
        });
        initDpOptions('${ctx}');
    </script>
  </body>
</html>


