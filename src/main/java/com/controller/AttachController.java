package com.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.common.PathTool;
import com.dto.DealData;
import com.entity.Form;
import com.entity.Setting;
import com.entity.Student;
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
		Long studentId = (Long) session.getAttribute("studentId");
		String fileName = null;
		Form form = attachService.getForm(studentId);
		boolean isSelect = attachService.isSelectTopic(studentId);
		if(form != null) {
			fileName = form.getOpeningReport();
		}
		
		Setting settings = (Setting) session.getAttribute("setting");
		boolean isNow = false;
		if(settings != null) {
			String startTime = settings.getOpenReportStartTime();
			String endTime = settings.getOpenReportEndTime();
		    isNow = dealData.isNow(startTime, endTime);
		}
		
		request.setAttribute("isNow", isNow);
		request.setAttribute("isSelect", isSelect);
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
//		String path = request.getSession().getServletContext().getRealPath("upload");
		String path = PathTool.getPath();
		Long studentId = (Long) session.getAttribute("studentId");
		boolean result = false;
//		获取时间
		Setting settings = (Setting) session.getAttribute("setting");
		String startTime = settings.getOpenReportStartTime();
		String endTime = settings.getOpenReportEndTime();
	    boolean isNow = dealData.isNow(startTime, endTime);
