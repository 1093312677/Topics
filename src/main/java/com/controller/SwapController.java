package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.Topics;
import com.service.SwapService;

/**
 * 调配的相关设置，系内调配，教师调配
 * @author kone
 * 2017-4-3
 */
@Controller
@RequestMapping("/swap")
public class SwapController {
	@Autowired
	private SwapService swapService;
	/**
	 * 查看当前系内调配情况
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewDepartSwap")
	public String viewDepartSwap(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		return "student/swapInDepart";
	}
	/**
	 * 修改系内调配
	 * @param state
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/changeSwapInDepart")
	public String changeSwapInDepart(String state, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Student student = (Student) session.getAttribute("student");
		String id = String.valueOf(student.getId());
		if(swapService.swapInDepart(state, id)){
//				修改成功后更新session里面的信息
			student.setSwapInDepa(Integer.valueOf(state));
			session.setAttribute("student", student);
			JSONObject json = new JSONObject();
			json.put("result", 1);
			try {
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} 
			
		JSONObject json = new JSONObject();
		json.put("result", 1);
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	/**
	 * 修改教师调配
	 * @param state
	 * @param intenId 意向题目列表id
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/changeSwapInTeacher")
	public String changeSwapInTeacher(String intenId, String state, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		if(swapService.swapInTeacher(state, intenId)){
			try {
				response.getWriter().print(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().print(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	/**
	 * 留言
	 * @param id
	 * @param message
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/leavMessage")
	public String leavMessage(String id, String message, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		if(swapService.leavMessage(id, message)){
			try {
				response.getWriter().print(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().print(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 教师调配前选择题目
	 * @param directionId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewSwapTeacher")
	public String viewSwapTeacher(long studentId, long directionId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		session.setAttribute("studentId", studentId);
		List<Topics> topics = null;
		if(teachers.size()  > 0 ) {
			topics = swapService.viewSwapTeacher(teachers.get(0).getId(), directionId);
		}
		request.setAttribute("topics", topics);
		return "swap/swapTopics";
	}
	
	/**
	 * 教师调剂学生
	 * @param topicId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/swapTeacher")
	public String swapTeacher(String type, long topicId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		long studentId = (long) session.getAttribute("studentId");
		Long gradeId = (Long) session.getAttribute("gradeId");
		String path = null;
		if("depart".equals(type)) {
		} else {
			path = "teacher/viewStudentSelectedIntent.do?gradeId="+gradeId;
		}
		JSONObject json = new JSONObject();
		
		if(swapService.swapTeacher(topicId, studentId)){
			json.put("result", 1);
			try {
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			json.put("result", 0);
			try {
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 系主任查看还未选题的学生
	 * @param gradeId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewSwapStudentDept")
	public String viewSwapStudentDept(long gradeId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		int page = 0;
		int eachPage = 100000;
		
		List<Student> students = swapService.viewSwapStudentDept(gradeId, page, eachPage);
		request.setAttribute("sutdents", students);
		session.setAttribute("gradeId", gradeId);
		return "swap/viewSwapStudent";
	}
	/**
	 * 获取该方向对应的题目
	 * @param directionId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewSwapTopicDept")
	public String viewSwapTopicDept(long studentId, long directionId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		int page = 0;
		int eachPage = 100;
		List<Topics> topics = swapService.viewSwapTopicDept(directionId, page, eachPage);
		request.setAttribute("topics", topics);
		session.setAttribute("studentId", studentId);
		return "swap/viewSwapTopicDept";
	}
	
	
	/**
	 * 修改系内调配APP
	 * @param state
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/changeSwapInDepartApp")
	public String changeSwapInDepartApp(String state, String userId, HttpServletRequest request,HttpServletResponse response){
		if(swapService.swapInDepart(state, userId)){
			JSONObject json = new JSONObject();
			json.put("data", "1");
			try {
				response.getWriter().print(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			JSONObject json = new JSONObject();
			json.put("data", "0");
			try {
				response.getWriter().print(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
		
}
