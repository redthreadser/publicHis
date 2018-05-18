package com.his.dao;

import java.util.List;

import com.his.entity.Department;
import com.his.entity.Doctor;
import com.his.entity.Drug;
import com.his.entity.Role;
import com.his.entity.User;

public interface ManagerDao {
	/*
	 * 指定用户名和密码
	 */
	public int register(User user);
	
	/*
	 * 登录
	 */
	public User login(User user);
	
	/*
	 * 查询所有角色信息
	 */
	public List<Role> getAllRole();
	
	/*
	 * 查询对应账号的权限
	 */
	public int getRoleByUid(String u_number);
	
	/*
	 * 插入医生信息
	 */
	public int insertDoctor(Doctor doctor);
	
	/*
	 * 查询所有科室表
	 */
	public List<Department> getAllDepartment();
	
	/*
	 * 根据医生的编号在医生表里面查询详情
	 */
	public Doctor SelecNameById(String doctor_id);
	
	/*
	 * 插入药品信息
	 */
	public int insertDrug(Drug drug);
}
