<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%@ page import="java.util.List,com.cn.model.Menu,com.cn.model.Role"%>	
<% 
	List<Menu> pm = (List<Menu>)request.getAttribute("pm");
	Role role = (Role)request.getAttribute("role");
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <title>${Title}-权限设置</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" type="text/css" href="${ctx}/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/stylesheets/theme.css">
    <link rel="stylesheet" href="${ctx}/lib/font-awesome/css/font-awesome.css">
    <script src="${ctx}/lib/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
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
            <h1 class="page-title">权限设置</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span></li>
            <li class="active">权限设置</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid">     
<div class="well">
	<div class="col-sm-8">
		<form id="form_data"  action="${ctx}/role/setPer.html" name="form_data" method="post" class="form-horizontal">
		    <input type="hidden" name="id" value="${role.id}"/>
			<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
			  <%for(Menu menu:pm){ %>
	    		 <tr>
	    		 	<td>+ ---- <input type="checkbox" id="<%=menu.getId() %>" value="<%=menu.getId() %>" name="rmId" />&nbsp;&nbsp;<%=menu.getName() %></td>
	    		 </tr>
	    		 	<%for(Menu sunMenu:menu.getSun()){%>
	    		 		<tr>
					    	<td>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| <input type="checkbox" id="<%=sunMenu.getId() %>" value="<%=sunMenu.getId() %>" name="rmId"/>&nbsp;&nbsp;<%=sunMenu.getName() %><% if(!"".equals(sunMenu.getDesc().trim())){ %><font color="blue">(<%=sunMenu.getDesc() %>)</font><%} %></td>
					    </tr>
	    		 <%    } 
				    }
				 %>
				 <tr><td>&nbsp;</td></tr>
			</table>
			<div align="left">
				<input  class="btn  btn-sm btn-primary"  type="submit"  value="提交" >
			</div>
		</form>
	</div>
</div>           
            </div>
        </div>
    </div>
    <script src="${ctx}/lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
		<% for(int i:role.getRmId()){ %>
		  	var mid = <%=i%>
		  	document.getElementById(mid).checked = true ;
		<%}%>
    
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
  </body>
</html>


