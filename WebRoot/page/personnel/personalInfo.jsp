<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>${Title}-个人信息</title>
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

			<h1 class="page-title">个人信息</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span>
			</li>
			<li class="active">个人信息</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="well">

					<div class="col-sm-8">
						<form id="form_data" action="${ctx}/User/editUser.html"
							enctype="multipart/form-data" name="form_data" method="post"
							class="form-horizontal">
							<input type="hidden" name="token" value="${token}" />
							<input type="hidden" name=id  value="${personalInfo.id}" />
							<table class="table table-bordered">
								<tbody>
									<tr>
										<th><nobr> 员工编号* </nobr>
										</th>
										<td colspan="2"><b  id="id" >${personalInfo.id}</b></td>
										<td rowspan="4">
										<c:if
												test="${personalInfo.icon!=''}">
												<img class="img-thumbnail col-12" id="emp_pic"
													src="${ctx}${personalInfo.icon}" width="200px" height="166px" >
											</c:if></td>
									</tr>
									<tr>
										<th>姓名*</th>
										<td colspan="2"><b  id="name"  >${personalInfo.realName}</b></td>
									</tr>
									<tr>
										<th>性别</th>
										<td colspan="2">${personalInfo.sexString}</td>
									</tr>
									<tr>
										<th>生日</th>
										<td colspan="2"><input value="${personalInfo.birth}"
											readonly="readonly" type="date" id="birth" name="birth"
											class="input-date form-control"
											onfocus="WdatePicker({isShowWeek:true})"></td>
									</tr>
									<tr>
										<th>部门*</th>
										<td colspan="2">
											<div class="input-group ">${personalInfo.dpName}</div>
										</td>
										<td colspan="2">上传头像: <input name="iconFile"
											id="iconFile" type="file" value="上传头像">
										</td>
									</tr>
									<tr>
										<th>职位*</th>
										<td>${personalInfo.post}
										<th>Q Q</th>
										<td><input type="text" id="qq" name="qq"
											class="form-control" value="${personalInfo.qq}"></td>
									</tr>
									<tr>
										<th><nobr> 办公室电话 </nobr>
										</th>
										<td><input type="text" id="officePhone"
											name="officePhone" class="form-control"
											value="${personalInfo.officePhone}"></td>
										<th><nobr> 移动电话 </nobr>
										</th>
										<td><input type="text" id="phone" name="phone"
											class="form-control" value="${personalInfo.phone}"></td>
									</tr>
									<tr>
									</tr>
									<tr>
										<th>电子邮箱</th>
										<td colspan="3"><input type="text" id="email"
											name="email" class="form-control"
											value="${personalInfo.email}"></td>
									</tr>
								</tbody>
							</table>
							<div align="right">
								<input class="btn  btn-sm btn-primary" type="submit" value="修改">
							</div>
						</form>
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


