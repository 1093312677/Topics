package com.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 限制教师指导人数
 * @author kone
 * 2017.3.28
 */
@Entity
@Table(name="limitNumber")
public class LimitNumber {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private int number;//限制的人数
	private int alreadyNumber;//已经选题的人数
	
//	教师在某一个年级的人数限制
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="gradeId")
	private Grade grade;
	
//	和教师实现一对一
	@ManyToOne
	@JoinColumn(name="teacherId")
	@Basic(fetch=FetchType.LAZY)
	private Teacher teacher;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getAlreadyNumber() {
		return alreadyNumber;
	}
	public void setAlreadyNumber(int alreadyNumber) {
		this.alreadyNumber = alreadyNumber;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	

}
