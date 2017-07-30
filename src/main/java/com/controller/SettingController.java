package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.ServerResponse;
import com.entity.Grade;
import com.entity.Setting;
import com.service.CommonService;
import com.service.GradeService;
import com.service.SettingService;
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
	private SettingService settingService;
	
	@Autowired
	private GradeService gradeService;
	/**
	 * 查看年级
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewGradeSetting")
	public String viewGradeSetting(HttpSession session,HttpServletRequest request,HttpServletResponse response){
		Long departmentId = (Long)session.getAttribute("departmentId");
		List<Grade> grades = null;
		grades = gradeService.viewGrades(departmentId);
		request.setAttribute("message", "view");
		request.setAttribute("grades", grades);
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
	public String viewSetting(Long gradeId,HttpSession session,HttpServletRequest request,HttpServletResponse response){
		Setting setting = new Setting();
		setting = settingService.getSettingDean(gradeId);
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
		Grade grade = new Grade();
		grade.setId(gradeId);
		setting.setGrade(grade);
		if (commonService.saveOrUpdate(setting)) {
			request.setAttribute("message", "success");
		} else {
			request.setAttribute("message", "failed");
		}
		
		request.setAttribute("gradeId", gradeId);
		request.setAttribute("settings", setting);
		return "setting/viewSetting";
	}
	
	
	/**
	 * 查看年级对应的设置APP
	 * @param gradeId
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewSettingApp")
	@ResponseBody
	public ServerResponse<Setting> viewSettingApp(Long gradeId, HttpServletRequest request,HttpServletResponse response){
		Setting setting = new Setting();
		setting = settingService.getSettingDean(gradeId);
		setting.setGrade(null);
		return ServerResponse.response(200, "获取数据", setting);
	}
	
}
