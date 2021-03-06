package com.guo.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entity.Student;
import com.entity.Teacher;
import com.guo.dao.ITeacherDao;
import com.guo.service.IStudentService;
import com.guo.service.ITeacherService;

@Controller
@RequestMapping("/update")
public class UpdateInfo_guo {
	@Resource(name="studentService1")
	IStudentService studentService;
	
	@Resource(name="teacherService1")
	ITeacherService teacherService;
	
	
	@RequestMapping(value="viewInfo.do")
	public String update(HttpServletRequest resquest,HttpServletResponse response) throws IOException{
		String url = null;
		HttpSession session=resquest.getSession();
		int privilege=Integer.parseInt(session.getAttribute("privilege").toString());
		String no=session.getAttribute("username").toString();
		if(privilege==4)
		{
			try {
				Student student=studentService.gets(no);
				resquest.setAttribute("student", student);
				url="student/information_guo";
			}finally{
				studentService.closeSession();
			}
		}
		else if(privilege==3||privilege==2){
			try {
				Teacher teacher=teacherService.get(no);
				resquest.setAttribute("teacher", teacher);
				url="teacher/information_zhao";
			}finally{
				teacherService.closeSession();
			}
		}
		return url;
	}
	@RequestMapping(value="update.do",method=RequestMethod.POST)
	public String update(HttpServletRequest request){
		String url = null;
		String qq=request.getParameter("qq");
		String email=request.getParameter("email");
		String telephone=request.getParameter("telephone");
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		HttpSession session=request.getSession();
		String no=session.getAttribute("username").toString();
		int privilege=Integer.parseInt(session.getAttribute("privilege").toString());
		if(privilege==4)
		{	
			Student stu=new Student();
			stu.setNo(no);
			stu.setName(name);
			stu.setSex(sex);
			stu.setQq(qq);
			stu.setEmail(email);
			stu.setTelephone(telephone);
			studentService.update(stu);
			request.setAttribute("student", stu);
			request.setAttribute("temp", 1);
			url="student/information_guo";
		}
		else if(privilege==3||privilege==2){
			String position=request.getParameter("position");
			String remark=request.getParameter("remark");
			Teacher teacher=new Teacher();
			teacher.setNo(no);
			teacher.setName(name);
			teacher.setSex(sex);
			teacher.setQq(qq);
			teacher.setEmail(email);
			teacher.setTelephone(telephone);
			teacher.setPosition(position);
			teacher.setRemark(remark);
			teacherService.update(teacher);
			request.setAttribute("teacher", teacher);
			request.setAttribute("temp", 1);
			teacher=teacherService.get(no);
			request.setAttribute("teacher", teacher);
			url="teacher/information_zhao";
		}
		return url;
	}
}
