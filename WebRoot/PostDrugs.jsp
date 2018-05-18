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
    
    <title>发药</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/examine_detail.css">
	<script type = "text/javascript" src = "js/jquery-1.12.4.js"></script>
	<script src = "js/PostDrugs.js"></script>
  </head>
  
  <body>
  <div id="wrapper" >
  <a href = "welcome.jsp" id = "main">回主页</a>
  	<div class="allPatiente">
  	 	<h2>所有患者</h2>
  	 	<table border="1">
			<tr>
				<td>患者编号</td>
				<td>姓名</td>
				<td>开检查单时间</td>
			</tr>
			<c:forEach items="${pacts }" var="p"> 
			   	<tr>
			   		<td>${p.p_number }</td>
			   		<td class="detail" style="cursor:pointer; ">${p.p_name}</td>
			   		<td>${p.d_time}</td>
			   	</tr>
			</c:forEach>	
		</table>	
  	</div>
  	<div class="main">
    	 <h1>药品详情表</h1> 
    	 <ul class="clear">
    		<li>患者姓名：<span id="p_name"></span></li>
    		<li>年龄：<span id="p_age"></span></li>
    		<li>性别：<span id="p_sex"></span></li>
    		<li>主治医师：<span id="d_name"></span></li>
    	 </ul>
	     <div class="project_name">
		    <table border="1" id="content_1" >
		    	<tr>
		    		<td>序号</td>
		    		<td>药品名称</td>
		    		<td>数量</td>
		    		<td>总价</td>
		    		<td>状态</td>
		    		<td>操作</td>
		    	</tr>
		    </table>
		     <table border="1" id="content">
		    </table>
		  </div>
	  </div>
    </div>
  </body>
</html>
