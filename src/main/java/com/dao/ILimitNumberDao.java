package com.dao;

import java.util.List;

import com.entity.LimitNumber;

public interface ILimitNumberDao {
	/**
	 * 查看教师对应年级的题目数
	 * @param gradeId
	 * @return
	 */
	public List<LimitNumber> getLimitNumbers(Long gradeId, Long teacherId);
	
	/**
	 * 更新题目数
	 * @param limitId
	 * @return
	 */
	public boolean updateLimitNumber(Long limitId, int number);
	
	
}
