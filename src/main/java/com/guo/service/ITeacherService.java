package com.guo.service;
import java.util.List;

import com.entity.Teacher;

public interface ITeacherService {
	public Teacher get(String no);
	public Teacher get(int id);
	public int update(Teacher  t);
	public void closeSession(); 
	public int updateInfo(Teacher  t);
	public int updateAllInfo(Teacher  teacher,long dempartmentId);
	public List<Teacher> teachers(String primaryKey,String findby,long departmentId);
	public int inspection(String teacherno);
}
