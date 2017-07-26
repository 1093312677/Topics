package com.dao;

import com.entity.TeacherAutoSelect;

public interface ITeacherAutoSelectDao {
	/**
	 * 查看教师是否在该年级设置自动选题
	 * @param gradeId
	 * @param teacherId
	 * @return
	 */
	public TeacherAutoSelect getTeacherAutoSelect(Long gradeId, Long teacherId);
	
	/**
	 * 更新教师自动选题
	 * @param state
	 * @param id
	 * @return
	 */
	public boolean updateAutoSelect(int state, Long id);
}
