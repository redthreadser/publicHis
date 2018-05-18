package com.his.dao;

public interface PostDrugDao {
	/*
	 * 更新状态
	 */
	public int updateState(int ex_id, int newState);
	/*
	 * 查询对应药品的数量
	 */
	public int selectDrugCount(String drug_name);
	
	/*
	 * 更新对应药品的数量
	 */
	public int updateDrugCount(String drug_name, int changeCount);
}
