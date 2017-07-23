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
import com.entity.Clazz;
import com.entity.Direction;
import com.entity.Grade;
import com.entity.Specialty;
import com.entity.Teacher;
import com.service.CommonService;

@Controller
@RequestMapping("/clazz")
public class ClazzController {
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
	 * 查看班级
	 * @param request
	 * @param response
	 * @param type
	 * @param page
	 * @return
	 */
	@RequestMapping("/viewClazz")
	public String viewClazz(HttpSession session, HttpServletRequest request,HttpServletResponse response,String type,String page){
		List<Grade> grades = null;
		
		int eachPage = 1000000;
		List<Clazz> clazzs2 = commonService.view("Clazz",0, eachPage);
//		获取完数据后关闭session
		commonService.closeSession();
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		
		List<Clazz> clazzs = new ArrayList<Clazz>();
		if(teachers.size() > 0) {
//			查找出当前班级属于本系的
			for(int i=0; i<clazzs2.size();i++) {
//				如果班级所属系的id和系主任的系id号不一样，就移除
				if( clazzs2.get(i).getDirection().getSpceialty().getGrade().getDepartment().getId() == teachers.get(0).getDepartment().getId() ){
					clazzs.add(clazzs2.get(i));
				}
			}
			
//			查询年级，在添加年级的时候使用
			grades = commonService.findBy("Grade", "departmentId", String.valueOf(teachers.get(0).getDepartment().getId()) );
			commonService.closeSession();
			
		} 
		
		
		if(type==null){
			type="null";
		}
		if(type.equals("json")){
			try {
				JSONObject json = new JSONObject();
				json.put("clazzs", clazzs);
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}else{
			request.setAttribute("grades", grades);
			request.setAttribute("clazzs", clazzs);
			request.setAttribute("message", "view");

			return "admin/clazz/viewClazz";
		}
	}
	/**
	 * 保存班级
	 * @param directionId
	 * @param clazz
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addClazz")
	public String addClazz(long directionId,Clazz clazz,HttpServletRequest request,HttpServletResponse response){
		Direction direction = new Direction();
		direction.setId(directionId);
		clazz.setDirection(direction);
		try {
			if(commonService.save(clazz)){
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
	 * 查找班级
	 * @param request
	 * @param response
	 * @param directionId
	 * @param type
	 * @return
	 */
	@RequestMapping("/findClazzBy")
	public String findClazzBy(HttpServletRequest request,HttpServletResponse response,String directionId,String type){
		List<Clazz> clazzs = commonService.findBy("Clazz", "directionId", directionId);
		if(type.equals("json")){
			
			JSONObject json = new JSONObject();
//			将处理后的数据转换为json
			json.put("clazzs", dealData.dealClazzData(clazzs));
			try {
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
//			获取完数据后关闭session
			commonService.closeSession();
			return null;
		}else{
			request.setAttribute("clazzs", clazzs);
//			获取完数据后关闭session
			commonService.closeSession();
			return null;
		}
	}
	
	
	
	
}
