package com.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.common.ServerResponse;
import com.dto.DealData;
import com.entity.Setting;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.User;
import com.service.AccountService;
import com.service.SettingService;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private SettingService settingService;
	
	@Autowired
	private DealData dealData;
	private static Logger logger =LoggerFactory.getLogger(AccountController.class);
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,HttpSession session, User user){
		logger.debug("IP:"+request.getRemoteAddr()+"username:"+user.getUsername());
		logger.error("error");
		logger.info("info");
		User user1 = accountService.login(user);
		if(user1 != null){
			List infor = null;
				if(user1.getPrivilege().equals("2")||user1.getPrivilege().equals("3")){
//					管理员或者老师
					infor = accountService.findBy("Teacher", "no", user.getUsername());
					List<Teacher> teachers = infor;
					accountService.closeSession();
					
					session.setAttribute("departmentId", teachers.get(0).getDepartment().getId());
				} else if( "4".equals(user1.getPrivilege()) ){
//					学生
					List<Student> inforStu = accountService.findBy("Student", "no",user.getUsername());
					accountService.closeSession();
//					题目设置相关
					infor = inforStu;
					Long gradeId = inforStu.get(0).getClazz().getDirection().getSpceialty().getGrade().getId();
					Setting setting = settingService.getSetting(gradeId);
						session.setAttribute("setting", setting);
					session.setAttribute("studentId", inforStu.get(0).getId());
				}
			session.setAttribute("user", user1);
			session.setAttribute("id", user.getId());
			session.setAttribute("privilege", user1.getPrivilege());
			session.setAttribute("infor", infor);
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
		try {
			response.sendRedirect("../index.jsp");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 手机端登录
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/loginApp")
	public @ResponseBody ServerResponse<User> loginApp(User user, HttpServletRequest request,HttpServletResponse response){
		
		return accountService.loginApp(user);
	}
	
	/**
	 * 手机端更新密码
	 * @param request
	 * @param response
	 * @param session
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
	 * @param telephone
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
