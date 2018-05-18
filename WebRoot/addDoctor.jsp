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
    
    <title>My JSP 'addDoctor.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		li{
			list-style:none;
			margin-top:30px;
		}
	</style>
  </head>
  
  <body>
  <h1>这是一个master分支</h1>

  	<h3>医生信息</h3>
  	<a href = "welcome.jsp" id = "main">回主页</a>
   		<form method = "post" action = "managerAction">
   			<ul>
   				<li><span>姓名：</span><input type = "text" name = "d_name"/></li>
   				<li><span>年龄：</span><input type = "text" name = "d_age"/></li>
   				<li>
   				<span>性别：</span>
	   				<input type = "radio" name = "sex" value = "male"/>男
	   				<input type = "radio" name = "sex" value = "famale"/>女
				</li>
				<li>
					<span>部门：</span>
					<select name = "department_id">
						<c:forEach items="${departments }" var="department">
							<option value = "${department.id }">${department.department_name }</option>
						</c:forEach>
						
					</select>
				</li>
   				<li><input type = "submit" value = "提交"/></li>
   				<li><input type = "hidden" name = "actionMethod" value = "add_doctor_info"/></li>
   				
   			</ul>
   		</form>
  </body>
</html>