//	          如果在当前时间段，进行操作
	    if(isNow) {
	    	result = attachService.addOpenReport(file, path, studentId);
			if(result) {
				JSONObject json = new JSONObject();
				json.put("result", 1);
				try {
					response.getWriter().println(json.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
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
	 * 查看中期报告提交情况
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewMidReport")
	public String viewMidReport(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Long studentId = (Long) session.getAttribute("studentId");
		String fileName = null;
		boolean isSelect = attachService.isSelectTopic(studentId);
		Form form = attachService.getForm(studentId);
		if(form != null) {
			fileName = form.getInterimReport();
		}
		Setting settings = (Setting) session.getAttribute("setting");
		boolean isNow = false;
		if(settings != null) {
			String startTime = settings.getMidReportStartTime();
			String endTime = settings.getMidReportEndTime();
//			判断是否是当前时间段
		    isNow = dealData.isNow(startTime, endTime);
		}
		
		request.setAttribute("isNow", isNow);
		request.setAttribute("isSelect", isSelect);
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
//		String path = request.getSession().getServletContext().getRealPath("upload");
		String path = PathTool.getPath();
		Long studentId = (Long) session.getAttribute("studentId");
		boolean result = false;
//		获取时间
		Setting settings = (Setting) session.getAttribute("setting");
		String startTime = settings.getMidReportStartTime();
		String endTime = settings.getMidReportEndTime();
	    boolean isNow = dealData.isNow(startTime, endTime);
//	          如果在当前时间段，进行操作
	    if(isNow) {
			result = attachService.addMidReport(file, path, studentId);
			if(result) {
				JSONObject json = new JSONObject();
				json.put("result", 1);
				try {
					response.getWriter().println(json.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return null;
			} 
	    }
	    JSONObject json = new JSONObject();
		json.put("result", 0);
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
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
		Long studentId = (Long) session.getAttribute("studentId");
		String fileName = null;
		boolean isSelect = attachService.isSelectTopic(studentId);
		Form form = attachService.getForm(studentId);
		if(form != null) {
			fileName = form.getFileName();
		}
		Setting settings = (Setting) session.getAttribute("setting");
		boolean isNow = false;
		if(settings != null) {
			String startTime = settings.getCommitAttachStartTime();
			String endTime = settings.getCommitAttachEndTime();
//			判断是否是当前时间段
		    isNow = dealData.isNow(startTime, endTime);
		}
		
		request.setAttribute("isNow", isNow);
		request.setAttribute("isSelect", isSelect);
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
//		String path = request.getSession().getServletContext().getRealPath("upload");
		String path = PathTool.getPath();
		Long studentId = (Long) session.getAttribute("studentId");
		boolean result = false;
//		获取时间
		Setting settings = (Setting) session.getAttribute("setting");
		String startTime = settings.getCommitAttachStartTime();
		String endTime = settings.getCommitAttachEndTime();
	    boolean isNow = dealData.isNow(startTime, endTime);
//	          如果在当前时间段，进行操作
	    if(isNow) {
			result = attachService.addSubmitThesis(file, path, studentId);
			if(result) {
				JSONObject json = new JSONObject();
				json.put("result", 1);
				try {
					response.getWriter().println(json.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			} 
	    }
	    JSONObject json = new JSONObject();
		json.put("result", 0);
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
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
	public String instructorReview(Long gradeId, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Setting setting = attachService.getSetting(gradeId);
		boolean isNow = false;
		String startTime = setting.getInstructorReviewStartTime();
		String endTime = setting.getInstructorReviewEndTime();
//			判断是否是当前时间段
	    isNow = dealData.isNow(startTime, endTime);
		
    	Long teacherId = (Long)session.getAttribute("teacherId");
		List<Student> students = null;
		students = attachService.instructorReview(String.valueOf(gradeId), teacherId);
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
	public String submitInstructorReview(float mediumScore,long studentId, @RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		
		Boolean isNow = (Boolean) session.getAttribute("isNow");
//	            如果当前是提交时间，保存信息
	    if(isNow) {
//	    	String path = request.getSession().getServletContext().getRealPath("upload");
	    	String path = PathTool.getPath();
	    	if(attachService.submitInstructorReview(path, studentId, mediumScore, file)) {
	    		JSONObject json = new JSONObject();
	    		json.put("result", 1);
	    		try {
					response.getWriter().println(json.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	} else {
	    		JSONObject json = new JSONObject();
	    		json.put("result", 0);
	    		try {
					response.getWriter().println(json.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    	
//			if(attachService.submitInstructorReview(path, studentId, mediumScore, file)) {
//				request.setAttribute("message", "成功！");
//				request.setAttribute("path", "attach/instructorReview.do?gradeId="+gradeId);
//				return "common/success";
//			} else {
//				request.setAttribute("message", "失败！");
//				request.setAttribute("path", "attach/instructorReview.do?gradeId="+gradeId);
//				return "common/failed";
//			}
	    } else {
	    	JSONObject json = new JSONObject();
    		json.put("result", 0);
    		try {
				response.getWriter().println(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    }
	    return null;
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
	public String midReview(Long gradeId, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Setting setting = attachService.getSetting(gradeId);
		boolean isNow = false;
		String startTime = setting.getMidReviewStartTime();
		String endTime = setting.getMidReviewEndTime();
//			判断是否是当前时间段
	    isNow = dealData.isNow(startTime, endTime);
		
		
	    Long teacherId = (Long)session.getAttribute("teacherId");
		List<Student> students = null;
		students = attachService.midReview(String.valueOf(gradeId), teacherId);
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
	public String submitMidReview(float score,long studentId, @RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		
		Boolean isNow = (Boolean) session.getAttribute("isNow");
		JSONObject json = new JSONObject();
//	            如果当前是提交时间，保存信息
	    if(isNow) {
//	    	String path = request.getSession().getServletContext().getRealPath("upload");
	    	String path = PathTool.getPath();
	    	if(attachService.submitMidReview(path, studentId, score, file)) {
	    		try {
	    			json.put("result", 1);
					response.getWriter().println(json.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
	    		return null;
	    	}
	    } 
	    
		try {
			 json.put("result", 0);
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return null;
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
	public String replyResults(Long gradeId, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Setting setting = attachService.getSetting(gradeId);
		boolean isNow = false;
		String startTime = setting.getReplyResultsStartTime();
		String endTime = setting.getReplyResultsEndTime();
//			判断是否是当前时间段
	    isNow = dealData.isNow(startTime, endTime);
		
		
	    Long teacherId = (Long)session.getAttribute("teacherId");
		List<Student> students = null;
		students = attachService.replyResults(String.valueOf(gradeId), teacherId);
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
		
		Boolean isNow = (Boolean) session.getAttribute("isNow");
		JSONObject json = new JSONObject();
//	            如果当前是提交时间，保存信息
	    if(isNow) {
	    	//String path = request.getSession().getServletContext().getRealPath("upload");
	    	String path = PathTool.getPath();
	    	if(attachService.submitReplyResults(path, studentId, score, file, level)) {
				json.put("result", 1);
	    		try {
					response.getWriter().println(json.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
	    		return null;
			} 
	    }
	    
	    
		try {
			json.put("result", 0);
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return null;
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
	public String downAttach(Long gradeId, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		//String path = request.getSession().getServletContext().getRealPath("upload");
		String path = PathTool.getPath();
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
