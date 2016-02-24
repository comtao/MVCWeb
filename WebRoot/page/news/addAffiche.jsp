<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<title>${Title}-发布公告</title>
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
<script src="${ctx}/js/news/news.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${ctx}/ckfinder/ckfinder.js"></script>

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

			<h1 class="page-title">公告</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span></li>
			<li class="active">发布公告</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
							
				<div class="well">
					<br><br>
					<div class="col-sm-8">
						<form   method="post" action="${ctx}/News/addAffiche.html"
							class="form-horizontal"   onsubmit="return  addAffiche();">
							<!-- <input type="hidden" id="save_name" name="save_name"> 
							<input type="hidden" name="id" id="id"> 
							<input type="hidden" name="pic" id="pic"> 
							<input type="hidden" name="opmode" id="opmode" value="edit"> -->
							<table class="table table-bordered">
								<tbody>
									<tr>
										<th width="8%">标题*</th>
										<td width="92%"><input class="form-control" type="text" id="title"
											name="title" check="require" msg="请输入标题">
											</td>
									</tr>
									<tr>
										<th>部门*</th>
										<td>
											<div class="input-group "  >
												<c:if test="${not empty dPlist}">
													<c:forEach  var="dpList"  items="${dPlist}">
														<c:if test="${dpList.id==0}">
															<input  name="dpvalueId" checked="checked" style="width: 20px;height: 20px;"  type="checkbox" value="${dpList.id}"/>全公司<br>
														</c:if>
														<c:if test="${dpList.id!=0}">
															<input  name="dpvalueId" style="width: 20px;height: 20px;"  type="checkbox" value="${dpList.id}"/>${dpList.dpName}
														</c:if>
													</c:forEach>
													<br>
													<a href="javascript:void(0);" onclick="selectAll();">全选</a>&nbsp;|&nbsp;<a href="javascript:void(0);" onclick="inverse();">反选</a>&nbsp;|&nbsp;<a href="javascript:void(0);" onclick="cancel();">取消</a> 
												</c:if>
												<c:if test="${not empty dp}">
												  <input name="dpvalueId" checked="checked"  style="width: 20px;height: 20px;"  type="checkbox" value="${dp.id}"/>${dp.dpName}
												</c:if>
											</div>
										</td>
									</tr>
									<tr>
										<th>状态</th>
										<td><select class="form-control col-10" name="status" id="status">
												<option value="0">启用</option>
												<option value="1">禁用</option>
										</select>
										</td>
									</tr>
									<tr>
									  <td colspan="7" style="text-align:center;">
									           内容<font style="color:red;font-size:12px;font-weight:normal;">(*注意：编辑框中插入图片/flash不要用中文命名)</font>
									  </td>
									</tr>
									<tr>
										<td  colspan="7">
										<div>
										 <textarea name="content" class="ckeditor" cols="20"></textarea>
									    </div>
										</td>
									</tr>
								</tbody>
							</table>
							<input type="reset" class="btn" value="重置"> 
				    		<input type="submit" class="btn btn-primary" style="float: right;" value="发布">
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>


		<script src="${ctx}/lib/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript">
		var basePath='${ctx}';
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
            alterMsg('${msg}');
        });
    </script>
</body>
</html>


