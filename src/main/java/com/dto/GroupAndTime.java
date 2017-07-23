package com.dto;
/**
 * App端用来查询教师分组和答辩时间地点
 * @author kone
 * 2017.7.16
 */
public class GroupAndTime {
	private String groupName;//分组名称
	private String time;
	private String place;
	
	public GroupAndTime() {
		super();
	}
	public GroupAndTime(String groupName, String time, String place) {
		super();
		this.groupName = groupName;
		this.time = time;
		this.place = place;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	
	
}
