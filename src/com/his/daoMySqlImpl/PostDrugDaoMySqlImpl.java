package com.his.daoMySqlImpl;

import java.sql.SQLException;

import com.his.dao.PostDrugDao;
import com.his.util.DBUtil;

public class PostDrugDaoMySqlImpl extends DBUtil implements PostDrugDao{

	public int selectDrugCount(String drug_name) {
		int count = 0;
		String sql = "select `count` from drug where drug_name = ?";
		rs = this.executeQuery(sql, drug_name);
		try {
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int updateDrugCount(String drug_name, int changeCount) {
		int count = this.selectDrugCount(drug_name);
		String sql = "update drug set `count` = ? where drug_name = ?";
		int result = this.executeUpdate(sql, (count - changeCount), drug_name);
		return result;
	}

	public int updateState(int ex_id, int newState) {
		System.out.println("updateState++-dao!!");
		String sql = "update examine set ex_state=? where id=?";
		int result = this.executeUpdate(sql,newState,ex_id);
		return result;
	}

}
