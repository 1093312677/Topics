package com.guo.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.entity.Grade;
import com.entity.Specialty;
import com.guo.service.IGradeService;

@Controller
@RequestMapping("/grade")
public class GradeController_guo {
	@Resource(name="gradeService1")
	IGradeService gradeService;
	
	@RequestMapping(value="findgrade1By.do")
	public String findSpecialtyBy(HttpServletRequest request,HttpServletResponse response,long gradeId){
		Grade grade=gradeService.getgrade(gradeId);
		try {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("grade", grade);
			response.getWriter().println(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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
}
