<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>${Title}-修改员工信息</title>
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
            
            <h1 class="page-title">员工信息</h1>
        </div>
        
                <ul class="breadcrumb">
            <li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span></li>
            <li class="active">修改员工信息</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    
 <div class="well">
 
	<div class="col-sm-8">
		<form id="form_data" name="form_data" method="post" action="${ctx}/User/editPersonalInfo.html"   class="form-horizontal">
			<input type="hidden" name="token" value="${token}" />
			<input type="hidden" name="id" value="${personalInfo.id}" />
			<table class="table table-bordered">
				<tbody> 
				<tr>
					<th width="60px">帐号*</th>
					<td width="430px">
					<input class="form-control" value="${personalInfo.userName }" type="text" id="userName" name="userName"  >
					<span  id="userNameInfo"></span>
					</td>
					<th width="60px">密码*</th>
					<td width="450px">
					 <input value="${personalInfo.passWord }" readonly class="form-control" type="password" id="passWord" name="passWord"    >
					 <span id="passWordInfo"></span>
					</td>
				</tr>
				<tr>
					<th>姓名*</th>
					<td >
					 <input class="form-control" type="text" 
						value="${personalInfo.realName }"
					 id="realName" name="realName"  >
					 <span  id="realNameInfo"></span>
					  </td>
					<th>性别*</th>
					<td  >
					 	<select   name=sex>
					 		<c:choose>
						 		<c:when test="${personalInfo.sex==2}">
						 			<option  value="2" selected="selected"> 女</option>
						 	 		<option  value="1"> 男</option>
						 		</c:when>
						 		<c:when test="${personalInfo.sex==1}">
						 			<option  value="2" > 女</option>
						 	 		<option  value="1" selected="selected"> 男</option>
						 		</c:when>
					 		</c:choose>
					 	</select>
					 </td>
				</tr>
				<tr>
					<th>部门*</th>
					<td  >
					<div class="input-group ">
					<select name="dpId" id="dpId" class="form-control" >
						<option value="-1">选择部门</option>
						 <c:forEach  items="${dpList}" var="dpList">
						 <c:if test="${dpList.id==personalInfo.dpId }">
						 	<option value="${dpList.id}" selected="selected">${dpList.dpName}</option>
						 </c:if>
						  <c:if test="${dpList.id!=personalInfo.dpId }">
						 	<option value="${dpList.id}">${dpList.dpName}</option>
						  </c:if>
						 </c:forEach>
						</select> 
						<span  id="dpIdInfo"></span>
					</div></td>
					 <th>职位*</th>
					<td>
						<input name="post" id="post"   type="text"   class="form-control" 
						 value="${personalInfo.post}"
						 >
						<span  id="postInfo"></span>
					 </td>
				</tr>
				<tr>
				<th>角色*</th>
					<td  >
					<div class="input-group ">
					<select name="roleId" id="roleId" class="form-control" >
						<option value="-1">选择部门</option>
						 <c:forEach  items="${roleList}" var="roleList">
						 <c:if test="${roleList.id==personalInfo.roleId }">
						 	<option value="${roleList.id}" selected="selected">${roleList.roleName}</option>
						 </c:if>
						  <c:if test="${roleList.id!=personalInfo.roleId }">
						 	<option value="${roleList.id}">${roleList.roleName}</option>
						  </c:if>
						 </c:forEach>
						</select> 
						<span  id="droleIdInfo"></span>
					</div></td>
					<th>属性</th>
					<td  >
					<select class="form-control col-10" name="grade" id="grade">
						<c:choose>
						 		<c:when test="${personalInfo.grade==0}">
						 			<option value="0" selected="selected">主管</option>
									<option value="1">员工</option>
						 		</c:when>
						 		<c:when test="${personalInfo.grade==1}">
						 			<option value="0" >主管</option>
									<option value="1" selected="selected">员工</option>
						 		</c:when>
					 		</c:choose>
					</select></td>
				</tr>
				<tr>
				<th>状态</th>
					<td  colspan="3">
					<select class="form-control col-10" name="status" id="status">
						<option value="0">启用</option>
						<option value="1">禁用</option>
					</select></td>
				</tr>
			</tbody></table>
			<input  type="reset" class="btn btn-primary" value="重置">
			<input  type="submit" class="btn btn-primary" value="提交">
		</form>
	
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


