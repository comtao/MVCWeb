<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>${Title}-添加投票</title>
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
<script type="text/javascript" src="${ctx}/js/news/news.js"></script>

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
			<h1 class="page-title">添加投票</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span></li>
			<li class="active">添加投票</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<!-- <div class="btn-toolbar">
					<a class="btn btn-primary" href="#addLeave" role="button"
						data-toggle="modal"><i class="icon-save"></i>请假申请</a>
					<div class="btn-group"></div>
				</div> -->
				<div class="well">
					<div class="modal-header">
						<h3 id="myModalLabel">添加投票单</h3>
					</div>
					<form id="form_data" name="form_data" method="post"
						class="form-horizontal" onsubmit="return addVote();"
						action="${ctx}/Vote/add.html" enctype="multipart/form-data">
						<table class="table table-bordered">
						  <tbody>
						  <tr>
							<td width="100px;">投票标题:</td>
							<td><input class="form-control" name=title id=title type="text"></td>
						  </tr>
							<!-- 投票类型:<select  name = status >
										<option value="0">单选</option>
										<option value="0">多选</option>
									</select><br> -->
						  <tr>
							<td>投票部门:</td>
							<td> 
			                    <c:if test="${not empty dPlist}">
									<c:forEach  var="dpList"  items="${dPlist}">
										<c:if test="${dpList.id==0}">
											<input  name="dpvalueId" checked="checked" style="width: 20px;height: 20px;"  type="checkbox" value="${dpList.id}"/>${dpList.dpName}
										</c:if>
										<c:if test="${dpList.id!=0}">
											<input  name="dpvalueId" style="width: 20px;height: 20px;"  type="checkbox" value="${dpList.id}"/>${dpList.dpName}
										</c:if>
									</c:forEach>
									<br>
									<a href="javascript:void(0);" onclick="selectAll();">全选</a>&nbsp;|&nbsp;
									<a href="javascript:void(0);" onclick="inverse();">反选</a>&nbsp;|&nbsp;
									<a href="javascript:void(0);" onclick="cancel();">取消</a>
								</c:if>
								<c:if test="${not empty dp}">
									<input  name="dpvalueId" checked="checked" style="width: 20px;height: 20px;"  type="checkbox" value="${dp.id}"/>${dp.dpName}
					            </c:if>
							 </td>
						    </tr>
						    <tr>
							<td>投票选项:</td>
							<td>
							  <a href="javascript:void(0);" onclick="addOptions();"><i class="icon-plus"></i></a>
							</td>
							</tr>
							<tr>
							<td colspan=2>
							<div id=options style="margin-left:120px;">
								<div id=A ><span style="margin-right: 10px">选项A</span><input   value="A" class="form-control" name=options type="text">
								  <input type="file" value="" name=file > 
								</div>
							</div>
							</td>
							</tr>
							<tr>
							 <td> 开始时间:</td>
							 <td><input name=startTime id=startTime  style="background-color: white;"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH'})"  readonly="readonly">
                             </td>
							</tr>
							<tr>
							<td>结束时间:</td>
							<td><input name=endTime  id=endTime  style="background-color: white;"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH'})"  readonly="readonly">
                           </td>
							</tr>
						<tr>
						<td colspan=2>	
						<div class="modal-footer" style="border-top:none;">
							<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
							<input class="btn btn-danger" type="submit" value="提交">
						</div>
						</td></tr>
						</tbody>
						</table>
					</form>
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


