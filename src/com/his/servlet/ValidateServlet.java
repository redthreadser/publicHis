package com.his.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.his.util.GraphicHelper;
@WebServlet(urlPatterns = "/regist.do" )
public class ValidateServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3612206906370091862L;

	@Override  
	   protected void service(HttpServletRequest request, HttpServletResponse response)  
	       throws ServletException, IOException {  
	   // 获得 当前请求 对应的 会话对象  
	   HttpSession session = request.getSession();  
	 
	   // 从请求中获得 URI ( 统一资源标识符 )  
	   String uri = request.getRequestURI();  
	   System.out.println("hello : " + uri);  
	 
	   final int width = 180; // 图片宽度  
	   final int height = 40; // 图片高度  
	   final String imgType = "jpeg"; // 指定图片格式 (不是指MIME类型)  
	   
	   // 获得可以向客户端返回图片的输出流  
	   final OutputStream output = response.getOutputStream(); 
	   System.out.println("output======" +output);
	   // (字节流)  
	   // 创建验证码图片并返回图片上的字符串  
	   String code = GraphicHelper.create(width, height, imgType, output);  
	   System.out.println("验证码内容====: " + code);  
	 
	   // 建立 uri 和 相应的 验证码 的关联 ( 存储到当前会话对象的属性中 )  
	   session.setAttribute("uri", code);  
	 
	   System.out.println("存储session中的验证码=="+session.getAttribute("uri"));  
	   }

	 
}
