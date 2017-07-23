package com.guo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Student;
import com.guo.dao.IStudentDao;

import com.guo.service.IStudentService;

@Service(value="studentService1")
public class StudentService implements IStudentService {
	
	@Resource(name="studentDao1")
	IStudentDao studentDao;
	
	@Override
	public Student gets(String no) {
		
		return studentDao.gets(no);
	}

	@Override
	public int update(Student stu) {
		return studentDao.update(stu);	 
	}

	@Override
	public Student get(int id) {
		return studentDao.get(id);
	}

	@Override
	public void closeSession() {
		studentDao.closeSession();
	}

	@Override
	public int updateInfo(Student stu, long clazzId) {
		studentDao.updateInfo(stu, clazzId);
		return 0;
	}

	@Override
	public List<Student> getStudents(String name,String queryBy){

		return studentDao.getStudents(name,queryBy);
	}

}
