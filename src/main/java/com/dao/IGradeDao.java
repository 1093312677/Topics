package com.dao;

import java.util.List;

import com.entity.Grade;

public interface IGradeDao {
	/**
	 * 查看年级
	 * @param departmentId
	 * @return
	 */
	public List<Grade> viewGrades(Long departmentId);
	
	
}
