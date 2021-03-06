package com.guo.dao;

import java.util.List;

import com.entity.Student;
import com.entity.Teacher;

public interface ITeacherDao {
	public Teacher get(String no);
	public Teacher get(int id);
	public int update(Teacher  teacher);
	public void closeSession();
	public int updateInfo(Teacher  teacher);
	public int updateAllInfo(Teacher  teacher,long dempartmentId);
	public List<Teacher> teachers(String primaryKey,String findby,long departmentId);
	public List<Teacher> inspection(String teacherno);
}
