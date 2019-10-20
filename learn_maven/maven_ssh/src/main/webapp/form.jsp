<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  </head>
  
  <body>
   	<form action="mavenSSH/userSave" method="post">
   		姓名：<input type="text" name="user.name"><br>
   		年龄：<input type="text" name="user.age"><br>
   		地址：<input type="text" name="user.address"><br>
   		<input type="submit" value="保存">
   	</form>
  </body>
</html>
