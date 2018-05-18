package com.his.serviceMySqlImpl;

import com.his.dao.PostDrugDao;
import com.his.daoMySqlImpl.PostDrugDaoMySqlImpl;
import com.his.service.PostDrugService;

public class PostDrugServiceMySqlImpl implements PostDrugService{
	PostDrugDao postDrugDao = new PostDrugDaoMySqlImpl();
	public int selectDrugCount(String drug_name) {
		return postDrugDao.selectDrugCount(drug_name);
	}

	public int updateDrugCount(String drug_name, int changeCount) {
		return postDrugDao.updateDrugCount(drug_name, changeCount);
	}

	public int updateState(int ex_id, int newState) {
		return postDrugDao.updateState(ex_id, newState);
	}

}
