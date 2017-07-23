package com.guo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Grade;
import com.guo.dao.IGradeDao;
import com.guo.dao.impl.GradeDao;
import com.guo.service.IGradeService;

@Service(value="gradeService1")
public class GradeService implements IGradeService {
	@Resource(name="gradeDao1")
	IGradeDao gradeDao;
	@Override
	public Grade getgrade(long gradeId) {
		
		return gradeDao.getgrade(gradeId);
	}

	@Override
	public void closeSession() {
		gradeDao.closeSession();
	}

	@Override
	public int updateInfo(Grade grade) {
		gradeDao.updateInfo(grade);
		return 0;
	}

	@Override
	public int inspection(long departmentId, String gradeName) {
		int n=0;
		List<Grade>grades=gradeDao.inspection(departmentId, gradeName);
		if(grades.size()>0) n=1;
		return n;
	}

}
