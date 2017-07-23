package com.dao;

import java.util.List;

import com.entity.IntentionTopic;
import com.entity.Student;
import com.entity.Topics;

public interface IStudentDao {
	/**
	 * 根据志愿和批次查询学生是否选择意向题目
	 * @param id 学生id
	 * @param batch
	 * @return
	 */
	public List<IntentionTopic> viewIntentions(Long id, Integer batch);
	
	/**
	 * 学生查看题目
	 * @param directionId
	 * @return
	 */
	public List<Topics> viewTopics(Long directionId);
	
	/**
	 * 获取学生
	 * @param id
	 * @return
	 */
	public Student getStudent(Long id);
	
	/**
	 * 获取未选题的学生
	 * @param gradeId
	 * @param num
	 * @param size
	 * @return
	 */
	public List<Student> getStudentsNotSelect(Long gradeId, Integer num, Integer size);
	
	/**
	 * 获取未选题的学生人数
	 * @param gradeId
	 * @return
	 */
	public Integer getStudentsNotSelectCount(Long gradeId);
	/**
	 * 系主任查看学生最后的选题
	 * @param gradeId
	 * @param num
	 * @param size
	 * @return
	 */
	public List<Student> getStudents(Long gradeId, int num, int size);
	
	/**
	 * 获取最后选题的学生人数
	 * @param gradeId
	 * @return
	 */
	public Integer getLastSelectStudentsCount(Long gradeId);
	
	/**
	 * 关闭session
	 */
	public void closeSession();
	
	/**
	 * 获取学生的基本的信息
	 * @param studentId
	 * @return
	 */
	public Student getStudentBasicInfor(Long studentId);
}
