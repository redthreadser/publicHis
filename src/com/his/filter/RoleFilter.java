package com.his.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.his.dao.ManagerDao;
import com.his.daoMySqlImpl.ManagerDaoMySqlImpl;

public class RoleFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest req =  (HttpServletRequest)arg0;
		String u_number = req.getParameter("u_number");
		int book = Integer.parseInt(req.getParameter("book"));
		ManagerDao managerDao = new ManagerDaoMySqlImpl();
		int role = managerDao.getRoleByUid(u_number);
		if (book == role) {
			arg2.doFilter(arg0, arg1);
		} else {
			req.setAttribute("role_err", "你不是对应版块的，没有权限进入");
			req.getRequestDispatcher("welcome.jsp").forward(arg0, arg1);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
