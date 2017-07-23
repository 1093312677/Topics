package com.guo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Specialty;
import com.guo.dao.ISpecialtyDao;
import com.guo.service.ISpecialtyService;

@Service(value="specialtyService1")
public class SpecialtyService implements ISpecialtyService {
	@Resource(name="specialtyDao1")
	ISpecialtyDao specialtyDao;
	@Override
	public Specialty get(int specialtyId) {
		return specialtyDao.get(specialtyId);
	}

	@Override
	public void closeSession() {
		specialtyDao.closeSession();
	}

	@Override
	public int updateInfo(Specialty specialty) {
		specialtyDao.updateInfo(specialty);
		return 0;
	}

}
