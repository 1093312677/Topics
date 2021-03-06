package com.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.BaseEntity;

import java.io.Serializable;

/**
 * 意向题目实体
 * @author kone
 * 2017-1-13
 */
@Entity
@Table(name="intentionTopic")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
public class IntentionTopic extends BaseEntity  implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private int choice;//志愿
	private int batch;//批次
	private int swap;//是否允许调剂
	@Column(columnDefinition="TEXT")
	private String message;//留言
//	和学生实现多对一
	@ManyToOne
	@JoinColumn(name="studentId")
	@Basic(fetch=FetchType.LAZY)
	private Student student;
//	和选题之间实现一对一关系
	@ManyToOne
	@Basic(fetch=FetchType.LAZY)
	@JoinColumn(name="topicId")
	private Topics topic;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
	public int getBatch() {
		return batch;
	}
	public void setBatch(int batch) {
		this.batch = batch;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Topics getTopic() {
		return topic;
	}
	public void setTopic(Topics topic) {
		this.topic = topic;
	}
	public int getSwap() {
		return swap;
	}
	public void setSwap(int swap) {
		this.swap = swap;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
