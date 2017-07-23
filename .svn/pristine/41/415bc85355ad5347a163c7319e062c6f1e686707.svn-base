package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.common.Pagination;
import com.dto.DealData;
import com.entity.College;
import com.entity.Department;
import com.entity.Grade;
import com.entity.Specialty;
import com.entity.Teacher;
import com.service.CommonService;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private DealData dealData;
	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public DealData getDealData() {
		return dealData;
	}

	public void setDealData(DealData dealData) {
		this.dealData = dealData;
	}

	/**
	 * 查看系
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewDepartment")
	public String viewDepartment(HttpServletRequest request,HttpServletResponse response,String type,Pagination pagination){
		int eachPage = 15;
		pagination.setEachPage(eachPage);
		pagination.setTotleSize(commonService.getCount("Department"));//获取总记录数
		commonService.closeSession();
		List<Department> departments = commonService.view("Department", pagination.getPage()*eachPage, eachPage);
//		获取处理后的页数类
		pagination = dealData.getPagination(departments, pagination);
		if(type==null){
			type="null";
		}
		if(type.equals("json")){
			try {
				JSONObject json = new JSONObject();
				json.put("departments", departments);
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
//			获取完数据后关闭session
			commonService.closeSession();
			return null;
		}else{
			request.setAttribute("departments", departments);
			request.setAttribute("message", "view");
			request.setAttribute("pagination", pagination);
//			获取完数据后关闭session
			commonService.closeSession();
			return "admin/department/viewDepartment";
		}
	}
	
	/**
	 *  增加系
	 * @param gradeId
	 * @param department
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addDepartment")
	public String addDepartment(long collegeId,Department department,HttpServletRequest request,HttpServletResponse response){
		
		
		College college = new College();
		college.setId(collegeId);
		department.setCollege(college);
		try {
			if(commonService.save(department)){
				response.getWriter().println("1");
			}else{
				response.getWriter().println("0");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除系
	 * @param department
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteDepartment")
	public String deleteDepartment(Department department,HttpServletRequest request,HttpServletResponse response,Pagination pagination){
//		查找年级
		List<Grade> grades = commonService.findBy("Grade;", "departmentId", String.valueOf(department.getId()));
		if(grades.size()>0){
			department.setGrades(grades);
		}
//		查找教师
		List<Teacher> teachers = commonService.findBy("Teacher;", "departmentId", String.valueOf(department.getId()));
		if(teachers.size()>0){
			department.setTeachers(teachers);
		}
		
		boolean result = commonService.delete(department);
		
		viewDepartment(request,response,"null",pagination);
//		给前台提示消息
		if(result){
			request.setAttribute("message", "success");
		}else{
			request.setAttribute("message", "failed");
		}
		return "admin/department/viewDepartment";
	}
	/**
	 * 通过条件查找系
	 * @param request
	 * @param response
	 * @param collegeId
	 * @param type
	 * @return
	 */
	@RequestMapping("/findDepartmentBy")
	public String findDepartmentBy(HttpServletRequest request,HttpServletResponse response,String collegeId,String type){
		List<Department> departments = commonService.findBy("Department", "collegeId", collegeId);
		if(type.equals("json")){
			
			JSONObject json = new JSONObject();
//			将处理后的数据转换为json
			json.put("departments", dealData.dealDepartmentData(departments));
			try {
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
//			获取完数据后关闭session
			commonService.closeSession();
			return null;
		}else{
			request.setAttribute("departments", departments);
//			获取完数据后关闭session
			commonService.closeSession();
			return null;
		}
		
	}
	
}
