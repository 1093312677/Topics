package com.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.GroupTimeAndPlace;
import com.entity.Grade;
import com.entity.Group;
import com.entity.StuTeachGroup;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.TeacherGroup;
import com.entity.TimeAndPlace;
import com.service.GroupService;
/**
 * 关于分组的操作
 * @author kone
 * 2017.3.30
 */
@Controller
@RequestMapping("/group")
public class GroupController {
	@Autowired
	private GroupService groupService;
	/**
	 * 添加分组
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/addGroup")
	public String addGroup(Group group,long gradeId,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Grade grade = new Grade();
		grade.setId(gradeId);
		group.setGrade(grade);
		if(groupService.save(group)){
			try {
				response.getWriter().println("1");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 查看分组信息
	 * @param request
	 * @param response
	 * @param session
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/viewGroup")
	public String viewGroup(HttpServletRequest request,HttpServletResponse response,HttpSession session,String gradeId){
		List<Teacher> infor = (List<Teacher>) session.getAttribute("infor");
		if(infor.size() > 0 ){
			List<Group> groups = groupService.viewGroup(gradeId);
			request.setAttribute("groups", groups);
			request.setAttribute("departmentId", infor.get(0).getDepartment().getId());
			session.setAttribute("gradeId", gradeId);
		}
		
		
		return "group/viewGroup";
	}
	/**
	 * 删除分组
	 * @param request
	 * @param response
	 * @param session
	 * @param group
	 * @return
	 */
	@RequestMapping("/deleteGroup")
	public String deleteGroup(HttpServletRequest request,HttpServletResponse response,HttpSession session,Group group){
		String privilege =  (String) session.getAttribute("privilege");
		String gradeId = (String) session.getAttribute("gradeId");
		if("2".equals(privilege)){
			if(groupService.deleteGroup(group)){
				request.setAttribute("message", "删除成功！");
				request.setAttribute("path", "group/viewGroup.do?gradeId="+gradeId);
				return "common/success";
			}else{
				request.setAttribute("message", "删除失败！");
				request.setAttribute("path", "group/viewGroup.do?gradeId="+gradeId);
				return "common/failed";
			}
			
		}else{
			request.setAttribute("message", "删除失败！");
			request.setAttribute("path", "group/viewGroup.do?gradeId="+gradeId);
			return "common/failed";
		}
	}
	
