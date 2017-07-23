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
import com.common.Pagination;
import com.dto.DealData;
import com.entity.Student;
import com.entity.Teacher;
import com.service.TeachStuService;

/**
 * <p>学生和教师Controller</p>
 * @author kone
 * 2017.4.18
 */
@Controller
@RequestMapping("/teachStu")
public class TeachStuController {
	@Autowired
	private TeachStuService teachStuService;
	@Autowired
	private DealData dealData;
	
	/**
	 * <p>教师查看指导的学生</p>
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewGuideStudent")
	public String viewGuideStudent(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		List<Teacher> teacher = (List<Teacher>) session.getAttribute("infor");
		List<Student> students = null;
		if(teacher.size() > 0) {
			students = teachStuService.viewGuideStudent(teacher.get(0).getId());
		}
		request.setAttribute("students", students);
		return "teacher/viewGuideStudent";
	}
	
	/**
	 * 查看最后选题
	 * @param session
	 * @param request
	 * @param response
	 * @param pageType
	 * @param type
	 * @param pagination
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/viewLastSelect")
	public String viewLastSelect(HttpSession session,HttpServletRequest request,HttpServletResponse response, String pageType, String type,Pagination pagination, String gradeId){
//		将gradeId保存为session，后面返回使用
		session.setAttribute("gradeId", gradeId);
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
//		如果是输入的页数进行减一
		if("1".equals(pageType)) {
			pagination.setPage(pagination.getPage() - 1);
		}
		
		int eachPage = 3;
		pagination.setEachPage(eachPage);
		int totleSize = teachStuService.getStudentsNum(gradeId);
		System.out.println(totleSize);
		pagination.setTotleSize(totleSize);//获取总记录数
		
		List<Student> students = teachStuService.viewStudents(gradeId, pagination.getPage()*eachPage, eachPage);
		
//		处理分页数据
		pagination = dealData.getPagination(students, pagination);
		
		if(type==null){
			type="null";
		}
		if(type.equals("json")){
			try {
				JSONObject json = new JSONObject();
				json.put("students", students);
				response.getWriter().println(json.toString());
				teachStuService.closeSession();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}else{
			request.setAttribute("students", students);
			request.setAttribute("pagination", pagination);
			request.setAttribute("message", "view");
			request.setAttribute("gradeId", gradeId);
//			teachStuService.closeSession();
			return "teacher/viewLastSelect";
		}
	}
}
