<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'pacienteReg.jsp' starting pp_age</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my pp_age">
  	<link rel="stylesheet" type="text/css" href="css/pacientReg.css">
    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src = "js/pacienteReg.js"></script>
  </head>
  
  <body>
  <div class="wrapper">
  <a href = "welcome.jsp" id = "main">回主页</a>
   	<div class="RegWelcom">
	  	<a href="registerAction?actionMethod=showPacienteReg">
	  		<img src="images/1.png"><span>门诊挂号</span>
	  	</a>
 		<a href="pacientePayment.jsp">
 			<img src="images/3.png"><span>账号缴费</span>
 		</a>	
 		<a href="costShow.jsp">
  				<img src="images/5.png"><span>消费明细查询</span>
  		</a>
 	</div>
  	<div class="register">
  		<h1 align="center" class="title">挂号系统</h1>
  		<div align="center" class="user">登录人：${u_name}</div>
  		<form method = "post" action="registerAction">
	  		<div class="normal">
	  			<div class="p_number"><span>病例号：</span><input type="text" name="p_number"></div>
	  			<div class="p_name"><span>姓名：</span><input type="text" name="p_name"></div>
	  			<div class="p_sex">
	  				<span>性别：</span>
	  				<select name="p_sex">
						<option value = "male">男</option>
						<option value = "female">女</option>
					</select>
	  			</div>
	  			<div class="p_age"><span>年龄：</span><input type="text" name="p_age"></div>
	  			<div class="p_tel"><span>电话：</span><input type="text" name="p_tel"></div>
	  		</div>
	  		<div class="more">
	  			<div class="departments">
	  				<span>科室：</span>
	  				<select name="department" id="department">
						<c:forEach items="${departments }" var="department">
							<option id="tab" value = "${department.id }">${department.department_name }</option>
						</c:forEach>	  				
					</select>
	  			</div>
	  			<div class="doctors">
	  				<span>医生：</span>
	  				<select name="doctor" id="doctor">
	  					
	  				</select>
	  			</div>
	  			<span class="count"></span>
	  		</div>
	  		<input type="hidden" name="option_name" value="${u_name}">
	  		<input type="hidden" name="actionMethod" value="registion">
	  		<div class="sub"><input id="submit" type="submit" value="挂号"></div>
  		</form>
  	</div>
  	<input class="success" type="hidden" name="actionMethod" value="${success }">
  </div>    
  </body>
</html>
