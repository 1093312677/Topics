package com.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="stuTeachGroup")
public class StuTeachGroup {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
//	和教师实现多对一
	@ManyToOne
	@JoinColumn(name="teacherId")
	@Basic(fetch=FetchType.LAZY)
	private Teacher teacher;
//	和学生实现多对一
	@OneToOne
	@JoinColumn(name="studentId")
	@Basic(fetch=FetchType.LAZY)
	private Student student;
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
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	
	
}
