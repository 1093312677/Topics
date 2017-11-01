package com.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.common.ServerResponse;
import com.dto.DealData;
import com.entity.Clazz;
import com.entity.Department;
import com.entity.Direction;
import com.entity.Grade;
import com.entity.Setting;
import com.entity.Specialty;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.Topics;
import com.entity.User;
import com.service.AccountService;
import com.service.SettingService;

@Controller
@Scope("singleton")
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private SettingService settingService;
	
	@Autowired
	private DealData dealData;
	private static Logger logger = Logger.getLogger(AccountController.class);
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,HttpSession session, User user){
		logger.info("user->"+"IP:"+request.getRemoteAddr()+"->username:"+user.getUsername());
		User user1 = accountService.login(user);
		if(user1 != null){
				if(user1.getPrivilege().equals("2")||user1.getPrivilege().equals("3")){
//					管理员或者老师
					List<Teacher> teachers = accountService.findBy("Teacher", "no", user.getUsername());
					List<Teacher> teachers2=new ArrayList<Teacher>();
					Teacher teacher=new Teacher();
					teacher.setId(teachers.get(0).getId());
					Department department=new Department();
					department.setId(teachers.get(0).getDepartment().getId());
					teacher.setDepartment(department);
					teachers2.add(teacher);
					session.setAttribute("teacherId", teachers.get(0).getId());
					session.setAttribute("departmentId", teachers.get(0).getDepartment().getId());
					session.setAttribute("name", teachers.get(0).getName());
					session.setAttribute("no", teachers.get(0).getNo());
					session.setAttribute("infor", teachers2);									
				} else if( "4".equals(user1.getPrivilege()) ){
//					学生
					
					Student student = accountService.getStudentInfor(user.getUsername());
					Clazz clazz = accountService.getClassByStudentId(student.getId());
					Direction direction = accountService.getDirectionByClazzId(clazz.getId());
					Grade grade = accountService.getGradeByDirectionId(direction.getId());
					Department deparment = accountService.getDepartmentByGradeId(grade.getId());
					Long gradeId = grade.getId();
					Long studentDirectionId = direction.getId();
					Setting setting = settingService.getSetting(gradeId);
					session.setAttribute("setting", setting);
					session.setAttribute("student", student);
					session.setAttribute("studentId", student.getId());
					session.setAttribute("studentDirectionId", studentDirectionId);
					session.setAttribute("studentGradeId", gradeId);
					session.setAttribute("studentDepartmentId", deparment.getId());
					session.setAttribute("name", student.getName());
					session.setAttribute("no", student.getNo());
					List<Student>students=new ArrayList<Student>();
					Student student2=new Student();
					Clazz clazz2=new Clazz();
					Direction direction2=new Direction();
					Specialty specialty2=new Specialty();
					Grade grade2=new Grade();
					Department department2=new Department();
					student2.setId(student.getId());					
					clazz2.setId(clazz.getId());
					direction2.setId(direction.getId());
					grade2.setId(grade.getId());
					department2.setId(deparment.getId());
					grade2.setDepartment(department2);
					specialty2.setGrade(grade2);
					direction2.setSpceialty(specialty2);
					clazz2.setDirection(direction2);
					student2.setClazz(clazz2);
					students.add(student2);
					session.setAttribute("infor", students);					
//					}
					
				}
			session.setAttribute("user", user1);
			session.setAttribute("id", user.getId());
			session.setAttribute("privilege", user1.getPrivilege());
			session.setAttribute("username", user.getUsername());
			return "background";
		}else{
			try {
				request.setAttribute("loginMessage", "error");
				request.getRequestDispatcher("../index.jsp").forward(request, response); 
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
	/**
	 * 欢迎主页
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/mainpage")
	public String mainpage(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		return "common/mainpage";
	}
	/**
	 * 登出
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/loginOut")
	public String loginout(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		session.removeAttribute("user");
		session.removeAttribute("infor");
		session.removeAttribute("privilege");
		session.removeAttribute("setting");
		session.removeAttribute("username");
		try {
			response.sendRedirect("../index.jsp");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 手机端登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/loginApp")
	public @ResponseBody ServerResponse<User> loginApp(User user, HttpServletRequest request,HttpServletResponse response){
		logger.info("用户手机端登录：username->"+user.getUsername());
		return accountService.loginApp(user);
	}
	
	/**
	 * 手机端更新密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/changePw")
	public String changePw(String pw, String userId, HttpServletRequest request,HttpServletResponse response){
		if(accountService.changePw(userId, pw)) {
			try {
				JSONObject json = new JSONObject();
				json.put("data", "1");
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				JSONObject json = new JSONObject();
				json.put("data", "0");
				response.getWriter().println(json.toString());
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 手机端更新信息
	 * @param qq
	 * @param email
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateInfor")
	public String updateInfor(String privilege, String qq, String email, String phone, String userId, HttpServletRequest request,HttpServletResponse response){
		if(accountService.updateInfor(privilege, qq, email, phone, userId)) {
			try {
				JSONObject json = new JSONObject();
				json.put("data", "1");
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				JSONObject json = new JSONObject();
				json.put("data", "0");
				response.getWriter().println(json.toString());
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
