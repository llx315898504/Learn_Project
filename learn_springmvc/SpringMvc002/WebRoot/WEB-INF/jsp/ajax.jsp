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
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#mybutton").click(function(){
			var mytext=$("#mytext").val();
			$.ajax({
				url:"test/ajax1.do",
				type:"post",
				dataType:"text",
				data:{
					name:mytext
				},
				success:function(responseText){
					alert(responseText);
				},
				error:function(){
					alert("system error");
				}
			});
		});
	});
</script>
  </head>
  <body>
  <input id="mytext" type="text">
  <input id="mybutton" type="button" value="click">
  </body>
</html>
