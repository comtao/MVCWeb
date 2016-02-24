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

			<h1 class="page-title">员工考勤</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="${ctx}/User/index.html">主页</a> <span
				class="divider">/</span></li>
			<li class="active">员工考勤</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
				<form action="${ctx}/Leave/attendance.html" method="post">
					请假人姓名:<input type="text" value="" name="realName"> 请假开始日期:<input
						size="14" class="btn" id="start" name="start" value="${start}"
						readonly="readonly" type=text style="background-color: white;"
						onfocus="WdatePicker({isShowWeek:true})" /> <input type="submit"
						value="搜索" class="btn" />
				</form>
				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>请假人</th>
								<th>职务</th>
								<th>请假类别</th>
								<th>时间(天/小时)</th>
								<th>开始日期</th>
								<th>结束日期</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${map.list }">
								<tr>
									<td>${list.real_name}</td>
									<td>${list.post}</td>
									<td><c:choose>
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
									<td>${list.levae_d}天${list.levae_h}小时</td>
									<td>${list.l_start}</td>
									<td>${list.l_end}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="pagination">
					<ul>
						<li><a
							href="${ctx}/Leave/attendance.html?pageNum=1&start=${start}&userId=${map.userId}">首
								页</a>
						</li>
						<li><a
							href="${ctx}/Leave/attendance.html?pageNum=${map.page-1 }&start=${start}&userId=${map.userId}">上一页</a>
						</li>
						<li><a
							href="${ctx}/Leave/attendance.html?pageNum=${map.page+1 }&start=${start}&userId=${map.userId}">下一页</a>
						</li>
						<li><a
							href="${ctx}/Leave/attendance.html?pageNum=${map.count }&start=${start}&userId=${map.userId}">尾
								页</a>
						</li>
						<li><a>共${map.sum}条 当前第${map.page }页/共${map.count }页</a>
						</li>
					</ul>
				</div>

			</div>
		</div>
	</div>


	<script src="${ctx}/lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
		$("[rel=tooltip]").tooltip();
		$(function() {
			$('.demo-cancel-click').click(function() {
				return false;
			});
		});
	</script>

</body>
</html>


