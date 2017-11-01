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

import com.alibaba.fastjson.JSONObject;
import com.dto.DealData;
import com.entity.Direction;
import com.entity.Specialty;
import com.entity.Student;
import com.guo.service.IDirectionService;

@Controller
@RequestMapping("/direction")
public class DirectionController_guo {
	@Autowired
	private DealData dealData;
	public DealData getDealData() {
		return dealData;
	}

	public void setDealData(DealData dealData) {
		this.dealData = dealData;
	}
	@Resource(name="directionService1")
	IDirectionService directionService; 
	
	@RequestMapping(value="findDirection1By.do")
	public String findspecialty(HttpServletRequest request,HttpServletResponse response,int directionId){
		Direction direction=directionService.get(directionId);
		List<Direction>directions=new ArrayList<Direction>();
		directions.add(direction);
		JSONObject json = new JSONObject();
		json.put("directions", dealData.dealDirectionData(directions));
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			directionService.closeSession();
		}
		return null;	
	}
	@RequestMapping("updateInfo.do")
	public String updateInfo(HttpServletRequest request,HttpServletResponse response,Direction direction){
		directionService.updateInfo(direction);
		try {
			response.getWriter().println(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("inspection.do")
	public String inspection(HttpServletRequest request,HttpServletResponse response,String directionName ,long specialtyId){
		try {
			response.getWriter().println(directionService.inspection(directionName, specialtyId));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			directionService.closeSession();
		}
		return null;
	}
	@RequestMapping("findDirection.do")
	public String findDirection(HttpServletRequest request,HttpServletResponse response,long departmentId,HttpSession session){
		List<Student>students=(List<Student>) session.getAttribute("infor");
		Student student =students.get(0);
		departmentId=student.getClazz().getDirection().getSpceialty().getGrade().getDepartment().getId();		
		List<Direction>directions=directionService.findDirection(departmentId);
		JSONObject json = new JSONObject();
		json.put("directions", dealData.dealDirectionData(directions));
		System.out.println(json.toString());
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
