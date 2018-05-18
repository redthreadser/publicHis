package com.his.daoMySqlImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.his.dao.ManagerDao;
import com.his.entity.Department;
import com.his.entity.Doctor;
import com.his.entity.Drug;
import com.his.entity.Role;
import com.his.entity.User;
import com.his.util.DBUtil;


public class ManagerDaoMySqlImpl extends DBUtil implements ManagerDao{
	
	public int register(User user) {
		String sql = "insert into user(`u_number`,`password`,`u_name`,`role`) values(?, ?, ?, ?)";
		int result = this.executeUpdate(sql, user.getU_number(), user.getPassword(), user.getU_name(), user.getRole());
		return result;
	}
	/*
	 * 登录
	 * (non-Javadoc)
	 * @see com.his.dao.ManagerDao#login(com.his.entity.User)
	 */
	public User login(User user) {
		ResultSet rs = null;
		User newUser = new User();
		String sql = "select `u_number`,`password`,`u_name`,`role` from user where u_number = ? and password = ?";
		rs = this.executeQuery(sql, user.getU_number(), user.getPassword());
		try {
			if (rs.next()) {
				newUser.setU_number(rs.getString("u_number"));
				newUser.setPassword(rs.getString("password"));
				newUser.setU_name(rs.getString("u_name"));
				System.out.println("u_name的值是：" +rs.getString("u_name"));
				newUser.setRole(rs.getInt("role"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstm, rs);
		}
		return newUser;
	}
	
	public List<Role> getAllRole() {
		List<Role> roles = new ArrayList<Role>();
		
		ResultSet rs = null;
		String sql = "select id, role_name from role";
		rs = this.executeQuery(sql, null);
		try {
			while (rs.next()) {
				Role role = new Role();
				role.setId(rs.getInt("id"));
				role.setRole_name(rs.getString("role_name"));
				roles.add(role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeAll(con, pstm, rs);
		}
		return roles;
	}
	
	/*
	 * 查询对应账号的权限
	 */
	public int getRoleByUid(String u_number) {
		int role = 0;
		String sql = "select role from user where u_number = ?";
		rs = this.executeQuery(sql, u_number);
		try {
			if (rs.next()) {
				role = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return role;
	}
	public int insertDoctor(Doctor doctor) {
		String sql = "insert into doctor(`doctor_id`,`departament_id`,`d_name`,`d_age`,`d_sex`,`count`) " +
				"values(?, ?, ?, ?, ?, ?)";
		int result = this.executeUpdate(sql, doctor.getDoctor_id(), doctor.getDepartament_id(), doctor.getD_name(), doctor.getD_age(), doctor.getD_sex(), doctor.getCount());
		return result;
	}
	
	
	public List<Department> getAllDepartment() {
		List<Department> departments = new ArrayList<Department>();
		String sql = "select id, department_name from department";
		rs = this.executeQuery(sql, null);
		try {
			while (rs.next()) {
				Department department = new Department();
				department.setId(rs.getInt("id"));
				department.setDepartment_name(rs.getString("department_name"));
				departments.add(department);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstm, rs);
		}
		return departments;
	}
	public Doctor SelecNameById(String doctor_id) {
		Doctor doctor = new Doctor();
//		String sql = "select `doctor_id`,dpt.department_name,`d_name`,`d_age`,`d_sex`,`count` " +
//				"from doctor dct, department dpt  " +
//				"where dct.departament_id = dpt.id and doctor_id = ?";
		String sql = "SELECT `doctor_id`,dpt.department_name,`d_name`,`d_age`,`d_sex`,`count`, departament_id FROM doctor dct, department dpt  WHERE dct.departament_id = dpt.id AND doctor_id = ?";
		rs = this.executeQuery(sql, doctor_id);
		try {
			if (rs.next()) {
				doctor.setDoctor_id(rs.getString("doctor_id"));
				doctor.setDepartament_name(rs.getString("department_name"));
				doctor.setD_name(rs.getString("d_name"));
				doctor.setD_age(rs.getInt("d_age"));
				doctor.setD_sex(rs.getString("d_sex"));
				doctor.setCount(rs.getInt("count"));
				doctor.setDepartament_id(rs.getInt("departament_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstm, rs);
		}
		return doctor;
	}
	
	/*
	 * 插入药品信息
	 */
	public int insertDrug(Drug drug) {
		String sql = "insert into `drug`(drug_name,model,drug_product,drug_price,cost_type,`key`,`count`)  " +
				" values (?,?,?,?,?,?,?) ";
		int result=this.executeUpdate(sql, drug.getDrug_name(),drug.getModel(),drug.getDrug_product(),drug.getDrug_price(),drug.getCost_type(),drug.getKey(),drug.getCount());
		return result;
	}
	
}
