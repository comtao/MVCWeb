<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<%-- <%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%> --%>
<html lang="en">
  <head>
    <title>${Title }-任务</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" type="text/css" href="${ctx}/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/stylesheets/theme.css">
    <link rel="stylesheet" href="${ctx}/lib/font-awesome/css/font-awesome.css">
    <script src="${ctx}/lib/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="${ctx}/js/work/task.js" type="text/javascript"></script>
    <script src="${ctx}/js/common/util.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="${ctx}/ckfinder/ckfinder.js"></script>
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
           <h1 class="page-title">任务</h1>
        </div>
		<ul class="breadcrumb">
			<li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span>
			</li>
			<li class="active">发起任务</li>
		</ul>
		
		<div class="container-fluid">
            <div class="row-fluid">             
				<div class="well">
					<div class="col-sm-8">
						<form class="form-horizontal" enctype="multipart/form-data" method="post" name="form_data" id="form_data" action="${ctx}/task/addSave.html">
							<table class="table table-bordered">
								<tbody>
								  <tr>
									<th width="30%">
									  <nobr>任务标题*</nobr>
									</th>
									<td width="70%">
									  <input type="text" msg="请输入任务标题" check="require" name="title" id="taskTitle" class="form-control">
									</td>
								  </tr>
								<tr>
									<th>任务级别*</th>
									<td> 
									  <input type="radio" name="grade" value="0" checked="checked"/>一般&nbsp;&nbsp;
									  <input type="radio" name="grade" value="1"/>较急&nbsp;&nbsp;
									  <input type="radio" name="grade" value="2">加急
									</td>
								</tr>
								<tr>
									<th>受理人部门*</th>
									<td colspan="2">
										<select class="form-control col-10" id="receiveDpId" name="receiveDpId" OnChange="receiveDp('${ctx}')">
											
										</select>
									</td>
								</tr>
								<tr>
									<th>受理人*</th>
									<td>
									 <select class="form-control col-10" id="receiveUserId" name="receiveUserId">
										
									 </select>
									</td>
								</tr>
								 <tr>
									<th>附件</th>
									<td>
									  <input type="file" name="attachmentFile"/>
									</td>
								</tr> 
								<tr>
								</tr>
								<tr><th style="text-align:center;" colspan=2>内容*<font style="color:red;font-size:12px;font-weight:normal;">(*注意：编辑框中插入图片/flash不要用中文命名)</font></th></tr>
								<tr>
									<td colspan=2>
									 <div>
										<textarea name="content" class="ckeditor" cols="20"></textarea>
									</div>
									 <!-- <textarea id="content" class="" name="content" style="height:100px;width:700px;max-width:700px;"></textarea> -->
									</td>
								</tr>
								<tr>
								  <td colspan=2  style="text-align:center;">
								    <input class="btn btn-primary" type="submit" value="提交">
								  </td>
								</tr>
							</tbody>
							</table>
						</form>
						<%-- <ckeditor:replace replace="content" basePath="${ctx}/lib/ckeditor/" /> --%>
					</div>
				</div>         
            </div>
        </div>
    </div>
    <script src="${ctx}/lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function(){
            $('.demo-cancel-click').click(function(){return false;});
        });
        var basePath='${ctx}';
        initDpOptions('${ctx}');
    </script>
  </body>
</html>


