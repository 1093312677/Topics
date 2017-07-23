package com.guo.controller;

import java.io.IOException;
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
import com.entity.Department;
import com.entity.Teacher;
import com.guo.service.IDepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController_guo {
	@Autowired
	private DealData dealData;
	public DealData getDealData() {
		return dealData;
	}

	public void setDealData(DealData dealData) {
		this.dealData = dealData;
	}
	
	@Resource(name="departmentService1")
	IDepartmentService departmentService;
	
	@RequestMapping(value="findDepartment1By.do")
	public String findDepartment1By(HttpServletRequest request,HttpServletResponse response,long departmentId){
		List<Department> departments=departmentService.departments(departmentId);
		try {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("departments", dealData.dealDepartmentData(departments));
			response.getWriter().println(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			departmentService.closeSession();
		}
		return null;	
	}
	@RequestMapping(value="updateInfo.do")
	public String updateInfo(Department department,HttpServletRequest request,HttpServletResponse response){
		departmentService.updateInfo(department);
		try {
			response.getWriter().println(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
