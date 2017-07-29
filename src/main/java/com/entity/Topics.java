package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 * 毕业选题实体类
 * @author kone
 * 2017-1-13
 */
@Entity
@Table(name="topics")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
public class Topics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String topicsName;//题目名称
	@Column(columnDefinition="TEXT")
	private String introduce;//题目简介
	private String time;//发布时间
	@ManyToMany(fetch=FetchType.LAZY,targetEntity = Direction.class,cascade={CascadeType.ALL})
	@JoinTable(name = "t_topic_direction",
		joinColumns={@JoinColumn(name="topics_id")},   //本表与中间表的外键对应关系
		inverseJoinColumns={@JoinColumn(name="directions_id")}) //另一张表与中间表的外键的对应关系
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Direction> directions = new ArrayList<Direction>();
	
	private int selectedStudent;//学生选择个数
	private int enableSelect;//可选学生个数
	private int state;//题目状态，1通过审核，2在审核中，3未通审核
	private String taskBookName;//任务书名称
	private int intentionNumber;
//	和老师实现多对一关系
	@ManyToOne
	@JoinColumn(name="teacherId")
	@Basic(fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Teacher teacher;
	
//	实现和意向题目的一对多关联
	@OneToMany(mappedBy="topic",cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<IntentionTopic> intentionTopics = new ArrayList<IntentionTopic>();
	
//	选题和学生一对多
	@OneToMany(mappedBy="topics", fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Student> students = new ArrayList<Student>();
	
//	和年级实现多对一
	@ManyToOne
	@JoinColumn( name = "gradeId")
	@Basic(fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Grade grade;
	
//	和子题目实现一对多
	@OneToMany(mappedBy="topic",cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<SubTopic> subTopic = new ArrayList<SubTopic>(); 
	
	public Topics() {
		super();
	}
	
	
	public Topics(long id, String topicsName, String introduce, String time, List<Direction> directions,
			int selectedStudent, int enableSelect, int state, String taskBookName, int intentionNumber) {
		super();
		this.id = id;
		this.topicsName = topicsName;
		this.introduce = introduce;
		this.time = time;
		this.directions = directions;
		this.selectedStudent = selectedStudent;
		this.enableSelect = enableSelect;
		this.state = state;
		this.taskBookName = taskBookName;
		this.intentionNumber = intentionNumber;
	}


	public Topics(long id, String topicsName, String introduce, int selectedStudent, int enableSelect,
			String taskBookName, int intentionNumber) {
		super();
		this.id = id;
		this.topicsName = topicsName;
		this.introduce = introduce;
		this.selectedStudent = selectedStudent;
		this.enableSelect = enableSelect;
		this.taskBookName = taskBookName;
		this.intentionNumber = intentionNumber;
	}


	public Topics(long id, String topicsName, String introduce, String taskBookName) {
		super();
		this.id = id;
		this.topicsName = topicsName;
		this.introduce = introduce;
		this.taskBookName = taskBookName;
	}


	public Topics(long id, String topicsName, int selectedStudent, int enableSelect) {
		super();
		this.id = id;
		this.topicsName = topicsName;
		this.selectedStudent = selectedStudent;
		this.enableSelect = enableSelect;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTopicsName() {
		return topicsName;
	}
	public void setTopicsName(String topicsName) {
		this.topicsName = topicsName;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getSelectedStudent() {
		return selectedStudent;
	}
	public void setSelectedStudent(int selectedStudent) {
		this.selectedStudent = selectedStudent;
	}
	public int getEnableSelect() {
		return enableSelect;
	}
	public void setEnableSelect(int enableSelect) {
		this.enableSelect = enableSelect;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getTaskBookName() {
		return taskBookName;
	}
	public void setTaskBookName(String taskBookName) {
		this.taskBookName = taskBookName;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public List<IntentionTopic> getIntentionTopics() {
		return intentionTopics;
	}
	public void setIntentionTopics(List<IntentionTopic> intentionTopics) {
		this.intentionTopics = intentionTopics;
	}
	public int getIntentionNumber() {
		return intentionNumber;
	}
	public void setIntentionNumber(int intentionNumber) {
		this.intentionNumber = intentionNumber;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public List<SubTopic> getSubTopic() {
		return subTopic;
	}
	public void setSubTopic(List<SubTopic> subTopic) {
		this.subTopic = subTopic;
	}


	public List<Direction> getDirections() {
		return directions;
	}


	public void setDirections(List<Direction> directions) {
		this.directions = directions;
	}


	
	
}