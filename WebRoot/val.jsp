<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'val.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	  function myRefersh( e ) {  
     
	  const source = e.src ; // 获得原来的 src 中的内容，  const定义的变量不可以修改，且必须赋初值
	  console.log( "source : " + source  ) ;  
	     
	   var index = source.indexOf( "?" ) ;  // 从 source 中寻找 ? 第一次出现的位置 (如果不存在则返回 -1 )  
	   console.log( "index : " + index  ) ;  
	     
	   if( index > -1 ) { // 如果找到了 ?  就进入内部  
	       var s = source.substring( 0 , index ) ; // 从 source 中截取 index 之前的内容 ( index 以及 index 之后的内容都被舍弃 )  
	        console.log( "新的src==== : " + s  ) ;  
	         
	       var date = new Date(); // 创建一个 Date 对象的 一个 实例  
	       var time = date.getTime() ; // 从 新创建的 Date 对象的实例中获得该时间对应毫秒值  
	       e.src = s + "?time=" + time ; // 将加了 尾巴的地址 重新放入到 src上  
	         
	       console.log( e.src ) ;  
	   } else {  
	       var date = new Date();  
	       e.src = source + "?time=" + date.getTime();  
	   }  
     
}
	</script>
	
	<style type="text/css" >  
 
       .logo-container {  
           margin-top: 50px ;  
       }  
       .logo-container img {  
           width: 100px ;  
       }  
 
       .message-container {  
           height: 80px ;  
       }  
 
       .link-container {  
           height: 40px ;  
           line-height: 40px ;  
       }  
 
       .link-container a {  
           text-decoration: none ;  
       }  
 
   </style>  
  </head>
  
  <body>
    <div class="container form-container">  
   <form action="loginAction" method="post">  
       <div class="form"> <!-- 注册表单开始 -->  
           <div class="form-row">  
              <span class="cell-1">  
                <i class="fa fa-user"></i>  
              </span>  
              <span class="cell-11" style="text-align: left;">  
                <input type="text" name="username" placeholder="请输入用户名">  
              </span>  
           </div>  
 
         <div class="form-row">  
              <span class="cell-1">  
                <i class="fa fa-key"></i>  
              </span>  
              <span class="cell-11" style="text-align: left;">  
                <input type="password" name="password" placeholder="请输入密码">  
              </span>  
         </div>  
 
         <div class="form-row">  
           <span class="cell-1">  
             <i class="fa fa-keyboard-o"></i>  
           </span>  
           <span class="cell-11" style="text-align: left;">  
             <input type="password" name="confirm" placeholder="请确认密码">  
           </span>  
         </div>  
 
           <div class="form-row">  
              <span class="cell-7">  
                <input type="text" name="verifyCode" placeholder="请输入验证码">  
              </span>  
              <span class="cell-5" style="text-align: center;">  
                <img src="regist.do" onclick="myRefersh(this)">  
              </span>  
           </div>  
 
           <div class="form-row" style="border: none;">  
             <span class="cell-6" style="text-align: left">  
                <input type="reset" value="重置">  
             </span>  
             <span class="cell-6"  style="text-align:right;">  
                <input type="submit" value="注册">  
             </span>  
           </div>  
       </div> <!-- 注册表单结束 -->  
   </form>  
</div>
  </body>
</html>
