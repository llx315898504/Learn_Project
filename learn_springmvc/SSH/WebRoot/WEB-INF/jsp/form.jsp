<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <form action="person/savePerson.do" method="post">
	   	<table align="center" width="80%">
	   		<tr>
	   			<td><label>姓名：</label><input type="text" name="name" ></td>
	   			<td><label>性别：</label>
	   				<select name="gender">
	   					<option value="">请选择</option>
	   					<option value="1">帅哥</option>
	   					<option value="0" >美女</option>
	   				</select>
	   			</td>
	   			<td><label>地址：</label><input type="text" name="address" ></td>
	   			<td><label>生日：</label><input type="text" name="birthday"></td>
	   			<td><input type="submit" value="添加"></td>
	   		</tr>
	   	</table>
   	</form>
  </body>
</html>
