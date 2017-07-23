package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 子题目
 * @author kone
 * 2017.3.28
 */
@Entity
@Table(name="subTopic")
public class SubTopic {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String subName;//子题目名称
//	和题目实现多对一
	@ManyToOne
	@JoinColumn( name = "topicId")
	private Topics topic;
//	子题目和学生之间实现一对一
	@OneToOne(mappedBy="subTopic")
	private Student student;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public Topics getTopic() {
		return topic;
	}
	public void setTopic(Topics topic) {
		this.topic = topic;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
