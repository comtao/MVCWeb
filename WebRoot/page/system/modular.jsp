<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<html lang="en">
  <head>
    <title>${Title}-模块管理</title>
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
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
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
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
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
            <h1 class="page-title">模块管理</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span></li>
            <li class="active">模块管理</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">             
<div class="btn-toolbar">
    <a class="btn btn-primary" href="${ctx}/menu/addMenu.html"><i class="icon-save"></i>添加</a>
  <div class="btn-group">
  </div>
</div>
<div class="well">
    <table class="table">
      <thead>
        <tr>
          <th width="5%">ID</th>
          <th width="5%">&nbsp;</th>
          <th width="10%">菜单名称</th>
          <th width="15%">菜单描述</th>
          <th width="15%">菜单路径</th>
          <th width="8%">父菜单ID</th>
          <th width="8%">是否可见</th>
          <th width="8%">排序标识</th>
          <th width="10%">操作</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="itm" items="${list}">
           <tr>
		    <td>${itm.id}</td>
               <td>
			<c:choose>
				<c:when test="${itm.parent == 0}">——</c:when>
				<c:otherwise>&nbsp;&nbsp;&nbsp;|</c:otherwise>
			</c:choose>
			</td>
               <td>${itm.name}</td>
               <td>${itm.desc}</td>
               <td>${itm.path}</td>
               <td>${itm.parent}</td>
               <td>${itm.visibleStr}</td>
               <td>${itm.orderNo}</td>
               <td>
	          <a href="${ctx}/menu/editMenu.html?id=${itm.id}" >编辑</a>
	          | <a href="javascript:void(0)" onclick="sureDel('${ctx}',${itm.id})">删除</a>
         </tr>
        </c:forEach>
      </tbody>
    </table>
</div>
<div class="pagination">
    <ul>
        <li><a href="#">上一页</a></li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">下一页</a></li>
    </ul>
</div>

<div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">删除提示</h3>
    </div>
    <div class="modal-body">
        <p class="error-text"><i class="icon-warning-sign modal-icon"></i>你确定删除该项?</p>
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
        
        function sureDel(path,menuId){
          if(confirm("确认删除这个菜单？")){
            window.location.href=path+"/menu/delMenu.html?id="+menuId;
          }
        };
    </script>
  </body>
</html>


