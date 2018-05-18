<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'pacientePayment.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/pacientePayment.css">
	<style>
		
	</style>
	<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src = "js/pacientePayment.js"></script>
  </head>
  
  <body>
  <a href = "welcome.jsp" id = "main">回主页</a>
    <div class="wrapper">
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
    	<div class="payment">
	    	<h1 align="center" class="title">缴费系统</h1>
	  		<div align="center" class="user">登录人：${u_name}</div>
	  		<form method = "post" action="registerAction">
		    	<div class="selectP">
		    		<div class="p_number"><span>病例号：</span><input id="pNumber" type="text" name="p_number"></div>
		    	</div>
		    	<div class="pacienteMess">
					<div class="p_name"><span>姓名：</span><span id="p_name"></span></div>
					<div class="p_sex"><span>性别：</span><span id="p_sex"></span></div>
					<div class="p_age"><span>年龄：</span><span id="p_age"></span></div>
					<div class="p_tel"><span>电话：</span><span id="p_tel"></span></div>
		    	</div>
			  	<div class="extraMoney"><span>卡余额:</span><input id="extraMoney" type="text" name="extraMoney"></div>
			  	<input type="hidden" name="actionMethod" value="payment">
			  	<div class="sub"><input type="submit" value="存入"></div>
			  </form>
	    	</div>
	    	<input class="success" type="hidden" name="actionMethod" value="${succession }">
    	</div>
  </body>
</html>
