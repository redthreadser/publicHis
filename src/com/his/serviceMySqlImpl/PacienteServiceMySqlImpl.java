package com.his.serviceMySqlImpl;

import java.util.ArrayList;
import java.util.List;

import com.his.dao.PacienteRegDao;
import com.his.daoMySqlImpl.PacienteRegDaoMySqlImpl;
import com.his.entity.Doctor;
import com.his.entity.Paciente;
import com.his.service.PacienteService;

public class PacienteServiceMySqlImpl implements PacienteService{
	
	PacienteRegDao pacienteRegDao = new PacienteRegDaoMySqlImpl();
	/**
	 * 门诊挂号
	 */
	public int doPacienteReg(Paciente paciente) {
		int result = pacienteRegDao.doPacienteReg(paciente);
		return result;
	}
	/**
	 * 显示医生信息
	 */
	public List<Doctor> doShowDoctor() {
		List<Doctor> doctors = new ArrayList<Doctor>();
		doctors = pacienteRegDao.doShowDoctor();
		return doctors;
	}
	/**
	 * 显示部门医生信息
	 */
	public List<Doctor> doShowDepDoctor(int departament_id) {
		List<Doctor> doctors = new ArrayList<Doctor>();
		doctors = pacienteRegDao.doShowDepDoctor(departament_id);
		return doctors;
	}
	/**
	 * 医生挂号人数显示
	 */
	public int doShowDocCount(String doctor_id) {
		int count = pacienteRegDao.doShowDocCount(doctor_id);
		return count;
	}
	/**
	 * 医生挂号人数增加
	 */
	public int doDocCount(String doctor_id, int count) {
		int result = pacienteRegDao.doDocCount(doctor_id, count);
		return result;
	}
	/**
	 * 缴费
	 */
	public int doPayment(double money, String p_number) {
		int result = pacienteRegDao.doPayment(money, p_number);
		return result;
	}
	/*
	 * 判断该项目余额是否够用
	 * 返回两个数相减的结果
	 * (non-Javadoc)
	 * @see com.his.service.PacienteService#doShowExtraMoney(java.lang.String, double)
	 */
	public double doShowExtraMoney(String p_number,double pr_price) {
		double extraMoney = pacienteRegDao.doShowExtraMoney(p_number,pr_price);
		return extraMoney - pr_price;
	}
	public int clearDocCount() {
		int result = pacienteRegDao.clearDocCount();
		return result;
	}

}
