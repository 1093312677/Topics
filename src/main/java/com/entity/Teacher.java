package com.entity;


import java.io.Serializable;
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

import com.common.BaseEntity;


@Entity
@Table(name="teacher")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
/**
 * 教师实体类
 * @author kone
 * 2017-1-7
 */
public class Teacher extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String no;//工号
	private String name;
	private String sex;
	private String qq;
	private String position;//职称
	private String telephone;
	private String email;
	private String privilege;//权限
	
	private String remark;
//	和系多对一
	@ManyToOne
	@JoinColumn(name="departmentId")
	@Basic(fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Department department;
	
//	和登录表一对一
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userId")
	@Basic(fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private User user;
	
//	和选题实现一对多关系
	@OneToMany(mappedBy="teacher",cascade={CascadeType.REFRESH,CascadeType.REMOVE}, fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Topics> topics = new ArrayList<Topics>();
	
//	和需要查看的课程表实现一对多
	@OneToMany(mappedBy="teacher",cascade={CascadeType.REFRESH,CascadeType.REMOVE}, fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<CheckViewGrade> checkViewGrade = new ArrayList<CheckViewGrade>();
	
//	和教师分组表实现一对多
	@OneToMany(mappedBy="teacher",cascade={CascadeType.REFRESH,CascadeType.REMOVE}, fetch=FetchType.LAZY)
	
	private List<TeacherGroup> teacherGroup = new ArrayList<TeacherGroup>();
//	和学生教师表实现一对多
	@OneToMany(mappedBy="teacher",cascade={CascadeType.REFRESH,CascadeType.REMOVE}, fetch=FetchType.LAZY)
	private List<StuTeachGroup> stuTeachGroups = new ArrayList<StuTeachGroup>();
 	
	
//	和人数限制表实现一对多
	@OneToMany(mappedBy="teacher",cascade={CascadeType.REFRESH,CascadeType.REMOVE}, fetch=FetchType.LAZY)
	private List<LimitNumber> limitNumbers = new ArrayList<LimitNumber>();
	
//	和教师自动选题实现一对多
	@OneToMany(mappedBy="teacher",cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<TeacherAutoSelect> teacherAutoSelects = new ArrayList<TeacherAutoSelect>();
	
	public Teacher() {
	super();
}
	public Teacher(long id, String name) {
	super();
	this.id = id;
	this.name = name;
}
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Topics> getTopics() {
		return topics;
	}
	public void setTopics(List<Topics> topics) {
		this.topics = topics;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<CheckViewGrade> getCheckViewGrade() {
		return checkViewGrade;
	}
	public void setCheckViewGrade(List<CheckViewGrade> checkViewGrade) {
		this.checkViewGrade = checkViewGrade;
	}
	public List<TeacherGroup> getTeacherGroup() {
		return teacherGroup;
	}
	public void setTeacherGroup(List<TeacherGroup> teacherGroup) {
		this.teacherGroup = teacherGroup;
	}
	public List<StuTeachGroup> getStuTeachGroups() {
		return stuTeachGroups;
	}
	public void setStuTeachGroups(List<StuTeachGroup> stuTeachGroups) {
		this.stuTeachGroups = stuTeachGroups;
	}
	public List<LimitNumber> getLimitNumbers() {
		return limitNumbers;
	}
	public void setLimitNumbers(List<LimitNumber> limitNumbers) {
		this.limitNumbers = limitNumbers;
	}
	public List<TeacherAutoSelect> getTeacherAutoSelects() {
		return teacherAutoSelects;
	}
	public void setTeacherAutoSelects(List<TeacherAutoSelect> teacherAutoSelects) {
		this.teacherAutoSelects = teacherAutoSelects;
	}
	
	
}
