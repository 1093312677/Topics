package com.controller;

import java.io.File;
import java.io.IOException;
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
import com.entity.Direction;
import com.entity.Grade;
import com.entity.Specialty;
import com.entity.Teacher;
import com.entity.Topics;
import com.entity.User;
import com.service.CommonService;
/**
 * 选题的操作
 * @author kone
 *	2017-1-16
 */
@Controller
@RequestMapping("/topic")
public class TopicController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private DealData dealData;
	public CommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public DealData getDealData() {
		return dealData;
	}
	public void setDealData(DealData dealData) {
		this.dealData = dealData;
	}
	/**
	 * 添加毕业选题
	 * @param teacherId
	 * @param topic
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/addTopic")
	public String addTopic(long gradeId, Topics topic,long directionIds[],@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response,HttpSession session){

//		获取登录人员的信息
		User user = (User) session.getAttribute("user");
		//查找出相应id
		List<User> users = commonService.findBy("User", "username", user.getUsername());
		
//		设置题目状态
		if(users.get(0).getPrivilege().equals("2")){
//			系主任上传为通过
			topic.setState(1);
		}else{
//			教师上传为审核状态
			topic.setState(2);
		}
		
//		设置题目适合的年级
		Grade grade = new Grade();
		grade.setId(gradeId);
		
		
//		方向
		Direction direction = null;
		List<Direction> directions = new ArrayList<Direction>();
//		题目适配的方向
		for(int i=0;i<directionIds.length;i++){
			direction= new Direction();
			direction.setId(directionIds[i]);
			
			topic.getDirections().add(direction);
//		    directions.add(direction);
		}
//		设置题目负责老师*
		Teacher teacher = new Teacher();
		if(users.size()>0){
			teacher = users.get(0).getTeacher();
		}
		String fileName = "";
		File file2 = null;
		if(!file.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("upload");
			String origName = file.getOriginalFilename();
			int newNameIndex = origName.lastIndexOf('.');
			String suffix = origName.substring(newNameIndex);
			long name = System.currentTimeMillis();
//			文件随机名称
			fileName = String.valueOf(name)+suffix;
			file2 = new File(path,fileName);
			if(!file2.exists()){
				file2.mkdirs();
			}
		}
		
//		获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		
//		设置上传题目的时间
		topic.setTime(time);
//		设置文件的文件名
		topic.setTaskBookName(fileName);
//		设置方向
//		topic.setDirections(directions);
		
//		设置出题人信息
		topic.setTeacher(teacher);
//		设置年级
		topic.setGrade(grade);
		
//		保存信息
		String topicId = commonService.saveGetId(topic);
		if (topicId != null) {
			for(int i=0;i<directionIds.length;i++){
				commonService.insertSql(topicId, String.valueOf(directionIds[i]));
				
			}
			try {
				//保存文件
				if(!file.isEmpty()){
					file.transferTo(file2);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	/**
	 * 跳转到添加毕业选题界面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/goAddTopic")
	public String goAddTopic(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		int page = 0;
		int eachPage = 10000;
		List<Teacher> teacher = (List<Teacher>) session.getAttribute("infor");
		List<Grade> grades = null;
		if(teacher.size()>0){
			grades = commonService.findBy("Grade", "departmentId", String.valueOf(teacher.get(0).getDepartment().getId()));
		}
		request.setAttribute("grades", grades);
		commonService.closeSession();
		return "teacher/addTopic";
	}
	
	/**
	 * 在知道系的情况下，查找所有的方向
	 * @param request
	 * @param response
	 * @param departmentId
	 * @param type
	 * @return
	 */
	@RequestMapping("/findDirectionBy")
	public String findDirectionBy(HttpServletRequest request,HttpServletResponse response,String gradeId,String type){
		List<Specialty> specialtys = commonService.findBy("Specialty", "gradeId", gradeId);
		int specialtySize = specialtys.size();
		commonService.closeSession();
		List<Direction> directions2 = new ArrayList<Direction>();
		Direction direction;
//		处理所有的方向
		for(int i=0;i<specialtySize;i++){
			List<Direction> directions = commonService.findBy("Direction", "specialtyId", String.valueOf(specialtys.get(i).getId()));
			int directionsSize = directions.size();
			commonService.closeSession();
			for(int j=0;j<directionsSize;j++){
				direction = new Direction();
				direction.setDirectionName(directions.get(j).getDirectionName());
				direction.setId(directions.get(j).getId());
				
				directions2.add(direction);
			}
		}
		if(type.equals("json")){
			
			JSONObject json = new JSONObject();
//			将处理后的数据转换为json
			json.put("directions", directions2);
			try {
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
//			获取完数据后关闭session
			commonService.closeSession();
			return null;
		}else{
			request.setAttribute("directions2", directions2);
//			获取完数据后关闭session
			commonService.closeSession();
			return null;
		}
		
	}
	
	/**
	 * 获取题目信息
	 * @param request
	 * @param response
	 * @param type
	 * @param pagination
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewTopic")
	public String viewTopic(int state,HttpServletRequest request,HttpServletResponse response,String type,String gradeId,Pagination pagination,HttpSession session){
		List<Topics> topics = null;
		List<Teacher> teacher = (List<Teacher>) session.getAttribute("infor");
		String privilege = (String) session.getAttribute("privilege");
		if(gradeId != null || gradeId != ""){
			topics = commonService.viewTopic(gradeId, teacher.get(0), privilege);
		}
		
		request.setAttribute("topics", topics);
		request.setAttribute("state", state);
		commonService.closeSession();
		return "topic/viewTopics";
	}
	
	/**
	 * 查看年级获取题目
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewGradeTopic")
	public String viewGradeTopic(HttpSession session,HttpServletRequest request,HttpServletResponse response,int state){
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		List<Grade> grades = null;
		if(teachers.size()>0){
			grades = commonService.findBy("Grade", "departmentId", String.valueOf(teachers.get(0).getDepartment().getId()));
		}
		request.setAttribute("grades", grades);
		request.setAttribute("message", "view");
		request.setAttribute("state", state);
//		获取完数据后关闭session
		commonService.closeSession();
		return "topic/viewGradeTopic";
	}
	
	
	
	
}
