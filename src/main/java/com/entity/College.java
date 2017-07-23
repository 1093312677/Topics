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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.BaseEntity;

@Entity
@Table(name="college")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
	
//和系实现一对多
	@OneToMany(mappedBy="college",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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
