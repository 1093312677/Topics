package com.guo.dao;

import java.util.List;

import com.entity.Grade;

public interface IGradeDao {
	public Grade getgrade(long gradeId);
	public void  closeSession();
	public int updateInfo(Grade grade);
	public List<Grade>inspection(long departmentId,String gradeName);
}
