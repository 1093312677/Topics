package com.guo.service;

import java.util.List;

import com.entity.Direction;

public interface IDirectionService {
	public void closeSession();
	public Direction get(long directionId);
	public int updateInfo(Direction direction);
	public int inspection(String directionName,long specialtyId);
	public List<Direction> directionsByGrade(long gradeId);
	public List<Direction> findDirection(long departmentId);
}
