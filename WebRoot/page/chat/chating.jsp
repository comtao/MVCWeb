<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线聊天</title>
<script type="text/javascript" src="${ctx}/lib/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" href="${ctx}/lib/bootstrap/css/bootstrap.min.css"/>
<script type="text/javascript" src="${ctx}/lib/jquery.timer.js"></script>
<script type="text/javascript" src="${ctx}/js/comet4j/comet4j.js"></script>
<script type="text/javascript">
  $(function(){
      $("#btn_send").click(function() {
          $.ajax({
              url:'${ctx}'+"/sendMsg.html",
              type:"post",
              data:{msg:$("#msg").val()},
              success:function(){
                  $("#msg").val("");
              }
          });
      });
      
      JS.Engine.on({
          user_chating_content:function(data){//侦听聊天内容
              var result = $.parseJSON(data);
              $("<p><b>"+result.date+"</b></p>").appendTo("#msgs");
              $("<p ><b style='color:red;'>"+result.user+"</b>: "+result.msg+"</p>").appendTo("#msgs");
          },
          onlineUsers: function(data){//侦听在线用户
                  var result = $.parseJSON(data);
console.log(result);
                  $("#onlineUsers option").remove();
                  $(result).each(function(){
                      $("<option>" + this.userName + ":" + this.chatState + "</option>").appendTo("#onlineUsers");
                  });

            }
      });
      JS.Engine.start('state');
      function initState(){
         $.ajax({
		     url:'${ctx}'+"/getState.html",
		     type:"post",
		     dataType:"json",
		     success:function(user) {
		         $("#uid").html(user.chatId);
		         $("#nickName").html(user.userName);
		     }
		 });
      }
      initState();
      
      /* var timer = $.timer(
          function() {
              $.ajax({
              url:'${ctx}'+"/getState.html",
              type:"get",
              dataType:"json",
              success:function(user) {
                  $("#uid").html(user.clientId);
                  $("#nickName").html(user.name);
              }
          });
          }
      );  
     timer.once(5000); */
         
  });
</script>
</head>
<body>
<div style="background-color: silver;width:650px;">
  当前用户长连接id:<span id="uid"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  当前用户昵称  :<span id="nickName"></span>
</div>
<div >
<div id="msgs" style="height: 200px; width:650px; overflow: scroll;"></div>
<select multiple="multiple" id="onlineUsers" style="width: 650px;">
</select>
<br>
<form action="${ctx}/chatOut.html">
请输入要发送的消息：<input type="text" id="msg" >
<input type="button" value="发        送    " id="btn_send" class="btn btn-success btn-large">
<input type="submit" value="离开聊天室" class="btn btn-warning btn-large">
</form>
</div>
</body>
</html>
