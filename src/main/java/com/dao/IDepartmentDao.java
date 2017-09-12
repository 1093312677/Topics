package com.dao;

import com.entity.Department;

public interface IDepartmentDao {
	/**
	 * 查看系详情
	 * @param departmentId
	 * @return
	 */
	public Department viewDepartment(Long departmentId);
	
	
}
