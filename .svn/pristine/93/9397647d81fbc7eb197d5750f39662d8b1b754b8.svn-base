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

import com.common.BaseEntity;

@Entity
@Table(name="grade")
/**
 * 年级实体类
 * @author kone
 * 2017-1-7
 */
public class Grade extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String gradeName;
	
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
	@OneToMany(mappedBy="grade",cascade={CascadeType.ALL})
	private List<Topics> topics = new ArrayList<Topics>();
	
//	和设置表实现一对一
	@OneToOne(mappedBy="grade")
	private Setting setting;
	
//	和课程实现一对多
	@OneToMany(mappedBy="grade",cascade={CascadeType.ALL})
	private List<Course> courses = new ArrayList<Course>();	
	
//	和人数限制表实现一对一
	@OneToMany(mappedBy="grade")
	private List<LimitNumber> limitNumbers = new ArrayList<LimitNumber>();;
	
//	和分组表实现一对多
	@OneToMany(mappedBy="grade",cascade={CascadeType.ALL})
	private List<Group> group = new ArrayList<Group>();
//	和学生分组表实现一对多
	@OneToMany(mappedBy="grade",cascade={CascadeType.ALL})
	private List<StuTeachGroup> stuTeachGroups = new ArrayList<StuTeachGroup>();
	
//	和课程成绩实现一对多
	@OneToMany(mappedBy="grade",cascade={CascadeType.REMOVE,CascadeType.DETACH,CascadeType.ALL})
	private List<CourseAndGrade> courseAndGrade = new ArrayList<CourseAndGrade>();
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
	
}
