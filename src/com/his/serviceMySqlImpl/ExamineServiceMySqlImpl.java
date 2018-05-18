package com.his.serviceMySqlImpl;

import java.util.List;

import com.his.dao.ExamineDao;
import com.his.daoMySqlImpl.ExamineDaoMySqlImpl;
import com.his.entity.Examine;
import com.his.entity.Paciente;
import com.his.service.ExamineService;

public class ExamineServiceMySqlImpl implements ExamineService {
	ExamineDao examineDao = new ExamineDaoMySqlImpl();

	public List<Paciente> allPaciente() {
		return examineDao.allPaciente();
	}

	public List<Examine> allProjectName(String p_number) {
		return examineDao.allProjectName(p_number);
	}

	public Paciente getPacienteInfo(String p_number) {
		return examineDao.getPacienteInfo(p_number);
	}

	public List<Paciente> selectAllExPaciente(int cost_type) {
		return examineDao.selectAllExPaciente(cost_type);
	}

	public int updateEx_result(Examine ex) {
		return examineDao.updateEx_result(ex);
	}

	public int selectStateByEx_id(int ex_id) {
		return examineDao.selectStateByEx_id(ex_id);
	}

}
