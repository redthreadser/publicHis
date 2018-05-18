package com.his.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.his.entity.Drug;
import com.his.entity.Examine;
import com.his.entity.Paciente;
import com.his.entity.Project;
import com.his.entity.Record;
import com.his.service.DoctorService;
import com.his.service.PostDrugService;
import com.his.serviceMySqlImpl.DoctorServiceMySqlImpl;
import com.his.serviceMySqlImpl.PostDrugServiceMySqlImpl;

public class DoctorServlet extends HttpServlet{
	DoctorService doctorService = new DoctorServiceMySqlImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String status = req.getParameter("actionMethod");
		if ("selelctAllInfo".equals(status)) {
			doSelectAllInfo(req, resp);
		} else if ("insertRecord".equals(status)) {
			doInsertRecord(req, resp);
		} else if ("selectDrugs".equals(status)) {
			doSelectDrugs(req, resp);
		} else if ("selectDrugByDr_id".equals(status)) {
			doSelectDrugByDr_id(req, resp);
		} else if ("insertPDrug".equals(status)) {
			doInsertPDrug(req, resp);
		} else if ("selectProjects".equals(status)) {
			doSelectProjects(req, resp);
		} else if ("insertProject".equals(status)) {
			doInsertProject(req, resp);
		} else if ("selectExamineByP_number".equals(status)) {
			doSelectExamineByP_number(req, resp);
		} else if ("deleleExamine".equals(status)) {
			doDeleleExamine(req, resp);
		} else if ("updateRecord".equals(status)) {
			doUpdateRecord(req, resp);
		} else if ("delPaciente".equals(status)) {
			doDelPaciente(req, resp);
		}
	}
	private void doDelPaciente(HttpServletRequest req, HttpServletResponse resp) {
		String p_number = req.getParameter("p_number");
		
	}

	/*
	 * 更新病历
	 */
	private void doUpdateRecord(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String introduce = req.getParameter("introduce");
		String illness = req.getParameter("illness");
		String detail = req.getParameter("detail");
		String agree = req.getParameter("agree");
		String p_number = req.getParameter("p_number");
		System.out.println("更新患者病历introduce = " +introduce +"illness="+illness+"detail=" +detail+"agree=" +agree +"p_number=" +p_number);
		//封装数据
		Record record = new Record();
		record.setIntroduce(introduce);
		record.setIllness(illness);
		record.setDetail(detail);
		record.setAgree(agree);
		record.setP_number(p_number);
		String sava_mess = "";
		int result = doctorService.insertRecord(record);
		if (result > 0) {
			sava_mess = "病历更新成功";
		} else {
			sava_mess = "病历更新失败";
		}
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(sava_mess);
	}

	/*
	 * 删除检查项目
	 */
	private void doDeleleExamine(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		int ex_id = Integer.parseInt(req.getParameter("ex_id"));
		System.out.println("进入删除id=" +ex_id);
		int result = doctorService.deleteExamine(ex_id);
		String del_mess = "";
		if (result > 0) {
			del_mess = "删除成功";
		} else {
			del_mess = "未找到该项目，可能已经删除";
		}
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(del_mess);
	}

	private void doSelectExamineByP_number(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String p_number = req.getParameter("p_number");
		int cost_type = Integer.parseInt(req.getParameter("cost_type"));
		System.out.println("进入查询该患者所有药品cost_type=" +cost_type +"p_number="+p_number);
		List<Examine> examines = doctorService.selectExamineByP_number(p_number, cost_type);
		System.out.println("examines的数量是：" +examines.size());
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSON(examines));
	}

	private void doInsertProject(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String ex_name = req.getParameter("ex_name");
		int number = 1;
		double ex_price = Double.parseDouble(req.getParameter("ex_price"));
		String p_number = req.getParameter("p_number");
		String doctor_id = (String)req.getSession().getAttribute("u_number");
		int ex_state = 0;
		
		int cost_type = Integer.parseInt(req.getParameter("cost_type"));
		Examine ex = new Examine();
		ex.setP_number(p_number);
		ex.setSum_price(ex_price);
		ex.setProject_name(ex_name);
		ex.setNumber(number);
		ex.setDoctor_id(doctor_id);
		ex.setEx_state(ex_state);
		ex.setCost_type(cost_type);
		ex.setPrice(ex_price);
		ex.setModel("无");
		int result = doctorService.insertExamine(ex);
		String message = "";
		if (result > 0) {
			message = "检查"+ex_name +"保存成功";
		} else {
			message = "保存失败，请重试，或者联系管理员";
		}
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(message);
	}

	private void doSelectProjects(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String key = req.getParameter("key");
		System.out.println("key" +key);
		List<Project> Projects = doctorService.selectAllProject(key);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSON(Projects));
	}

	private void doInsertPDrug(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PostDrugService postDrugService = new PostDrugServiceMySqlImpl();
		String message = "";
		String drug_name = req.getParameter("drug_name");
		int drug_count = postDrugService.selectDrugCount(drug_name); //当前药品的数量
		int number = Integer.parseInt(req.getParameter("drug_number"));
		//如果库存药品数量减去当前需要的药品数量小于零，表明库存不足，则给前台传输标记1，前台阻止他提交，并给他提醒
		if (drug_count - number <= 0) {
			message = "1";
		} else {
			double sum = Double.parseDouble(req.getParameter("drug_sum"));
			String p_number = req.getParameter("p_number");
			String doctor_id = (String)req.getSession().getAttribute("u_number");
			int ex_state = 0;
			String product = req.getParameter("drug_product");
			int cost_type = Integer.parseInt(req.getParameter("cost_type"));
			double drug_price = Double.parseDouble(req.getParameter("drug_price"));
			String model = req.getParameter("model");
			Examine ex = new Examine();
			ex.setP_number(p_number);
			ex.setSum_price(sum);
			ex.setProject_name(drug_name);
			ex.setNumber(number);
			ex.setDoctor_id(doctor_id);
			ex.setEx_state(ex_state);
			ex.setAdd_content(product);
			ex.setCost_type(cost_type);
			ex.setPrice(drug_price);
			ex.setModel(model);
			int result = doctorService.insertExamine(ex);
			if (result > 0) {
				message = "药品"+drug_name +"保存成功";
			} else {
				message = "保存失败，请重试，或者联系管理员";
			}
		}
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(message);
	
		//封装对象
		
//		System.out.println("drug_name=" +drug_name + "number=" +number+ "sum=" +sum );
	}

	private void doSelectDrugByDr_id(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		int dr_id = Integer.parseInt(req.getParameter("dr_id"));
		System.out.println("dr_id" +dr_id);
		Drug drug = doctorService.selectDrugByDr_id(dr_id);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSON(drug));
	}

	private void doSelectDrugs(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String key = req.getParameter("key");
		System.out.println("key" +key);
		List<Drug> drugs = doctorService.selectDrugByKey(key);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSON(drugs));
	}

	private void doInsertRecord(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String introduce = req.getParameter("introduce");
		String illness = req.getParameter("illness");
		String detail = req.getParameter("detail");
		String agree = req.getParameter("agree");
		String u_number = (String)req.getSession().getAttribute("u_number");
		String p_number = req.getParameter("p_number");
		System.out.println("插入患者病历introduce = " +introduce +"illness="+illness+"detail=" +detail+"agree=" +agree +"p_number=" +p_number);
		//封装数据
		Record record = new Record();
		record.setIntroduce(introduce);
		record.setIllness(illness);
		record.setDetail(detail);
		record.setAgree(agree);
		record.setP_number(p_number);
		record.setDoctor_id(u_number);
		String sava_mess = "";
		int result = doctorService.insertRecord(record);
		if (result > 0) {
			sava_mess = "病历保存成功";
		} else {
			sava_mess = "病历已经保存，不可重复保存";
		}
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(sava_mess);
	}

	private void doSelectAllInfo(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String p_number = req.getParameter("p_number");
		System.out.println("进入查询患者详情信息p_number" +p_number);
		Paciente pct = doctorService.selectPacienteByid(p_number);
		System.out.println(pct.getP_tel());
		//将数据传到前台
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSON(pct));
	}
	
	
//	private void doSelectAllIllness(HttpServletRequest req,
//			HttpServletResponse resp) {
//		int department_id = (Integer)req.getSession().getAttribute("departament_id");
//		List<Illness_type> itypes = doctorService.getAllIllness(department_id);
//		System.out.println("疾病的类型：" +itypes.size());
//		if (itypes.size() > 0) {
//			req.setAttribute("itypes", itypes);
//			
//		}
//	}
//	private void doselectPacienteByDoctor_id(HttpServletRequest req,
//			HttpServletResponse resp) {
//		String doctor_id = req.getParameter("u_number");
//		List<Paciente> doctors = doctorService.selectPacienteByDoctor_id(doctor_id);
//		if (doctors.size() > 0) {
//			req.setAttribute("doctors", doctors);
//		}
//	}

	
	
}
