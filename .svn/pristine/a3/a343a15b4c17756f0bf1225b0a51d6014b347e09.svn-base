package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * 答辩时间地点表
 * @author kone
 * 2017.4.4
 */
@Entity
@Table(name="timeAndPlace")
public class TimeAndPlace {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String time;
	private String place;
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="groupId")
	private Group group;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	
}
