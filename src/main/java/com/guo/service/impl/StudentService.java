package com.guo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Student;
import com.entity.Topics;
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
	public List<Student> getStudents(String name,String queryBy,long gradeId){

		return studentDao.getStudents(name,queryBy,gradeId);
	}

	@Override
	public int inspection(String studentno) {
		int n=0;
		List<Student>students=studentDao.getStudents(studentno, "no",1);
		if(students.size()>0) n=1;
		return n;
	}

	@Override
	public List<Topics> findTopicBy(String pk, String findType, long directionId) {
		List<Topics>topics=studentDao.findTopicBy(pk, findType, directionId);
		return topics;
	}

	@Override
	public boolean isSelected(long id) {
		try {
			Student student=studentDao.isSelected(id);
			if(student.getTopics()!=null) return true;
		} finally {
			studentDao.closeSession();
		}		
		return false;
	}

}
