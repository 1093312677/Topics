package com.guo.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.entity.Specialty;
import com.guo.service.ISpecialtyService;

@Controller
@RequestMapping("/specialty")
public class SpecialtyController_guo {
	@Resource(name="specialtyService1")
	ISpecialtyService specialtyService;
	
	@RequestMapping(value="findspecialty1By.do")
	public String findspecialty(HttpServletRequest request,HttpServletResponse response,int specialtyId){
		Specialty specialty=specialtyService.get(specialtyId);
		JSONObject json = new JSONObject();
		json.put("specialty", specialty);
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			specialtyService.closeSession();
		}
		return null;	
	}
	@RequestMapping("updateInfo.do")
	public String updateInfo(HttpServletRequest request,HttpServletResponse response,Specialty specialty){
		specialtyService.updateInfo(specialty);
		try {
			response.getWriter().println(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
