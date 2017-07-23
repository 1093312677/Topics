package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
		List<Student> students = (List<Student>) session.getAttribute("infor");
		if(students.size() > 0) {
			String id = String.valueOf(students.get(0).getId());
			if(swapService.swapInDepart(state, id)){
				request.setAttribute("message", "修改成功");
				request.setAttribute("path", "swap/viewDepartSwap.do");
				
//				修改成功后更新session里面的信息
				students.get(0).setSwapInDepa(Integer.valueOf(state));
				session.setAttribute("infor", students);
				return "common/success";
			} else {
				request.setAttribute("message", "修改失败");
				request.setAttribute("path", "swap/viewDepartSwap.do");
				return "common/failed";
			}
		}
		request.setAttribute("message", "修改失败");
		request.setAttribute("path", "swap/viewDepartSwap.do");
		return "common/failed";
		
		
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
			request.setAttribute("message", "修改成功");
			request.setAttribute("path", "student/viewIntentionTopic.do");
			return "common/success";
		} else {
			request.setAttribute("message", "修改失败");
			request.setAttribute("path", "student/viewIntentionTopic.do");
			return "common/failed";
		}
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
			request.setAttribute("message", "留言成功");
			request.setAttribute("path", "student/viewIntentionTopic.do");
			return "common/success";
		} else {
			request.setAttribute("message", "留言失败");
			request.setAttribute("path", "student/viewIntentionTopic.do");
			return "common/failed";
		}
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
		String gradeId = (String) session.getAttribute("gradeId");
		String path = null;
		if("depart".equals(type)) {
			path = "swap/viewSwapStudentDept.do?gradeId="+gradeId;
		} else {
			path = "teacher/viewStudentSelectedIntent.do?gradeId="+gradeId;
		}
		if(swapService.swapTeacher(topicId, studentId)){
			request.setAttribute("message", "调剂成功");
			request.setAttribute("path", path);
			return "common/success";
		} else {
			request.setAttribute("message", "调剂失败");
			request.setAttribute("path", path);
			return "common/failed";
		}
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
	public String swapStudentDept(long gradeId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		int page = 0;
		int eachPage = 100000;
		
		List<Student> students = swapService.viewSwapStudentDept(gradeId, page, eachPage);
		request.setAttribute("sutdents", students);
		session.setAttribute("gradeId", String.valueOf(gradeId));
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
	
	
		
}
