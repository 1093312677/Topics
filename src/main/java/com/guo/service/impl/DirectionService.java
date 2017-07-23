package com.guo.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dto.DealData;
import com.entity.Direction;
import com.guo.dao.IDirectionDao;
import com.guo.service.IDirectionService;

@Service(value="directionService1")
public class DirectionService implements IDirectionService {
	private DealData dealData;
	public DealData getDealData() {
		return dealData;
	}

	public void setDealData(DealData dealData) {
		this.dealData = dealData;
	}
	@Resource(name="directionDao1")
	IDirectionDao directionDao;
	@Override
	public void closeSession() {
		directionDao.closeSession();
	}

	@Override
	public Direction get(long directionId) {
		return directionDao.get(directionId);
	}

	@Override
	public int updateInfo(Direction direction) {
		directionDao.updateInfo(direction);
		return 0;
	}

	@Override
	public int inspection(String directionName, long specialtyId) {
		int n=0;
		List<Direction>directions=directionDao.inspection(directionName, specialtyId);
		if(directions.size()>0) n=1;
		return n;
	}

	@Override
	public List<Direction> directionsByGrade(long gradeId) {
		
		return directionDao.directionsByGrade(gradeId);
	}

	@Override
	public List<Direction> findDirection(long departmentId) {
		/*List<Direction> directions=null;

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		if (month >= 8)
			year = year + 1;
		directions = directionDao.findDirection(String.valueOf(year-3),departmentId);*/
		
		return null;
	}

}
