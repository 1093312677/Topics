package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.common.Course;
import com.common.Pagination;
import com.dto.DealData;
import com.entity.CheckViewGrade;
import com.entity.CourseAndGrade;
import com.entity.Grade;
import com.entity.Student;
import com.entity.Teacher;
import com.service.CommonService;
import com.service.CourseGradeService;
/**
 * 选题的操作
 * @author kone
 *	2017-1-16
 */
@Controller
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private DealData dealData;
	@Autowired
	private CourseGradeService courseGradeService;
	
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
	
	public CourseGradeService getCourseGradeService() {
		return courseGradeService;
	}
	public void setCourseGradeService(CourseGradeService courseGradeService) {
		this.courseGradeService = courseGradeService;
	}
	/**
	 * 添加课程
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/addCourse")
	public String addCourse(Course course, long gradeId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
//		Grade grade = new Grade();
//		grade.setId(gradeId);
//		
//		course.setGrade(grade);
//		try {
//			PrintWriter out = response.getWriter();
//			if(commonService.save(course)){
//				out.println(1);
//				return null;
//			} 
//			out.print(0);
//			return null;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		return null;
	}
	
	
	
	
	/**
	 * 查看年级获取课程
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewGradeCourse")
	public String viewGradeCourse(String pageType, Pagination pagination, long gradeId, HttpSession session,HttpServletRequest request,HttpServletResponse response){

//		如果是输入的页数进行减一
		if("1".equals(pageType)) {
			pagination.setPage(pagination.getPage() - 1);
		}
		
		int eachPage = 15;
		pagination.setEachPage(eachPage);
		pagination.setTotleSize(courseGradeService.getGradeNum(gradeId));//获取总记录数
//		获取完数据后关闭session
		commonService.closeSession();
		
		List<CourseAndGrade>  courseAndGrades = courseGradeService.viewGrades(gradeId, pagination.getPage()*eachPage, eachPage);
//		获取完数据后关闭session
		commonService.closeSession();
		
//		处理分页数据
		pagination = dealData.getPagination(courseAndGrades, pagination);
		
//		将gradeId保存为session，后面返回使用
		session.setAttribute("gradeId", gradeId);
		
		request.setAttribute("pagination", pagination);
		request.setAttribute("grades", courseAndGrades);
	    return "course/viewCourse";
	}
	/**
	 * 批量导入学生的课程成绩
	 * @param file
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/batchImportGrade")
	public String batchImportGrade(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		long gradeId = (long) session.getAttribute("gradeId");
		List<CourseAndGrade> grades = null;
		if (!file.isEmpty()) {
			grades = courseGradeService.saveGrade(file, gradeId);
		}
		
		session.setAttribute("grades", grades);
		if(grades.size() == 0) {
			request.setAttribute("message", "(上传失败学生的信息)");
		}
		return "course/viewCourse";
	}
	
	/**
	 * 获取课程信息
	 * @param request
	 * @param response
	 * @param type
	 * @param pagination
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewCourse")
	public String viewCourse(HttpServletRequest request,HttpServletResponse response,long gradeId, HttpSession session){
		int page = 0;
		int eachPage = 100000;
		List<Course> courses = courseGradeService.viewCourse(gradeId, page, eachPage);
		request.setAttribute("courses", courses);
		session.setAttribute("gradeId", gradeId);
		commonService.closeSession();
		return "course/viewCourseChoice";
	}
	/**
	 * 设置教师查看的课程
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/setViewCourse")
	public String setViewCourse(String []courseName, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		long gradeId = (long) session.getAttribute("gradeId");
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		if(teachers.size() > 0) {
			Teacher teacher = teachers.get(0);
			courseGradeService.setViewCourse(teacher, courseName, gradeId);
		}
		
		viewCourse(request, response, gradeId, session);
		return "course/viewCourseChoice";
	}
	/**
	 * 查看已选择的
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewCourseChoice")
	public String viewCourseChoice(String gradeId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		List<CheckViewGrade> checkViewGrade = null;
		if(teachers.size() > 0) {
			Teacher teacher = teachers.get(0);
			checkViewGrade = courseGradeService.viewCourseChoice(teacher, gradeId);
		}
		request.setAttribute("checkViewGrade", checkViewGrade);
		return "course/viewChoice";
	}
	
}
