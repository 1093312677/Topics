package com.guo.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.User;
import com.guo.service.IUserService;

@Controller
@RequestMapping("/admin")
public class AdminController_guo {
	@Resource(name="userService1")
	IUserService userService;
	@RequestMapping(value="/viewAdministrator.do")
	public String viewadmin(HttpServletRequest request){
		List<User> users=userService.alluser();
		request.setAttribute("users", users);
		return "admin/administrator/viewAdministrator";
	}
	@RequestMapping(value="/addAdmin.do")
	public String addAdmin(String username,String password,HttpServletResponse response){
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPrivilege("1");
		userService.addAdmin(user);
		try {
			response.getWriter().println(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
