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
@Table(name="clazz")
/**
 * 班级实体类
 * @author kone
 * 2017-1-7
 */
public class Clazz {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String className;
//	现实和方向的多对一关联
	@ManyToOne
	@JoinColumn(name="directionId")
	private Direction direction;
	
//	班级和学生之间实现一对多关系
	@OneToMany(mappedBy="clazz",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<Student> students = new ArrayList<Student>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
