<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="/menu" prefix="m" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${ctx}/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/stylesheets/theme.css">
    <link rel="stylesheet" href="${ctx}/lib/font-awesome/css/font-awesome.css">
    <script src="${ctx}/lib/jquery-1.7.2.min.js" type="text/javascript"></script>
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  </head>
  <body>
	<div class="sidebar-nav">
	   <m:menu></m:menu>
	
		<%-- 
		<a href="#dashboard-menu" class="nav-header" data-toggle="collapse">
		<i class="icon-dashboard"></i>个人办公</a>
		<ul id="dashboard-menu" class="nav nav-list collapse in">
			<li><a href="${ctx}/User/index.html" target="mainFrame">主页</a>
			</li>
			<li><a href="${ctx}/task/list.html" target="mainFrame">任务</a>
			</li>
			<li><a href="${ctx}/task/approvalList.html" target="mainFrame">审批</a>
			</li>
			<li><a href="${ctx}/News/affiche.html?dpId=${user.dpId}" target="mainFrame">公告</a>
			</li>
		</ul>
		<a href="#accounts-menu" class="nav-header" data-toggle="collapse">
			<i class="icon-briefcase"></i>系统管理
		</a>
		<ul id="accounts-menu" class="nav nav-list collapse">
			<li><a href="${ctx}/role/list.html" target="mainFrame">角色管理</a>
			</li>
			<li><a href="${ctx}/menu/list.html" target="mainFrame">模块管理</a>
			</li>
			<li><a href="${ctx}/User/rePsw.html" target="mainFrame">重置密码</a>
			</li>
		</ul>

		<a href="#legal-menu" class="nav-header" data-toggle="collapse"><i
			class="icon-legal"></i>人事档案模块</a>
		<ul id="legal-menu" class="nav nav-list collapse">
			<li><a href="${ctx}/User/personalInfo.html?id=${user.id}" target="mainFrame">个人信息</a>
			</li>
			<li><a href="${ctx}/User/userList.html" target="mainFrame">员工列表</a>
			</li>
			<li><a href="${ctx}/User/addPersonalInfo.html" target="mainFrame">新增员工</a>
			</li>
			<li><a href="${ctx}/Leave/attendance.html" target="mainFrame">员工考勤</a>
			</li> 
			<li><a href="${ctx}/Leave/leaveRecord.html" target="mainFrame">请假记录</a>
			</li> 
			<li><a href="${ctx}/Leave/leaveList.html" target="mainFrame">请假申请</a>
			</li>
			<li><a href="${ctx}/dp/dpList.html" target="mainFrame">部门管理</a>
			</li>
		</ul>
		<a href="#vote-menu" class="nav-header" data-toggle="collapse"><i
			class="icon-comment"></i>投票</a>
		<ul id="vote-menu" class="nav nav-list collapse">
			<li><a href="${ctx}/page/vote/addVote.jsp" target="mainFrame">添加投票</a>
			</li>
			<li><a href="${ctx}/Vote/listVote.html" target="mainFrame">投票列表</a>
			</li>
		</ul>
	  <a href="${ctx}/User/addressList.html" target="mainFrame" class="nav-header"><i class="icon-ok-sign"></i>通讯录</a> 
	  <a href="help.html" class="nav-header"><i class="icon-question-sign"></i>帮助</a> 
	  <a href="faq.html" class="nav-header"><i class="icon-comment"></i>常见问题解答</a> --%>
	</div>
	</body>
	<script src="${ctx}/lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
	</html>