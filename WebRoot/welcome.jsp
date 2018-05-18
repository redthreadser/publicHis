<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎来到HIS系统v1.0</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/welcome.css">
	<c:if test="${role_err != null }">
		<script type = "text/javascript">
			alert("${role_err}");
		</script>
	</c:if>
  </head>
  
  <body>
  <div id="main">
  	<div class="logo">
  		<img src="images/logo.png" width="97px">
  	</div>
    	<ul>
    		<li class="admin">
    			<p><img src="images/admin.jpg" width="97px" height="97px"></p>
    			<a href = "login.jsp?book=1">管理员</a>
    		</li>
    		<li>
    			<p><img src="images/1.png"></p>
    			<a href = "login.jsp?book=2">挂号</a>
    		</li>
    		<li>
    			<p><img src="images/2.png"></p>
    			<a href = "login.jsp?book=3">医生诊断</a>
    		</li>
    		<li>
    			<p><img src="images/3.png"></p>
    			<a href = "login.jsp?book=4">检查</a>
    		</li>
    		<li>
    			<p><img src="images/4.png"></p>
    			<a href = "login.jsp?book=5">药房管理</a>
    		</li>
    	</ul>
    	</div>
  </body>
</html>
