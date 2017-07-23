package com.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 老师分组的分配表
 * @author kone
 * 2017.3.29
 */
@Entity
@Table(name="teacherGroup")
public class TeacherGroup {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int isLeader;//是否是组长
//	和分组表实现多对一
	@ManyToOne
	@JoinColumn(name="groupId")
	@Basic(fetch=FetchType.LAZY)
	private Group group;
	
//	和教师表实现多对一
	@ManyToOne
	@JoinColumn(name="teacherId")
	@Basic(fetch=FetchType.LAZY)
	private Teacher teacher;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(int isLeader) {
		this.isLeader = isLeader;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	
	
}
