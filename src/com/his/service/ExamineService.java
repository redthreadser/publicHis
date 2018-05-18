package com.his.service;

import java.util.List;

import com.his.entity.Examine;
import com.his.entity.Paciente;

public interface ExamineService {
	/*
	 * 查询所有患者
	 */
	public List<Paciente> allPaciente();
	
	/*
	 * 根据患者id查询该患者的主治医生、以及该患者的姓名、年龄、性别
	 */
	public Paciente getPacienteInfo(String p_number);
	
	/*
	 * 根据患者id查询该患者所需要做的全部检查项目
	 */
	public List<Examine> allProjectName(String p_number);
	
	/*
	 * 查询等待检查的患者
	 */
	public List<Paciente> selectAllExPaciente(int cost_type);
	
	/*
	 * 更新检查结果
	 */
	public int updateEx_result(Examine ex);
	
	/*
	 * 查看对应检查项目的当前的状态
	 */
	public int selectStateByEx_id(int ex_id);
	
	
	

}
