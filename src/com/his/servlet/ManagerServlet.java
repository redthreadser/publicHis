package com.his.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.his.entity.Department;
import com.his.entity.Doctor;
import com.his.entity.Drug;
import com.his.entity.Role;
import com.his.entity.User;
import com.his.service.ManagerService;
import com.his.serviceMySqlImpl.ManagerServiceMySqlImpl;

public class ManagerServlet extends HttpServlet{
	ManagerService managerService = new ManagerServiceMySqlImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String status = req.getParameter("actionMethod");
		if ("register".equals(status)) {
			doRegister(req, resp);
		} else if ("select_all_role".equals(status)) {
			doSelectAllRole(req, resp);
		} else if ("select_all_department".equals(status)) {
			doSelectAllDepartment(req, resp);
		} else if ("add_doctor_info".equals(status)) {
			doAddDoctorInfo(req, resp);
		} else if ("selectNameById".equals(status)) {
			doSelecNameById(req, resp);
		} else if ("add_drug_info".equals(status)) {
			doAddDrugInfo(req, resp);
		} 
	}

	/*
	 * 添加药品信息
	 */
	private void doAddDrugInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String drug_name=req.getParameter("drug_name");
		String model=req.getParameter("model");
		String drug_product=req.getParameter("drug_product");
		double drug_price=Double.parseDouble((String)req.getParameter("drug_price"));
		int cost_type=Integer.parseInt((String)req.getParameter("cost_type"));
		String key=req.getParameter("key");
		int count=Integer.parseInt((String)req.getParameter("count"));
		System.out.println("药品名称："+drug_name+"\t药品规格："+model);
		System.out.println("药品生产厂家："+drug_product+"\t药品价格："+drug_price);
		System.out.println("消费类型："+cost_type+"\t药品简称："+key+"\t药品数量："+count);
		
		Drug drug=new Drug();
		drug.setDrug_name(drug_name);
		drug.setModel(model);
		drug.setDrug_product(drug_product);
		drug.setDrug_price(drug_price);
		drug.setCost_type(cost_type);
		drug.setKey(key);
		drug.setCount(count);
		int result = managerService.insertDrug(drug);
		String info="";
		if(result>0){
			info="1";
		}
		
		/*resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(info);*/
		resp.getWriter().print(JSON.toJSON(info));
	}

	private void doSelecNameById(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		String u_number =  req.getParameter("u_number");
		System.out.println("进入doSelecNameById  u_number=" +u_number);
		Doctor doctor = managerService.SelecNameById(u_number);
		if (doctor != null) {
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			System.out.println("姓名：" +doctor.getD_name());
			out.print(JSON.toJSON(doctor));
			//req.setAttribute("doctor", doctor);
			//req.getRequestDispatcher("Register.jsp").forward(req, resp);
		}
		
		
	}

	private void doAddDoctorInfo(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		String d_name = req.getParameter("d_name");
		int d_age = Integer.parseInt(req.getParameter("d_age"));
		String d_sex = req.getParameter("sex");
		int department_id = Integer.parseInt(req.getParameter("department_id"));
		int no = (int)(Math.random() * 100);
		
		String doctor_id = "d" + department_id +  no;
		System.out.println("进入添加医生信息doctor_id"+doctor_id);
		Doctor doctor = new Doctor();
		doctor.setD_name(d_name);
		doctor.setD_age(d_age);
		doctor.setD_sex(d_sex);
		doctor.setCount(0);
		doctor.setDepartament_id(department_id);
		doctor.setDoctor_id(doctor_id);
		int result = managerService.insertDoctor(doctor);
		if (result > 0) {
			req.getRequestDispatcher("ManagerNav.jsp").forward(req, resp);
		}
		
		
	}

	private void doSelectAllDepartment(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("进入doSelectAllDepartment");
		List<Department> departments = managerService.getAllDepartment();
		if (departments != null) {
			req.setAttribute("departments", departments);
			req.getRequestDispatcher("addDoctor.jsp").forward(req, resp);
		}
	}

	private void doSelectAllRole(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("进入doSelectAllRole");
		List<Role> roles = managerService.getAllRole();
		if (roles != null) {
			req.setAttribute("roles", roles);
			req.getRequestDispatcher("Register.jsp").forward(req, resp);
		}
		
		
	}

	private void doRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String u_number = req.getParameter("u_number");
		System.out.println("u_number="+u_number);
		String password = req.getParameter("password");
		String u_name = req.getParameter("u_name");
		int role = Integer.parseInt(req.getParameter("role"));
		//对用户的信息进行分装
		User user = new User();
		user.setU_number(u_number);
		user.setPassword(password);
		user.setU_name(u_name);
		user.setRole(role);
		int result = managerService.register(user);
		if (result > 0) {
			req.getRequestDispatcher("ManagerNav.jsp").forward(req, resp);
		}
		
		
		
	}

}
