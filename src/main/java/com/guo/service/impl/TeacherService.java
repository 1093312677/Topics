package com.guo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Teacher;
import com.guo.dao.ITeacherDao;
import com.guo.service.ITeacherService;

@Service(value="teacherService1")
public class TeacherService implements ITeacherService {
	@Resource(name="teacherDao1")
	ITeacherDao teacherDao;
	@Override
	public Teacher get(String no) {	
		return teacherDao.get(no);
	}

	@Override
	public int update(Teacher t) {
		return teacherDao.update(t);
	}

	@Override
	public void closeSession() {
		teacherDao.getClass();
	}

	@Override
	public int updateInfo(Teacher t) {
		teacherDao.updateInfo(t);
		return 0;
	}

	@Override
	public Teacher get(int id) {
		return teacherDao.get(id);
	}

	@Override
	public List<Teacher> teachers(String primaryKey, String findby,long departmentId){
		return teacherDao.teachers(primaryKey, findby,departmentId);
	}

	@Override
	public int updateAllInfo(Teacher teacher, long dempartmentId) {
		teacherDao.updateAllInfo(teacher, dempartmentId);
		return 0;
	}

	@Override
	public int inspection(String teacherno) {
		int n=0;
		List<Teacher>teachers=teacherDao.inspection(teacherno);
		if(teachers.size()>0) n=1;
		return n;
	}
	
}
