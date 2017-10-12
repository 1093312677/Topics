package com.dao;

import java.util.List;

import org.hibernate.Session;

import com.entity.Grade;
import com.entity.SubTopic;
import com.entity.TeacherAutoSelect;
import com.entity.TeacherGroup;
import com.entity.Topics;

public interface ITopicDao {
	/**
	 * 教师查看自己出的题目 app
	 * @param teacherId
	 * @param gradeId
	 * @param status 题目不同状态1通过审核，2在审核中，3未通审核
	 * @return
	 */
	public List<Topics> teacherViewTopicsApp(String teacherId, String gradeId, int status);
	
	/**
	 * 教师查看之前查看年级 app
	 * @param departmentId
	 * @return
	 */
	public List<Grade> teacherViewGradesApp(String departmentId) ;
	
	/**
	 * 教师查看分组和答辩时间和地点
	 * @param teacherId
	 * @return
	 */
	public TeacherGroup viewTeacherGroup(String teacherId, String gradeId);
	
	/**
	 * 查看教师的指导学生
	 * @param teacherId
	 * @param gradeId
	 * @return
	 */
	public List<Topics> viewGuideStudents(String teacherId, String gradeId);
	
	/**
	 * 手机端查看教师是否设置自动选择学生
	 * @param teacherId
	 * @param gradeId
	 * @return
	 */
	public TeacherAutoSelect viewAutoSelect(String teacherId, String gradeId);
	
	
	/**
	 * 更新教师自动选择学生APP
	 * @param teacherId
	 * @param status 是否自动选题
	 * @param gradeId
	 * @return
	 */
	public boolean updateAutoSelect(String teacherId, String gradeId, int status);
	
	/**
	 * 如果不存在保存自动选择学生选项APP
	 * @param teacherAutoSelect
	 * @return
	 */
	public boolean addAutoSelect(TeacherAutoSelect teacherAutoSelect);
	
	/**
	 * 系主任一键选题查找能够选择的题目
	 * @param session
	 * @param gradeId
	 * @return
	 */
	public List<Topics> getTopics(Session session, String gradeId);
	
	/**
	 * 学生查看题目数量
	 * @param directionId
	 * @return
	 */
	public Integer getTopicCount(Long directionId);
	
	/**
	 * 学生查看题目
	 * @param directionId
	 * @param batch
	 * @return
	 */
	public List<Topics> studentGetTopics(Long directionId, int batch, int num, int size);
	
	/**
	 * 学生查看自己的题目
	 * @param studentId
	 * @return
	 */
	public Topics getStudentTopic(Long studentId);
	
	/**
	 * 获取学生子题目
	 * @param studentId
	 * @return
	 */
	public SubTopic getStudentSubTopic(Long studentId);

	public List<Topics> findByTwo(String string, String string2, String valueOf, String string3, String state);

	public int viewTopicNum(String gradeId, String state);

	public List<Topics> viewTopic(String gradeId, String state, int page, int eachPage);

	public boolean withdrawalTopic(String studentId, String topicId);

	public Topics viewTopicDetials(String topicId);
	
}
