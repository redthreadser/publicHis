package com.his.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.his.entity.Cost;
import com.his.entity.Examine;
import com.his.entity.Paciente;
import com.his.service.CostService;
import com.his.service.ExamineService;
import com.his.service.PacienteService;
import com.his.service.PostDrugService;
import com.his.serviceMySqlImpl.CostServiceMySqlImpl;
import com.his.serviceMySqlImpl.ExamineServiceMySqlImpl;
import com.his.serviceMySqlImpl.PacienteServiceMySqlImpl;
import com.his.serviceMySqlImpl.PostDrugServiceMySqlImpl;

public class ExamineServlet extends HttpServlet {
	ExamineService examineService = new ExamineServiceMySqlImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("3.进入ExamineServlet");
		String status = req.getParameter("actionMethod");
		if ("getPacienteInfo".equals(status)) {
			doPacienteInfo(req, resp);
		}else if ("allProjectName".equals(status)) {
			doAllProjectName(req, resp);
		} else if ("ex_cost".equals(status)) {
			doEx_cost(req, resp);
		} else if ("b_examine".equals(status)) {
			doB_examine(req, resp);
		} 

	}
	
	/*
	 * 检查或者发药
	 */
	private void doB_examine(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int ex_id = Integer.parseInt(req.getParameter("ex_id"));
		String option_name = (String)req.getSession().getAttribute("u_name");
		int cost_type = Integer.parseInt(req.getParameter("cost_type"));
		
		String drug_name = req.getParameter("drug_name");
		PostDrugService postDrugService = new PostDrugServiceMySqlImpl();
		ExamineService examineService = new ExamineServiceMySqlImpl();
		String err_info = "";
		int state = examineService.selectStateByEx_id(ex_id);
		if (state != 1) {
			err_info = "该患者已经进行过此功能，或者未缴费，不能进行该操作";
		} else {
			String ex_result = ((int)(Math.random()*100000))+"";
			System.out.println("进入检查ex_id=" +ex_id +"Ex_result" +ex_result);
			Examine ex = new Examine();
			ex.setId(ex_id);
			ex.setOption_name(option_name);
			ex.setEx_result(ex_result);
			//更新状态信息
			int result_3 = postDrugService.updateState(ex_id, 2);
			if (result_3 > 0) {
				if (cost_type  == 2) {
					//更新药品数量
					int dr_count = Integer.parseInt(req.getParameter("dr_count"));
					int result_4 = postDrugService.updateDrugCount(drug_name, dr_count);
					if (result_4 > 0) {
						err_info = "1";
					}
				} else {
					//更新检查结果
					int result_1 = examineService.updateEx_result(ex);
					if (result_1 > 0) {
						err_info = "1";
					}
				}
			} else {
				err_info = "更新信息失败";
			}
			
			
		}
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(err_info);
		
	}
	/*
	 * 收费
	 */
	private void doEx_cost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PacienteService pacienteService = new PacienteServiceMySqlImpl();
		CostService costService = new CostServiceMySqlImpl();
		PostDrugService postDrugsService = new PostDrugServiceMySqlImpl();
		ExamineService examineService = new ExamineServiceMySqlImpl();
		String err_info = "";
		//接收患者前台接受的数据
		String p_number = req.getParameter("p_number");
		int cost_type = Integer.parseInt(req.getParameter("cost_type"));
		String project_name = req.getParameter("project_name");
		double sum_price = Double.parseDouble(req.getParameter("sum_price"));
		String option_name = (String)req.getSession().getAttribute("u_name");
		System.out.println("option_name="+option_name);
		int ex_id = Integer.parseInt(req.getParameter("ex_id")); //检查项目的id
		System.out.println("检查缴费：p_number="+p_number +"project_name" +project_name+"sum_price" +sum_price);
		//封装对象
		Cost cost = new Cost();
		cost.setP_number(p_number);
		cost.setCost_type(cost_type);
		cost.setProject_name(project_name);
		cost.setPr_price(sum_price);
		cost.setOption_name(option_name);
		int state = examineService.selectStateByEx_id(ex_id);
		//判断状态
		if (state != 0) {
			err_info = "该患者已经操作过，或者已经缴费,不能进行缴费操作";
		} else {
			//判断患者余额是否够用
			double extr_price = pacienteService.doShowExtraMoney(p_number, sum_price);
			if (extr_price < 0) {
				err_info = "你的余额不足，不能进行缴费";
			} else {
				//扣除患者的余额
				int result_1 = pacienteService.doPayment(extr_price, p_number);
				if (result_1 > 0) {
					System.out.println("result_1"+result_1);
					//添加一条消费信息
					int result_2 = costService.doInsertCost(cost);
					if (result_2 > 0) {
						System.out.println("result_2"+result_2);
						//将状态改为1
						int result_3 = postDrugsService.updateState(ex_id, 1);
						if (result_3 > 0) {
							System.out.println("result_3"+result_3);
							err_info = "1";
						}
					} else {
						err_info = "添加消费记录失败, 请重新操作";
					}
				} else {
					err_info = "扣款失败, 请重新操作";
				}
			}
		}
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(err_info);
		
	}

	/*
	 * 根据患者id查询该患者的主治医生、以及该患者的姓名、年龄、性别
	 */
	private void doPacienteInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String p_number=req.getParameter("p_number");
		System.out.println("4.点击详情后的患者编号："+p_number);
		System.out.println("5.当前页面");
		req.setAttribute("p_number", p_number);
		Paciente paciente = examineService.getPacienteInfo(p_number);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSON(paciente));
	}

	/*
	 *显示该患者所有需要检查的项目
	 */
	private void doAllProjectName(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {
		
		String p_number=req.getParameter("p_number");
		System.out.println("4.点击详情后的患者编号："+p_number);
		System.out.println("5.当前页面");
		req.setAttribute("p_number", p_number);
		List<Examine> examines = examineService.allProjectName(p_number);
		if(examines.size()>0){
			req.setAttribute("examines", examines);
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.print(JSON.toJSON(examines));
		}
	}
		
	}
