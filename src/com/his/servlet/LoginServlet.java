package com.his.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.his.entity.Doctor;
import com.his.entity.Illness_type;
import com.his.entity.Paciente;
import com.his.entity.User;
import com.his.service.DoctorService;
import com.his.service.ExamineService;
import com.his.service.ManagerService;
import com.his.serviceMySqlImpl.DoctorServiceMySqlImpl;
import com.his.serviceMySqlImpl.ExamineServiceMySqlImpl;
import com.his.serviceMySqlImpl.ManagerServiceMySqlImpl;

public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("进入登录");
		ManagerService managerService = new ManagerServiceMySqlImpl();
		String path = "";
		String loing_err = "";
		String verifyCode = req.getParameter("verifyCode");
		String uri = (String)req.getSession().getAttribute("uri");
//		if (!uri.equals(verifyCode)) {
//			loing_err = "验证码不正确";
//			path = "login.jsp";
//		} else {
			
			int book = Integer.parseInt(req.getParameter("book"));
			String u_number = req.getParameter("u_number"); 
			String password = req.getParameter("password"); 
			User user = new User();
			user.setU_number(u_number);
			user.setPassword(password);
			User newUser = managerService.login(user);
			System.out.println("newUser = " +newUser.getU_name());
			
			if (newUser.getU_name() != null) {
				ExamineService examineService = new ExamineServiceMySqlImpl();
				req.getSession().setAttribute("u_name", newUser.getU_name()); //登录人的姓名
				System.out.println("u_name=" +newUser.getU_name());
				req.getSession().setAttribute("u_number", u_number); //登录人的id
				switch (book) {
				case 1:
					path = "ManagerNav.jsp";
					break;
				case 2:
					path = "pacienteRegWelcom.jsp";
					break;
				case 3:
					Doctor doctor = managerService.SelecNameById(u_number);
					//部门名称
					req.getSession().setAttribute("dapartment", doctor.getDepartament_name());
					//部门编号
					req.getSession().setAttribute("departament_id", doctor.getDepartament_id());
					
					//将所有等待的病人显示
					DoctorService doctorService = new DoctorServiceMySqlImpl();
					System.out.println("登录人的id" +u_number);
					List<Paciente> pacientes = doctorService.selectPacienteByDoctor_id(u_number);
					System.out.println("等待病人的人数"+pacientes.size());
					if (pacientes.size() > 0) {
						req.setAttribute("pacientes", pacientes);
					}
					
					//显示所有疾病的类型
					List<Illness_type> itypes = doctorService.getAllIllness(doctor.getDepartament_id());
					System.out.println("疾病的类型：" +itypes.size());
					if (itypes.size() > 0) {
						req.setAttribute("itypes", itypes);
						
					}
					path = "AllPaciente.jsp";
					break;
				case 4:
					//显示所有的患者信息
					
					List<Paciente> pacs = examineService.selectAllExPaciente(3);
					System.out.println("1.查询所有的患者人数："+pacs.size());
					if (pacs.size() > 0) {
						req.setAttribute("pacs", pacs);
					}
					System.out.println("2.跳转至检查页面");
					path = "examine_detail.jsp";
					break;
				case 5:
					//显示所有的患者信息
					List<Paciente> pacts = examineService.selectAllExPaciente(2);
					System.out.println("1.查询所有的患者人数："+pacts.size());
					if (pacts.size() > 0) {
						req.setAttribute("pacts", pacts);
					}
					System.out.println("2.跳转至检查页面");
					path = "PostDrugs.jsp";
					break;
				}	
//			} else {
//				path = "login.jsp";
//				loing_err = "用户名或者密码不正确";
//			}
		}
		req.setAttribute("loing_err", loing_err);
		req.getRequestDispatcher(path).forward(req, resp);
	}

}
