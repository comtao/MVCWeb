<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<html lang="en">
  <head>
    <title>${Title}-审批</title>
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
            <h1 class="page-title">审批</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="${ctx}/User/index.html">主页</a> <span class="divider">/</span></li>
            <li class="active">审批</li>
        </ul>
        <div class="container-fluid">
            <div class="row-fluid"> 
			<div class="btn-toolbar">
			 <button class="btn btn-primary">任务审批</button> 
		     <!-- <button class="btn">任务下派</button>  -->
		     <div class="btn-group">
		     </div>
		    </div>

           <div class="well">
					<div class="col-sm-8">
						<form class="form-horizontal" method="post" name="form_data" id="form_data" action="${ctx}/task/approvalSave.html">
							<input type="hidden" name="taskId" value="${task.id}">
							<input type="hidden" id="flag" name="flag" value="0">
							<input type="hidden" id="taskStatus" name="status" value="1" >
							<table class="table table-bordered">
								<tbody>
								  <tr>
									<th width="30%">
									  <nobr>任务标题*</nobr>
									</th>
									<td width="70%">
									  &nbsp;${task.title}
									</td>
								  </tr>
								<tr>
									<th>任务级别*</th>
									<td>
									  &nbsp;${task.gradeStr} 
									</td>
								</tr>
								<tr>
									<th>任务发起人*</th>
									<td>
									  &nbsp;${task.realName} 
									</td>
								</tr>
								<tr>
									<th>任务发起部门*</th>
									<td>
									  &nbsp;${task.dpName} 
									</td>
								</tr>
								<tr>
								</tr>
								<tr>
									<th>内容*</th>
									<td>
									 &nbsp;${task.content}
									</td>
								</tr>
								<tr>
									<th>审批意见*</th>
									<td>
									 <textarea name="comment" style="height:100px;width:700px;max-width:700px;"></textarea>
									</td>
								</tr>
								<tr>
								  <th>向同级/上级递交审批？</th>
								  <td>&nbsp;
								     <input type="checkbox"  id="isShift" onclick="approvalShift()">
								  </td>
								</tr>
								<tr id="dpIdTr" style="display:none;">
									<th>同级/上级部门*</th>
									<td colspan="2">
										<select class="form-control col-10" id="receiveDpId" name="receiveDpId" OnChange="receiveDp('${ctx}')">
											 
										</select>
									</td>
								</tr>
								<tr id="userIdTr" style="display:none;">
									<th>同级/上级人*</th>
									<td>
									 <select class="form-control col-10" id="receiveUserId" name="receiveUserId">
										
									 </select>
									</td>
								</tr>
								<tr>
								  <td colspan=2  style="text-align:center;">
								    <input class="btn btn-primary" type="button" value="驳回" onclick="refuseTask();">
								    <input class="btn btn-primary" type="submit" id="submitType" value="提交审核">
								  </td>
								</tr>
							</tbody>
							</table>
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
            approvalShift();
        });
        initDpOptions('${ctx}');
    </script>
  </body>
</html>


