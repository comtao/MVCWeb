<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>${Title}-错误提示</title>

    <link rel="stylesheet" type="text/css" href="${ctx}/lib/bootstrap/css/bootstrap.css"> 
    <link rel="stylesheet" type="text/css" href="${ctx}/stylesheets/theme.css">
    <link rel="stylesheet" href="${ctx}/lib/font-awesome/css/font-awesome.css">
    <script src="${ctx}/lib/jquery-1.7.2.min.js" type="text/javascript"></script>
    <!-- Demo page code -->
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
    </style>
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
</head>
 <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 http-error"> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 http-error"> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 http-error"> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  <body style="border-left:3px solid #999999;min-height:800px;"> 
  <!--<![endif]-->
   <div class="row-fluid http-error" style="margin-top:0px;">
    <div class="http-error" style="margin-top:10em;">
        <h1 style="color: red;">${msg}</h1>
        <p class="info">系统提示!</p>
        <p><a href="${ctx}/User/index.html"><i class="icon-home"></i></a></p>
    </div>
   </div>
	<!-- <script type="text/javascript"> 
	var pgo=0;
      function JumpUrl(){
        if(pgo==0){ location='javascript:history.go(-1);'; pgo=1; }
      }
	setTimeout('JumpUrl()',3000);
	</script> -->
</body>
</html>
