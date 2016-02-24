<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<title>${Title}-通讯录</title>
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
			<h1 class="page-title">通讯录</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="${ctx}/User/index.html">主页</a> <span
				class="divider">/</span>
			</li>
			<li class="active">通讯录</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
				<form method="post" action="${ctx}/User/addressList.html">
					姓名:<input name=realName style="width: 100px;"> 部门: <select
						name=dpName style="width: 150px;">
						<option value="">请选择部门</option>
						<c:forEach var="dplist" items="${dplist }">
							<option value="${dplist.dpName}">${dplist.dpName}</option>
						</c:forEach>
					</select> 职务:<input name=post style="width: 100px;"> <input
						value="搜索" type="submit" class="btn">
				</form>
				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>姓名</th>
								<th>部门</th>
								<th>职务</th>
								<th>电话</th>
								<th>邮箱</th>
								<th>QQ</th>
								<th>办公室电话</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${map.list }">
								<tr onmouseover="overIcon('${ctx}','${list.icon}',event)" 
								onmouseout="outIcon();" style='cursor:pointer;'>
									<td>${list.realName }</td>
									<td>${list.dpName}</td>
									<td>${list.post}</td>
									<td>${list.phone}</td>
									<td>${list.email}</td>
									<td>${list.qq}</td>
									<td>${list.officePhone}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="pagination">
					<ul>
						<li><a
							href="${ctx}/User/addressList.html?pageNum=1&lTime=${lTime}&dpName=${dpName}&realName=${realName}&post=${post}">首
								页</a>
						</li>
						<li><a
							href="${ctx}/User/addressList.html?pageNum=${map.page-1 }&dpName=${dpName}&realName=${realName}&post=${post}">上一页</a>
						</li>
						<li><a
							href="${ctx}/User/addressList.html?pageNum=${map.page+1 }&dpName=${dpName}&realName=${realName}&post=${post}">下一页</a>
						</li>
						<li><a
							href="${ctx}/User/addressList.html?pageNum=${map.count }&dpName=${dpName}&realName=${realName}&post=${post}">尾
								页</a>
						</li>
						<li><a>共${map.sum}条 当前${map.page }页/${map.count }页</a>
						</li>
					</ul>
				</div>

				<div class="modal small hide fade" id="myModal" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel">删除提示</h3>
					</div>
					<div class="modal-body">
						<p class="error-text">
							<i class="icon-warning-sign modal-icon"></i>你确定删除该项?
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
						<button class="btn btn-danger" data-dismiss="modal">删除</button>
					</div>
				</div>





			</div>
		</div>
	</div>

	<div id=iconOver style="height:100px;width:100px;"></div>

	<script src="${ctx}/lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
		$("[rel=tooltip]").tooltip();
		$(function() {
			$('.demo-cancel-click').click(function() {
				return false;
			});
			
		});
		function overIcon(path, icon,e) {
			var pointX = e.pageX;
			var pointY = e.pageY;
			var nobrDisply = $("#iconOver");
			nobrDisply.css("position", "absolute");
			nobrDisply.css("left", pointX + 5 + "px");
			nobrDisply.css("top", pointY  + "px");
			$("#iconOver").show();
			$("#iconOver").html("<img src="+path+icon+"></img>");
		}
		
		
		function outIcon(path, icon,e) {
			$("#iconOver").hide();
		}
	</script>

</body>
</html>


