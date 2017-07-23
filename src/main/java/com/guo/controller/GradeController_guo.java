package com.guo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.dto.DealData;
import com.entity.Grade;
import com.entity.Specialty;
import com.entity.Teacher;
import com.guo.service.IGradeService;

@Controller
@RequestMapping("/grade")
public class GradeController_guo {
	@Autowired
	private DealData dealData;
	public DealData getDealData() {
		return dealData;
	}

	public void setDealData(DealData dealData) {
		this.dealData = dealData;
	}

	
	@Resource(name="gradeService1")
	IGradeService gradeService;
	
	@RequestMapping(value="findgrade1By.do")
	public String findSpecialtyBy(HttpServletRequest request,HttpServletResponse response,long gradeId){
		Grade grade=gradeService.getgrade(gradeId);
		List<Grade>grades=new ArrayList<>();
		grades.add(grade);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("grades",dealData.dealGradeData(grades));
		try {			
			response.getWriter().println(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//System.out.println(jsonObject.toString());
			gradeService.closeSession();
		}
		return null;	
	}
	@RequestMapping("updateInfo.do")
	public String updateInfo(HttpServletRequest request,HttpServletResponse response,Grade grade){
		gradeService.updateInfo(grade);
		try {
			response.getWriter().println(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("inspection.do")
	public String inspection(HttpServletRequest request,HttpServletResponse response,HttpSession session,String gradeName,long departmentId){
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		departmentId=teachers.get(0).getDepartment().getId();
		try {
			response.getWriter().println(gradeService.inspection(departmentId, gradeName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			gradeService.closeSession();
		}
		return null;
	}
}
