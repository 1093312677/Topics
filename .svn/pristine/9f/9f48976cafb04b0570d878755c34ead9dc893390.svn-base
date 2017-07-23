package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 课程
 * @author kone
 *	2017-1-13
 */
@Entity
@Table(name="course")
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String courseName;//课程名称
	private String courseWeek;//行课周数
	private String courseClassify;//课程分类
	private String courseCredit;//课程学分
	private String courseTerm;//行课学期
	private String coursePlan;//教学计划
//	和年级实现多对一
	@ManyToOne
	@JoinColumn( name = "gradeId")
	private Grade grade;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseWeek() {
		return courseWeek;
	}
	public void setCourseWeek(String courseWeek) {
		this.courseWeek = courseWeek;
	}
	public String getCourseClassify() {
		return courseClassify;
	}
	public void setCourseClassify(String courseClassify) {
		this.courseClassify = courseClassify;
	}
	public String getCourseCredit() {
		return courseCredit;
	}
	public void setCourseCredit(String courseCredit) {
		this.courseCredit = courseCredit;
	}
	public String getCourseTerm() {
		return courseTerm;
	}
	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm;
	}
	public String getCoursePlan() {
		return coursePlan;
	}
	public void setCoursePlan(String coursePlan) {
		this.coursePlan = coursePlan;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	
}
