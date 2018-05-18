package com.his.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.his.dao.CostDao;
import com.his.dao.DoctorDao;
import com.his.daoMySqlImpl.CostDaoMySqlImpl;
import com.his.daoMySqlImpl.DoctorDaoMySqlImpl;
import com.his.entity.Cost;
import com.his.entity.Department;
import com.his.entity.Doctor;
import com.his.entity.Paciente;
import com.his.entity.Project;
import com.his.service.CostService;
import com.his.service.ManagerService;
import com.his.service.PacienteService;
import com.his.serviceMySqlImpl.CostServiceMySqlImpl;
import com.his.serviceMySqlImpl.ManagerServiceMySqlImpl;
import com.his.serviceMySqlImpl.PacienteServiceMySqlImpl;

public class PacienteRegServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);

	}
	PacienteService pacienteService = new PacienteServiceMySqlImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String status = req.getParameter("actionMethod");
		if("registion".equals(status)){
			doPacienteReg(req,resp);		//门诊挂号
		}else if("showPacienteReg".equals(status)){
			doShowPacienteReg(req,resp);  	//进入挂号页面
		}else if("showDepDoctor".equals(status)){	//显示科室医生
			doShowDepDoctor(req,resp);
		}else if("payment".equals(status)){	//缴费
			System.out.println("!!!!!!!");
			doPacoentePayment(req,resp);
		}else if("showPaciente".equals(status)){	//显示患者信息
			doShowPaciente(req,resp);
		}else if("showDoctorCount".equals(status)){	 //查询医生患者人数
			doShowDoctorCount(req,resp);
		}else if("showCost".equals(status)){	//查询消费明细
			doShowCost(req,resp);
		}else if("clearCount".equals(status)){
			System.out.println("doClearDocCount!!!!!!!");
			doClearDocCount(req,resp);
		}
	}
	/**
	 * 医生患者人数清空
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void doClearDocCount(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		System.out.println("doClearDocCount!!!!!!!!!se");
		int result = pacienteService.clearDocCount();
		resp.getWriter().print(JSON.toJSON(result));	
	}
	/**
	 * 查询消费明细
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void doShowCost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("doShowCost!!!!!!!!!se");
		String p_number = req.getParameter("p_number");
		List<Cost> cost = new ArrayList<Cost>();
		CostDao costDao = new CostDaoMySqlImpl();
		cost = costDao.doShowCost(p_number);
		resp.getWriter().print(JSON.toJSON(cost));
	}
	/**
	 * 查询医生患者人数
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void doShowDoctorCount(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String doctor_id = (String)req.getParameter("doctor_id");
		PacienteService pacienteService = new PacienteServiceMySqlImpl();
		int count = pacienteService.doShowDocCount(doctor_id);
		resp.getWriter().print(JSON.toJSON(count));
	}
	/**
	 * 消费明细写入
	 * @param req
	 * @param resp
	 */
	private void doInsertCost(HttpServletRequest req, HttpServletResponse resp) {
		String p_number = (String)req.getSession().getAttribute("p_number");
		String option_name = (String)req.getSession().getAttribute("option_name");
		
		CostService costService = new CostServiceMySqlImpl();
		Project project = new Project();
		Cost cost = new Cost();
		project = costService.doShowProjet(1);  //显示消费项目
		cost.setP_number(p_number);
		cost.setCost_type(1);
		cost.setProject_name(project.getProject_name());
		cost.setPr_price(project.getPr_price());
		cost.setOption_name(option_name); 	//插入消费记录对象
		costService.doInsertCost(cost);	//消费记录插入
	}
	/**
	 * 显示患者信息
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void doShowPaciente(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Payent-Welcome");
		String p_number = req.getParameter("p_number");
		DoctorDao doctorDao = new DoctorDaoMySqlImpl();
		Paciente p = new Paciente();	//患者对象
		p = doctorDao.selectPacienteByid(p_number);
		resp.getWriter().print(JSON.toJSON(p));
	}
	/**
	 * 缴费
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doPacoentePayment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("DoPayment-Welcome");
		String p_number = req.getParameter("p_number");
		String insertMoney = req.getParameter("extraMoney");
		double insert_money = Double.parseDouble(insertMoney);
		double extraMoney = pacienteService.doShowExtraMoney(p_number, -insert_money);
		int result = pacienteService.doPayment(extraMoney, p_number);
		if(result > 0){
			req.setAttribute("succession", "您已挂号成功！");
			req.getRequestDispatcher("pacientePayment.jsp").forward(req, resp);				
		}
	}

	/**
	 * 显示部门下医生
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void doShowDepDoctor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Doctor-Welcome");
		String departamentID = req.getParameter("department_id");	//科室id
		System.out.println(departamentID);
		int departament_id = Integer.parseInt(departamentID);
		List<Doctor> doctors = new ArrayList<Doctor>();
		doctors = pacienteService.doShowDepDoctor(departament_id);
		resp.getWriter().print(JSON.toJSON(doctors));
	}

	/**
	 * 进入挂号页面
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doShowPacienteReg(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("PacientReg-Welcome");
		//拿到所有科室
		ManagerService managerService = new ManagerServiceMySqlImpl();
		List<Department> departments = new ArrayList<Department>();
		departments = managerService.getAllDepartment();
		//拿到所有医生
//		List<Doctor> doctors = new ArrayList<Doctor>(); 
//		doctors = pacienteService.doShowDoctor();
		req.getSession().setAttribute("departments", departments);
//		req.getSession().setAttribute("doctors", doctors);
		if(null != departments){
			req.getRequestDispatcher("pacienteReg.jsp").forward(req, resp);
		}
	}
	/**
	 * 挂号
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doPacienteReg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p_number = req.getParameter("p_number");
//		String p_name = req.getParameter("p_name");
//		String p_sex = req.getParameter("p_sex");
//		String p_age = req.getParameter("p_age");
		String p_tel = req.getParameter("p_tel");
//		String extraMoney = req.getParameter("extraMoney");
//		String department_id = req.getParameter("department");
		String doctor_id = req.getParameter("doctor");
		String option_name = req.getParameter("option_name");
		System.out.println("registing"+p_tel);
		/**
		 * 传入患者数据放入对象中
		 */
		Paciente paciente = new Paciente();
		paciente.setP_number(req.getParameter("p_number"));
		paciente.setP_name(req.getParameter("p_name"));
		paciente.setP_sex(req.getParameter("p_sex"));
		paciente.setP_age(Integer.parseInt(req.getParameter("p_age")));
		paciente.setP_tel(p_tel);
		paciente.setExtraMoney(0);
		paciente.setDepartment_id(Integer.parseInt(req.getParameter("department")));
		paciente.setDoctor_id(req.getParameter("doctor"));
		paciente.setOption_name(req.getParameter("option_name"));
		
		int count = pacienteService.doShowDocCount(doctor_id);	//医生挂号人数
		if(count < 50){		//医生挂号人数50人上限
			count ++;
			int resultCount = pacienteService.doDocCount(doctor_id, count);//更新医生表患者人数
			if(resultCount > 0){
				req.getSession().setAttribute("p_number", p_number);
				req.getSession().setAttribute("option_name", option_name);
				doInsertCost(req,resp);	//插入消费记录
				int result = pacienteService.doPacienteReg(paciente);//挂号
				if(result > 0){
					req.setAttribute("success", "您已挂号成功！");
					req.getRequestDispatcher("pacienteReg.jsp").forward(req, resp);				
				}
			}
		}
	
	}
	

}
