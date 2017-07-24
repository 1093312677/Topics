package com.dao;

import java.util.List;

import com.entity.Grade;
import com.entity.Teacher;

public interface ITeacherDao {
	/**
	 * 查看年级
	 * @param departmentId
	 * @return
	 */
	public List<Grade> viewGrades(Long departmentId);
	
	/**
	 * 查看教师
	 * @param departmentId
	 * @return
	 */
	public List<Teacher> viewTeachers(Long departmentId, Integer num, Integer size);
	
	
	/**
	 * 获取教师数量
	 * @param departmentId
	 * @return
	 */
	public Integer getTeachersCount(Long departmentId);
	
	/**
	 * 更新题目状态
	 * @param topicId
	 * @param state
	 * @return
	 */
	public boolean updateTopicState(Long topicId, int state);
}
