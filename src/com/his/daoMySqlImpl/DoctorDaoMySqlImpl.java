package com.his.daoMySqlImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.his.dao.DoctorDao;
import com.his.entity.Drug;
import com.his.entity.Examine;
import com.his.entity.Illness_type;
import com.his.entity.Paciente;
import com.his.entity.Project;
import com.his.entity.Record;
import com.his.util.DBUtil;


public class DoctorDaoMySqlImpl extends DBUtil implements DoctorDao{
	ResultSet rs = null;
	public List<Paciente> selectPacienteByDoctor_id(String doctor_id) {
		List<Paciente> pacientes = new ArrayList<Paciente>();
		String sql = "select `p_number`,`p_name`,registo_time " +
				"from paciente " +
				"where doctor_id = ? " +
				"order by registo_time";
		rs = this.executeQuery(sql, doctor_id);
		try {
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setP_number(rs.getString("p_number"));
				p.setP_name(rs.getString("p_name"));
				p.setRegisto_time(rs.getString("registo_time"));
				/*p.setP_age(rs.getInt("p_age"));
				p.setP_sex(rs.getString("p_sex"));
				p.setP_tel(rs.getInt("tel"));*/
				pacientes.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstm, rs);
		}
		return pacientes;
	}
	public Paciente selectPacienteByid(String p_number) {
		Paciente p = new Paciente();
		String sql = "select `p_number`,`p_name`,`p_age`,`p_sex`,`tel`,money from paciente where p_number = ?";
		rs = this.executeQuery(sql, p_number);
		try {
			if (rs.next()) {
				p.setP_name(rs.getString("p_name"));
				p.setP_number(rs.getString("p_number"));
				p.setP_age(rs.getInt("p_age"));
				p.setP_sex(rs.getString("p_sex"));
				p.setP_tel(rs.getString("tel"));
				p.setExtraMoney(rs.getDouble("money"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstm, rs);
		}
		return p;
	}
	public List<Illness_type> getAllIllness(int department_id) {
		List<Illness_type> itypes = new ArrayList<Illness_type>();
		String sql = "select `id`,`type_name`,`department_id` from illness_type where department_id = ?";
		rs = this.executeQuery(sql, department_id);
		try {
			while (rs.next()) {
				Illness_type itype = new Illness_type();
				itype.setId(rs.getInt("id"));
				itype.setType_name(rs.getString("type_name"));
				itype.setDepartment_id(rs.getInt("department_id"));
				itypes.add(itype);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstm, rs);
		}
		return itypes;
	}
	
	public int insertRecord(Record record) {
		String sql = "insert into record(`p_number`,`introduce`,`illness`,`detail`,`agree`,`doctor_id`) " +
				"values(?, ?, ?, ?, ?, ?)";
		int result =  this.executeUpdate(sql, record.getP_number(), record.getIntroduce(), record.getIllness(),
				record.getDetail(), record.getAgree(), record.getDoctor_id());
		return result;
	}
	
	public List<Drug> selectDrugByKey(String key) {
		List<Drug> drugs = new ArrayList<Drug>();
		String sql = "select id, `drug_name` from drug where `key` like ?";
		rs = this.executeQuery(sql, key + "%");
		try {
			while (rs.next()) {
				Drug d = new Drug();
				d.setId(rs.getInt("id"));
				d.setDrug_name(rs.getString("drug_name"));
				drugs.add(d);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstm, rs);
		}
		return drugs;
	}
	
	public Drug selectDrugByDr_id(int dr_id) {
		Drug d = new Drug();
		String sql = "select `drug_name`,`model`,`drug_product`,`drug_price`,`cost_type` from drug where id = ?";
		rs = this.executeQuery(sql, dr_id);
		try {
			if (rs.next()) {
				d.setDrug_name(rs.getString("drug_name"));
				d.setModel(rs.getString("model"));
				d.setDrug_product(rs.getString("drug_product"));
				d.setDrug_price(rs.getDouble("drug_price"));
				d.setCost_type(rs.getInt("cost_type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstm, rs);
		}
		return d;
	}
	/*
	 * 插入药品信息(non-Javadoc)
	 * @see com.his.dao.DoctorDao#insertExamine(com.his.entity.Examine)
	 */
	public int insertExamine(Examine examine) {
		String sql = "insert into " +
				"examine(`p_number`,`doctor_id`,`cost_type`,`project_name`,`number`,`sum_price`,`ex_state`,`add_content`,`d_time`, price, model) " +
				"values(?, ?, ?, ?,?, ?, ?, ?, NOW(), ?, ?)";
		int result = this.executeUpdate(sql, examine.getP_number(), examine.getDoctor_id(), examine.getCost_type(), 
				examine.getProject_name(), examine.getNumber(), examine.getSum_price(), examine.getEx_state(),
				examine.getAdd_content(), examine.getPrice(), examine.getModel());
		return result;
	}
	
	public List<Project> selectAllProject(String key) {
		List<Project> projects = new ArrayList<Project>();
		String sql = "select `cost_type`,`project_name`,`pr_price` from project where `key` like ?";
		rs = this.executeQuery(sql, key + "%");
		try {
			while (rs.next()) { 
				Project pr = new Project();
				pr.setCost_type(rs.getInt("cost_type"));
				pr.setProject_name(rs.getString("project_name"));
				pr.setPr_price(rs.getDouble("pr_price"));
				projects.add(pr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstm, rs);
		}
		return projects;
	}
	public List<Examine> selectExamineByP_number(String p_number, int cost_type) {
		List<Examine> examines = new ArrayList<Examine>();
		String sql = "select id, `project_name`,`number`,`d_time`,`sum_price`,`ex_state`,`ex_result`,`option_name`,ex_time, price, model, add_content " +
				"from examine " +
				"where p_number = ? and cost_type = ?";
		rs = this.executeQuery(sql, p_number, cost_type);
		try {
			while (rs.next()) {
				Examine ex = new Examine();
				ex.setProject_name(rs.getString("project_name"));
				ex.setId(rs.getInt("id"));
				ex.setNumber(rs.getInt("number"));
				ex.setEx_time(rs.getDate("ex_time"));
				ex.setD_time(rs.getString("d_time"));
				ex.setSum_price(rs.getDouble("sum_price"));
				ex.setEx_state(rs.getInt("ex_state"));
				ex.setEx_result(rs.getString("ex_result"));
				ex.setOption_name(rs.getString("option_name"));
				ex.setPrice(rs.getDouble("price"));
				ex.setModel(rs.getString("model"));
				ex.setAdd_content(rs.getString("add_content"));
				examines.add(ex);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstm, rs);
		}
		return examines;
	}
	/*
	 * 删除项目(non-Javadoc)
	 * @see com.his.dao.DoctorDao#deleteExamine(int)
	 */
	public int deleteExamine(int id) {
		String sql = "delete from examine where id = ?";
		int result = this.executeUpdate(sql, id);
		return result;
	}
	public int updateRecord(Record record) {
		String sql = "update record set introduce = ?, illness`detail`=?, `agree`=?, `re_time`=NOW() where p_number=?";
		int result = this.executeUpdate(sql, record.getIntroduce(), record.getIllness(),
				record.getAgree(), record.getP_number());
		return result;
	}
	public int delPaciente(String p_nubmer) {
		String sql = "delete from paciente where p_number = ?";
		int result = this.executeUpdate(sql, p_nubmer);
		return result;
	}
}
