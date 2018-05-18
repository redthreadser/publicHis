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
    
    <title>My JSP 'Register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type = "text/javascript" src = "js/jquery-1.12.4.js"></script>
	<script>
		$(document).ready(function(){
			$("#u_number").blur(function() {
			var role = $("#role").val();
			var u_number = $("#u_number").val();
			 if (role == 1) {
			 	$.ajax({
			 		"url" : "managerAction?actionMethod=selectNameById",
			 		"type" : "get",
			 		"data" : "u_number="+u_number,
			 		"dataType" : "json",
			 		"success" : function(doctor) {
						/* var json = eval("("+data+")"); */
						console.log(doctor["d_name"]);
						$("#u_name").val(doctor["d_name"]);	
						//$("#u_name").attr({disable:"disable"});		 			  
			 		}
			 	})
			 	//console.log(typeof(role));
			}
			}) 
		})
	</script>
	<style>
		li{
			list-style:none;
			margin-top:30px;
		}
	</style>
  </head>
  
  <body>
  <a href = "welcome.jsp" id = "main">回主页</a>
   		<form method = "post" action = "managerAction">
   			<ul>
   				<li>
   					<select name = "role">
   						角色 ：<c:forEach items="${roles}" var = "role">
   							<option value = "${role.id }" id = "role">${role.role_name}</option>
   						</c:forEach>
   					</select>
   				</li>
   				<li><span>用户名：</span><input type = "text" name = "u_number" id = "u_number"/></li>
   				<li><span>姓名：</span><input type = "text" name = "u_name" id = "u_name" value=""/></li>
   				<li><span>密码：</span><input type = "password" name = "password" /></li>
   				<li><input type = "submit" value = "注册"/></li>
   			</ul>
   		</form>
  </body>
</html>
