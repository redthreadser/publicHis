package com.his.serviceMySqlImpl;

import java.util.List;

import com.his.dao.ManagerDao;
import com.his.daoMySqlImpl.ManagerDaoMySqlImpl;
import com.his.entity.Department;
import com.his.entity.Doctor;
import com.his.entity.Drug;
import com.his.entity.Role;
import com.his.entity.User;
import com.his.service.ManagerService;

public class ManagerServiceMySqlImpl implements ManagerService{
	ManagerDao managerDao = new ManagerDaoMySqlImpl();
	public int register(User user) {
		return managerDao.register(user);
	}

	public User login(User user) {
		return managerDao.login(user);
	}

	public List<Role> getAllRole() {
		return managerDao.getAllRole();
	}

	public int insertDoctor(Doctor doctor) {
		return managerDao.insertDoctor(doctor);
	}

	public List<Department> getAllDepartment() {
		return managerDao.getAllDepartment();
	}

	public Doctor SelecNameById(String doctor_id) {
		return managerDao.SelecNameById(doctor_id);
	}

	public int insertDrug(Drug drug) {
		return managerDao.insertDrug(drug);
	}

}
