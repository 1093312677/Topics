package com.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 课程成绩(更新)
 * @author kone
 *	2017-3-28
 */
@Entity
@Table(name="courseAndGrade")
public class CourseAndGrade {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String no;
	private String name;
	private String courseName;//课程名称
	private String courseNature;//课程性质
	private float credit;//学分
	private float score;//课程成绩分数
//	和年级实现多对一
	@ManyToOne
	@JoinColumn(name="gradeId")
	@Basic(fetch=FetchType.LAZY)
	private Grade grade;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseNature() {
		return courseNature;
	}
	public void setCourseNature(String courseNature) {
		this.courseNature = courseNature;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	
	
}
