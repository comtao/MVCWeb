<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${ctx}/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/stylesheets/theme.css">
    <link rel="stylesheet" href="${ctx}/lib/font-awesome/css/font-awesome.css">
    <script src="${ctx}/lib/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="${ctx}/lib/bootstrap/js/bootstrap.js"></script>
    <script src="${ctx}/js/common/util.js"></script>
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
    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;}
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;}
        .brand .second {
            color: #fff;
            font-weight: bold;}
         .msg-box{
           color: red; position: absolute; font-weight: bold; left: 26px; bottom: 20px;}
    </style>
  </head>
  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  <body class=""> 
  <!--<![endif]-->
 	<div class="navbar">
		<div class="navbar-inner">
			<ul class="nav pull-right">
			    <li id="msgNum-li" style="display:none;">
			      <a href="${ctx}/msg/msgList.html" target="mainFrame">
			        <i class="icon-envelope"></i>
			      </a>
			      <span id="msgNum" class="msg-box">+0</span>
			    </li>
				<li>
				 <a href="${ctx}/User/personalInfo.html" target="mainFrame"> 
					<i class="icon-user"></i>${user.realName}
				 </a>
				</li>
				<li>
				  <a href="${ctx}/logout.html" target="_top">退出</a>
				</li>
				
				
				<li>
				  <a href="${ctx}/toChat.html" target="_top">聊天</a>
				</li>
				
			</ul>
			<a class="brand">
			 <span class="first">石器</span>
			 <span class="second">部落</span>
			</a>
			<div class="stats" >
			<p class="stat">
					<span class="number" id="time"></span>
				</p>
			</div>
		</div>
	</div>
</body>
	<script>
    $(function(){
      getForMsg('${ctx}');
      $("#msgNum-li .icon-envelope").click(function(data){
         $("#msgNum-li").hide();
      });
    });
    
    function getForMsg(path){
		getAjax(path+"/msg/getMsgNum.html",{},function(data){
			if(parseInt(data.msgNum) > 0){
				$("#msgNum-li").show();
				$("#msgNum").text("+"+data.msgNum);
			}else{
				$("#msgNum-li").hide();
			}
		});	
   }
   if(parseInt('${user.id}')>0){
     setInterval(getForMsg('${ctx}'),1000*60*5);
   }
	$("[rel=tooltip]").tooltip();
    $(function() {
        $('.demo-cancel-click').click(function(){return false;});
    });
	setInterval("time.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
	</script>
	</html>