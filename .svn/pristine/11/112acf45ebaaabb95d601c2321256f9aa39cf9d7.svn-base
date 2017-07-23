package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dao.impl.CommonDaoImpl;
import com.dto.DealData;
import com.entity.Setting;
import com.entity.Student;
import com.entity.Teacher;
import com.service.AttachService;

/**
 * 附件提交控制器（开题报告，中期报告）
 * @author kone
 * 2017-4-13
 */
@Controller
@RequestMapping("/attach")
public class AttachController {
	@Autowired
	private AttachService attachService;
	@Autowired
	private DealData dealData;
	
	/**
	 * 查看开题报告提交情况
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewOpenReport")
	public String viewOpenReport(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		List<Student> students = (List<Student>) session.getAttribute("infor");
		String fileName = null;
		if(students.size() > 0) {
			fileName = attachService.getStudent(String.valueOf(students.get(0).getId()));
		}
		Setting settings = (Setting) session.getAttribute("setting");
		boolean isNow = false;
		String startTime = settings.getOpenReportStartTime();
		String endTime = settings.getOpenReportEndTime();
	    isNow = dealData.isNow(startTime, endTime);
		request.setAttribute("isNow", isNow);
		request.setAttribute("fileName", fileName);

		return "attach/viewOpenReport";
	}
	
	/**
	 * 提交开题报告
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/addOpenReport")
	public String addOpenReport(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String path = request.getSession().getServletContext().getRealPath("upload");
		List<Student> students = (List<Student>) session.getAttribute("infor");
		boolean result = false;
//		获取时间
		Setting settings = (Setting) session.getAttribute("setting");
		String startTime = settings.getOpenReportStartTime();
		String endTime = settings.getOpenReportEndTime();
	    boolean isNow = dealData.isNow(startTime, endTime);
//	          如果在当前时间段，进行操作
	    if(isNow) {
	    	if(students.size() > 0) {
				result = attachService.addOpenReport(file, path, String.valueOf(students.get(0).getId()));
			}
			if(result) {
				request.setAttribute("message", "成功！");
				request.setAttribute("path", "attach/viewOpenReport.do");
				return "common/success";
			} else {
				request.setAttribute("message", "失败！");
				request.setAttribute("path", "attach/viewOpenReport.do");
				return "common/failed";
			}
	    }
	    return null;
	}
	
	
	
	/**
	 * 查看中期报告提交情况
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewMidReport")
	public String viewMidReport(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		List<Student> students = (List<Student>) session.getAttribute("infor");
		String fileName = null;
		if(students.size() > 0) {
			fileName = attachService.getStudentMidReport(String.valueOf(students.get(0).getId()));
		}
		Setting settings = (Setting) session.getAttribute("setting");
		boolean isNow = false;
		String startTime = settings.getMidReportStartTime();
		String endTime = settings.getMidReportEndTime();
//		判断是否是当前时间段
	    isNow = dealData.isNow(startTime, endTime);
		request.setAttribute("isNow", isNow);
		request.setAttribute("fileName", fileName);

		return "attach/viewMidReport";
	}
	
	/**
	 * 提交开题报告
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/addMidReport")
	public String addMidReport(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String path = request.getSession().getServletContext().getRealPath("upload");
		List<Student> students = (List<Student>) session.getAttribute("infor");
		boolean result = false;
//		获取时间
		Setting settings = (Setting) session.getAttribute("setting");
		String startTime = settings.getMidReportStartTime();
		String endTime = settings.getMidReportEndTime();
	    boolean isNow = dealData.isNow(startTime, endTime);
//	          如果在当前时间段，进行操作
	    if(isNow) {
	    	if(students.size() > 0) {
				result = attachService.addMidReport(file, path, String.valueOf(students.get(0).getId()));
			}
			if(result) {
				request.setAttribute("message", "成功！");
				request.setAttribute("path", "attach/viewMidReport.do");
				return "common/success";
			} else {
				request.setAttribute("message", "失败！");
				request.setAttribute("path", "attach/viewMidReport.do");
				return "common/failed";
			}
	    }
	    return null;
	}
	
	
	/**
	 * 查看毕业论文提交情况
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewSubmitThesis")
	public String viewSubmitThesis(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		List<Student> students = (List<Student>) session.getAttribute("infor");
		String fileName = null;
		if(students.size() > 0) {
			fileName = attachService.viewSubmitThesis(String.valueOf(students.get(0).getId()));
		}
		Setting settings = (Setting) session.getAttribute("setting");
		boolean isNow = false;
		String startTime = settings.getCommitAttachStartTime();
		String endTime = settings.getCommitAttachEndTime();
//		判断是否是当前时间段
	    isNow = dealData.isNow(startTime, endTime);
		request.setAttribute("isNow", isNow);
		request.setAttribute("fileName", fileName);

		return "attach/viewSubmitThesis";
	}
	
	/**
	 * 提交毕业论文
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/addSubmitThesis")
	public String addSubmitThesis(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String path = request.getSession().getServletContext().getRealPath("upload");
		List<Student> students = (List<Student>) session.getAttribute("infor");
		boolean result = false;
//		获取时间
		Setting settings = (Setting) session.getAttribute("setting");
		String startTime = settings.getCommitAttachStartTime();
		String endTime = settings.getCommitAttachEndTime();
	    boolean isNow = dealData.isNow(startTime, endTime);
//	          如果在当前时间段，进行操作
	    if(isNow) {
	    	if(students.size() > 0) {
				result = attachService.addSubmitThesis(file, path, String.valueOf(students.get(0).getId()));
			}
			if(result) {
				request.setAttribute("message", "成功！");
				request.setAttribute("path", "attach/viewSubmitThesis.do");
				return "common/success";
			} else {
				request.setAttribute("message", "失败！");
				request.setAttribute("path", "attach/viewSubmitThesis.do");
				return "common/failed";
			}
	    }
	    return null;
	}
	
	/**
	 * 指导教师提交学生成绩
	 * @param gradeId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/instructorReview")
	public String instructorReview(String gradeId, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		List<Setting> settings = attachService.getSetting(gradeId);
		boolean isNow = false;
		if(settings.size() > 0) {
			
			String startTime = settings.get(0).getInstructorReviewStartTime();
			String endTime = settings.get(0).getInstructorReviewEndTime();
//			判断是否是当前时间段
		    isNow = dealData.isNow(startTime, endTime);
		}
		
		
    	List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		List<Student> students = null;
		if(teachers.size() > 0) {
			students = attachService.instructorReview(gradeId, teachers.get(0).getId());
		}
		request.setAttribute("students", students);
		session.setAttribute("isNow", isNow);
		session.setAttribute("gradeId", gradeId);

		return "review/instructorReview";
	}
	/**
	 * 指导老师提交文档和成绩
	 * @param mediumScore
	 * @param studentId
	 * @param file
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/submitInstructorReview")
	public String submitInstructorReview(float mediumScore,long studentId, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		
		boolean isNow = (boolean) session.getAttribute("isNow");
//	            如果当前是提交时间，保存信息
	    if(isNow) {
	    	String gradeId = (String) session.getAttribute("gradeId");
	    	String path = request.getSession().getServletContext().getRealPath("upload");
			if(attachService.submitInstructorReview(path, studentId, mediumScore, file)) {
				request.setAttribute("message", "成功！");
				request.setAttribute("path", "attach/instructorReview.do?gradeId="+gradeId);
				return "common/success";
			} else {
				request.setAttribute("message", "失败！");
				request.setAttribute("path", "attach/instructorReview.do?gradeId="+gradeId);
				return "common/failed";
			}
	    } else {
	    	return null;
	    }
	}
	
	/**
	 * 查看中期报告提交情况
	 * @param gradeId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/midReview")
	public String midReview(String gradeId, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		List<Setting> settings = attachService.getSetting(gradeId);
		boolean isNow = false;
		if(settings.size() > 0) {
			
			String startTime = settings.get(0).getMidReviewStartTime();
			String endTime = settings.get(0).getMidReviewEndTime();
//			判断是否是当前时间段
		    isNow = dealData.isNow(startTime, endTime);
		}
		
		
    	List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		List<Student> students = null;
		if(teachers.size() > 0) {
			students = attachService.midReview(gradeId, teachers.get(0).getId());
		}
		request.setAttribute("students", students);
		session.setAttribute("isNow", isNow);
		session.setAttribute("gradeId", gradeId);

		return "review/midReview";
	}
	/**
	 * 小组成员提交负责学生的评阅情况
	 * @param mediumScore
	 * @param studentId
	 * @param file
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/submitMidReview")
	public String submitMidReview(float score,long studentId, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		
		boolean isNow = (boolean) session.getAttribute("isNow");
//	            如果当前是提交时间，保存信息
	    if(isNow) {
	    	String gradeId = (String) session.getAttribute("gradeId");
	    	String path = request.getSession().getServletContext().getRealPath("upload");
			if(attachService.submitMidReview(path, studentId, score, file)) {
				request.setAttribute("message", "成功！");
				request.setAttribute("path", "attach/midReview.do?gradeId="+gradeId);
				return "common/success";
			} else {
				request.setAttribute("message", "失败！");
				request.setAttribute("path", "attach/midReview.do?gradeId="+gradeId);
				return "common/failed";
			}
	    } else {
	    	return null;
	    }
	}
	
	/**
	 * <p>组长提交最后的答辩成绩，先查看学生</p>
	 * @param gradeId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/replyResults")
	public String replyResults(String gradeId, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		List<Setting> settings = attachService.getSetting(gradeId);
		boolean isNow = false;
		if(settings.size() > 0) {
			
			String startTime = settings.get(0).getReplyResultsStartTime();
			String endTime = settings.get(0).getReplyResultsEndTime();
//			判断是否是当前时间段
		    isNow = dealData.isNow(startTime, endTime);
		}
		
		
    	List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		List<Student> students = null;
		if(teachers.size() > 0) {
			students = attachService.replyResults(gradeId, teachers.get(0).getId());
		}
		if(students == null) {
			request.setAttribute("isLeader", false);
		} else {
			request.setAttribute("isLeader", true);
		}
		request.setAttribute("students", students);
		session.setAttribute("isNow", isNow);
		session.setAttribute("gradeId", gradeId);

		return "review/replyResults";
	}
	
	/**
	 * <p>小组长提交答辩记录表</p>
	 * @param score
	 * @param studentId
	 * @param file
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/submitReplyResults")
	public String submitReplyResults(String level, float score,long studentId, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		
		boolean isNow = (boolean) session.getAttribute("isNow");
//	            如果当前是提交时间，保存信息
	    if(isNow) {
	    	String gradeId = (String) session.getAttribute("gradeId");
	    	String path = request.getSession().getServletContext().getRealPath("upload");
			if(attachService.submitReplyResults(path, studentId, score, file, level)) {
				request.setAttribute("message", "成功！");
				request.setAttribute("path", "attach/replyResults.do?gradeId="+gradeId);
				return "common/success";
			} else {
				request.setAttribute("message", "失败！");
				request.setAttribute("path", "attach/replyResults.do?gradeId="+gradeId);
				return "common/failed";
			}
	    } else {
	    	return null;
	    }
	}
	/**
	 * 下载附件
	 * @param gradeId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/downAttach")
	public String downAttach(String gradeId, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String path = request.getSession().getServletContext().getRealPath("upload");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
	    response.setContentType("application/octet-stream;charset=utf-8");
	    try {
			response.setHeader("Content-Disposition", "attachment;filename="  
			        + java.net.URLEncoder.encode("documents"+ new SimpleDateFormat("yyyyMMddHH").format(new Date()) + ".zip", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //  客户端不缓存  
	    response.addHeader("Pragma", "no-cache");  
	    response.addHeader("Cache-Control", "no-cache"); 
	    
	    
	    attachService.downAttach(response, path, gradeId);
		return null;
	}
	
	
	
}
