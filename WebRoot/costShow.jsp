<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'costShow.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/costShow.css">
    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src = "js/costShow.js"></script>

  </head>
  
  <body>
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
	 	<div class="costShow">
	 	  	<h1 align="center" class="title">消费明细</h1>
		 	<div class="p_number"><span>病例号：</span><input type="text" name="p_number"></div>
		  	<div class="p_name"><span>姓名：</span><span class="name"></span></div>
			<div class="cost">
				<table border="1" class="title">
			    	<tr>
			    		<td class="project">消费项目</td>
			    		<td class="price">消费金额(元)</td>
			    		<td class="time">消费时间</td>
			    		<td class="option">操作人</td>
			    	</tr>
			    </table>
			    <table border="1" class="content"></table>
			</div>
	 	</div>
	  	
    </div>
  </body>
</html>
