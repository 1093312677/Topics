package com.guo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.dto.DealData;
import com.entity.Grade;
import com.entity.Specialty;
import com.guo.service.ISpecialtyService;

@Controller
@RequestMapping("/specialty")
public class SpecialtyController_guo {
	@Autowired
	private DealData dealData;
	public DealData getDealData() {
		return dealData;
	}

	public void setDealData(DealData dealData) {
		this.dealData = dealData;
	}

	
	@Resource(name="specialtyService1")
	ISpecialtyService specialtyService;
	
	@RequestMapping(value="findspecialty1By.do")
	public String findspecialty(HttpServletRequest request,HttpServletResponse response,int specialtyId){
		Specialty specialty=specialtyService.get(specialtyId);
		List<Specialty>specialtys=new ArrayList<Specialty>();
		specialtys.add(specialty);
		JSONObject json = new JSONObject();
		json.put("specialtys", dealData.dealSpecialtyData(specialtys));
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
	@RequestMapping("inspection.do")
	public String inspection(HttpServletRequest request,HttpServletResponse response,String specialtyName,long gradeId){
		try {
			response.getWriter().println(specialtyService.inspection(specialtyName,gradeId));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			specialtyService.closeSession();
		}
		return null;
	}
}
