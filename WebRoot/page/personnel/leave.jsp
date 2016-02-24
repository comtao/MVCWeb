<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>${Title}-请假申请</title>
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

			<h1 class="page-title">请假申请</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span>
			</li>
			<li class="active">请假申请</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
<div class="btn-toolbar">
    <a class="btn btn-primary"  href="#addLeave"  role="button" data-toggle="modal"><i class="icon-save"></i>请假申请</a>
  <div class="btn-group">
  </div>
</div>
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
						<c:forEach var="list" items="${list}">
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
								<a  href="#search" onclick="searchAjax('${ctx}','${list.id}','${list.dp_id}','${list.userid }');" data-toggle="modal" title="查看"><i class="icon-search"></i>
								</a>   
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="pagination">
					<!-- <ul>
						<li><a href="#">上一页</a>
						</li>
						<li><a href="#">1</a>
						</li>
						<li><a href="#">2</a>
						</li>
						<li><a href="#">3</a>
						</li>
						<li><a href="#">4</a>
						</li>
						<li><a href="#">下一页</a>
						</li>
					</ul> -->
				</div>

				
				<div class="modal leave hide fade" id="addLeave" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel">添加请假单</h3>
					</div>
					<form id="form_data" name="form_data" method="post"
							class="form-horizontal"  onsubmit="return addLevae();" action="${ctx}/Leave/addLeave.html">
					<div class="modal-body">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<th width="100px">标题*</th>
										<td  width="100px"><input class="form-control" type="text" id="name"
											name="name" check="require" msg="公司员工请假单" value="请假单"  readonly="readonly"></td>
									</tr>
									<tr>
										<th>姓名*</th>
										<td  >
											 <input class="form-control" type="text" id="name"
											name="name" check="require" msg="姓名" value="${user.realName}" readonly="readonly">
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
										<th>请假类型*</th>
										<td  >
											 <select class="form-control col-10"
											name="levaeStatus" id="lStatus">
												<option value="-1">请选择请假类型</option>
												<option value="1">事假</option>
												<option value="2">病假</option>
												<option value="3">婚假</option>
												<option value="4">产假</option>
												<option value="5">丧假</option>
												<option value="6">工伤</option>
												<option value="7">年休假</option>
												<option value="8">其他</option>
										</select>
										</td>
									</tr>
									<tr>
									<th><b>请假事由*：</b> </th>
										<td  >
											 <textarea  id="reason" name="reason" rows="5" cols="50" style="width: 579px; height: 30px;"></textarea>
										</td>
									</tr>
									<tr>
										<th>请假时间*</th>
										<td  > 
										<input id=start name="start" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})" readonly="readonly" type="text">
										至
										<input id=end name="end" type="text"  readonly="readonly"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})" >
										</td>
									</tr>
									<tr>
										<th>审批对象*</th>
										<td> 
										    <select  id="diId"   name=diId>
											    <c:forEach  var="map"	items="${map}">
											    	<option value="${map.id}">${map.role_name}(${map.real_name})</option>
											    </c:forEach> 
										    </select>
										</td>
									</tr>
									
								</tbody>
							</table>
						
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
						<input  class="btn btn-danger" type="submit"  value="提交">
					</div>
					</form>
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
											 <textarea  id="reasonAjax"  readonly="readonly" name="reason" rows="5" cols="50" style="width: 579px; height: 30px;"></textarea>
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
        alterMsg('${msg}');
    </script>

</body>
</html>


