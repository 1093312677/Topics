package com.guo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Clazz;
import com.guo.dao.IClazzDao;
import com.guo.service.IClazzService;
@Service(value="clazzService1")
public class ClazzService implements IClazzService{
	@Resource(name="clazzDao1")
	IClazzDao clazzDao;
	@Override
	public Clazz get(long clazzId) {
		return clazzDao.get(clazzId);
	}

	@Override
	public void closeSession() {
		clazzDao.closeSession();
	}

	@Override
	public int updateInfo(Clazz clazz) {
		clazzDao.updateInfo(clazz);
		return 0;
	}

	@Override
	public int inspection(String clazzName, long directionId) {
		int n=0;
		List<Clazz>clazzs=clazzDao.inspection(clazzName, directionId);
		if(clazzs.size()>0) n=1;
		return n;
	}

}
