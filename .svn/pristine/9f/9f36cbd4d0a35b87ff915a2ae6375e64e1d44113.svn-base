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
import javax.persistence.Table;

@Entity
@Table(name="specialty")
/**
 * 专业实体类
 * @author kone
 * 2017-1-7
 */
public class Specialty {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String specialtyName;
	
////	和部门实现多对一
//	@ManyToOne
//	@JoinColumn(name="departmentId")
//	@Basic(fetch=FetchType.EAGER)
//	private Department department;
	
//	和年级实现多对一
	@ManyToOne
	@JoinColumn(name="gradeId")
	private Grade grade;
	
//	和方向实现一对多
	@OneToMany(mappedBy="spceialty",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<Direction> directions = new ArrayList<Direction>();
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSpecialtyName() {
		return specialtyName;
	}
	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}
	public List<Direction> getDirections() {
		return directions;
	}
	public void setDirections(List<Direction> directions) {
		this.directions = directions;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
}
