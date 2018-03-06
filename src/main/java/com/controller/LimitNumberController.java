package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.Pagination;
import com.dto.DealData;
import com.entity.Teacher;
import com.service.LimitService;
import com.service.TimerService;

/**
 * 题目限制人数相关操作
 * @author kone
 * 2017.4.4
 */
@Controller
@RequestMapping("/limit")
public class LimitNumberController {
	@Autowired
	private LimitService limitService;
	@Autowired
	private DealData dealData;
	@Autowired
	private TimerService timerService;
	public LimitService getLimitService() {
		return limitService;
	}


	public void setLimitService(LimitService limitService) {
		this.limitService = limitService;
	}


	/**
	 * 设置教师出题人数限制之前查看
	 * @param session
	 * @param request
	 * @param response
	 * @param pagination
	 * @param pageType
	 * @return
	 */
	@RequestMapping("/viewSetTeacherTopicNum")
	public String viewSetTeacherTopicNum(String gradeId, HttpSession session, HttpServletRequest request,HttpServletResponse response,Pagination pagination, String pageType){
//		如果是输入的页数进行减一
		if("1".equals(pageType)) {
			pagination.setPage(pagination.getPage() - 1);
		}
		int eachPage = 15;
		pagination.setEachPage(eachPage);
		Long departmentId = (Long)session.getAttribute("departmentId");
		
		List<Teacher> teachers = null;
		
		int count = limitService.getTeacherCount(departmentId);//获取总记录数
		
		if(gradeId == null) {
			gradeId = (String) session.getAttribute("gradeId");
		}
		
		teachers =  limitService.viewTeacher(gradeId, String.valueOf(departmentId ), pagination.getPage()*eachPage, eachPage);
//			设置总条数
		pagination.setTotleSize(count);
//			处理分页数据
		pagination = dealData.getPagination(teachers, pagination);
		request.setAttribute("teachers", teachers);
		request.setAttribute("message", "view");
		request.setAttribute("pagination", pagination);
		
//		保存年级id后面使用
		if(gradeId != null) {
			session.setAttribute("gradeId", gradeId);
		}
		
		return "teacher/viewSetTeacherTopicNum";
	}
	/**
	 * 设置教师可选人数
	 * @param number
	 * @param type  表示是修改还是添加
	 * @param teacherId
	 * @param limitId
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/setNumber")
	public String setNumber(int number, String type, String teacherId, String limitId, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		String gradeId = (String) session.getAttribute("gradeId");
//		设置人数
		if("set".equals(type)) {
			try {
				int result = limitService.setNumber(Long.valueOf(teacherId), Long.valueOf(gradeId), number);
				response.getWriter().println(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
//		修改人数
		} else {
			try {
				response.getWriter().println(limitService.updateNumber(number, limitId));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 获取老师在该年级下的指导学生认识
	 * @param gradeId
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getTeacherNum")
	public String getTeacherNum(String gradeId, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		Long teacherId = (Long)session.getAttribute("teacherId");
		int num = limitService.getTeacherNum(gradeId, teacherId);
		try {
			response.getWriter().println(num);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/auto")
	public String auto(HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		timerService.start();
		return null;
	}
	
}
