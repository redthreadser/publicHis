package com.his.dao;

import java.util.List;

import com.his.entity.Drug;
import com.his.entity.Examine;
import com.his.entity.Illness_type;
import com.his.entity.Paciente;
import com.his.entity.Project;
import com.his.entity.Record;

public interface DoctorDao {
	/*
	 * 通过医生id找到到该科室就诊的患者
	 */
	public List<Paciente> selectPacienteByDoctor_id(String doctor_id);
	
	/*
	 * 根据患者id找到患者详情信息
	 */
	public Paciente selectPacienteByid(String p_number);
		
	/*
	 * 根据科室查找对应的疾病类型
	 */
	public List<Illness_type> getAllIllness(int department_id);
	
	/*
	 * 插入病历
	 */
	public int insertRecord(Record record);
	
	/*
	 * 模糊搜索药品
	 */
	public List<Drug> selectDrugByKey(String key);
	
	/*
	 * 通过药品名称查找药品信息
	 */
	public Drug selectDrugByDr_id(int dr_id);
	
	/*
	 * 插入检查表的信息
	 */
	public int insertExamine(Examine examine);
	
	/*
	 * 查询该患者的对应检查或者药品信息
	 */
	public List<Examine> selectExamineByP_number(String p_number, int cost_type);
	
	/*
	 * 查询所有的检查项目
	 */
	public List<Project> selectAllProject(String key);
	
	
	/*
	 * 删除项目表
	 */
	public int deleteExamine(int id);
	
	/*
	 * 更新病历
	 */
	public int updateRecord(Record record);
	
	/*
	 * 删除患者挂号信息
	 */
	public int delPaciente(String p_nubmer);
	
}
