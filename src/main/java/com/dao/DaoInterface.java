package com.dao;

import java.util.List;

public interface DaoInterface<T> {
	public boolean save(T entity);
	public boolean delete(T entity);
	public List view(String gradeId);
	public List view(String id, int page, int eachPage);
	public boolean calcelGroup(String id);
	public List findById(String id);
	public boolean update(T entity);
	
	public boolean swapInDepart(String state, String id);
	public boolean swapInTeacher(String state, String id);
	public boolean leavMessage(String id, String message);
	
	public List findTeacherGroup(String teacherId);
	public List findStuTeachGroup(long gradeId, long teacherId );
	
	public List findBy(String table, String col, String value);
	public List findBy(String table, String col, String value, int eachPage, int page);
	
	public int getTeacherNum(long departmentId);
//	获取老师可设置学生人数
	public List getTeacherStuNum(String gradeId, long teacherId);
	
	public int getNum(String table, String col, String value);
}
