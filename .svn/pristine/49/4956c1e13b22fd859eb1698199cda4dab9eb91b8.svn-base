package com.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.common.Pagination;
import com.dto.DealData;
import com.entity.Group;
import com.entity.Teacher;
import com.service.LimitService;

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
	
	public LimitService getLimitService() {
		return limitService;
	}


	public void setLimitService(LimitService limitService) {
		this.limitService = limitService;
	}


	/**
	 * 查看老师
	 * @param request
	 * @param response
	 * @param session
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/viewTeacher")
	public String viewTeacher(HttpServletRequest request,HttpServletResponse response,HttpSession session,String gradeId){
		List<Teacher> infor = (List<Teacher>) session.getAttribute("infor");
		if(infor.size() > 0 ){
//			List<Teacher> teachers = limitService.viewTeacher(gradeId);
//			teachers.get(0).getLimitNumbers().get(0).getTeacher().get
//			request.setAttribute("groups", groups);
			session.setAttribute("gradeId", gradeId);
		}
		
		
		return "group/viewGroup";
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
		int eachPage = 2;
		pagination.setEachPage(eachPage);
		List<Teacher> teachers2 = (List<Teacher>) session.getAttribute("infor");
		
		List<Teacher> teachers = null;
		
		if(teachers2.size() > 0) {
			int count = limitService.getTeacherCount(teachers2.get(0).getDepartment().getId());//获取总记录数
			
			if(gradeId == null) {
				gradeId = (String) session.getAttribute("gradeId");
			}
			
			teachers =  limitService.viewTeacher(gradeId, String.valueOf(teachers2.get(0).getDepartment().getId() ), pagination.getPage()*eachPage, eachPage);
//			设置总条数
			pagination.setTotleSize(count);
//			处理分页数据
			pagination = dealData.getPagination(teachers, pagination);
		}
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		修改人数
		} else {
			try {
				response.getWriter().println(limitService.updateNumber(number, limitId));
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		if(teachers.size() > 0 ) {
			long teacherId = teachers.get(0).getId();
			int num = limitService.getTeacherNum(gradeId, teacherId);
			try {
				response.getWriter().println(num);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
