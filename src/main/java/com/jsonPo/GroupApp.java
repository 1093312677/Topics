package com.jsonPo;

import java.util.ArrayList;
import java.util.List;

import com.entity.Teacher;
/**
 * 用户app学生查看分组
 * @author kone
 *
 */
public class GroupApp {
	private String groupName;
	private List<Teacher> teachers = new ArrayList<Teacher>();
	private String time;
	private String place;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
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
