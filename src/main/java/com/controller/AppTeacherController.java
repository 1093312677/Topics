package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.common.ServerResponse;
import com.dto.GroupAndTime;
import com.entity.TeacherAutoSelect;
import com.entity.Topics;
import com.jsonPo.GuideStudents;
import com.service.TopicService;

/**
 * 手机端教师相关操作
 * @author kone
 *
 */
@Controller
@RequestMapping("/app/teacher")
public class AppTeacherController {
	@Autowired
	private TopicService topicService;
	
	/**
	 * 教师查看自己出的题目 app
	 * @param teacherId
	 * @param gradeId
	 * @param status 题目不同状态1通过审核，2在审核中，3未通审核
	 * @return
	 */
	@RequestMapping("/teacherViewTopicsApp")
	@ResponseBody
	public ServerResponse<List<Topics>> teacherViewTopicsApp(String teacherId, String gradeId, int status) {
		
		
		return topicService.teacherViewTopicsApp(teacherId, gradeId, status);
	}
	
	/**
	 * 教师查看分组和答辩时间和地点
	 * @param teacherId
	 * @return
	 */
	@RequestMapping("/teacherViewTimeAndPlaceApp")
	@ResponseBody
	public ServerResponse<GroupAndTime> teacherViewTimeAndPlaceApp(String teacherId, String gradeId) {
		
		
		return topicService.viewTeacherGroup(teacherId, gradeId);
	}
	
	/**
	 * 教师查看指导学生
	 * @param teacherId
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/viewGuideStudents")
	@ResponseBody
	public ServerResponse<List<GuideStudents>> viewGuideStudents(String teacherId, String gradeId) {
		return topicService.viewGuideStudents(teacherId, gradeId);
	}
	
	/**
	 * 查询教师自动选择学生
	 * @param teacherId
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/viewAutoSelect")
	@ResponseBody
	public ServerResponse<TeacherAutoSelect> viewAutoSelect(String teacherId, String gradeId) {
		return topicService.viewAutoSelect(teacherId, gradeId);
	}
	
	/**
	 * 更新教师自动选择学生
	 * @param teacherId
	 * @param status 是否自动选题
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/updateAutoSelect")
	public String updateAutoSelect(HttpServletResponse response, int status, String teacherId, String gradeId) {
		int result = topicService.updateAutoSelect(teacherId, gradeId, status);
		JSONObject json  = new JSONObject();
		json.put("status", result);
		if(result == 200) {
			json.put("message", "success");
		} else {
			json.put("message", "failed");
		}
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
