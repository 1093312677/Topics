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
import com.entity.Clazz;
import com.entity.Grade;
import com.guo.service.IClazzService;

@Controller
@RequestMapping("/clazz")
public class ClazzController_guo {
	@Autowired
	private DealData dealData;
	public DealData getDealData() {
		return dealData;
	}

	public void setDealData(DealData dealData) {
		this.dealData = dealData;
	}
	@Resource(name="clazzService1")
	IClazzService clazzService;
	@RequestMapping(value="findclazz1By.do")
	public String findSpecialtyBy(HttpServletRequest request,HttpServletResponse response,long clazzId){
		Clazz clazz=clazzService.get(clazzId);
		List<Clazz>clazzs=new ArrayList<Clazz>();
		clazzs.add(clazz);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("clazzs", dealData.dealClazzData(clazzs));
		try {		
			response.getWriter().println(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clazzService.closeSession();
		}
		return null;	
	}
	@RequestMapping(value="updateInfo.do")
	public String updateInfo(HttpServletRequest request,HttpServletResponse response,Clazz clazz){
		clazzService.updateInfo(clazz);
		try {
			response.getWriter().println(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value="inspection.do")
	public String inspection(HttpServletRequest request,HttpServletResponse response,String className,long directionId){
		try {
			response.getWriter().println(clazzService.inspection(className, directionId));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			clazzService.closeSession();
		}
		return null;
	}
}
