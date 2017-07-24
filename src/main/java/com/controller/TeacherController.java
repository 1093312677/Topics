package com.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.Pagination;
import com.common.QueryCondition;
import com.dto.DealData;
import com.entity.Department;
import com.entity.Grade;
import com.entity.Score;
import com.entity.Setting;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.Topics;
import com.entity.User;
import com.service.CommonService;
import com.service.TeacherService;
/**
 * 老师相关操作
 * @author kone
 * 2017-1-16
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private DealData dealData;
	
	@Autowired
	private TeacherService teacherService;
	/**
	 * 保存老师信息
	 * @param departmentId
	 * @param teacher
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addTeacher")
	public String addTeacher(Teacher teacher,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		List<Teacher> deans = (List<Teacher>) session.getAttribute("infor");
		if(deans.size()>0){
//			设置老师的登录信息
			User user = new User();
			user.setPassword("123456");
			user.setPrivilege("3");
			user.setUsername(teacher.getNo());
			teacher.setUser(user);
//			设置系
			teacher.setDepartment(deans.get(0).getDepartment());
			try {
				if(commonService.save(teacher)){
					response.getWriter().println("1");
				}else{
					response.getWriter().println("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().println("0");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}
	
	/**
	 * 保存系主任信息
	 * @param departmentId
	 * @param teacher
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addDean")
	public String addDean(long departmentId,Teacher teacher,HttpServletRequest request,HttpServletResponse response){
//		和系相关联
		Department department = new Department();
		department.setId(departmentId);
		teacher.setDepartment(department);
//		设置老师的登录信息
		User user = new User();
		user.setPassword("123456");
		user.setPrivilege("2");
		user.setUsername(teacher.getNo());
		teacher.setUser(user);
		try {
			if(commonService.save(teacher)){
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
	 * 查看老师信息
	 * @param request
	 * @param response
	 * @param type
	 * @param pagination
	 * @return
	 */
	@RequestMapping("/viewTeacher")
	public String viewTeacher(HttpSession session, HttpServletRequest request,HttpServletResponse response,String type,Pagination pagination, String pageType){
//		如果是输入的页数进行减一
		if("1".equals(pageType)) {
			pagination.setPage(pagination.getPage() - 1);
		}
		int eachPage = 15;
		pagination.setEachPage(eachPage);
		Long departmentId = (Long) session.getAttribute("departmentId");
		List<Teacher> teachers = null;
		int count = teacherService.getTeachersCount(departmentId);//获取总记录数
		teachers = teacherService.viewTeachers(departmentId, pagination.getPage(), eachPage);
//			设置总条数
		pagination.setTotleSize(count);
//			处理分页数据
		pagination = dealData.getPagination(teachers, pagination);
		request.setAttribute("teachers", teachers);
		request.setAttribute("message", "view");
		request.setAttribute("pagination", pagination);
//			获取完数据后关闭session
		commonService.closeSession();
		return "teacher/viewTeacher";
	}
	/**
	 * 查看系主任信息
	 * @param request
	 * @param response
	 * @param type
	 * @param pagination
	 * @return
	 */
	@RequestMapping("/viewDean")
	public String viewDean(HttpServletRequest request,HttpServletResponse response,String type,Pagination pagination,  String pageType){
//		如果是输入的页数进行减一
		if("1".equals(pageType)) {
			pagination.setPage(pagination.getPage() - 1);
		}
		int eachPage = 15;
		pagination.setEachPage(eachPage);
		pagination.setTotleSize(teacherService.getDeanNum());//获取总记录数
		teacherService.closeSession();
		
		List<Teacher> teachers = teacherService.getDean( pagination.getPage()*eachPage, eachPage);
		teacherService.closeSession();
//		处理分页数据
		pagination = dealData.getPagination(teachers, pagination);
		request.setAttribute("teachers", teachers);
		request.setAttribute("message", "view");
		request.setAttribute("pagination", pagination);
		return "teacher/viewDean";
}
	/**
	 * 查看老师信息
	 * @param request
	 * @param response
	 * @param type
	 * @param pagination
	 * @return
	 */
	@RequestMapping("/viewTeacherOne")
	public String viewTeacherOne(String view, String id,HttpServletRequest request,HttpServletResponse response,String type){
		if(type.equals("json")){
			
			return null;
		}else{
			List<Teacher> teacher = commonService.find("Teacher", id);
			commonService.closeSession();
			if(teacher.size()>0){
				request.setAttribute("teacher", teacher.get(0));
				request.setAttribute("view", view);
			}
			return "teacher/viewTeacherDetials";
		}
	}
	
	/**
	 * 查看选择了意向题目，选择年级
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/viewGradeSelectedIntent")
	public String viewGradeSelectedIntent(String viewType, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		List<Grade> grades = null;
		if(teachers.size()>0){
			grades = teacherService.viewGrade(teachers.get(0).getDepartment().getId());
		}
		request.setAttribute("grades", grades);
		request.setAttribute("viewType", viewType);
		return "teacher/viewGradeSelect";
	}
	
	
	
	/**
	 * 查看选择了意向题目的学生
	 * @param gradeId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewStudentSelectedIntent")
	public String viewStudentSelectedIntent(String gradeId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
//		获取选题设置
		List<Setting> settings = (List<Setting>) commonService.findBy("Setting", "gradeId", gradeId);
		Set<Topics> topics = null;
		int bc[] = null;
		commonService.closeSession();
		if ( settings.size() > 0) {
			Setting setting = settings.get(0);
			topics = teacherService.viewSelected(teachers.get(0), gradeId, setting);
			bc = teacherService.getBatchChoice(setting);
		}
		
		
		request.setAttribute("topics", topics);
		session.setAttribute("gradeId", gradeId);
		request.setAttribute("bc", bc);
		return "teacher/viewTopicsSelected";
	}
	
	/**
	 * 确认学生
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/comfirmStudent")
	public String comfirmStudent(String gradeId, String topicId, String studentId, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		if( teacherService.confirmStudent(topicId, studentId) ) {
			request.setAttribute("message", "选择成功！");
			request.setAttribute("path", "teacher/viewStudentSelectedIntent.do?gradeId="+gradeId);
			return "common/success";
		}
		request.setAttribute("message", "选择失败！此题达到最大选择数量！");
		request.setAttribute("path", "teacher/viewStudentSelectedIntent.do?gradeId="+gradeId);
		return "common/failed";
	}
	/**
	 * 审核题目通过
	 * @param topicId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/auditTopic")
	public String auditTopic(Long topicId, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String privilege = (String) session.getAttribute("privilege");
		if("2".equals(privilege)) {
			if( teacherService.updateTopicState(topicId, 1) ) {
				try {
					PrintWriter out = response.getWriter();
					out.print(1);
					return null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
		
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 审核题目未通过
	 * @param topicId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/notAuditTopic")
	public String notAuditTopic(Long topicId, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String privilege = (String) session.getAttribute("privilege");
		if("2".equals(privilege)) {
			if( teacherService.updateTopicState(topicId, 3) ) {
				try {
					PrintWriter out = response.getWriter();
					out.print(1);
					return null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
		
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 查看未选题的学生
	 * @param gradeId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewNotSelected")
	public String viewNotSelected(String viewType, String pageType, String type,Pagination pagination, Long gradeId, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//		如果是输入的页数进行减一
		if("1".equals(pageType)) {
			pagination.setPage(pagination.getPage() - 1);
		}
		
		int eachPage = 15;
		pagination.setEachPage(eachPage);
		pagination.setTotleSize(teacherService.getStudentsNotSelectCount(gradeId));//获取总记录数
//		获取完数据后关闭session
		commonService.closeSession();
		
		List<Student> students = teacherService.getStudentsNotSelect(gradeId,pagination.getPage() ,eachPage);
		
//		处理分页数据
		pagination = dealData.getPagination(students, pagination);
		
		request.setAttribute("students", students);
		request.setAttribute("pagination", pagination);
		request.setAttribute("viewType", viewType);
		session.setAttribute("gradeId", gradeId);
		return "teacher/viewStudentNotSelected";
	}
	/**
	 * 题目评测
	 * @param gradeId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/topicEvaluation")
	public String topicEvaluation(String gradeId, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<Topics> topics = null;
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		if(teachers.size() > 0) {
			topics = commonService.findByTwo("Topics", "teacherId", String.valueOf(teachers.get(0).getId()), "gradeId", gradeId);
		}
		for(int i=0;i<topics.size();i++) {
			for(int j=0;j<topics.get(i).getStudents().size();j++) {
				Score score = topics.get(i).getStudents().get(j).getScore();
			}
		}
		
		request.setAttribute("topics",topics);
		commonService.closeSession();
		return "teacher/topicEvaluation";
	}
	
	/**
	 * 录入成绩
	 * @param gradeId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/entryScore")
	public String entryScore(String gradeId, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<Topics> topics = null;
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		if(teachers.size() > 0) {
			topics = commonService.findByTwo("Topics", "teacherId", String.valueOf(teachers.get(0).getId()), "gradeId", gradeId);
		}
		for(int i=0;i<topics.size();i++) {
			for(int j=0;j<topics.get(i).getStudents().size();j++) {
				Score score = topics.get(i).getStudents().get(j).getScore();
			}
		}
		request.setAttribute("topics",topics);
		request.setAttribute("gradeId", gradeId);
		commonService.closeSession();
		return "teacher/entryScore";
	}
	/**
	 * 批量录入学生成绩
	 * @param id
	 * @param score
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/batchEntryScore")
	public String batchEntryScore(String gradeId, String []id, String []score, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		QueryCondition queryCondition = null;
		boolean flag = true;
		for(int i=0;i<id.length;i++){
			queryCondition = new QueryCondition();
			queryCondition.setTable("Score");
			queryCondition.setConunt(1);
			queryCondition.setValue4(score[i]);
			queryCondition.setRow4("score");
			queryCondition.setRow1("id");
			queryCondition.setValue1(id[i]);
			if( !commonService.updateByFree(queryCondition) ) {
				flag = false;
			}
		}
		if (flag) {
			request.setAttribute("message", "录入成功！");
			request.setAttribute("path", "teacher/entryScore.do?gradeId="+gradeId);
			return "common/success";
		} else {
			request.setAttribute("message", "录入失败！");
			request.setAttribute("path", "teacher/entryScore.do?gradeId="+gradeId);
			return "common/failed";
		}
		
		
	}
	/**
	 * 查看成绩
	 * @param gradeId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewResults")
	public String viewResults( String pageType, Pagination pagination, Long gradeId, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//		如果是输入的页数进行减一
		if("1".equals(pageType)) {
			pagination.setPage(pagination.getPage() - 1);
		}
		
		int eachPage = 15;
		pagination.setEachPage(eachPage);
		pagination.setTotleSize(teacherService.getStudentsNum(gradeId));//获取总记录数
		
		List<Student> students = null;
		
		students = teacherService.getStudents(gradeId,  pagination.getPage(), eachPage);
		
//		处理分页数据
		pagination = dealData.getPagination(students, pagination);
		
		
		session.setAttribute("gradeId", gradeId);
		request.setAttribute("students",students);
		request.setAttribute("pagination", pagination);
		teacherService.closeSession();
		return "teacher/viewResults";
	}
	/**
	 * 查看最后选题
	 * @param session
	 * @param request
	 * @param response
	 * @param pageType
	 * @param type
	 * @param pagination
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/viewLastSelect")
	public String viewLastSelect(HttpSession session,HttpServletRequest request,HttpServletResponse response, String pageType, String type,Pagination pagination, Long gradeId){
//		将gradeId保存为session，后面返回使用
		session.setAttribute("gradeId", gradeId);
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
//		如果是输入的页数进行减一
		if("1".equals(pageType)) {
			pagination.setPage(pagination.getPage() - 1);
		}
		
		int eachPage = 15;
		pagination.setEachPage(eachPage);
		pagination.setTotleSize(teacherService.getStudentsNum(gradeId));//获取总记录数
//		获取完数据后关闭session
		commonService.closeSession();
		
		List<Student> students = teacherService.getStudents(gradeId, pagination.getPage(), eachPage);
		
//		处理分页数据
		pagination = dealData.getPagination(students, pagination);
		
		request.setAttribute("students", students);
		request.setAttribute("pagination", pagination);
		request.setAttribute("message", "view");
		request.setAttribute("gradeId", gradeId);
		return "teacher/viewLastSelect";
	}
	/**
	 * 导出最后选题
	 * @param session
	 * @param request
	 * @param response
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/exportLastSelect")
	public String exportLastSelect(HttpSession session,HttpServletRequest request,HttpServletResponse response){
		Long gradeId = (Long) session.getAttribute("gradeId");
		try {
			HSSFWorkbook wb = teacherService.exportStudentsLastSelect(gradeId);
			//输出Excel文件
		    OutputStream output=response.getOutputStream();
		    response.reset();
		    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
		    response.setContentType("application/octet-stream;charset=utf-8");
		    response.setHeader("Content-Disposition", "attachment;filename="  
		            + java.net.URLEncoder.encode("名单"+ new SimpleDateFormat("yyyy-MM-dd-HH").format(new Date()) + ".xls", "utf-8"));
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
	 * 查看时间节点
	 * @param gradeId
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewTime")
	public String viewTime(String gradeId, HttpSession session,HttpServletRequest request,HttpServletResponse response){
		Setting setting = teacherService.viewTime(gradeId);
		request.setAttribute("setting", setting);
		return "teacher/viewTime";
	}
	
	
	
}
