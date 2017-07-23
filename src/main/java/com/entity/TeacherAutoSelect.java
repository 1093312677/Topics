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

@Entity
@Table(name="teacherAutoSelect")
public class TeacherAutoSelect {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int autoSelect;
	
//	和教师实现多对一
	@ManyToOne
	@JoinColumn(name="teacherId")
	@Basic(fetch=FetchType.LAZY)
	private Teacher teacher;
	
//	和年级实现多对一 
	@ManyToOne
	@JoinColumn(name="gradeId")
	@Basic(fetch=FetchType.LAZY)
	private Grade grade;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAutoSelect() {
		return autoSelect;
	}
	public void setAutoSelect(int autoSelect) {
		this.autoSelect = autoSelect;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	
}
