<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>${Title}-投票</title>
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

			<h1 class="page-title">投票</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span>
			</li>
			<li class="active">投票</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
			<div class="well" >
				<div class="modal-header">
					<h3 id="myModalLabel">投票</h3>
				</div>
				 <h4>标题：${map.list[0].title}</h4>
				 <div id=well>
				   <c:choose>
					<c:when test="${map.s==0}">
						<c:forEach var="list" items="${map.list}">
							 <div>
								<c:if test="${list.ostatus==0}">
									<input type="radio" name=optionsId id=optionsId  value="${list.id}">
									<span>${list.option_con}</span>
								</c:if>
								<c:if test="${list.option_icon!=' '}">
									<a href="${ctx}${list.option_icon}" target="_blank"><img width="220"  src="${ctx}${list.option_icon}" /></a>
								</c:if> 
							</div> 
						</c:forEach>
						<br><br>
						<input type="submit" class="btn btn-primary" onclick="toupiao('${ctx}',${map.list[0].vid});" value="投票">
					  </c:when>
					  <c:otherwise>
						 	<c:forEach var="listU" items="${map.listU}">
						 		<b>${listU.option_con}</b> 
							 		<c:if test="${listU.option_icon!=' '}">
								 		<a href="${ctx}${listU.option_icon}" target="_blank">
								 		<img width="220"  src="${ctx}${listU.option_icon}" /></a>
							 		</c:if>
						 		<br>
						 		<b style="background: none repeat scroll 0 0 #CCCCCC;width: 400px;display: block;" >
						 		<i style="background: none repeat scroll 0 0 #cec;display: block; text-align:center;width: <fmt:formatNumber type="number" value="${listU.sum/map.sum*100}" maxFractionDigits="1"/>%;">
						 			${listU.sum}<font color="#8A8A8A">(<fmt:formatNumber type="number" value="${listU.sum/map.sum*100}" maxFractionDigits="1"/>%)</font></i></b>
						 			<br>
						 	</c:forEach>
					  </c:otherwise>
					</c:choose>
					
					
					<hr>
					<h4>评论</h4>
					<div   id=comment>
						<h4>标题：${map.list[0].title}</h4>
						<div id=comments>
							<c:forEach var="listcom" items="${map.listcom}">
								<p><b style="color: #1874CD;">${listcom.real_name}</b>&nbsp;&nbsp;&nbsp;${listcom.user_comment}<br>
								<font  color="#6E6E6E"><fmt:formatDate value="${listcom.itime}" pattern="MM-dd hh:mm:ss"/></font></p>
							</c:forEach>
						</div>
					<textarea  style="width: 500px;" id=com></textarea><br>
					<input style="margin:10px 0 0 400px;" type="button" onclick="comment('${ctx}',${map.list[0].vid});" class="btn"  value="发表">
					</div>
				  </div>
				</div>
			</div>
		</div>
	</div>



	<script src="${ctx}/lib/bootstrap/js/bootstrap.js"></script>
	<script src="${ctx}/js/vote/vote.js"></script>
	<script type="text/javascript">
		$("[rel=tooltip]").tooltip();
		$(function() {
			$('.demo-cancel-click').click(function() {
				return false;
			});
		});
		alterMsg('${msg}');
	</script>

</body>
</html>


