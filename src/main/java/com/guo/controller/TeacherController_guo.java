package com.guo.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.dto.DealData;
import com.entity.Student;
import com.entity.Teacher;
import com.guo.service.ITeacherService;


@Controller
@RequestMapping("/teacher")
public class TeacherController_guo {
	@Autowired
	private DealData dealData;
	public DealData getDealData() {
		return dealData;
	}

	public void setDealData(DealData dealData) {
		this.dealData = dealData;
	}
	@Resource(name="teacherService1")
	ITeacherService teacherService;
	@RequestMapping(value="findTeacher1By.do")
	public String infoManege(HttpServletRequest request,HttpServletResponse response,int teacherNo,String type){
		Teacher teacher=teacherService.get(teacherNo);
		try {
			JSONObject json = new JSONObject();
			json.put("teacher", teacher);
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			teacherService.closeSession();
		}
		return null;
	}
	@RequestMapping(value="updateInfo.do")
	public String updateInfo(Teacher teacher,HttpServletRequest request,HttpServletResponse response){
		teacherService.updateInfo(teacher);
		try {
			response.getWriter().println(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	@RequestMapping(value="updateAllInfo.do")
	public String updateAllInfo(Teacher teacher,long dempartmentID,HttpServletRequest request,HttpServletResponse response){
		teacherService.updateAllInfo(teacher, dempartmentID);
		try {
			response.getWriter().println(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	@RequestMapping(value="findTeacherInfo.do")
	public String findTeacherInfo(String findBy,String primaryKey,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		List<Teacher> teachers1 = (List<Teacher>) session.getAttribute("infor");
		long departmentId=teachers1.get(0).getDepartment().getId();
		java.util.List<Teacher>teachers=teacherService.teachers(primaryKey, findBy,departmentId);
		try {
			JSONObject json = new JSONObject();
			json.put("teachers", dealData.dealTeacher(teachers));
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			teacherService.closeSession();
		}
		return null;
		
	}
	@RequestMapping(value="inspection.do")
	public String inspection(String teacherno,HttpServletRequest request,HttpSession session,HttpServletResponse response){
		//List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		//long departmentId=teachers.get(0).getDepartment().getId();
		try {
			response.getWriter().println(teacherService.inspection(teacherno));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			teacherService.closeSession();
		}
		return null;
		
	}
}
