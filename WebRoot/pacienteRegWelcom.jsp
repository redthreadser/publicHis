<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'pacienteRegWelcom.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/PacienteRegWelcome.css">

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

  	</div>
  </body>
</html>
