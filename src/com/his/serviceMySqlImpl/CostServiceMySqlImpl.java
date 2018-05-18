package com.his.serviceMySqlImpl;

import java.util.ArrayList;
import java.util.List;

import com.his.dao.CostDao;
import com.his.daoMySqlImpl.CostDaoMySqlImpl;
import com.his.entity.Cost;
import com.his.entity.Project;
import com.his.service.CostService;

public class CostServiceMySqlImpl implements CostService {
	CostDao costDao = new CostDaoMySqlImpl();
	
	/**
	 * 消费明细
	 */
	public int doInsertCost(Cost cost) {
		int result = costDao.doInsertCost(cost);
		return result;
	}
	/**
	 * 查询项目表
	 */
	public Project doShowProjet(int cost_type) {
		Project project = new Project();
		project = costDao.doShowProjet(cost_type);
		return project;
	}
	/**
	 * 查询消费明细
	 */
	public List<Cost> doShowCost(String p_number) {
		List<Cost> cost = new ArrayList<Cost>();
		cost = costDao.doShowCost(p_number);
		return cost;
	}
}
