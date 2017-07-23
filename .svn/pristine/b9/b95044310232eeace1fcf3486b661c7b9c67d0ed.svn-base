package com.guo.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.entity.Clazz;
import com.entity.Grade;
import com.guo.service.IClazzService;

@Controller
@RequestMapping("/clazz")
public class ClazzController_guo {
	@Resource(name="clazzService1")
	IClazzService clazzService;
	@RequestMapping(value="findclazz1By.do")
	public String findSpecialtyBy(HttpServletRequest request,HttpServletResponse response,long clazzId){
		Clazz clazz=clazzService.get(clazzId);
		try {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("clazz", clazz);
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
}
