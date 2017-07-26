package com.dao;

import com.entity.Score;

public interface IScoreDao {
	/**
	 * 查看学生成绩
	 * @param gradeId
	 * @param teacherId
	 * @return
	 */
	public Score getScoreParam(Long studentId);
	
	/**
	 * 更新学生成绩
	 * @param score
	 * @return
	 */
	public boolean updateScore(Score score);
	
	/**
	 * 保存
	 * @param score
	 * @return
	 */
	public boolean saveScore(Score score);
}
