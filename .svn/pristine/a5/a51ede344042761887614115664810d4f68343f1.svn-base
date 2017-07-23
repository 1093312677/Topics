package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.entity.College;
import com.entity.Department;
import com.entity.Grade;
import com.service.CommonService;

@Controller
@RequestMapping("/college")
/**
 * 学院，年级，系，方向，班级的管理
 * @author kone
 *2017-1-7
 */
public class CollegeController {
	@Autowired
	private CommonService commonService;
	
	/**
	 * 增加学院
	 * @return
	 */
	@RequestMapping("/addCollege")
	public String addCollege(HttpServletRequest request,HttpServletResponse response, String collegeName){
		College college = new College();
		college.setCollegeName(collegeName);
		commonService.save(college);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println("1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 *查看学院
	 */
	@RequestMapping("/viewCollege")
	public String viewCollege(HttpServletRequest request,HttpServletResponse response,String type){
		int page = 0;
		int eachPage = 10;
		List<College> colleges = commonService.view("College", page, eachPage);
		
		if(type==null){
			type = "null";
		}
		if(type.equals("json")){
			try {
				JSONObject json = new JSONObject();
				json.put("colleges", colleges);
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
//			获取完数据后关闭session
			commonService.closeSession();
			return null;
		}else{
			request.setAttribute("colleges", colleges);
			request.setAttribute("message", "view");
//			获取完数据后关闭session
			commonService.closeSession();
			return "admin/college/viewCollege";
		}
	}
	/**
	 * 删除学院
	 */
	@RequestMapping("/deleteCollege")
	public String deleteCollege(HttpServletRequest request,HttpServletResponse response,long id){
		College college = new College();
		college.setId(id);
		
		List<Department> departments = commonService.findBy("Department", "collegeId", String.valueOf(id));
//		关闭session
		commonService.closeSession();
//		判断查找是否有数据
		if(departments.size()>0){
			college.setDepartments(departments);
		}
		if(commonService.delete(college)){
			request.setAttribute("message", "success");
		}else{
			request.setAttribute("message", "failed");
		}
		viewCollege(request,response,"null");
		return "admin/college/viewCollege";
	}
	/**
	 * 查找学院
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/findCollege")
	public String find(HttpServletRequest request,HttpServletResponse response,String id){
		List<College> colleges = commonService.find("College",id);
		response.setCharacterEncoding("utf-8");
		request.setAttribute("college", colleges);
//		获取完数据后关闭session
		commonService.closeSession();
		return "admin/college/updateCollege";
	}
	/**
	 * 更新学院
	 * @param college
	 * @return
	 */
	@RequestMapping("/updateCollege")
	public String updateCollege(HttpServletRequest request,HttpServletResponse response,College college){
		response.setCharacterEncoding("utf-8");
		
		viewCollege(request,response,"null");
		if(commonService.update(college)){
			request.setAttribute("message", "success");
		}else{
			request.setAttribute("message", "failed");
		}
		return "admin/college/viewCollege";
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
}
