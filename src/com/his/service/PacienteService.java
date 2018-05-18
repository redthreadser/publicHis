package com.his.service;

import java.util.List;

import com.his.entity.Doctor;
import com.his.entity.Paciente;

public interface PacienteService {
	public int doPacienteReg(Paciente paciente); 	//挂号插入患者数据
	public List<Doctor> doShowDoctor(); 		//显示医生信息
	public List<Doctor> doShowDepDoctor(int departament_id); 		//显示科室医生信息
	public int doShowDocCount(String doctor_id);		//医生计数
	public int doDocCount(String doctor_id,int count); //更新医生挂号人数
	public int doPayment(double money ,String p_number); //缴费
	public double doShowExtraMoney(String p_number,double pr_price);	//查询余额
	public int clearDocCount();  	//清除医生挂号人数

}
