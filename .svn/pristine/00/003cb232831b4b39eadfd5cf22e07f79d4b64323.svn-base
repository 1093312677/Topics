package com.guo.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.entity.Direction;
import com.entity.Specialty;
import com.guo.service.IDirectionService;

@Controller
@RequestMapping("/direction")
public class DirectionController_guo {
	@Resource(name="directionService1")
	IDirectionService directionService; 
	@RequestMapping(value="findDirection1By.do")
	public String findspecialty(HttpServletRequest request,HttpServletResponse response,int directionId){
		Direction direction=directionService.get(directionId);
		JSONObject json = new JSONObject();
		json.put("direction", direction);
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
}
