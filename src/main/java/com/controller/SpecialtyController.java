package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.dto.DealData;
import com.entity.Direction;
import com.entity.Grade;
import com.entity.Specialty;
import com.entity.Teacher;
import com.service.CommonService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/specialty")
public class SpecialtyController {
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
	 * 查看专业
	 * @param request
	 * @param response
	 * @param type
	 * @return
	 */
	@RequestMapping("/viewSpecialty")
	public String viewSpecialty(HttpSession session, HttpServletRequest request,HttpServletResponse response,String type){
		List<Grade> grades = null;
		
		int page = 0;
		int eachPage = 1000000;
		List<Specialty> specialtys2 = commonService.view("Specialty", Integer.valueOf(page), eachPage);
		Long departmentId = (Long)session.getAttribute("departmentId");
		
		List<Specialty> specialtys = new ArrayList<Specialty>();
//			查找出当前专业属于本系的
		for(int i=0; i<specialtys2.size();i++) {
//				如果专业所属系的id和系主任的系id号不一样，就移除
			if( specialtys2.get(i).getGrade().getDepartment().getId() == departmentId ){
				specialtys.add( specialtys2.get(i) );
			}
		}
			
//			查询年级，在添加年级的时候使用
			grades = commonService.findBy("Grade", "departmentId", String.valueOf(departmentId) );
		if(type==null){
			type="null";
		}
		if(type.equals("json")){
			try {
				JSONObject json = new JSONObject();
				json.put("specialtys", specialtys);
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}else{
			request.setAttribute("grades", grades);
			request.setAttribute("specialtys", specialtys);
			request.setAttribute("message", "view");

			return "admin/specialty/viewSpecialty";
		}
	}
	/**
	 * 增加专业
	 * @param specialty
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addSpecialty")
	@ResponseBody
	public Integer addSpecialty(long gradeId,Specialty specialty,HttpServletRequest request,HttpServletResponse response){
		Grade grade = new Grade();
		grade.setId(gradeId);
		specialty.setGrade(grade);
		if(commonService.save(specialty)){
//			特殊需求，保存专业的时候，同时把方向保存，取相同的名称
			saveDirection(specialty);
			return 1;
		}else{
			return 0;
		}
	}

	public void saveDirection(Specialty specialty) {
		Direction direction = new Direction();
		direction.setSpceialty(specialty);
		direction.setDirectionName(specialty.getSpecialtyName());
		commonService.save(direction);
	}
	/**
	 * 通过条件查找专业
	 * @param request
	 * @param response
	 * @param type
	 * @return
	 */
	@RequestMapping("/findSpecialtyBy")
	public String findSpecialtyBy(HttpServletRequest request,HttpServletResponse response,String gradeId,String type){
		List<Specialty> specialtys = commonService.findBy("Specialty", "gradeId", gradeId);
		if(type.equals("json")){
			
			JSONObject json = new JSONObject();
//			将处理后的数据转换为json
			json.put("specialtys", dealData.dealSpecialtyData(specialtys));
			try {
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}else{
			request.setAttribute("specialtys", specialtys);
			return null;
		}
	}
	/**
	 * 删除专业
	 * @param specialty
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteSpecialty")
	public String deleteSpecialty(Specialty specialty,HttpSession session, HttpServletRequest request,HttpServletResponse response){
//		查找方向
		List<Specialty> specialtys = commonService.findBy("Specialty", "id", String.valueOf(specialty.getId()));
		boolean result = false;
		if(specialtys.size()>0){
			result = commonService.delete(specialtys.get(0));
		}
		
		
		
		viewSpecialty(session, request,response,"null");
//		给前台提示消息
		if(result){
			request.setAttribute("message", "success");
		}else{
			request.setAttribute("message", "failed");
		}
		return "admin/specialty/viewSpecialty";
	}
	
	
	
	
	
	
}
