package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.JavaWebToken;
import com.dto.AppViewIntentStu;
import com.dto.StudentDTO;
import com.dto.TopicStudent;
import com.entity.IntentionTopic;
import com.service.TeacherService;
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

	@Autowired
	private TeacherService teacherService;
	
	/**
	 * 教师查看自己出的题目 app
	 * @param teacherId
	 * @param gradeId
	 * @param status 题目不同状态1通过审核，2在审核中，3未通审核
	 * @return
	 */
	@RequestMapping("/teacherViewTopicsApp")
	@ResponseBody
	public ServerResponse<List<Topics>> teacherViewTopicsApp(String teacherId, String gradeId, Integer status) {
		
		
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

	/**
	 * 查看选择了意向题目的学生
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/viewStudentSelectedIntent")
	@ResponseBody
	public ServerResponse viewStudentSelectedIntent(Long gradeId, Long teacherId){

		AppViewIntentStu appViewIntentStu = new AppViewIntentStu();
//		获取选题设置
		Set<Topics> topics = null;
		int bc[] = null;
		topics = teacherService.viewSelected(teacherId, gradeId);
		bc = teacherService.getBatchChoice(gradeId);

		TopicStudent topicStudent = null;
		StudentDTO studentDTO = null;
		for(Topics topic : topics) {
			topicStudent = new TopicStudent();
			topicStudent.setTopicName(topic.getTopicsName());
			topicStudent.setTopicId(topic.getId());
			topicStudent.setEnable(topic.getEnableSelect());
			topicStudent.setSelected(topic.getSelectedStudent());
			int count = 0;

			for(IntentionTopic intent : topic.getIntentionTopics()) {
				if(null != intent && null != intent.getStudent()) {
					studentDTO = new StudentDTO();
					studentDTO.setId(intent.getStudent().getId());
					studentDTO.setName(intent.getStudent().getName());
					studentDTO.setNo(intent.getStudent().getNo());

					topicStudent.getStudents().add(studentDTO);
					count ++;
				}
			}

			topicStudent.setNum(count);


			appViewIntentStu.getTopics().add(topicStudent);
		}

		appViewIntentStu.setBatch(bc[0]);
		appViewIntentStu.setChoice(bc[1]);

		return ServerResponse.response(200, "success", appViewIntentStu);
	}

    /**
     * 通过题目id查看当前批次当前志愿的学生
     * @param topicId
     * @return
     */
    @RequestMapping("/viewIntentStudent")
    @ResponseBody
    public ServerResponse viewIntentStudent(Long topicId, Long gradeId){

	    return ServerResponse.response(200, "success", teacherService.viewIntentStudent(topicId, gradeId));
    }


	/**
	 * token验证
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/testToken")
	@ResponseBody
    public ServerResponse testToken(String username, String password) {
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("userId", username);
		String token = JavaWebToken.createJavaWebToken(m);
		System.out.println(token);
    	return ServerResponse.response(200, "success", token);
	}

	@RequestMapping("/vertifyToken")
	@ResponseBody
	public ServerResponse vertifyToken(String token) {
		Map map = JavaWebToken.parserJavaWebToken(token);
		return ServerResponse.response(200, "success", map);
	}
	
}
