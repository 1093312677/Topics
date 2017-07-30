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

import com.common.BaseEntity;

@Entity
@Table(name="grade")
/**
 * 年级实体类
 * @author kone
 * 2017-1-7
 */
public class Grade extends BaseEntity implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String gradeName;
	
//	系统自动选择学生
	private int isSelect11;
	private int isSelect12;
	private int isSelect13;
	private int isSelect21;
	private int isSelect22;
	private int isSelect23;
	
//	//和学院实现多对一
//	@ManyToOne
//	@JoinColumn(name="collegeId")
//	@Basic(fetch=FetchType.EAGER)
//	private College college;
//	和专业实现一对多
	@OneToMany(mappedBy="grade",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<Specialty> spceialtys = new ArrayList<Specialty>();
	
	//和部门实现多对一
	@ManyToOne
	@JoinColumn(name="departmentId")
	@Basic(fetch=FetchType.LAZY)
	private Department department;
	
//	和题目实现一对多
	@OneToMany(mappedBy="grade",cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<Topics> topics = new ArrayList<Topics>();
	
//	和设置表实现一对一
	@OneToOne(mappedBy="grade", cascade = CascadeType.ALL)
	@Basic(fetch=FetchType.LAZY)
	private Setting setting;
	
//	和课程实现一对多
	@OneToMany(mappedBy="grade",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<Course> courses = new ArrayList<Course>();	
	
//	和人数限制表实现一对一
	@OneToMany(mappedBy="grade",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private List<LimitNumber> limitNumbers = new ArrayList<LimitNumber>();;
	
//	和分组表实现一对多
	@OneToMany(mappedBy="grade",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<Group> group = new ArrayList<Group>();
//	和学生分组表实现一对多
	@OneToMany(mappedBy="grade",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<StuTeachGroup> stuTeachGroups = new ArrayList<StuTeachGroup>();
	
//	和课程成绩实现一对多
	@OneToMany(mappedBy="grade",cascade={CascadeType.REMOVE,CascadeType.DETACH,CascadeType.ALL},fetch=FetchType.LAZY)
	private List<CourseAndGrade> courseAndGrade = new ArrayList<CourseAndGrade>();
//	和教师自动选题实现一对多
	@OneToMany(mappedBy="grade",cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	private List<TeacherAutoSelect> teacherAutoSelects = new ArrayList<TeacherAutoSelect>();
	
	
	
	
	public Grade() {
		super();
	}

	public Grade(long id, String gradeName) {
		super();
		this.id = id;
		this.gradeName = gradeName;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public Setting getSetting() {
		return setting;
	}
	public void setSetting(Setting setting) {
		this.setting = setting;
	}
	public List<Specialty> getSpceialtys() {
		return spceialtys;
	}
	public void setSpceialtys(List<Specialty> spceialtys) {
		this.spceialtys = spceialtys;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public List<Topics> getTopics() {
		return topics;
	}
	public void setTopics(List<Topics> topics) {
		this.topics = topics;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public List<Group> getGroup() {
		return group;
	}
	public void setGroup(List<Group> group) {
		this.group = group;
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
	public List<CourseAndGrade> getCourseAndGrade() {
		return courseAndGrade;
	}
	public void setCourseAndGrade(List<CourseAndGrade> courseAndGrade) {
		this.courseAndGrade = courseAndGrade;
	}
	public List<TeacherAutoSelect> getTeacherAutoSelects() {
		return teacherAutoSelects;
	}
	public void setTeacherAutoSelects(List<TeacherAutoSelect> teacherAutoSelects) {
		this.teacherAutoSelects = teacherAutoSelects;
	}
	
	
	public int getIsSelect11() {
		return isSelect11;
	}
	public void setIsSelect11(int isSelect11) {
		this.isSelect11 = isSelect11;
	}
	public int getIsSelect12() {
		return isSelect12;
	}
	public void setIsSelect12(int isSelect12) {
		this.isSelect12 = isSelect12;
	}
	public int getIsSelect13() {
		return isSelect13;
	}
	public void setIsSelect13(int isSelect13) {
		this.isSelect13 = isSelect13;
	}
	public int getIsSelect21() {
		return isSelect21;
	}
	public void setIsSelect21(int isSelect21) {
		this.isSelect21 = isSelect21;
	}
	public int getIsSelect22() {
		return isSelect22;
	}
	public void setIsSelect22(int isSelect22) {
		this.isSelect22 = isSelect22;
	}
	public int getIsSelect23() {
		return isSelect23;
	}
	public void setIsSelect23(int isSelect23) {
		this.isSelect23 = isSelect23;
	}
}
