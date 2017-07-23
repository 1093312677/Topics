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
	public List<Teacher> teachers(String primaryKey, String findby){
		return teacherDao.teachers(primaryKey, findby);
	}
	
}
