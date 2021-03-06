package com.guo.dao;

import java.util.List;

import com.entity.Direction;

public interface IDirectionDao {
	public void closeSession();
	public Direction get(long directionId);
	public int updateInfo(Direction direction);
	public List<Direction> directionsByGrade(long gradeId);
	public List<Direction> inspection(String directionName,long specialtyId);
	public List<Direction> findDirection(String year,long departmentId);
}
