package com.guo.service;

import java.util.List;

import com.entity.Student;
import com.entity.Topics;

public interface IStudentService {
	public Student gets(String no);
	public List<Student> getStudents(String name,String queryBy,long gradeId); 
	public Student get(int id);
	public int update(Student  stu);
	public int updateInfo(Student stu,long clazzId);
	public void closeSession();
	public int inspection(String studentno);
	public List<Topics> findTopicBy(String pk,String findType,long directionId);
	public boolean isSelected(long id);
}
