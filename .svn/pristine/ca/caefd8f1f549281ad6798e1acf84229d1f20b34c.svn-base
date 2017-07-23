package com.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.common.Pagination;
import com.dto.DealData;
import com.entity.College;
import com.entity.Department;
import com.entity.Direction;
import com.entity.Grade;
import com.entity.Setting;
import com.entity.Specialty;
import com.entity.Teacher;
import com.entity.Topics;
import com.entity.User;
import com.service.CommonService;
/**
 * 设置的操作
 * @author kone
 *	2017-1-16
 */
@Controller
@RequestMapping("/setting")
public class SettingController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private DealData dealData;
	/**
	 * 查看年级
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewGradeSetting")
	public String viewGradeSetting(HttpSession session,HttpServletRequest request,HttpServletResponse response){
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		List<Grade> grades = null;
		if(teachers.size()>0){
			grades = commonService.findBy("Grade", "departmentId", String.valueOf(teachers.get(0).getDepartment().getId()));
		}
		request.setAttribute("grades", grades);
		request.setAttribute("message", "view");
//		获取完数据后关闭session
		commonService.closeSession();
		return "setting/viewGrade";
	}
	
	/**
	 * 查看年级对应的设置
	 * @param gradeId
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewSetting")
	public String viewSetting(String gradeId,HttpSession session,HttpServletRequest request,HttpServletResponse response){
		List<Setting> settings = null;
		settings = commonService.findBy("Setting", "gradeId", gradeId);
		Setting setting = new Setting();
		if(settings.size()>0){
			setting =  settings.get(0);
		}
		request.setAttribute("settings", setting);
		request.setAttribute("gradeId", gradeId);
		request.setAttribute("message", "view");
//		获取完数据后关闭session
		commonService.closeSession();
		return "setting/viewSetting";
	}
	/**
	 * 更新或者保存设置
	 * @param setting
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/saveOrUpdateSetting")
	public String saveOrUpdateSetting(long gradeId,Setting setting,HttpSession session,HttpServletRequest request,HttpServletResponse response){
		List<Grade> grades = commonService.find("Grade", String.valueOf(gradeId));
		commonService.closeSession();
		setting.setGrade(grades.get(0));
		if (commonService.saveOrUpdate(setting)) {
			request.setAttribute("message", "success");
		} else {
			request.setAttribute("message", "failed");
		}
		
		request.setAttribute("gradeId", gradeId);
		request.setAttribute("settings", setting);
		return "setting/viewSetting";
	}
	
}
