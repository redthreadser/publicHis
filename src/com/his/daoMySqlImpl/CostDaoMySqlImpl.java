package com.his.daoMySqlImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.his.dao.CostDao;
import com.his.entity.Cost;
import com.his.entity.Project;
import com.his.util.DBUtil;

public class CostDaoMySqlImpl extends DBUtil implements CostDao {
	/**
	 * 消费明细
	 */
	public int doInsertCost(Cost cost) {
		String sql = "insert into cost(`p_number`,`cost_type`,`project_name`,`pr_price`," +
				"`cost_time`,`option_name`) values(?, ?, ?, ?, now(), ?)";
		int result = this.executeUpdate(sql, cost.getP_number(), cost.getCost_type(), 
				cost.getProject_name(), cost.getPr_price(), cost.getOption_name());
		return result;
	}
	/**
	 * 查询项目表
	 */
	public Project doShowProjet(int cost_type) {
		System.out.println("doShowProjet-dao");
		Project project = new Project();
		String sql = "select `project_name`,`key`,`pr_price` from project where cost_type=?";
		rs = this.executeQuery(sql, cost_type);
		try {
			while (rs.next()) {
				project.setProject_name(rs.getString("project_name"));
				project.setKey(rs.getString("key"));
				project.setPr_price(rs.getDouble("pr_price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(con, pstm, rs);
		}
		return project;
	}
	/**
	 * 查询消费明细
	 */
	public List<Cost> doShowCost(String p_number) {
		System.out.println("doShowCost-dao");
		List<Cost> cost = new ArrayList<Cost>();
		String sql = "select `p_name`,c.p_number,`cost_type`,`project_name`,`pr_price`,`cost_time`," +
				"c.option_name from cost c,paciente p where c.p_number = p.p_number and p.p_number=?";
		rs = this.executeQuery(sql, p_number);
		try {
			while (rs.next()) {
				Cost cost1 = new Cost();
				cost1.setP_name(rs.getString("p_name"));
				cost1.setP_number(rs.getString("p_number"));
				cost1.setCost_type(rs.getInt("cost_type"));
				cost1.setProject_name(rs.getString("project_name"));
				cost1.setPr_price(rs.getDouble("pr_price"));
				cost1.setCost_time(rs.getString("cost_time"));
				cost1.setOption_name(rs.getString("option_name"));
				cost.add(cost1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(con, pstm, rs);
		}
		return cost;
	}

}	
