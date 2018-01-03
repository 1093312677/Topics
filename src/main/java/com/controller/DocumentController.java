package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.common.PathTool;
import com.entity.Document;
import com.service.DocumentService;

@Controller
@RequestMapping("/document")
public class DocumentController {
	@Autowired
	private DocumentService  documentService;
	/**
	 * 上传文档
	 * @param file
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/uploadDocument")
	public String uploadDocument(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Long departmentId = (Long) session.getAttribute("departmentId");
		//String path = request.getSession().getServletContext().getRealPath("upload");
		String path = PathTool.getPath();
		if(documentService.uploadDocument(file, departmentId, path)) {
			request.setAttribute("message", "上传成功");
			request.setAttribute("path", "document/goDocument.do");
			return "common/success";
		} else {
			request.setAttribute("message", "上传失败");
			request.setAttribute("path", "document/goDocument.do");
			return "common/failed";
		}
		
	}
	
	@RequestMapping("/goDocument")
	public String goDocument(HttpServletRequest request, HttpServletResponse response){
		
		return "document/uploadDocument";
	}
	/**
	 * 查看文档
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewDocument")
	public String viewDocument(String type, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		List<Document> documents = null;
		Long departmentId = (Long) session.getAttribute("departmentId");
		if("teacher".equals(type)) {
			documents = documentService.viewDocument(departmentId);
		} else {
			Long studentDepartmentId = (Long) session.getAttribute("studentDepartmentId");
			documents = documentService.viewDocument(studentDepartmentId);
		}
		request.setAttribute("documents", documents);
		return "document/viewDocument";
	}
	
	@RequestMapping("/download")
	public String download(String randName, String documentName, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		//String path = request.getSession().getServletContext().getRealPath("upload");
		String path = PathTool.getPath();
		File file = new File(path,randName);
		try {
			
			// 清空response
	        response.reset();
	        // 设置response的Header
	        response.setContentType("application/octet-stream;charset=utf-8");
	        response.addHeader("Content-Disposition", "attachment;filename=" + new String(documentName.getBytes("gbk"),"iso-8859-1"));
		    //  客户端不缓存  
		    response.addHeader("Pragma", "no-cache");  
		    response.addHeader("Cache-Control", "no-cache"); 
			
			FileInputStream fis = new FileInputStream(file);
			int len;
			byte [] buffer = new byte[1024];
			while((len = fis.read(buffer))!=-1) {
				response.getOutputStream().write(buffer,0,len);
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 删除模板文档
	 * @return
	 */
	@RequestMapping("/deleteDocument")
	public String deleteDocument(HttpServletResponse response, Document document) {
		if(documentService.deleteDocument(document)) {
			try {
				response.getWriter().println(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().println(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
