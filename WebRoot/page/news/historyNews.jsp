<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<title>${Title}-历史公告</title>
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
			<h1 class="page-title">历史公告</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span>
			</li>
			<li class="active">历史公告</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<form action="${ctx}/News/historyNews.html" method="post">
					<select name="dpId">
						<option value="" selected="selected">请选择公告部门</option>
						<c:forEach var="dplist" items="${dplist}">
							<option value="${dplist.id}">${dplist.dpName}</option>
						</c:forEach>
					</select> <b> 时间:</b> <input size="14" class="btn" id="datetime"
						name="datetime" value="${datetime}" readonly="readonly" type=text
						style="background-color: white;"
						onfocus="WdatePicker({isShowWeek:true})" /> <input class="btn"
						type="submit" value="查询" />
				</form>
				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th width="10%">编号</th>
								<th width="30%">标题</th>
								<th width="20%">类别</th>
								<th width="15%">发布日期</th>
								<th width="8%">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${map.list}" var="list">
								<tr>
									<td>${list.id }</td>
									<td>${list.title }</td>
									<td>
									<c:set var="i"  value="${list.id }"  /> 
									<c:forEach items="${mapDpNameKey}" var="item">  
											<c:if test="${item.key==i}">
												<c:out value="${item.value}"/>   
											</c:if>
									</c:forEach>
									</td>
									<td>${list.shortTime}</td>
									<td><a href="${ctx}/News/seeNews.html?id=${list.id }"><i
											class="icon-search"></i>
									</a>  
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="pagination">
					<ul>
						<li><a
							href="${ctx}/News/historyNews.html?page=1&datetime=${datetime}&dpId=${map.dpId}">首
								页</a>
						</li>
						<li><a
							href="${ctx}/News/historyNews.html?page=${map.page-1 }&datetime=${datetime}&dpId=${map.dpId}">上一页</a>
						</li>
						<li><a
							href="${ctx}/News/historyNews.html?page=${map.page+1 }&datetime=${datetime}&dpId=${map.dpId}">下一页</a>
						</li>
						<li><a
							href="${ctx}/News/historyNews.html?page=${map.count }&datetime=${datetime}&dpId=${map.dpId}">尾
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
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>

</body>
</html>


