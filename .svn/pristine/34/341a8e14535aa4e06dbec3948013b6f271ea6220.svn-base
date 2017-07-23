package com.guo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.College;
import com.guo.dao.ICollegeDao;
import com.guo.service.ICollegeService;
@Service(value="collegeService1")
public class CollegeService implements ICollegeService {
	@Resource(name="collegeDao1")
	ICollegeDao collegeDao;
	@Override
	public int inspection(String collegeName) {
		List<College>colleges=collegeDao.inspection(collegeName);
		int n=0;
		if(colleges.size()>0) n=1;
		return n;
	}

	@Override
	public void cloesSession() {
		collegeDao.cloesSession();
	}

}
