<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<title>${Title}-重置密码</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" type="text/css"
	href="${ctx}/lib/bootstrap/css/bootstrap.css">

<link rel="stylesheet" type="text/css" href="${ctx}/stylesheets/theme.css">
<link rel="stylesheet" href="${ctx}/lib/font-awesome/css/font-awesome.css">

<script src="${ctx}/lib/jquery-1.7.2.min.js" type="text/javascript"></script>

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

			<h1 class="page-title">重置密码</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span>
			</li>
			<li><a href="${ctx}/User/personalInfo.html">个人信息</a> <span class="divider">/</span>
			</li>
			<li class="active">重置密码</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="well">

					<div class="tab-pane " id="profile">
						<form id="tab2" action="${ctx}/User/relpassword.html"  method="post" onsubmit="return repPassword();">
							<label>新的密码</label> 
							<input type="password" name=password  id=passWord class="input-xlarge">
							<label>再次确认</label> 
							<input type="password" id=repPassWord class="input-xlarge" onfocus="repPassword();">
							<span  id=passInfo></span>
							<br>
							<div>
								<button class="btn btn-primary">修改</button>
							</div>
						</form>
					</div>
				</div>

			</div>

		</div>
	</div>



	<script src="${ctx}/lib/bootstrap/js/bootstrap.js"></script>
	<script src="${ctx}/js/user/user.js"></script>
	<script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>

</body>
</html>


