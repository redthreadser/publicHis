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
    
    <title>医生诊断</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/AllPaciente.css">
	<script type = "text/javascript" src = "js/jquery-1.12.4.js"></script>
	<script src = "js/AllPaciente.js" charset="gb2313"></script>
  </head>
  <body>
  <div id="wrapper">
   <a href = "welcome.jsp" id = "main">回主页</a>
  <a href = "welcome.jsp" id = "main">回主页</a>
  	<div id="left">
		<p>部门：${dapartment } <span>医生: ${u_name}</span> </p>
		<ul id = "nav">
			<li id = "first">
				<span>目前在等待的所有患者</span>
			   	<table border="1">
			   		<tr>
			   			<td>编号</td><td>姓名</td><td>挂号时间</td>
			   		</tr>
			   	 	<c:forEach items="${pacientes }" var="p"> 
			   			<tr>
			   				<td class = "p_number" style="cursor:pointer;">${p.p_number }</td>
			   				<td>${p.p_name}</td>
			   				<td>${p.registo_time}</td>
			   			</tr>
			   		</c:forEach>	
			   	</table>
			  </`li>
		</ul>
  	</div>
 
  
  <!--右边电子病历  -->
   	<div id = "right">
   		<h2>电子病历</h2>
   		<div id = "p_info">
   			<div class="p_number">
	   			<span>病例号：</span><input type="text" name="p_number" id = "p_number"> 
	   			<span>姓名：</span><input type="text" name="p_name" id = "p_name">
   			</div>
	  		<div class="p_sex">
	  			<span>性别&nbsp;&nbsp;&nbsp;：</span><input type="text" name="p_sex" id = "p_sex">
	  			<span>年龄：</span><input type="text" name="p_age" id = "p_age">
	  		</div>
	  		<div class="p_tel">
	  			<span>电话&nbsp;&nbsp;&nbsp;：</span><input type="text" name="p_tel" id = "p_tel">
	  		</div>
   		</div>
   		<div id = "record">
   			<ul>
   				<li>
   					<span>患者主诉：</span>
   					<textarea rows="" cols="60" name = "introduce" id = "introduce"></textarea>
   				</li>
   				<li>
   					<span>病因：</span>
   					<select name = "illness" id = "illness">
   						<c:forEach items="${itypes }" var = "itp">
   							<option value = "${itp.id}" >${itp.type_name }</option>
   						</c:forEach>
   					</select>
   				</li>
   				<li>
   					<span>详细情况：</span>
   					<textarea rows="" cols="60" id = "detail"></textarea>
   				</li>
   				<li>
   					<span>医生建议：</span>
   					<textarea rows="" cols="60" id = "agree"></textarea>
   				</li>
   				<li>
   					<input type = "button" class = "sub" id = "submit" value = "保存"/>
   					<button class="sub" id = "update">修改</button>
   				</li>
   			</ul>
   		</div>
   		<!--底部导航  -->
   		<div id = "button_content">
   			 <button id = "drug"  class="butt">药品</button>
   			 <button id = "examine" class="butt">检查</button> 
   			 <button id = "examine" class="butt">检查结果</button>
   			<div>
		   		<div id = "drug" class = "b_content">
		   		<button id = "showAllDrug" >查看</button>
		   			<table border="1" id = "table">
		   				<tr>
		   					<td class = "no">编号</td> <td class = "drug_name_1">药品名称</td> 
		   					<td class = "price" >单价(元)</td> <td>规格</td> <td>厂商</td> 
		   					<td >数量</td> <td>总价</td> <td>操作</td>
		   				</tr>
		   				<tr>
		   					<td class = "no">搜索</td> 
		   					<td>
		   						<input type = "text" class = "drug_name_1" id = "first_d" />
		   						<div class = "content"></div>
		   					</td> 
		   					<td class = "price"><input type = "text" class = "price_1"  disabled/></td> 
		   					<td><input type = "text" class = "model_1" style="width:70px;" disabled /></td> 
		   					<td><input type = "text" class = "adress_1" style="width:170px;" disabled/></td>
		   					<td><input type = "text" class = "number_1" id = "first_num" value = 1 ></td> 
		   					<td><input type = "text" class = "sum_1" disabled/></td>
		   					<td><input type = "button" value = "保存" id = "dr_btn"/></td>
		   				</tr>
		   			</table>
		   		 	<table border=1 id = "d_drug"></table> 
		   		</div>
		   		<!--开具检查单  -->
		   		<div id = "ex_card" class = "b_content" style = "display:none">
		   			<button id = "ex_butn"  >查看</button>
		   			<table border=1 id = "ex_table">
		   				<tr>
		   					<td>编号</td> 
		   					<td>检查项目</td> 
		   					<td width=50>价格</td>
		   					<td >操作</td>
		   			    </tr>
		   				<tr>
		   					<td>搜索检查项目</td> 
		   					<td>
		   						<input type = "text" id = "ex_name" />
		   						<div class = "content_2"></div>
		   					</td> 
		   					<td><input type = "text" id = "ex_price" disabled/></td> 
		   					<td><input type = "button" value = "保存" id = "ex_btn"/></td>
		   				</tr>
		   			</table>
		   			<table border=1 id = "ex_detail"></table>
		   		</div>
		   		<div id = "examine_result" class = "b_content" style = "display:none">
		   			<h4>检查结果</h4>	
		   			<table id = "show_ex_result" border=1>
		   				<tr><td>编号</td> <td>检查项目</td> <td>结果</td> <td>状态</td></tr>
		   			</table>
		   		</div>
			</div>
   		</div> 
   	</div>
   	</div>
  </body>
</html >
