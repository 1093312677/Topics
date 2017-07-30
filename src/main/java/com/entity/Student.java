package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="student")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
/**
 * 学生实体类
 * @author kone
 * 2017-1-7
 */
public class Student{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String no;//学号
	private String name;
	private String sex;
	private String qq;
	private String telephone;
	private String email;
	private String remark;
	private int swapInDepa;//是否允许在系内调配 默认为0，表示允许调配，1为不允许调配
//	学生和班级之间实现多对一
	@ManyToOne
	@JoinColumn(name="clazzId")
	@Basic(fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Clazz clazz;
	
	
//	学生和选题之间实现一对一关系
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="topicsId")
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Topics topics;
	
//	和成绩实现一对一关系
	@OneToOne(mappedBy="student",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Score score;
	
//	和意向题目实现一对多关系
	@OneToMany(mappedBy="student",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<IntentionTopic> intentionTopics = new ArrayList<IntentionTopic>();
	
//	和课程成绩实现一对多
	@OneToMany(mappedBy="student",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<CourseGrade> courseGrades = new ArrayList<CourseGrade>();
	
//	和登录表一对一
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userId")
	@Basic(fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private User user;
	
//	学生和子题目实现一对一
	@OneToOne(mappedBy="student")
	@Basic(fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private SubTopic subTopic;
//	和学生教师分组表实现一对多
	@OneToOne(mappedBy="student",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private StuTeachGroup stuTeachGroup;
	
//	和文档实现一对一关系
	@OneToOne(mappedBy="student",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Form form;
	
	public Student(Long id, String no, String name, Topics topics) {
		super();
		this.id = id;
		this.no = no;
		this.name = name;
		this.topics = topics;
	}

	public Student(Long id, String no, String name) {
		super();
		this.id = id;
		this.no = no;
		this.name = name;
	}
	
	

	public Student(Long id, String no, String name, String sex, String qq, String telephone, String email,
			String remark, int swapInDepa) {
		super();
		this.id = id;
		this.no = no;
		this.name = name;
		this.sex = sex;
		this.qq = qq;
		this.telephone = telephone;
		this.email = email;
		this.remark = remark;
		this.swapInDepa = swapInDepa;
	}

	public Student(String no) {
		super();
		this.no = no;
	}

	public Student() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public Topics getTopics() {
		return topics;
	}

	public void setTopics(Topics topics) {
		this.topics = topics;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public List<IntentionTopic> getIntentionTopics() {
		return intentionTopics;
	}

	public void setIntentionTopics(List<IntentionTopic> intentionTopics) {
		this.intentionTopics = intentionTopics;
	}

	public List<CourseGrade> getCourseGrades() {
		return courseGrades;
	}

	public void setCourseGrades(List<CourseGrade> courseGrades) {
		this.courseGrades = courseGrades;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSwapInDepa() {
		return swapInDepa;
	}

	public void setSwapInDepa(int swapInDepa) {
		this.swapInDepa = swapInDepa;
	}

	public SubTopic getSubTopic() {
		return subTopic;
	}

	public void setSubTopic(SubTopic subTopic) {
		this.subTopic = subTopic;
	}

	public StuTeachGroup getStuTeachGroup() {
		return stuTeachGroup;
	}

	public void setStuTeachGroup(StuTeachGroup stuTeachGroup) {
		this.stuTeachGroup = stuTeachGroup;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
