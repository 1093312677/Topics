package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.dto.DealData;
import com.entity.College;
import com.entity.Department;
import com.entity.Grade;
import com.entity.Specialty;
import com.entity.Teacher;
import com.service.CommonService;

@Controller
@RequestMapping("/grade")
public class GradeController {
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
	 * 查看年级
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewGrade")
	public String viewGrade(HttpServletRequest request,HttpServletResponse response,String type, HttpSession session){
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		List<Grade> grades = null;
		if( teachers.size() > 0 ) {
			grades = commonService.findBy("Grade", "departmentId", String.valueOf(teachers.get(0).getDepartment().getId()) );
//			获取完数据后关闭session
			commonService.closeSession();
		}
		if(type==null){
			type="null";
		}
		if(type.equals("json")){
			try {
				JSONObject json = new JSONObject();
				json.put("grades", grades);
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}else{
			request.setAttribute("grades", grades);
			request.setAttribute("message", "view");
			return "admin/grade/viewGrade";
		}
		
		
	}

	/**
	 * 增加年级
	 * @return
	 */
	@RequestMapping("/addGrade")
	public String addGrade( Grade grade, HttpSession session, HttpServletRequest request,HttpServletResponse response){
//	查找出系主任所属系，将年级添加到所属系
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		if( teachers.size() > 0 ) {
			Department department = new Department();
			department.setId( teachers.get(0).getDepartment().getId() );
			grade.setDepartment(department);
			try {
				if(commonService.save(grade)){
					response.getWriter().println("1");
				}else{
					response.getWriter().println("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().println("0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		commonService.closeSession();
		return null;
	}
	/**
	 * 删除年级
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteGrade")
	public String deleteGrade(HttpServletRequest request,HttpServletResponse response,long id, HttpSession session){
//		查找系
		List<Specialty> specialtys = commonService.findBy("Specialty", "gradeId", String.valueOf(id));
		
		Grade grade = new Grade();
		if(specialtys.size()>0){
			grade.setSpceialtys(specialtys);
		}
		grade.setId(id);
//		关闭session
		commonService.closeSession();
		boolean result = commonService.delete(grade);
		
		viewGrade(request,response,"null",session);
		
//		给前台提示消息
		if(result){
			request.setAttribute("message", "success");
		}else{
			request.setAttribute("message", "failed");
		}
//		关闭session
		commonService.closeSession();
		return "admin/grade/viewGrade";
	}
	/**
	 * 通过条件查找年级
	 * @param request
	 * @param response
	 * @param collegeId
	 * @param type
	 * @return
	 */
	@RequestMapping("/findGradeBy")
	public String findGradeBy(HttpServletRequest request,HttpServletResponse response,String departmentId,String type){
		List<Grade> grades = commonService.findBy("Grade", "departmentId", departmentId);
		if(type.equals("json")){
			JSONObject json = new JSONObject();
//			将获取处理后的数据，转换成json数据
			json.put("grades", dealData.dealGradeData(grades));
			try {
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
//			获取完数据后关闭session
			commonService.closeSession();
			return null;
		}else{
			request.setAttribute("grades", grades);
			request.setAttribute("message", "view");
//			获取完数据后关闭session
			commonService.closeSession();
			return "admin/grade/viewGrade";
		}
		
	}
	/**
	 * 年级分类
	 * @param request
	 * @param response
	 * @param collegeId
	 * @param type
	 * @return
	 */
	@RequestMapping("/findGradeByTwo")
	public String findGradeByTwo(HttpServletRequest request,HttpServletResponse response,String collegeId,String type,String gradeName){
		List<Grade> grades = commonService.findByTwo("Grade", "collegeId", collegeId,"gradeName",gradeName);
		if(type.equals("json")){
			JSONObject json = new JSONObject();
//			将获取处理后的数据，转换成json数据
			json.put("grades", dealData.dealGradeData(grades));
			try {
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
//			获取完数据后关闭session
			commonService.closeSession();
			return null;
		}else{
			request.setAttribute("grades", grades);
			request.setAttribute("message", "view");
//			获取完数据后关闭session
			commonService.closeSession();
			return "admin/grade/viewGrade";
		}
		
	}
	
}
