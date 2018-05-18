package com.his.daoMySqlImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.his.dao.PacienteRegDao;
import com.his.entity.Doctor;
import com.his.entity.Paciente;
import com.his.util.DBUtil;

public class PacienteRegDaoMySqlImpl extends DBUtil implements PacienteRegDao{
	/**
	 * 门诊挂号
	 */
	public int doPacienteReg(Paciente paciente) {
		String sql = "insert into paciente(`p_number`,`p_name`,`p_age`,`p_sex`," +
				"`money`,`tel`,`registo_time`,`departament_id`,`doctor_id`,`option_name`) " +
				"values(?, ?, ?, ?, ?, ?, now(), ?, ? ,?)";
		int result = this.executeUpdate(sql, paciente.getP_number(), paciente.getP_name(), 
					paciente.getP_age(), paciente.getP_sex(), paciente.getExtraMoney(),
					paciente.getP_tel(), paciente.getDepartment_id(), 
					paciente.getDoctor_id(),paciente.getOption_name());
		return result;
	}
	/**
	 * 显示医生信息
	 */
	public List<Doctor> doShowDoctor() {
		System.out.println("DoctorIfo-dao");
		List<Doctor> doctors = new ArrayList<Doctor>();
		String sql = "select `doctor_id`,`d_name`,`count` from doctor";
		rs = this.executeQuery(sql, null);
		try {
			while (rs.next()) {
				Doctor doctor = new Doctor();
				doctor.setDoctor_id(rs.getString("doctor_id"));
				doctor.setD_name(rs.getString("d_name"));
				doctor.setCount(rs.getInt("count"));
				doctors.add(doctor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(con, pstm, rs);
		}
		return doctors;
	}
	/**
	 * 显示部门医生
	 */
	public List<Doctor> doShowDepDoctor(int departament_id) {
		System.out.println("DoctorDepIfo-dao");
		List<Doctor> doctors = new ArrayList<Doctor>();
		Map<Object, Object> map = new HashMap<Object, Object>();
		String sql = "select `doctor_id`,`d_name`,`count` from doctor where departament_id=?";
		rs = this.executeQuery(sql, departament_id);
		try {
			while (rs.next()) {
				Doctor doctor = new Doctor();
				doctor.setDoctor_id(rs.getString("doctor_id"));
				doctor.setD_name(rs.getString("d_name"));
				doctor.setCount(rs.getInt("count"));
				doctors.add(doctor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(con, pstm, rs);
		}
		return doctors;
	}
	/**
	 * 医生挂号人数显示
	 */
	public int doShowDocCount(String doctor_id) {
		System.out.println("DoctorCountIfo-dao");
		int count = 0;
		String sql = "select `count` from doctor where doctor_id=?";
		rs = this.executeQuery(sql, doctor_id);
		try {
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(con, pstm, rs);
		}
		return count;
	}
	/**
	 * 医生挂号人数增加
	 */
	public int doDocCount(String doctor_id, int count) {
		System.out.println("DoctorCount++-dao!!");
		String sql = "update doctor set count=? where doctor_id=?";
		int result = this.executeUpdate(sql,count,doctor_id);
		return result;
	}
	/**
	 * 更新余额
	 */
	public int doPayment(double money ,String p_number) {
		System.out.println("doPayment-dao!!");
		String sql = "update paciente set money=? where p_number=?";
		int result = this.executeUpdate(sql,money,p_number);
		return result;
	}

	/*
	 * 查询患者的余额
	 * (non-Javadoc)
	 * @see com.his.dao.PacienteRegDao#doShowExtraMoney(java.lang.String, double)
	 */
	public double doShowExtraMoney(String p_number,double pr_price) {
		System.out.println("doShowExtraMoney-dao");
		double extraMoney = 0;
		String sql = "select `money` from paciente where p_number=?";
		rs = this.executeQuery(sql, p_number);
		try {
			while (rs.next()) {
				extraMoney = rs.getDouble("money");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(con, pstm, rs);
		}
		return extraMoney;
	}
	/*
	 * 医生患者人数清空
	 * (non-Javadoc)
	 * @see com.his.dao.PacienteRegDao#clearDocCount()
	 */
	public int clearDocCount() {
		System.out.println("clearDocCount++-dao!!");
		String sql = "update doctor set count=0";
		int result = this.executeUpdate(sql);
		return result;
	}
}
