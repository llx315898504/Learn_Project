<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="taglibs.jsp"%>
<head>
<title>课程管理</title>

</head>
<h2><samp class="t03"></samp>课程管理</h2>
<ul class="ul">
<li><a href="${path}/shop/course/liststudent.jsp"><samp class="t05"></samp>新增课程</a></li>
<li><a href="${path}/shop/course/listAudit.jsp?auditStatus=0&showStatus=1"><samp class="t05"></samp>课程成绩管理</a></li>
</ul>

