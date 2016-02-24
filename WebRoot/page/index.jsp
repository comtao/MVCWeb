<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<title>${Title}</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" type="text/css" href="${ctx}/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${ctx}/stylesheets/theme.css">
<link rel="stylesheet" href="${ctx}/lib/font-awesome/css/font-awesome.css">
<script src="${ctx}/lib/jquery-1.7.2.min.js" type="text/javascript"></script>
<!-- Demo page code -->

<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;}
.brand {
	font-family: georgia, serif;}
.brand .first {
	color: #ccc;
	font-style: italic;}
.brand .second {
	color: #fff;
	font-weight: bold;}
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
			<h1 class="page-title">主页</h1>
		</div>

		<ul class="breadcrumb">
			<li class="active">主页</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="row-fluid">
					<div class="alert alert-info">
						<button type="button" class="close" data-dismiss="alert">×</button>
						<strong>欢迎:</strong> 进入石器部落!
					</div>
					<div class="block">
						<a  class="block-heading" data-toggle="collapse">
							<b style="cursor:pointer;"  onclick="news(0,'${ctx}');">公司公告</b>&nbsp;|&nbsp;
							<b style="cursor:pointer;"  onclick="news('${user.dpId}','${ctx}');">部门公告</b>
						</a>
						<p style="margin-top: -30px;margin-right: 60px;float: right;" id="newsHref">							
							</p>
						<div id="page-title" class="block-body collapse   title">
								<h2 id="title" align="center"> </h2>
							<textarea  id="content"  style="overflow-y:auto; overflow-x:hidden;width: 520px;height: 185;">
							</textarea >
							<b id="itime" style="color: red;float: right;" ></b>&nbsp;
						</div>
						<div id="page-con" class="block-body collapse   con">
							<table class="table" style="margin-left: 60px;">
								<thead>
									<tr>
										<th>编号</th>
										<th>标题</th>
										<th>时间</th>
									</tr>
								</thead>
								<tbody id="newsList" style='cursor:pointer;'>
									
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="block span6">
						<a   class="block-heading"
							data-toggle="collapse">当前任务 
						</a>
						<p style="margin-top: -30px;margin-right: 60px;float: right;" id="taskHref">							
							</p>
						<div id="tablewidget" class="block-body collapse in">
							<table class="table">
								<thead>
									<tr>
										<th>编号</th>
										<th>标题</th>
										<th>时间</th>
									</tr>
								</thead>
								<tbody id="taskList" style='cursor:pointer;'>
									
								</tbody>
							</table>
						</div>
					</div>
					<div class="block span6">
						<a   class="block-heading"
							data-toggle="collapse">最新投票</a>
						<p style="margin-top: -30px;margin-right: 60px;float: right;" id="voteHref">							
							</p>
						<div id="widget1container" class="block-body collapse in">
							<table class="table"  >
								<thead>
									<tr>
										<th>编号</th>
										<th>标题</th>
										<th>时间</th>
									</tr>
								</thead>
								<tbody id="voteList" style='cursor:pointer;'>
									
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<!-- <div class="row-fluid">
    <div class="block span6">
        <div class="block-heading">
            <span class="block-icon pull-right">
                <a href="#" class="demo-cancel-click" rel="tooltip" title="Click to refresh"><i class="icon-refresh"></i></a>
            </span>

            <a href="#widget2container" data-toggle="collapse">History</a>
        </div>
        <div id="widget2container" class="block-body collapse in">
            <table class="table list">
              <tbody>
                  <tr>
                      <td>
                          <p><i class="icon-user"></i> Mark Otto</p>
                      </td>
                      <td>
                          <p>Amount: $1,247</p>
                      </td>
                      <td>
                          <p>Date: 7/19/2012</p>
                          <a href="#">View Transaction</a>
                      </td>
                  </tr>
                  <tr>
                      <td>
                          <p><i class="icon-user"></i> Audrey Ann</p>
                      </td>
                      <td>
                          <p>Amount: $2,793</p>
                      </td>
                      <td>
                          <p>Date: 7/12/2012</p>
                          <a href="#">View Transaction</a>
                      </td>
                  </tr>
                  <tr>
                      <td>
                          <p><i class="icon-user"></i> Mark Tompson</p>
                      </td>
                      <td>
                          <p>Amount: $2,349</p>
                      </td>
                      <td>
                          <p>Date: 3/10/2012</p>
                          <a href="#">View Transaction</a>
                      </td>
                  </tr>
                  <tr>
                      <td>
                          <p><i class="icon-user"></i> Ashley Jacobs</p>
                      </td>
                      <td>
                          <p>Amount: $1,192</p>
                      </td>
                      <td>
                          <p>Date: 1/19/2012</p>
                          <a href="#">View Transaction</a>
                      </td>
                  </tr>
                    
              </tbody>
            </table>
        </div>
    </div>
    <div class="block span6">
        <p class="block-heading">Not Collapsible</p>
        <div class="block-body">
            <h2>Built with Less</h2>
            <p>The CSS is built with Less. There is a compiled version included if you prefer plain CSS.</p>
            <p>Fava bean jícama seakale beetroot courgette shallot amaranth pea garbanzo carrot radicchio peanut leek pea sprouts arugula brussels sprout green bean. Spring onion broccoli chicory shallot winter purslane pumpkin gumbo cabbage squash beet greens lettuce celery. Gram zucchini swiss chard mustard burdock radish brussels sprout groundnut. Asparagus horseradish beet greens broccoli brussels.</p>
            <p><a class="btn btn-primary btn-large">Learn more &raquo;</a></p>
        </div>
    </div>
</div> -->
				<!-- <footer>
                        <hr>

                        Purchase a site license to remove this link from the footer: http://www.portnine.com/bootstrap-themes
                        <p class="pull-right">A <a href="http://www.portnine.com/bootstrap-themes" target="_blank">Free Bootstrap Theme</a> by <a href="http://www.portnine.com" target="_blank">Portnine</a></p>

                        <p>&copy; 2012 <a href="http://www.portnine.com" target="_blank">Portnine</a></p>
                    </footer> -->

			</div>
		</div>
	</div>



	<script src="${ctx}/lib/bootstrap/js/bootstrap.js"></script>
	<script src="${ctx}/js/personalOffice/index.js"></script>
	<script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
         news(0,'${ctx}'); 
         voteAjax('${user.dpId}','${ctx}');
         taskAjax('${ctx}');
        });
	</script>
</body>
</html>


