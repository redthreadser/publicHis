<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ManagerNav.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/addDrug.css">
	<script src="js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="js/addDrug.js"></script>
  </head>
  
  <body>
    	<ul id="main">
    		<li><a href = "managerAction?actionMethod=select_all_role">增加用户</a></li>
    		<li>
	    		<p class="add" style="cursor:pointer;">添加药品信息</p>
	    		<div style="display:none;" class="info">
		    		<h1>添加药品信息</h1>
			    	<ul  class="item">
			    		<li><span>药品名称:</span><input type="text"  id="drug_name"></li>
			    		<li><span>药品规格:</span><input type="text"  id="model"></li>
			    		<li><span>药品生产厂家:</span><input type="text"  id="drug_product"></li>
			    		<li><span>药品价格:</span><input type="text"  id="drug_price"></li>
			    		<li><span>消费类型:</span><input type="text"  id="cost_type"></li>
			    		<li><span>药品简称:</span><input type="text"  id="key"></li>
			    		<li><span>药品数量:</span><input type="text"  id="count"></li>
			    		<li><button class="save">保存</button></li>
			    		<li><input type="hidden" value="add_drug_info" name="actionMethod"></li>
			    	</ul>
			    </div>
		    </li>
    		<li><a href = "javascript:void(0)">增加收费项目信息</a></li>
    		<li><a href = "managerAction?actionMethod=select_all_department">增加医生信息</a></li>
    		<li><a href = "managerAction"></a></li>
    	</ul>
  </body>
</html>
