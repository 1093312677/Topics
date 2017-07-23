package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.common.BaseEntity;

@Entity
@Table(name="college")
/**
 * 学院实体类
 * @author kone
 * 2017-1-7
 */
public class College extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String collegeName;
////	和年级实现一对多
//	@OneToMany(mappedBy="college",cascade={CascadeType.REMOVE,CascadeType.DETACH,CascadeType.ALL})
//	private List<Grade> grades = new ArrayList<Grade>();
	
//和系实现一对多
	@OneToMany(mappedBy="college",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<Department> departments = new ArrayList<Department>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public List<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	
}
