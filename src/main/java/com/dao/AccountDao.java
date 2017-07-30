package com.dao;

import com.entity.Clazz;
import com.entity.Department;
import com.entity.Direction;
import com.entity.Grade;
import com.entity.Student;
import com.entity.User;

public interface AccountDao {
	public boolean updatePw(String userId, String pw);
	public boolean updateInfor(String table,String qq, String email, String telephone, String userId);
	
	/**
	 * 登录，先查询是否存在该用户名
	 * @param username
	 * @return
	 */
	public User login(String username);
	
	/**
	 * 获取学生的基本信息
	 * @param no
	 * @return
	 */
	public Student getStudentInfo(String no);
	
	/**
	 * 通过学生id获取学生的班级
	 * @param studentId
	 * @return
	 */
	public Clazz getClazzByStudentId(Long studentId);
	
	/**
	 * 通过班级id获取方向信息
	 * @param clazzId
	 * @return
	 */
	public Direction getDirectionByClazzId(Long clazzId);
	
	/**
	 * 通过方向id获取年级信息
	 * @param directionId
	 * @return
	 */
	public Grade getGradeByDirectionId(Long directionId);
	
	/**
	 * 通过年级id获取系id
	 * @param gradeId
	 * @return
	 */
	public Department getDepartmentByGradeId(Long gradeId);
}
