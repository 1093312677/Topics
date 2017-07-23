package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 学生毕业成绩
 * @author kone
 * 2017-1-13
 */
@Entity
@Table(name="score")
public class Score {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private float score;
	
	private float mediumScore;//中期成绩
	private float headScore;//组内成员评阅成绩
	private String level;//等级
	private String replyScore;
	private float replyResult;//答辩成绩
//	和学生实现一对一关系
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="studentId")
	private Student student;
	
	
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
	public float getMediumScore() {
		return mediumScore;
	}
	public void setMediumScore(float mediumScore) {
		this.mediumScore = mediumScore;
	}
	public float getHeadScore() {
		return headScore;
	}
	public void setHeadScore(float headScore) {
		this.headScore = headScore;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getReplyScore() {
		return replyScore;
	}
	public void setReplyScore(String replyScore) {
		this.replyScore = replyScore;
	}
	public float getReplyResult() {
		return replyResult;
	}
	public void setReplyResult(float replyResult) {
		this.replyResult = replyResult;
	}
	
}
