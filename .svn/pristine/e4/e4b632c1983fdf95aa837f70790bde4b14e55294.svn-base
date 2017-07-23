package com.guo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Department;
import com.guo.dao.IDepartmentDao;
import com.guo.service.IDepartmentService;

@Service(value="departmentService1")
public class DepartmentService implements IDepartmentService {
	@Resource(name="departmentDao1")
	IDepartmentDao departmentDao;
	@Override
	public List<Department> departments(long departmentId) {
		
		return departmentDao.departments(departmentId);
	}

	@Override
	public void closeSession() {
		departmentDao.closeSession();
	}

	@Override
	public int updateInfo(Department department) {
		return departmentDao.updateInfo(department);
	}

}
