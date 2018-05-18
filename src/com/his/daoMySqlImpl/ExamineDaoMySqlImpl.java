package com.his.daoMySqlImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.his.dao.ExamineDao;
import com.his.entity.Examine;
import com.his.entity.Paciente;
import com.his.util.DBUtil;

public class ExamineDaoMySqlImpl extends DBUtil implements ExamineDao {

	/*
	 * 查询所有患者的编号、姓名、挂号时间
	 */
	public List<Paciente> allPaciente() {
		String sql = " select p_number,p_name,registo_time from paciente ";
		List<Paciente> pacientes = new ArrayList<Paciente>();
		rs = this.executeQuery(sql, null);
		try {
			while(rs.next()){
				Paciente paciente = new Paciente();
				paciente.setP_number(rs.getString("p_number"));
				paciente.setP_name(rs.getString("p_name"));
				paciente.setRegisto_time(rs.getString("registo_time"));
				pacientes.add(paciente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(con, pstm, rs);
		}
		return pacientes;
	}
	
	
	
	
	/*
	 * 根据患者id查询该患者的主治医生、以及该患者的姓名、年龄、性别
	 */
	public Paciente getPacienteInfo(String p_number){
		Paciente paciente = new Paciente();
		String sql=" select d_name ,p_name, p_age,p_sex "+
					" from doctor dct ,paciente p "+
					" where p.doctor_id = dct.doctor_id and p.p_number=? ";
		rs = this.executeQuery(sql, p_number);
		try {
			if(rs.next()){
				paciente.setD_name(rs.getString("d_name"));
				paciente.setP_name(rs.getString("p_name"));
				paciente.setP_age(rs.getInt("p_age"));
				paciente.setP_sex(rs.getString("p_sex"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			this.closeAll(con, pstm, rs);
		}
		return paciente;
	}
	
	
	/*
	 * 根据患者id查询该患者所需要做的全部检查项目
	 */
	public List<Examine> allProjectName(String p_number) {
		List<Examine> examines = new ArrayList<Examine>();
		String sql=" select project_name from examine where p_number = ? ";
		rs = this.executeQuery(sql, p_number);
		try {
			while(rs.next()){
				Examine examine = new Examine();
				examine.setProject_name(rs.getString("project_name"));
				examines.add(examine);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(con, pstm, rs);
		}
		return examines;
	} 

	/*
	 * 查询正在等待检查的病人
	 */
	public List<Paciente> selectAllExPaciente(int cost_type) {
		String sql = "SELECT pct.p_number, `p_name`,`d_time` FROM examine ex, paciente pct " +
				"WHERE ex.`p_number` = pct.`p_number` AND cost_type = ? " +
				"GROUP BY p_name";
		List<Paciente> pacientes = new ArrayList<Paciente>();
		rs = this.executeQuery(sql, cost_type);
		try {
			while(rs.next()){
				Paciente paciente = new Paciente();
				paciente.setP_number(rs.getString("p_number"));
				paciente.setP_name(rs.getString("p_name"));
				paciente.setD_time(rs.getString("d_time"));
				pacientes.add(paciente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(con, pstm, rs);
		}
		return pacientes;
	}



	public int updateEx_result(Examine ex) {
		String sql = "update examine set ex_result = ?, option_name = ? where id = ?";
		int result = this.executeUpdate(sql,ex.getEx_result(), ex.getOption_name(),ex.getId() );
		return result;
	}


	public int selectStateByEx_id(int ex_id) {
		String sql = "select ex_state from examine where id = ? ";
		rs = this.executeQuery(sql, ex_id);
		int state = 0;
		try {
			if (rs.next()) {
				state = rs.getInt("ex_state");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}

}
