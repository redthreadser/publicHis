package com.his.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostDrugServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String status = req.getParameter("actionMethod");
		if ("updateDrugCount".equals(status)) {
			doUpdateDrugCount(req, resp);
		}
	}

	private void doUpdateDrugCount(HttpServletRequest req,
			HttpServletResponse resp) {
		
	}

}
