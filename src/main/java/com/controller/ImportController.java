package com.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
/**
 * 导入信息和导出信息
 * @author kone
 * 2017.4.20
 */

import com.entity.Student;
import com.entity.Teacher;
import com.service.ImportService;
@Controller
@RequestMapping("/import")
public class ImportController {
	@Autowired
	private ImportService importService;
	/**
	 * 导入教师信息
	 * @param file
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/importTeacher")
	public String importTeacher(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		List<Teacher> teachers = null;
		Long departmentId = (Long)session.getAttribute("departmentId");
		teachers = importService.importTeacher(file, departmentId);
		session.setAttribute("teachers", teachers);
		return "import/viewImportErrorTeacher";
	}
	
	/**
	 * 导出错误教师信息
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/exportErrorTeacher")
	public String exportErrorTeacher( HttpServletRequest request, HttpServletResponse response, HttpSession session){
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("teachers");
		HSSFWorkbook wb = null;
		wb = importService.exportErrorTeacher(teachers);
		//输出Excel文件
	    OutputStream output;
		try {
			output = response.getOutputStream();
			response.reset();
		    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
		    response.setContentType("application/octet-stream;charset=utf-8");
		    response.setHeader("Content-Disposition", "attachment;filename="  
		            + java.net.URLEncoder.encode("导出教师错误表"+ new SimpleDateFormat("yyyy-MM-dd-HH").format(new Date()) + ".xls", "utf-8"));
		    //  客户端不缓存  
		    response.addHeader("Pragma", "no-cache");  
		    response.addHeader("Cache-Control", "no-cache"); 
		    //输出Excel文件
			wb.write(output);
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 导入学生信息
	 * @param file
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/importStudent")
	public String importStudent(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Long gradeId = (Long) session.getAttribute("gradeId");
		List<Student> students = null;
		students = importService.importStudent(file, gradeId);
		session.setAttribute("students", students);
		return "import/viewImportErrorStudent";
	}
	
	/**
	 * 导出错误学生信息
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/exportErrorStudent")
	public String exportErrorStudent( HttpServletRequest request, HttpServletResponse response, HttpSession session){
		List<Student> students = (List<Student>) session.getAttribute("students");
		HSSFWorkbook wb = null;
		wb = importService.exportErrorStudent(students);
		//输出Excel文件
	    OutputStream output;
		try {
			output = response.getOutputStream();
			response.reset();
		    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
		    response.setContentType("application/octet-stream;charset=utf-8");
		    response.setHeader("Content-Disposition", "attachment;filename="  
		            + java.net.URLEncoder.encode("导出学生错误表"+ new SimpleDateFormat("yyyy-MM-dd-HH").format(new Date()) + ".xls", "utf-8"));
		    //  客户端不缓存  
		    response.addHeader("Pragma", "no-cache");  
		    response.addHeader("Cache-Control", "no-cache"); 
		    //输出Excel文件
			wb.write(output);
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
