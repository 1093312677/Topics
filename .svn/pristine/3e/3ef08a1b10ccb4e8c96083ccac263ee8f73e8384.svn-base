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
 * 课程成绩
 * @author kone
 *	2017-1-13
 */
@Entity
@Table(name="courseGrade")
public class CourseGrade {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private float score;//课程成绩分数
//	和学生实现一对多
	@ManyToOne
	@JoinColumn(name="studentId")
	@Basic(fetch=FetchType.LAZY)
	private Student student;
	
//	和课程实现一对一
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="courseId")
	private Course course;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