	/**
	 * 删除分组
	 * @param request
	 * @param response
	 * @param session
	 * @param group
	 * @return
	 */
	@RequestMapping("/cancelGroup")
	public String cancelGroup(HttpServletRequest request,HttpServletResponse response,HttpSession session,String id){
		String privilege =  (String) session.getAttribute("privilege");
		String gradeId = (String) session.getAttribute("gradeId");
		if("2".equals(privilege)){
			if(groupService.cancelGroup(id)){
				request.setAttribute("message", "取消成功！");
				request.setAttribute("path", "group/viewGroup.do?gradeId="+gradeId);
				return "common/success";
			}else{
				request.setAttribute("message", "取消失败！");
				request.setAttribute("path", "group/viewGroup.do?gradeId="+gradeId);
				return "common/failed";
			}
			
		}else{
			request.setAttribute("message", "取消失败！");
			request.setAttribute("path", "group/viewGroup.do?gradeId="+gradeId);
			return "common/failed";
		}
	}
	/**
	 * 查找一个
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewOneGroup")
	public String viewOneGroup(HttpServletRequest request,HttpServletResponse response,HttpSession session,String id){
		List<Group> groups = groupService.findById(id);
		request.setAttribute("groups", groups);
		return "group/viewOneGroup";
	}
	
	/**
	 * 更新
	 * @param request
	 * @param response
	 * @param session
	 * @param group
	 * @return
	 */
	@RequestMapping("/updateGroup")
	public String updateGroup(HttpServletRequest request,HttpServletResponse response,HttpSession session,Group group){
		List<Group> groups = groupService.findById(String.valueOf(group.getId()));
//		通过session获取年级的id
		String gradeId = (String) session.getAttribute("gradeId");
		if (groups.size() > 0 ) {
			groups.get(0).setGroupName(group.getGroupName());
			if( groupService.updateGroup(groups.get(0))) {
				request.setAttribute("message", "更新成功！");
				request.setAttribute("path", "group/viewGroup.do?gradeId="+gradeId);
				return "common/success";
			}else{
				request.setAttribute("message", "更新失败！");
				request.setAttribute("path", "group/viewGroup.do?gradeId="+gradeId);
				return "common/failed";
			}
		}else{
			request.setAttribute("message", "更新失败！");
			request.setAttribute("path", "group/viewGroup.do?gradeId="+gradeId);
			return "common/failed";
		}
	}
	/**
	 * 设置前查看答辩组
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/setViewGroup")
	public String setViewGroup(HttpServletRequest request,HttpServletResponse response,HttpSession session,String id){
//		通过session获取年级的id
		session.setAttribute("groupId", id);
		String gradeId = (String) session.getAttribute("gradeId");
		List<Group> groups = groupService.setViewGroup(gradeId);
		request.setAttribute("groups", groups);
		return "group/setViewGroup";
	}
	/**
	 * 设置答辩组
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/setGroup")
	public String setGroup(HttpServletRequest request,HttpServletResponse response,HttpSession session,int id){
//		通过session获取年级的id
		String gradeId = (String) session.getAttribute("gradeId");
		
		String groupId = (String) session.getAttribute("groupId");
		List<Group> groups = groupService.findById(groupId);
		if (groups.size() > 0 ) {
			Group ansGroup = new Group();
			ansGroup.setId(id);
			groups.get(0).setAnsGroup(ansGroup);;
			if( groupService.updateGroup(groups.get(0))) {
				request.setAttribute("message", "更新成功！");
				request.setAttribute("path", "group/viewGroup.do?gradeId="+gradeId);
				return "common/success";
			}else{
				request.setAttribute("message", "更新失败！");
				request.setAttribute("path", "group/viewGroup.do?gradeId="+gradeId);
				return "common/failed";
			}
		}else{
			request.setAttribute("message", "更新失败！");
			request.setAttribute("path", "group/viewGroup.do?gradeId="+gradeId);
			return "common/failed";
		}
	}
	
	/**
	 * 设置组内老师成员，查看
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/setViewTeacherGroup")
	public String setViewTeacherGroup(HttpServletRequest request,HttpServletResponse response,HttpSession session,String id){
//		通过session获取年级的id
		session.setAttribute("groupId", id);
		String gradeId = (String) session.getAttribute("gradeId");
//		获取教师信息
		List<Teacher> infor = (List<Teacher>) session.getAttribute("infor");
		
		List<Teacher> teachers = null;
		if(infor.size() > 0 ){
			teachers = groupService.setViewTeacherGroup(String.valueOf(infor.get(0).getDepartment().getId()),gradeId);
		}
		
		request.setAttribute("teachers", teachers);
		return "group/setViewTeacherGroup";
	}
	
	/**
	 * 设置组内老师成员
	 * @param request
	 * @param response
	 * @param isChoiceLeader 额外增加的字段，判断是否选择了组长。
	 * @param session
	 * @return
	 */
	@RequestMapping("/setTeacherGroup")
	public String setTeacherGroup(long []teacherId,long teacherHeaderId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String groupId = (String) session.getAttribute("groupId");
//		通过session获取年级的id
		String gradeId = (String) session.getAttribute("gradeId");
		
		if( groupService.setTeacherGroup(teacherId, groupId, teacherHeaderId)){
			request.setAttribute("message", "成功！");
			request.setAttribute("path", "group/viewGroup.do?gradeId="+gradeId);
			return "common/success";
		} else {
			request.setAttribute("message", "失败！");
			request.setAttribute("path", "group/viewGroup.do?gradeId="+gradeId);
			return "common/failed";
		}
	}
	/**
	 * 查看组内老师进行调整
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/changeTeacherGroup")
	public String changeTeacherGroup(String id, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		List<Group> groups = null;
		groups = groupService.findById(id);
		
		if(groups.size() > 0 ) {
			request.setAttribute("group", groups.get(0));
		} else {
			request.setAttribute("group", null);
		}
		
//		将groupId保存，后面返回使用
		session.setAttribute("groupId", id);
		return "group/changeTeacherGroup";
	}
	
	/**
	 * 将老师移除组
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/removeTeacher")
	public String removeTeacher(int teacherGroupId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		TeacherGroup teacherGroup = new TeacherGroup();
		teacherGroup.setId(teacherGroupId);
		
		if(groupService.deleteTeacherGroup(teacherGroup)) {
			try {
				response.getWriter().print(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().print(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	/**
	 * 修改组长
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/changeTeacherLeader")
	public String changeTeacherLeader(int id,String type, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		int state = 0;
		if(type.equals("remove")){
			state = 0;
		} else {
			state = 1;
		}
		if(groupService.changeTeacherLeader(state, id)) {
			try {
				response.getWriter().print(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().print(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 查看分组的成员
	 * @param request
	 * @param response
	 * @param session
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/viewGroupMember")
	public String viewGroupMember(HttpServletRequest request,HttpServletResponse response,HttpSession session,long gradeId){
		List<Teacher> infor = (List<Teacher>) session.getAttribute("infor");
		if(infor.size() > 0){
			List<TeacherGroup> teacherGroup = groupService.viewGroupMember(gradeId, infor.get(0).getId());
//			表示非组长
			if(teacherGroup.size() == 0) {
				request.setAttribute("isLeader", "0");
			} else {
				request.setAttribute("isLeader", "1");
			}
			request.setAttribute("groups", teacherGroup);
			session.setAttribute("gradeId", gradeId);
		}
		
		
		return "group/viewGroupMember";
	}
	
	/**
	 * 设置组内学生
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/setStudentGroup")
	public String setStudentGroup(long teacherId, HttpServletRequest request,HttpServletResponse response,HttpSession session,String groupId){
//		通过session获取年级的id
		session.setAttribute("groupId", groupId);
		session.setAttribute("teacherId", teacherId);
		long gradeId = (long) session.getAttribute("gradeId");
		List<Student> students = groupService.setStudentGroup(groupId, gradeId);
		request.setAttribute("students", students);
		return "group/setStudentGroup";
	}
	/**
	 * 设置学生分组
	 * @param studentId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/setStuTeachGroup")
	public String setStuTeachGroup(int[] studentId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
//		通过session获取年级的id,教师id
		long teacherId = (long) session.getAttribute("teacherId");
		long gradeId = (long) session.getAttribute("gradeId");
		if(groupService.setStuTeachGroup(gradeId, teacherId, studentId)){
			request.setAttribute("message", "保存成功！");
			request.setAttribute("path", "group/viewGroupMember.do?gradeId="+gradeId);
			return "common/success";
		
		}
		request.setAttribute("message", "保存失败！");
		request.setAttribute("path", "group/viewGroupMember.do?gradeId="+gradeId);
		return "common/failed";
	}
	
	/**
	 * 查看老师负责的学生
	 * @param teacherId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewStudentGroup")
	public String viewStudentGroup(long teacherId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
//		通过session获取grade id
		long gradeId = (long) session.getAttribute("gradeId");
		List<StuTeachGroup> stuTeachGroups = groupService.viewStudentGroup(gradeId, teacherId);
		request.setAttribute("stuTeachGroups",stuTeachGroups);
		return "group/viewStudentGroup";
	}
	/**
	 *	移除学生分组
	 * @param studentId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/changeStudentGroup")
	public String changeStudentGroup(long id, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		if(groupService.deleteStudentGroup(id)) {
			try {
				response.getWriter().println("1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
	 * 查看老师负责的学生
	 * @param teacherId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/studentGroup")
	public String studentGroup(HttpServletRequest request,HttpServletResponse response,HttpSession session){
//		通过session获取id
		List<Student> students = (List<Student>) session.getAttribute("infor");
		
		Group group = null;
		if(students.size() > 0) {
			long id = students.get(0).getId();
			long gradeId = students.get(0).getClazz().getDirection().getSpceialty().getGrade().getId();
			group = groupService.studentGroup(id,gradeId);
		}
		request.setAttribute("group", group);
		return "group/studentGroup";
	}
	
	/**
	 * 查看答辩时间和地点
	 * @param request
	 * @param response
	 * @param session
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/viewTimeAndPlace")
	public String viewTimeAndPlace(HttpServletRequest request,HttpServletResponse response,HttpSession session,long gradeId){
		List<Teacher> infor = (List<Teacher>) session.getAttribute("infor");
		if(infor.size() > 0){
			GroupTimeAndPlace groupTimeAndPlace = groupService.viewTimeAndPlace(gradeId, infor.get(0).getId());
			request.setAttribute("groupTimeAndPlace", groupTimeAndPlace);
			session.setAttribute("gradeId", gradeId);
			session.setAttribute("gradeId", gradeId);
		}
		
		
		return "group/viewTimeAndPlace";
	}
	/**
	 * 增加答辩时间和地点
	 * @param check1
	 * @param place
	 * @param time
	 * @param groupId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addTimeAndPlace")
	public String addTimeAndPlace(int check1,int timeId, String place, String time, int groupId, HttpServletRequest request,HttpServletResponse response){
		TimeAndPlace timeAndPlace = new TimeAndPlace();
		if(check1 != 0) {
			timeAndPlace.setId(timeId);
		}
		timeAndPlace.setTime(time);
		timeAndPlace.setPlace(place);
		if(groupService.updateTimeAndPlace(timeAndPlace, groupId, check1)) {
			try {
				response.getWriter().println("1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
	 * 导出教师分组情况
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/exportTeacherGroup")
	public String exportTeacherGroup(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String gradeId = (String) session.getAttribute("gradeId");
		HSSFWorkbook wb = null;
		List<Teacher> infor = (List<Teacher>) session.getAttribute("infor");
		if(infor.size() > 0) {
			wb = groupService.exportTeacherGroup(gradeId, String.valueOf(infor.get(0).getDepartment().getId()));
		}
		//输出Excel文件
	    OutputStream output;
		try {
			output = response.getOutputStream();
			response.reset();
		    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
		    response.setContentType("application/octet-stream;charset=utf-8");
		    response.setHeader("Content-Disposition", "attachment;filename="  
		            + java.net.URLEncoder.encode("教师分组表"+ new SimpleDateFormat("yyyy-MM-dd-HH").format(new Date()) + ".xls", "utf-8"));
		    //  客户端不缓存  
		    response.addHeader("Pragma", "no-cache");  
		    response.addHeader("Cache-Control", "no-cache"); 
		    //输出Excel文件
			wb.write(output);
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		
		return null;
	}
	
	
	/**
	 * 导出学生分组情况
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/exportStudentGroup")
	public String exportStudentGroup(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String gradeId = (String) session.getAttribute("gradeId");
		HSSFWorkbook wb = null;
		List<Teacher> infor = (List<Teacher>) session.getAttribute("infor");
		if(infor.size() > 0) {
			wb = groupService.exportStudentGroup(gradeId);
		}
		//输出Excel文件
	    OutputStream output;
		try {
			output = response.getOutputStream();
			response.reset();
		    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
		    response.setContentType("application/octet-stream;charset=utf-8");
		    response.setHeader("Content-Disposition", "attachment;filename="  
		            + java.net.URLEncoder.encode("学生分组表"+ new SimpleDateFormat("yyyy-MM-dd-HH").format(new Date()) + ".xls", "utf-8"));
		    //  客户端不缓存  
		    response.addHeader("Pragma", "no-cache");  
		    response.addHeader("Cache-Control", "no-cache"); 
		    //输出Excel文件
			wb.write(output);
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		
		return null;
	}
}
