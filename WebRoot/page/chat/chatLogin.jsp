<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>进入聊天室</title>
<link rel="stylesheet" href="${ctx}/lib/bootstrap/css/bootstrap.min.css"/>
</head>
<body>
  <p class="text-warning">输入用户名进入聊天室</p>
  <form action="${ctx}/loginChat.html" method="post">
      用户名：<input type="text" name="name"/>
      <input type="submit" value="进入聊天室" class="btn btn-success"/>
  </form>
</body>
</html>
