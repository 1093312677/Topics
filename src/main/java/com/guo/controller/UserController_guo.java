package com.guo.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.guo.service.IUserService;
@Controller
@RequestMapping("/update")
public class UserController_guo {
	@Resource
	IUserService userService;
	@RequestMapping(value="alertpw.do")
	public String toalertpw(HttpServletRequest request){
		HttpSession session=request.getSession();
		int privilege=Integer.parseInt(session.getAttribute("privilege").toString());
		request.setAttribute("privilege",privilege);
		String username=(String) session.getAttribute("username");
		request.setAttribute("username",username);
		return "alterpw/alterpw";
	}
	@RequestMapping(value="alertpw.do",method=RequestMethod.POST)
	public String alterpw(HttpServletRequest request){
		String oldpw=request.getParameter("oldpw");
		String newpw1=request.getParameter("newpw1");
		String newpw2=request.getParameter("newpw2");
		String username=request.getSession().getAttribute("username").toString();
		System.out.println(newpw2+"   "+newpw1);
		if(!newpw1.equals(newpw2)){
			request.setAttribute("oldpw", oldpw);
			request.setAttribute("temp", 2);
		}
		else{
			if(userService.update(newpw1, oldpw, username)==0){
				request.setAttribute("temp", 0);
			}
			else{
				request.getSession().setAttribute("reload", "true");
				request.setAttribute("temp", 1);
				request.getSession().setMaxInactiveInterval(1);
				return "redirect:/index.jsp";
			}
		}
		return "alterpw/alterpw";
	}
	@RequestMapping(value="resetPw.do")
	public String resetPw(long userId,HttpServletRequest request,HttpServletResponse response){
		userService.resetPw(userId);
		try {
			response.getWriter().println(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value="inspection.do")
	public String inspection(String userName,HttpServletRequest request,HttpServletResponse response){
		try {
			response.getWriter().println(userService.inspection(userName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
