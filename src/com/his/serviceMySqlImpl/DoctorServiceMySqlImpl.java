package com.his.serviceMySqlImpl;

import java.util.List;

import com.his.dao.DoctorDao;
import com.his.daoMySqlImpl.DoctorDaoMySqlImpl;
import com.his.entity.Drug;
import com.his.entity.Examine;
import com.his.entity.Illness_type;
import com.his.entity.Paciente;
import com.his.entity.Project;
import com.his.entity.Record;
import com.his.service.DoctorService;

public class DoctorServiceMySqlImpl implements DoctorService{
	DoctorDao doctorDao = new DoctorDaoMySqlImpl();

	public List<Paciente> selectPacienteByDoctor_id(String doctor_id) {
		return doctorDao.selectPacienteByDoctor_id(doctor_id);
	}

	public Paciente selectPacienteByid(String p_number) {
		return doctorDao.selectPacienteByid(p_number);
	}

	public List<Illness_type> getAllIllness(int department_id) {
		return doctorDao.getAllIllness(department_id);
	}

	public int insertRecord(Record record) {
		return doctorDao.insertRecord(record);
	}

	public List<Drug> selectDrugByKey(String key) {
		return doctorDao.selectDrugByKey(key);
	}

	public Drug selectDrugByDr_id(int dr_id) {
		return doctorDao.selectDrugByDr_id(dr_id);
	}

	public int insertExamine(Examine examine) {
		return doctorDao.insertExamine(examine);
	}

	public List<Project> selectAllProject(String key) {
		return doctorDao.selectAllProject(key);
	}

	public List<Examine> selectExamineByP_number(String p_number, int cost_type) {
		return doctorDao.selectExamineByP_number(p_number, cost_type);
	}

	public int deleteExamine(int id) {
		return doctorDao.deleteExamine(id);
	}

	public int updateRecord(Record record) {
		return doctorDao.updateRecord(record);
	}
	
	
	
}
