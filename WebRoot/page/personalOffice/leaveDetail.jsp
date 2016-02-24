<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>${Title}-请假信息</title>
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
<script type="text/javascript"
	src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
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
			<h1 class="page-title">请假详情</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span>
			</li>
			<li class="active">请假详情</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="well">
		   		 <div class="col-sm-8">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<th><nobr>姓名 </nobr>
										</th>
										<td><b>${leave.real_name}</b></td>
									</tr>
									<tr>
										<th>请假类型</th>
										<td><b>
                                           <c:choose>
								 	  <c:when test="${leave.l_status==1}">
									    	事假
									  </c:when>
									  <c:when test="${leave.l_status==2}">
									    	病假
									  </c:when>
									  <c:when test="${leave.l_status==3}">
									    	婚假
									  </c:when>
									  <c:when test="${leave.l_status==4}">
									    	产假
									  </c:when>
									  <c:when test="${leave.l_status==5}">
									    	丧假
									  </c:when>
									  <c:when test="${leave.l_status==6}">
									    	工伤
									  </c:when>
									  <c:when test="${leave.l_status==7}">
									    	年休假
									  </c:when>
									  <c:otherwise>
									    	其他 
									  </c:otherwise>
									</c:choose>
                                        </b></td>
									</tr>
									<tr>
										<th>请假事由</th>
										<td>${leave.l_reason}</td>
									</tr>
									<tr>
										<th>请假时间</th>
										<td>${leave.l_start}&nbsp;&nbsp;~&nbsp;&nbsp;${leave.l_end}</td>
									</tr>
									<tr>
										<th>备注</th>
										<td>
											${leave.l_remarks}
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
	<script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
</body>
</html>