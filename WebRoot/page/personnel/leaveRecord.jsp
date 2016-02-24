<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>${Title}-员工考勤</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" type="text/css"
	href="${ctx}/lib/bootstrap/css/bootstrap.css">

<link rel="stylesheet" type="text/css"
	href="${ctx}/stylesheets/theme.css">
<link rel="stylesheet"
	href="${ctx}/lib/font-awesome/css/font-awesome.css">

<script src="${ctx}/lib/jquery-1.7.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

<!-- Demo page code -->

<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.brand {
	font-family: georgia, serif;
}

.brand .first {
	color: #ccc;
	font-style: italic;
}

.brand .second {
	color: #fff;
	font-weight: bold;
}
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

			<h1 class="page-title">请假记录</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span>
			</li>
			<li class="active">请假记录</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
				<form action="${ctx}/Leave/leaveRecord.html"  method="post"  >
					请假单编号:<input  type="number"  value="0"  name="id" style="width: 50px;" >	
					请假人姓名:<input  type="text"  value=""  name="realName" style="width: 100px;">	
					请假开始日期:<input style="width: 100px;" size="14" class="btn"  id="start" name="start"  value="${start}"  readonly="readonly" type=text style="background-color: white;"  onfocus="WdatePicker({isShowWeek:true})" />
					<input type="submit" value="搜索" class="btn"/>		
				</form>
				<div class="well">
				<table class="table">
						<thead>
							<tr>
								<th>请假单编号</th>
								<th>请假人</th>
								<th>请假日期</th>
								<th>结束日期</th>
								<th>所属部门</th>
								<th>职务</th>
								<th>添加日期</th>
								<th>类型</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="list" items="${map.list}">
							<tr>
								<td>${list.id}</td>
								<td>${list.real_name}</td>
								<td>${list.l_start}</td>
								<td>${list.l_end}</td>
								<td>${list.dp_name}</td>
								<td>${list.post}</td>
								<td><fmt:formatDate value="${list.itime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<c:choose>
								 	  <c:when test="${list.l_status==1}">
									    	事假
									  </c:when>
									  <c:when test="${list.l_status==2}">
									    	病假
									  </c:when>
									  <c:when test="${list.l_status==3}">
									    	婚假
									  </c:when>
									  <c:when test="${list.l_status==4}">
									    	产假
									  </c:when>
									  <c:when test="${list.l_status==5}">
									    	丧假
									  </c:when>
									  <c:when test="${list.l_status==6}">
									    	工伤
									  </c:when>
									  <c:when test="${list.l_status==7}">
									    	年休假
									  </c:when>
									  <c:otherwise>
									    	其他 
									  </c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:choose>
									  <c:when test="${list.status==0}">
									    	<font color=#8E8E8E>未审批</font>
									  </c:when>
									  <c:when test="${list.status==1}">
									    	<font color="green">已审批</font>
									  </c:when>
									  <c:when test="${list.status==2}">
									    	<font color="red">被驳回</font>
									  </c:when>
									  <c:otherwise>
									    	其他 
									  </c:otherwise>
									</c:choose></td>
								<td>
								<a  href="#search" onclick="searchAjax('${ctx}','${list.id}','${list.dp_id }','${list.userid }');" data-toggle="modal" title="查看"><i class="icon-search"></i>
								</a>   
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
 <div class="pagination">
	 <ul>
        <li ><a href="${ctx}/Leave/leaveRecord.html?pageNum=1&start=${start}&userId=${map.userId}">首  页</a></li>
        <li><a href="${ctx}/Leave/leaveRecord.html?pageNum=${map.page-1 }&start=${start}&userId=${map.userId}">上一页</a></li>
        <li><a href="${ctx}/Leave/leaveRecord.html?pageNum=${map.page+1 }&start=${start}&userId=${map.userId}">下一页</a></li>
        <li><a href="${ctx}/Leave/leaveRecord.html?pageNum=${map.count }&start=${start}&userId=${map.userId}">尾 页</a></li>
        <li><a>共${map.sum}条 当前第${map.page }页/共${map.count }页</a></li>
    </ul>
 </div>

				<div class="modal leave hide fade" id="search" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel">查看请假单</h3>
					</div>
					<div class="modal-body">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<th width="100px">标题</th>
										<td  width="100px"><input class="form-control" type="text" id="name"
											name="name" check="require" msg="公司员工请假单" value="请假单"  readonly="readonly"></td>
									</tr>
									<tr>
										<th>姓名</th>
										<td  >
											 <input class="form-control" type="text" id="nameAjax"
											name="name" check="require" msg="姓名"   readonly="readonly">
										</td>
									</tr>
									<!-- <tr>
										<th>部门*</th>
										<td  >
											 <input class="form-control" type="text" id="name"
											name="name" check="require" msg="请假部门"  readonly="readonly">
										</td>
									</tr> -->
										<tr>
										<th>请假类型</th>
										<td  >
										 <font id=lStatusAjax></font>
										</td>
									</tr>
									<tr>
									<th><b>请假事由：</b> </th>
										<td  >
											 <textarea  id="reasonAjax"  readonly="readonly" name="reason" rows="5" cols="50" style="width: 579px; height: 100px;"> </textarea>
										</td>
									</tr>
									<tr>
										<th>请假时间</th>
										<td  > 
										<input id=startAjax name="start"  readonly="readonly" type="text">
										至
										<input id=endAjax name="end" type="text"  readonly="readonly"  >
										</td>
									</tr>
									<tr>
										<th>审批对象</th>
										<td> 
										<input   type="text"  id=dpAjax readonly="readonly"> 
										</td>
									</tr>
									<tr>
										<th>备注</th>
										<td> 
											<textarea  id="bAjax"  readonly="readonly" name="reason" rows="5" cols="50" style="width: 579px; height: 100px;"></textarea>
										</td>
									</tr>
									
								</tbody>
							</table>
						
					</div>
				</div>	
				
			</div>
		</div>
	</div>


	<script src="${ctx}/lib/bootstrap/js/bootstrap.js"></script>
	<script src="${ctx}/js/user/leave.js"></script>
	<script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>

</body>
</html>


