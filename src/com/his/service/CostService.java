package com.his.service;

import java.util.List;

import com.his.entity.Cost;
import com.his.entity.Project;

public interface CostService {
	public int doInsertCost(Cost cost);	//消费明细插入
	public Project doShowProjet(int cost_type); //查询项目表内容
	public List<Cost> doShowCost(String p_number);	//查询消费明细

}
