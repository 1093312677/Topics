package com.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.Pagination;
import com.dto.DealData;
import com.entity.Student;
import com.service.TeachStuService;

/**
 * <p>学生和教师Controller</p>
 * @author kone
 * 2017.4.18
 */
@Controller
@RequestMapping("/teachStu")
public class TeachStuController {
	@Autowired
	private TeachStuService teachStuService;
	@Autowired
	private DealData dealData;
	
	private Logger logger = Logger.getLogger(TeachStuController.class);
	
	/**
	 * <p>教师查看指导的学生</p>
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewGuideStudent")
	public String viewGuideStudent(Long gradeId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Long teacherId = (Long)session.getAttribute("teacherId");
		List<Student> students = null;
		students = teachStuService.viewGuideStudent(teacherId, gradeId);
		request.setAttribute("students", students);
		session.setAttribute("gradeId", gradeId);
		teachStuService.closeStudentSession();
		return "teacher/viewGuideStudent";
	}
	
	/**
	 * 查看最后选题
	 * @param session
	 * @param request
	 * @param response
	 * @param pageType
	 * @param pagination
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/viewLastSelect")
	public String viewLastSelect(HttpSession session,HttpServletRequest request,HttpServletResponse response, String pageType, Pagination pagination, Long gradeId){
//		将gradeId保存为session，后面返回使用
		session.setAttribute("gradeId", gradeId);
//		如果是输入的页数进行减一
		if("1".equals(pageType)) {
			pagination.setPage(pagination.getPage() - 1);
		}
		
		int eachPage = 15;
		pagination.setEachPage(eachPage);
		int totleSize = teachStuService.getStudentsNum(gradeId);
		pagination.setTotleSize(totleSize);//获取总记录数
		
		List<Student> students = teachStuService.viewStudents(gradeId, pagination.getPage(), eachPage);
		
//		处理分页数据
		pagination = dealData.getPagination(students, pagination);
		
		request.setAttribute("students", students);
		request.setAttribute("pagination", pagination);
		request.setAttribute("message", "view");
		return "teacher/viewLastSelect";
}
	/**
	 * 导出学生最后成绩
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/exportStudentGrade")
	public String exportStudentGrade(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Long gradeId = (Long) session.getAttribute("gradeId");
		HSSFWorkbook wb = null;
		wb = teachStuService.exportStudentGrade(gradeId);
		//输出Excel文件
	    OutputStream output;
		try {
			output = response.getOutputStream();
			response.reset();
		    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
		    response.setContentType("application/octet-stream;charset=utf-8");
		    response.setHeader("Content-Disposition", "attachment;filename="  
		            + java.net.URLEncoder.encode("学生成绩表"+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + ".xls", "utf-8"));
		    //  客户端不缓存  
		    response.addHeader("Pragma", "no-cache");  
		    response.addHeader("Cache-Control", "no-cache"); 
		    //输出Excel文件
			wb.write(output);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 导出未选题学生
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/exportNotSelectedStudent")
	public String exportNotSelectedStudent(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Long gradeId = (Long) session.getAttribute("gradeId");
		HSSFWorkbook wb = null;
		wb = teachStuService.exportNotSelectedStudent(gradeId);
		//输出Excel文件
	    OutputStream output;
		try {
			output = response.getOutputStream();
			response.reset();
		    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
		    response.setContentType("application/octet-stream;charset=utf-8");
		    response.setHeader("Content-Disposition", "attachment;filename="  
		            + java.net.URLEncoder.encode("未选题学生统计表"+ new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()) + ".xls", "utf-8"));
		    //  客户端不缓存  
		    response.addHeader("Pragma", "no-cache");  
		    response.addHeader("Cache-Control", "no-cache"); 
		    //输出Excel文件
			wb.write(output);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 系主任一键选题
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/automaticSelection")
	public String automaticSelection(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Long gradeId = (Long) session.getAttribute("gradeId");
		logger.info("一键选题：gradeId="+gradeId);
		if(teachStuService.automaticSelection(String.valueOf(gradeId))) {
		try {
			response.getWriter().println(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		} else {
			try {
				response.getWriter().println(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
		
	}
	/**
	 * 教师查看自己是否是自动选题
	 * @param gradeId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewTeacherAutoSelect")
	public String viewTeacherAutoSelect(Long gradeId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Long teacherId = (Long)session.getAttribute("teacherId");
		boolean isAuto = false;
		isAuto = teachStuService.viewTeacherAutoSelect(gradeId, teacherId);
		request.setAttribute("isAuto", isAuto);
		session.setAttribute("gradeId", gradeId);
		return "teacher/viewTeacherAutoSelect";
	}
	
	/**
	 * 更新教师的自动选题设置
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/setTeacherAutoSelect")
	public String setTeacherAutoSelect(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Long teacherId = (Long)session.getAttribute("teacherId");
		Long gradeId = (Long) session.getAttribute("gradeId");
		if(teachStuService.setTeacherAutoSelect(gradeId, teacherId)) {
			try {
				response.getWriter().println(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().println(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 删除学生
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/deleteStudent")
	public String deleteStudent(String studentId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		if(teachStuService.deleteStudent(studentId)) {
			try {
				response.getWriter().println(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().println(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 删除教师
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/deleteTeacher")
	public String deleteTeacher(String teacherId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		if(teachStuService.deleteTeacher(teacherId)) {
			try {
				response.getWriter().println(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().println(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
}
