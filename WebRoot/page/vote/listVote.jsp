<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<title>${Title}-投票列表</title>
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

			<h1 class="page-title">投票列表</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span>
			</li>
			<li class="active">投票列表</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
				<%-- <form  method="post" action="${ctx}/Vote/voteList.html">
					编号:<input name="id" value="0" style="width: 100px;" type="number">
					姓名:<input name=realName style="width: 100px;"> 
					开始日期:<input name=lTime style="width: 100px;" value="${lTime}"  readonly="readonly" type=text    onfocus="WdatePicker({isShowWeek:true})"  >
					部门:<input name=dpName style="width: 100px;">
					角色:<input name=roleName style="width: 100px;">
					<input value="搜索"  type="submit"  class="btn">
				</form> --%>
				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>投票单编号</th>
								<th>标题</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<th>添加时间</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${VoteList}">
								<tr>
									<td>${list.vid }</td>
									<td>${list.title}</td>
									<td>${list.start_time}</td>
									<td>${list.end_time}</td>
									<td>${list.itime}</td>
									<td>
										<c:if test="${list.status==0}"><font  color="green">投票进行中</font></c:if>
										<c:if test="${list.status==1}"><font  color="red">已停止投票</font></c:if>
									</td>
									<td><a href="${ctx}/Vote/vote.html?id=${list.vid }&optionsId=${list.id }"><i
											class="icon-columns"></i>
										</a>  
										<%-- <a href="${ctx}/Vote/editSeeVote.html?id=${list.vid}"><i
											class="icon-edit"></i>
										</a> 
										<a href="${ctx}/Vote/removeVote.html?id=${list.vid}"><i
											class="icon-remove"></i>
										</a>  --%>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
	<div class="pagination">
		<%--  <ul>
	        <li ><a href="${ctx}/Vote/listVote.html?pageNum=1&lTime=${lTime}&id=${dpName}&roleName=${roleName}">首  页</a></li>
	        <li><a href="${ctx}/Vote/listVote.html?pageNum=${map.page-1 }&id=${dpName}&roleName=${roleName}">上一页</a></li>
	        <li><a href="${ctx}/Vote/listVote.html?pageNum=${map.page+1 }&id=${dpName}&roleName=${roleName}">下一页</a></li>
	        <li><a href="${ctx}/Vote/listVote.html?pageNum=${map.count }&id=${dpName}&roleName=${roleName}">尾 页</a></li>
	        <li><a>共${map.sum}条 当前${map.page }页/${map.count }页</a></li>
	    </ul> --%>
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



	<script src="${ctx}/lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>

</body>
</html>


