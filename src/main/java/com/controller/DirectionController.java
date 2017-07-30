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
import com.entity.Topics;
import com.service.CommonService;

@Controller
@RequestMapping("/direction")
public class DirectionController {
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
	 * 查看方向
	 * @param request
	 * @param response
	 * @param type
	 * @param page
	 * @return
	 */
	@RequestMapping("/viewDirection")
	public String viewDirection(HttpSession session, HttpServletRequest request,HttpServletResponse response,String type){
		List<Grade> grades = null;
		
		int page = 0;
		int eachPage = 1000000;
		List<Direction> directions2 = commonService.view("Direction", Integer.valueOf(page), eachPage);
//		获取完数据后关闭session
		commonService.closeSession();
		Long departmentId = (Long)session.getAttribute("departmentId");
		List<Direction> directions = new ArrayList<Direction>();
//			查找出当前方向属于本系的
		for(int i=0; i<directions2.size();i++) {
//				如果方向所属系的id和系主任的系id号不一样，就移除
			if( directions2.get(i).getSpceialty().getGrade().getDepartment().getId() == departmentId ){
				directions.add(directions2.get(i));
			}
		}
		
//			查询年级，在添加年级的时候使用
		grades = commonService.findBy("Grade", "departmentId", String.valueOf(departmentId) );
		commonService.closeSession();
			
		if(type==null){
			type="null";
		}
		if(type.equals("json")){
			try {
				JSONObject json = new JSONObject();
				json.put("directions", directions);
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}else{
			request.setAttribute("grades", grades);
			request.setAttribute("directions", directions);
			request.setAttribute("message", "view");
			return "admin/direction/viewDirection";
		}
	}
	/**
	 * 增加方向
	 * @param specialtyId
	 * @param direction
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addDirection")
	public String addDirection(long specialtyId,Direction direction,HttpServletRequest request,HttpServletResponse response){
		Specialty specialty = new Specialty();
		specialty.setId(specialtyId);
		direction.setSpceialty(specialty);
		try {
			if(commonService.save(direction)){
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
	 * 删除方向
	 * @param direction
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteDirection")
	public String deleteDirection(Direction direction, HttpSession session, HttpServletRequest request,HttpServletResponse response){
//		查找班级
		List<Direction> directions = commonService.findBy("Direction", "id", String.valueOf(direction.getId()));
//		关闭session
		commonService.closeSession();
		boolean result = false;
		if(directions.size()>0){
			result = commonService.delete(directions.get(0));
		}
		
		
		viewDirection(session, request,response,"null");
//		给前台提示消息
		if(result){
			request.setAttribute("message", "success");
		}else{
			request.setAttribute("message", "failed");
		}
		return "admin/direction/viewDirection";
	}
	/**
	 * 通过条件查找方向，用于联级查找
	 * @param request
	 * @param response
	 * @param specialtyId
	 * @param type
	 * @return
	 */
	@RequestMapping("/findDirectionBy")
	public String findDirectionBy(HttpServletRequest request,HttpServletResponse response,String specialtyId,String type){
		List<Direction> directions = commonService.findBy("Direction", "specialtyId", specialtyId);
		if(type.equals("json")){
			
			JSONObject json = new JSONObject();
//			将处理后的数据转换为json
			json.put("directions", dealData.dealDirectionData(directions));
			try {
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
//			获取完数据后关闭session
			commonService.closeSession();
			return null;
		}else{
			request.setAttribute("directions", directions);
//			获取完数据后关闭session
			commonService.closeSession();
			return null;
		}
		
	}
}
