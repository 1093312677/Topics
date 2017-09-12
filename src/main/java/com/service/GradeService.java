package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.IDepartmentDao;
import com.dao.IGradeDao;
import com.entity.Department;
import com.entity.Grade;

/**
 * 关于年级的逻辑处理
 * @author kone
 *	2017.7.19
 */
@Service
public class GradeService {
	
	@Autowired
	private IGradeDao gradeDao;
	
	@Autowired
	private IDepartmentDao departmentDao;
	
	public List<Grade> viewGrades(Long departmentId) {
		return gradeDao.viewGrades(departmentId);
	}
	
	/**
	 * 系主任查看年级信息
	 * @param departmentId
	 * @return
	 */
	public List<Grade> deanViewGrades(Long departmentId) {
		List<Grade> grades = gradeDao.viewGrades(departmentId);
		Department depart = departmentDao.viewDepartment(departmentId);
		for(int i=0;i<grades.size();i++) {
			grades.get(i).setDepartment(depart);
		}
		return grades;
	}
	 
}
