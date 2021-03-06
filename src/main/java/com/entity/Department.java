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
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.BaseEntity;

@Entity
@Table(name="department")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
/**
 * 系实体类
 * @author kone
 * 2017-1-7
 */
public class Department extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String departmentName;
	
//和学院实现多对一
	@ManyToOne
	@JoinColumn(name="collegeId")
	@Basic(fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private College college;
	
//	和年级实现一对多
	@OneToMany(mappedBy="department",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<Grade> grades = new ArrayList<Grade>();
	
//	和老师是实现一对多
	@OneToMany(mappedBy="department",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<Teacher> teachers = new ArrayList<Teacher>();
//	和文档是实现一对多
	@OneToMany(mappedBy="department",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<Document> documents = new ArrayList<Document>();	
	
	
	public Department(long id, String departmentName) {
		super();
		this.id = id;
		this.departmentName = departmentName;
	}

	public Department() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	
}
