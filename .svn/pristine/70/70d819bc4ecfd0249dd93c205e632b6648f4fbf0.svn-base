package com.guo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Direction;
import com.guo.dao.IDirectionDao;
import com.guo.service.IDirectionService;

@Service(value="directionService1")
public class DirectionService implements IDirectionService {
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

}
