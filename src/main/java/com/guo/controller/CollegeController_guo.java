package com.guo.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guo.service.ICollegeService;

@Controller
@RequestMapping("/college")
public class CollegeController_guo {

	@Resource(name="collegeService1")
	ICollegeService collegeService;
	@RequestMapping(value="inspection.do")
	public String inspection(String collegeName,HttpServletResponse response){
		try {
			response.getWriter().println(collegeService.inspection(collegeName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			collegeService.cloesSession();
		}
		return null;	
	}
}
