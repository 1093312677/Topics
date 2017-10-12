package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.common.Pagination;
import com.common.PathTool;
import com.common.ServerResponse;
import com.dto.DealData;
import com.entity.Direction;
import com.entity.Grade;
import com.entity.LimitNumber;
import com.entity.Specialty;
import com.entity.Student;
import com.entity.SubTopic;
import com.entity.Teacher;
import com.entity.Topics;
import com.service.CommonService;
import com.service.GradeService;
import com.service.TopicService;
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
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private GradeService gradeService;
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
	public String addTopic(Topics topic,long directionIds[],@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Long gradeId = (Long) session.getAttribute("gradeId");
		Long teacherId = (Long) session.getAttribute("teacherId");
		
//		设置题目状态,待审核
		topic.setState(2);
//		设置题目适合的年级
		Grade grade = new Grade();
		grade.setId(gradeId);
//		方向
		Direction direction = null;
//		题目适配的方向
		for(int i=0;i<directionIds.length;i++){
			direction= new Direction();
			direction.setId(directionIds[i]);
			
			topic.getDirections().add(direction);
		}
//		设置题目负责老师*
		Teacher teacher = new Teacher();
		teacher.setId(teacherId);
		String fileName = "";
		File file2 = null;
		if(!file.isEmpty()){
			//String path = request.getSession().getServletContext().getRealPath("upload");
			String path = PathTool.getPath();
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
//		设置出题人信息
		topic.setTeacher(teacher);
//		设置年级
		topic.setGrade(grade);
		
//		保存信息
		String topicId = commonService.saveGetId(topic);
		if (topicId != null) {
//			上传题目成功后，相应的数量进行减少
			List<LimitNumber> limitNumbers = commonService.findByTwo("LimitNumber", "gradeId", String.valueOf(gradeId), "teacherId", String.valueOf(teacherId));
			if(limitNumbers.size() > 0) {
				int number = limitNumbers.get(0).getAlreadyNumber() + topic.getEnableSelect();
				limitNumbers.get(0).setAlreadyNumber(number);
				commonService.saveOrUpdate(limitNumbers.get(0));
			}
			
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
		JSONObject json = new JSONObject();
		json.put("result", 1);
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
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
	public String goAddTopic(Long gradeId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		boolean isNow = topicService.goAddTopic(gradeId);
		session.setAttribute("gradeId", gradeId);
		request.setAttribute("isNow", isNow);
		
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
		List<Direction> directions2 = new ArrayList<Direction>();
		Direction direction;
//		处理所有的方向
		for(int i=0;i<specialtySize;i++){
			List<Direction> directions = commonService.findBy("Direction", "specialtyId", String.valueOf(specialtys.get(i).getId()));
			int directionsSize = directions.size();
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
			return null;
		}else{
			request.setAttribute("directions2", directions2);
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
	public String viewTopic(String pageType, int state,HttpServletRequest request,HttpServletResponse response,String type,Long gradeId,Pagination pagination,HttpSession session){
		if(gradeId == null || gradeId == 0){
			gradeId = (Long) session.getAttribute("gradeId");
		}
//		如果是输入的页数进行减一
		if("1".equals(pageType)) {
			pagination.setPage(pagination.getPage() - 1);
		}
		
		int eachPage = 15;
		pagination.setEachPage(eachPage);
		pagination.setTotleSize(topicService.getTopicsNum(String.valueOf(gradeId), String.valueOf(state)));//获取总记录数,1表示通过题目
		
		List<Topics> topics = null;
		if(gradeId != null){
			topics = topicService.getTopics(String.valueOf(gradeId), String.valueOf(state), pagination.getPage()*eachPage, eachPage);
			pagination.setTotleSize(topicService.getTopicsNum(String.valueOf(gradeId), String.valueOf(state)));//获取总记录数,1表示通过题目
		}
		if(gradeId != null || gradeId != 0){
			topics = topicService.getTopics(String.valueOf(gradeId), String.valueOf(state), pagination.getPage()*eachPage, eachPage);
			for(int i=0;i<topics.size();i++) {
				for(int j=0;j<topics.get(i).getDirections().size();j++) {
					topics.get(i).getDirections().get(j).getDirectionName();
				}
			}
		}
		
//		处理分页数据
		pagination = dealData.getPagination(topics, pagination);
		
		request.setAttribute("topics", topics);
		request.setAttribute("state", state);
		session.setAttribute("gradeId", gradeId);
		request.setAttribute("pagination", pagination);
		return "topic/viewTopics";
	}
	
	/**
	 * 教师获取题目信息
	 * @param request
	 * @param response
	 * @param type
	 * @param pagination
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewTopicTeacher")
	public String viewTopicTeacher(HttpServletRequest request,HttpServletResponse response,String type,String gradeId,Pagination pagination,HttpSession session){
		List<Topics> topics = null;
		Long teacherId = (Long) session.getAttribute("teacherId");
		String privilege = (String) session.getAttribute("privilege");
		if(gradeId != null || gradeId != ""){
			topics = commonService.viewTopic(gradeId, teacherId, privilege);
		}
		
		request.setAttribute("topics", topics);
		session.setAttribute("gradeId", gradeId);
		return "topic/viewTopicsTeacher";
	}
	/**
	 * 查看年级获取题目
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewGradeTopic")
	public String viewGradeTopic(HttpSession session,HttpServletRequest request,HttpServletResponse response,int state){
		Long departmentId = (Long)session.getAttribute("departmentId");
		List<Grade> grades = null;
		grades = gradeService.viewGrades(departmentId);
		request.setAttribute("grades", grades);
		request.setAttribute("message", "view");
		request.setAttribute("state", state);
		return "topic/viewGradeTopic";
	}
	
	/**
	 * 查看未通过题目
	 * @param request
	 * @param response
	 * @param type
	 * @param pagination
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewNotThoughtTopic")
	public String viewNotThoughtTopic(int state, HttpServletRequest request,HttpServletResponse response,String type,String gradeId, HttpSession session){
		state -= 2;
		List<Topics> topics = null;
		Long teacherId = (Long) session.getAttribute("teacherId");
		topics = topicService.viewNotThoughtTopic(gradeId, teacherId, String.valueOf(state));
		
		request.setAttribute("topics", topics);
		request.setAttribute("state", 1);
		return "topic/viewTopicsNotThought";
	}
	/**
	 * 添加或者更新题目附件
	 * @param id
	 * @param file
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/addUpdateAttach")
	public String addUpdateAttach(String id, @RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		//String path = request.getSession().getServletContext().getRealPath("upload");
		String path = PathTool.getPath();
		JSONObject json = new JSONObject();
		
		if(topicService.addUpdateAttach(path, id, file)) {
			try {
				json.put("result", 1);
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				json.put("result", 0);
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	/**
	 * 导出选题情况
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/exportTopic")
	public String exportTopic(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String gradeId = (String) session.getAttribute("gradeId");
		HSSFWorkbook wb = null;
		wb = topicService.exportTopic(gradeId);
		//输出Excel文件
	    OutputStream output;
		try {
			output = response.getOutputStream();
			response.reset();
		    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
		    response.setContentType("application/octet-stream;charset=utf-8");
		    response.setHeader("Content-Disposition", "attachment;filename="  
		            + java.net.URLEncoder.encode("选题情况表"+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + ".xls", "utf-8"));
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
	 * 删除、未通过的题目
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/deleteTopicNotThrought")
	public String deleteTopicNotThrought(long id, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Topics topic = new Topics();
		topic.setId(id);
		if(topicService.deleteTopicNotThrought(topic)) {
			try {
				response.getWriter().println(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				response.getWriter().println(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	/**
	 * 删除题目
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/deleteTopic")
	public String deleteTopic(String id, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		if(topicService.deleteTopic(id)) {
			try {
				response.getWriter().println(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				response.getWriter().println(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
	/**
	 * 查看子题目
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/addSubTopic")
	public String addSubTopic(String id, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		List<Student> students = topicService.addSubTopic(id);
		request.setAttribute("students", students);
		return "topic/viewSubTopics";
	}
	/**
	 * 添加子题目
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveSubTopic")
	public String saveSubTopic(Long studentId, long topicId,SubTopic subTopic, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		//String path = session.getServletContext().getRealPath("/upload");
		String path = PathTool.getPath();
		JSONObject json = new JSONObject();
		if(topicService.saveSubTopic(studentId, topicId, subTopic, path, file)) {
			try {
				json.put("result", 1);
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				json.put("result", 0);
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	/**
	 * 打包下载子题目
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/exportSubTopic")
	public String downAttach( HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String gradeId = (String) session.getAttribute("gradeId");
		//String path = request.getSession().getServletContext().getRealPath("upload");
		String path = PathTool.getPath();
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
	    response.setContentType("application/octet-stream;charset=utf-8");
	    try {
			response.setHeader("Content-Disposition", "attachment;filename="  
			        + java.net.URLEncoder.encode("SubTopics"+ new SimpleDateFormat("yyyyMMddHH").format(new Date()) + ".zip", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    //  客户端不缓存  
	    response.addHeader("Pragma", "no-cache");  
	    response.addHeader("Cache-Control", "no-cache"); 
	    
	    
	    topicService.downAttach(response, path, gradeId);
		return null;
	}
	/**
	 * 查看选择该题目的学生
	 * @param topicId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewStudentSelected")
	public String viewStudentSelected(String topicId, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		List<Student> students = topicService.viewStudentSelected(topicId);
		request.setAttribute("students", students);
		session.setAttribute("topicId", topicId);
		return "topic/viewStudentSelected";
	}
	
	/**
	 * 退选毕业选题
	 * @param studentId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/withdrawalTopic")
	public String withdrawalTopic(String studentId, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String topicId = (String) session.getAttribute("topicId");
		if(topicService.withdrawalTopic(studentId, topicId)) {
			try {
				response.getWriter().println(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				response.getWriter().println(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
		
	
	/**
	 * 查看题目详情
	 * @param topicId
	 * @return
	 */
	@RequestMapping("/viewTopicDetials")
	@ResponseBody
	public ServerResponse<Topics> viewTopicDetials(String topicId) {
		
		
		return topicService.viewTopicDetials(topicId);
	}
	
	
	
	
	/**
	 * 教师查看题目详情
	 * @param topicId
	 * @return
	 */
	@RequestMapping("/teacherViewTopicDetials")
	@ResponseBody
	public ServerResponse<Topics> teacherViewTopicDetials(String topicId) {
		
		
		return topicService.teacherViewTopicDetials(topicId);
	}
	
	/**
	 * 教师查看之前查看年级 app
	 * @param departmentId
	 * @return
	 */
	@RequestMapping("/teacherViewGradesApp")
	@ResponseBody
	public ServerResponse<List<Grade>> teacherViewGradesApp(String departmentId ) {
		
		
		return topicService.teacherViewGradesApp(departmentId);
	}
	
}
