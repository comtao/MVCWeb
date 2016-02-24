<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>${Title}-部门管理</title>
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

			<h1 class="page-title">部门管理</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="${ctx}/User/index.html">主页</a> <span
				class="divider">/</span>
			</li>
			<li class="active">部门管理</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="btn-toolbar">
					<a data-toggle="modal" id="aEditDiv" role="button" href="#addRole"
						class="btn btn-primary"><i class="icon-save"></i>添加</a>
					<!-- <button class="btn btn-primary"><i class="icon-save"></i>添加</button> -->
					<div class="btn-group"></div>
				</div>
				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>编号</th>
								<th>名称</th>
								<th>录入时间</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="dp" items="${list}">
								<tr>
									<td>${dp.id}</td>
									<td>${dp.dpName}</td>
									<td>${dp.shortItime}</td>
									<td><c:choose>
											<c:when test="${dp.status==0}">
										    	 启用
										    </c:when>
											<c:otherwise>
												<font color="red">废弃</font>
											</c:otherwise>
										</c:choose>
									</td>
									<td><a data-toggle="modal" href="#editRole" title="编辑"
										onclick="dpEdit('${dp.id}','${dp.dpName }')"><i
											class="icon-edit"></i> </a> <!--   <a href="#myModal" role="button" data-toggle="modal"><i class="icon-remove"></i></a> -->
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- <div class="pagination">
					<ul>
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
					</ul>
				</div> -->

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
				<div class="modal role hide fade" id="addRole" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel">增加部门</h3>
					</div>
					<form method="post" onsubmit="return addDpName('${ctx}');"
						class="form-horizontal" action="${ctx}/dp/addDp.html">
						<div class="modal-body">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<th width="100px">部门名*</th>
										<td width="100px"><input class="form-control" type="text"
											id="dpName" name="dpName" check="require" msg="增加部门">
											<span id="repNameInfo"></span>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
							<input id="submitForm" class="btn btn-danger" type="submit"
								value="提交">
						</div>
					</form>
				</div>

				<div class="modal role hide fade" id="editRole" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel">编辑部门</h3>
					</div>
					<form id="editRoleForm" name="form_data" method="post"
						class="form-horizontal" onsubmit="return editNameFrom('${ctx}');"
						action="${ctx}/dp/editDp.html">
						<input type="hidden" id="dpId" name="id" value="">
						<div class="modal-body">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<th width="100px">部门名*</th>
										<td width="100px"><input class="form-control" type="text"
											id="editName" name="dpName" check="require" value="">
											<span id="editNameInfo"></span>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
							<input id="submitEditForm" class="btn btn-danger" type="submit"
								value="提交">
						</div>
					</form>
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
			alterMsg('${msg}');

		});
		function alterMsg(msg) {
			if (msg && msg != '') {
				alert(msg);
			}
		}
		/**
		新增部门
		 **/
		function addDpName(path) {
			var dpName = $("#dpName").val();
			if (dpName.length < 1) {
				alert("请输入部门名称!");
				return false;
			} else {
				$.post(path+"/dp/dpRep.html", {
					repName : dpName
				}, function(data) {
					if (data == 0) {
						$("#repNameInfo").html(
								"<font    color='green'>部门名称可以使用!</font>");
						return true;
					} else {
						$("#repNameInfo").html(
								"<font    color='red'>部门名称已存在</font>");
						return false;
					}
				});
			}
			return true;
		}
		/**
		 修改部门
		 **/
		function dpEdit(id, dpName) {
			$("#dpId").val(id);
			$("#editName").val(dpName);
		}
		function editNameFrom() {
			var editName = $("#editName").val();
			if (editName.length < 1) {
				alert("请输入部门名称!");
				return false;
			} else {
				$.post(path+"/dp/dpRep.html", {
					repName : editName
				}, function(data) {
					if (data == 0) {
						$("#editNameInfo").html(
								"<font    color='green'>部门名称可以使用!</font>");
						return true;
					} else {
						$("#editNameInfo").html(
								"<font    color='red'>部门名称已存在</font>");
						return false;
					}
				});
			}

		}
	</script>

</body>
</html>


