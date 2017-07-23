package com.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.common.Pagination;
import com.common.ServerResponse;
import com.dto.DealData;
import com.entity.Clazz;
import com.entity.CourseAndGrade;
import com.entity.Grade;
import com.entity.IntentionTopic;
import com.entity.Setting;
import com.entity.Student;
import com.entity.SubTopic;
import com.entity.Teacher;
import com.entity.Topics;
import com.entity.User;
import com.service.CommonService;
import com.service.GradeService;
import com.service.StudentService;

/**
 * 学生相关操作Controller
 * @author kone
 * 2016-1-13
 */
@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private DealData dealData;
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private GradeService gradeService;
	/**
	 * 查看年级，选择年级后选择学生
	 * @param session
	 * @param request
	 * @param response
	 * @param type
	 * @param pagination
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/viewGrade")
	public String viewGrade(HttpSession session,HttpServletRequest request,HttpServletResponse response){
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		List<Grade> grades = null;
		if(teachers.size()>0){
			grades = gradeService.viewGrades(teachers.get(0).getDepartment().getId());
		}
		request.setAttribute("grades", grades);
		request.setAttribute("message", "view");
//		获取完数据后关闭session
		commonService.closeSession();
		return "student/viewGrade";
	}
	/**
	 * 查看学生
	 * @param request
	 * @param response
	 * @param type
	 * @param page
	 * @return
	 */
	@RequestMapping("/viewStudent")
	public String viewStudent(HttpSession session,HttpServletRequest request,HttpServletResponse response, String pageType, String type,Pagination pagination, Long gradeId){
//		将gradeId保存为session，后面返回使用
		session.setAttribute("gradeId", gradeId);
		List<Teacher> teachers = (List<Teacher>) session.getAttribute("infor");
		if(teachers.size()>0){
			request.setAttribute("departmentId", teachers.get(0).getDepartment().getId());
		}
//		如果是输入的页数进行减一
		if("1".equals(pageType)) {
			pagination.setPage(pagination.getPage() - 1);
		}
		
		int eachPage = 15;
		pagination.setEachPage(eachPage);
		pagination.setTotleSize(studentService.getStudentsNum(gradeId));//获取总记录数
//		获取完数据后关闭session
		commonService.closeSession();
		
		List<Student> students = studentService.viewStudents(String.valueOf(gradeId), pagination.getPage()*eachPage, eachPage);
//		获取完数据后关闭session
		commonService.closeSession();
		
//		处理分页数据
		pagination = dealData.getPagination(students, pagination);
		
		request.setAttribute("students", students);
		request.setAttribute("pagination", pagination);
		request.setAttribute("message", "view");
		request.setAttribute("gradeId", gradeId);
		return "student/viewStudent";
	}
	
	/**
	 * 保存学生
	 * @param classId
	 * @param student
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addStudent")
	public String addStudent(long classId,Student student,HttpServletRequest request,HttpServletResponse response){
		Clazz clazz = new Clazz();
		clazz.setId(classId);
		student.setClazz(clazz);
//		student.setAge("0");
		
//		设置登录信息
		User user = new User();
		user.setPassword("123456");
		user.setUsername(student.getNo());
		user.setPrivilege("4");
		
		student.setUser(user);
		try {
			if(commonService.save(student)){
				response.getWriter().println("1");
			}else{
				response.getWriter().println("0");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 批量保存学生信息
	 * @param file
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/batchImportStudent")
	public String batchImportStudent(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String gradeId = (String) session.getAttribute("gradeId");
//		获取登录人员的信息
		String path = request.getSession().getServletContext().getRealPath("upload");
		List<Student> students = null;
		if (!file.isEmpty()) {
			students = studentService.batchImportStudent(file, path);
			if (students.size() == 0) {
				request.setAttribute("message", "上传文件错误，或者是上传出错！");
				request.setAttribute("path", "student/viewStudent.do?gradeId="+gradeId);
				return "common/failed";
			} else {
				commonService.batchImport(students);
			}
			request.setAttribute("path", "student/viewStudent.do?gradeId="+gradeId);
			return "common/success";
//			request.setAttribute("students", students);
//			return "student/viewStudent2";
		}
		request.setAttribute("message", "请选择文件！");
		request.setAttribute("path", "student/viewStudent.do?gradeId="+gradeId);
		return "common/failed";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/exportStudent")
	public String exportStudent(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String gradeId = (String) session.getAttribute("gradeId");
		List<Student> students = studentService.viewStudents(gradeId, 0, 1000000);
		
		//创建HSSFWorkbook对象(excel的文档对象)
	     HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("学生信息表");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1=sheet.createRow(0);
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell=row1.createCell(0);
		      //设置单元格内容
		cell.setCellValue("学生信息表");
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,12));
		
		//在sheet里创建第二行
		HSSFRow row2=sheet.createRow(1);    
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("学号");
        row2.createCell(1).setCellValue("姓名");    
        row2.createCell(2).setCellValue("性别");
		row2.createCell(3).setCellValue("电话");  
		row2.createCell(4).setCellValue("QQ"); 
		row2.createCell(5).setCellValue("邮箱"); 
		row2.createCell(6).setCellValue("班级"); 
		row2.createCell(7).setCellValue("方向"); 
		row2.createCell(8).setCellValue("专业");
		row2.createCell(9).setCellValue("年级"); 
		row2.createCell(10).setCellValue("系"); 
		row2.createCell(11).setCellValue("学院"); 
		
		HSSFRow row = null;
		for (int i=0;i<students.size();i++) {
			row = sheet.createRow(i+2);
			row.createCell(0).setCellValue(students.get(i).getNo());
			row.createCell(1).setCellValue(students.get(i).getName());
			row.createCell(2).setCellValue(students.get(i).getSex());
			row.createCell(3).setCellValue(students.get(i).getTelephone());
			row.createCell(4).setCellValue(students.get(i).getQq());
			row.createCell(5).setCellValue(students.get(i).getEmail());
			row.createCell(6).setCellValue(students.get(i).getClazz().getClassName());
			row.createCell(7).setCellValue(students.get(i).getClazz().getDirection().getDirectionName());
			row.createCell(8).setCellValue(students.get(i).getClazz().getDirection().getSpceialty().getSpecialtyName());
			row.createCell(9).setCellValue(students.get(i).getClazz().getDirection().getSpceialty().getGrade().getGradeName());
			row.createCell(10).setCellValue(students.get(i).getClazz().getDirection().getSpceialty().getGrade().getDepartment().getDepartmentName());
			row.createCell(11).setCellValue(students.get(i).getClazz().getDirection().getSpceialty().getGrade().getDepartment().getCollege().getCollegeName());
			
		}
		
	   try {
		//输出Excel文件
	    OutputStream output=response.getOutputStream();
	    if (output instanceof HttpServletResponse) {  
	        setResponseHeader((HttpServletResponse) output, "kone");  
	    }  
	    response.reset();
	    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
	    response.setContentType("application/octet-stream;charset=utf-8");
	    response.setHeader("Content-Disposition", "attachment;filename="  
	            + java.net.URLEncoder.encode("名单"+ new SimpleDateFormat("yyyy-MM-dd-HH").format(new Date()) + ".xls", "utf-8"));
	    //  客户端不缓存  
	    response.addHeader("Pragma", "no-cache");  
	    response.addHeader("Cache-Control", "no-cache"); 
	    //输出Excel文件
			wb.write(output);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    commonService.closeSession();
		return null;
	}

	private void setResponseHeader(HttpServletResponse output, String string) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 学生查看题目，选择题目
	 * @return
	 */
	@RequestMapping("/viewTopicsStudent")
	public String viewTopicsStudent(Pagination pagination, String pageType, HttpServletRequest request,HttpServletResponse response,HttpSession session){
//		如果是输入的页数进行减一
		if("1".equals(pageType)) {
			pagination.setPage(pagination.getPage() - 1);
		}
		
		int eachPage = 15;
		pagination.setEachPage(eachPage);
		
		
		List<Topics> topics = null;
		List<Student> students = (List<Student>) session.getAttribute("infor");
		if(students.size() > 0) {
			pagination.setTotleSize(studentService.getTopicCount(students.get(0).getClazz().getDirection().getId()));//获取总记录数
			if(students.get(0).getTopics() == null) {
				request.setAttribute("selected", "no");
			} else {
				request.setAttribute("selected", "yes");
			}
		} else {
			request.setAttribute("selected", "no");
		}
		

		
		Setting setting = (Setting) session.getAttribute("setting");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (setting != null) {
			DealData deal = new DealData();
//			第一轮选题开始，结束时间
			if(deal.isNow(setting.getOneSelectStartTime(), setting.getOneSelectEndTimeOne())) {
				List<Student> infor =  (List<Student>)session.getAttribute("infor");
				topics = studentService.viewTopic(infor.get(0), 1, pagination.getPage(), eachPage);
				request.setAttribute("message", "第一轮选题");
			} else if(deal.isNow(setting.getTwoSelectStartTime(), setting.getTwoSelectEndTimeOne())) {//第二轮选题开始，结束时间
				List<Student> infor =  (List<Student>)session.getAttribute("infor");
				topics = studentService.viewTopic(infor.get(0), 2, pagination.getPage(), eachPage);
				request.setAttribute("message", "第二轮选题");
			} else {
				request.setAttribute("message", "不是选题的时间！");
			}
			
		} else {
			request.setAttribute("message", "不是选题的时间！");
		}
	
//		处理分页数据
		pagination = dealData.getPagination(students, pagination);
		request.setAttribute("topics", topics);
		request.setAttribute("pagination", pagination);
		return "student/viewTopics";
	}
	/**
	 * 学生选择意向题目
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectIntentionTopic")
	public String selectIntentionTopic(int choice,Topics topic,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		List<Student> infor =  (List<Student>)session.getAttribute("infor");
		Setting setting = (Setting) session.getAttribute("setting");
		PrintWriter out = null;
		try {
			out =  response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		if(infor.size()>0 && setting != null) {
//			获取当前日期是第几次选题
			int batch = dealData.getBatch(setting);
			int result = studentService.selectIntentionTopic(infor.get(0), choice, batch, topic);
			
			out.print(result);
		}
		return null;
	}
	
	/**
	 * 更新意向题目
	 * @param type 判断是更改志愿字段还是 题目字段
	 * @param choice
	 * @param id 题目id
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/updateIntentionTopic")
	public String updateIntentionTopic(int type,int choice,Long id,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		List<Student> infor =  (List<Student>)session.getAttribute("infor");
		
		Setting setting = (Setting) session.getAttribute("setting");
		PrintWriter out = null;
		try {
			out =  response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(infor.size() > 0 && setting != null) {
//			获取当前日期是第几次选题
			int batch = dealData.getBatch(setting);
			boolean result = studentService.updateIntentionTopic(infor.get(0).getId(), choice, batch, id, type);
			if(result) {
				out.print(1);
			}else{
				out.print(0);
			}
		}
		return null;
	}
	/**
	 * 查看意向题目
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewIntentionTopic")
	public String viewIntentionTopic(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		List<Student> infor =  (List<Student>)session.getAttribute("infor");
		String studentId = "0" ;
		if(infor.size()>0){
			studentId = String.valueOf(infor.get(0).getId());
		}
		List<IntentionTopic> intentionTopics = commonService.findBy("IntentionTopic", "studentId", studentId);
		commonService.closeSession();
		
		request.setAttribute("intentionTopics", intentionTopics);
		return "student/viewIntentionTopic";
	}
	/**
	 * 查看学生详情	
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewStudentOne")
	public String viewStudentOne(String filter, String id, String no, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		List<Teacher> infor =  (List<Teacher>)session.getAttribute("infor");
		
		if(infor.size() > 0) {
			Long gradeId = (Long) session.getAttribute("gradeId");
			List<Student> students = commonService.find("Student", id);
			request.setAttribute("students", students);
			List<CourseAndGrade> courseAndGrades = null;
			List<CourseAndGrade> courseAndGrades2 = new ArrayList<CourseAndGrade>();
			commonService.closeSession();
			courseAndGrades = commonService.findBy("CourseAndGrade", "no", no);
			commonService.closeSession();
			if("yes".equals(filter)) {
				courseAndGrades2 = studentService.getCourseAndGradesFilter(courseAndGrades, infor.get(0), String.valueOf(gradeId), no);
			}
			
			if(courseAndGrades2.size() == 0) {
//				计算平均分
				float total = 0;
				for(int i=0;i<courseAndGrades.size();i++) {
					total += courseAndGrades.get(i).getScore();
				}
				request.setAttribute("courseAndGrades", courseAndGrades);
				request.setAttribute("average", total/courseAndGrades.size());
			} else {
//				计算平均分
				float total = 0;
				for(int i=0;i<courseAndGrades2.size();i++) {
					total += courseAndGrades2.get(i).getScore();
				}
				request.setAttribute("courseAndGrades", courseAndGrades2);
				request.setAttribute("average", total/courseAndGrades2.size());
			}

		}
		
		return "student/viewStudentDetials";
	}
	
	/**
	 * 学生查看成绩
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewScoreStudent")
	public String viewScoreStudent(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		List<Student> students = (List<Student>) session.getAttribute("infor");
		if (students.size() > 0) {
			request.setAttribute("student", students.get(0));
		}
		return "student/viewScore";
	}
	
	/**
	 * 学生查看最终选题
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/finalSelection")
	public String finalSelection(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Long studentId = (Long) session.getAttribute("studentId");
		request.setAttribute("student", studentService.getStudent(studentId));
		return "student/finalSelection";
	}
	
//	/**
//	 * 提交论文
//	 * @param file
//	 * @param request
//	 * @param response
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping("/submitThesis")
//	public String submitThesis(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response,HttpSession session){
//		List<Student> students = (List<Student>) session.getAttribute("infor");
//		if (students.size() > 0) {
//			List<Score> scores = commonService.findBy("Score", "studentId", String.valueOf(students.get(0).getId()));
//			commonService.closeSession();
////			获取当前时间
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String time = df.format(new Date());
//			Score score = new Score();
//			score.setStudent(students.get(0));
//			
//			score.setTime(time);
//			
//			String fileName = "";
//			String origName = "";
//			File file2 = null;
//			if(!file.isEmpty()){
//				String path = request.getSession().getServletContext().getRealPath("upload");
//				origName = file.getOriginalFilename();
////				int newNameIndex = origName.lastIndexOf('.');
////				String suffix = origName.substring(newNameIndex);
////				long name = System.currentTimeMillis();
//////				文件随机名称
////				fileName = String.valueOf(name)+suffix;
//				file2 = new File(path,origName);
//				if(!file2.exists()){
//					file2.mkdirs();
//				}
//				score.setFileName(origName);
//			}
////			从未上传文件
//			if (scores.size() == 0) {
////				保存信息
//				if (commonService.save(score)) {
//					try {
//						//保存文件
//						if(!file.isEmpty()){
//							file.transferTo(file2);
//						}
//						
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					request.setAttribute("message", "上传成功！");
//					request.setAttribute("path", "student/goSubmitThesis.do");
//					return "common/success";
//				} else {
//					request.setAttribute("path", "student/goSubmitThesis.do");
//					request.setAttribute("message", "上传失败！保存出错！");
//					return "common/failed";
//				}
//			} else {
////				上传了文件，更新
////				QueryCondition queryCondition = new QueryCondition();
////				queryCondition.setTable("Score");
////				queryCondition.setConunt(2);
////				queryCondition.setRow1("fileName");
////				queryCondition.setValue1(origName);
////				queryCondition.setRow2("time");
////				queryCondition.setValue2(time);
////				commonService.updateByFree(queryCondition);
//				scores.get(0).setFileName(origName);
//				scores.get(0).setTime(time); 
//				if ( commonService.update(scores.get(0)) ) {
//					request.setAttribute("path", "student/goSubmitThesis.do");
//					request.setAttribute("message", "更新成功！");
//					return "common/success";
//				}
//				request.setAttribute("path", "student/goSubmitThesis.do");
//				request.setAttribute("message", "更新失败！");
//				return "common/failed";
//			}
//			
//		} else {
//			request.setAttribute("path", "student/goSubmitThesis.do");
//			request.setAttribute("message", "上传失败！登录信息失效！请重新登录");
//			return "common/failed";
//		}
//		
//		
//		
//	}
	
	
//	/**
//	 * 前往题目提交界面
//	 * @param request
//	 * @param response
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping("/goSubmitThesis")
//	public String goSubmitThesis(HttpServletRequest request, HttpServletResponse response, HttpSession session){
////		获取时间
//		Setting settings = (Setting) session.getAttribute("setting");
//		String startTime = settings.getMidReportStartTime();
//		String endTime = settings.getMidReportEndTime();
//	    boolean isNow = dealData.isNow(startTime, endTime);
//		
////		查询是否提交论文
//		List<Student> students = (List<Student>) session.getAttribute("infor");
//		List<Score> scores = null;
//		if (students.size() > 0) {
//			scores = commonService.findBy("Score", "studentId", String.valueOf(students.get(0).getId()));
//			commonService.closeSession();
//		}
//		request.setAttribute("scores", scores);
//		
//		request.setAttribute("result", isNow);
//
//		return "student/submitThesis";
//	}
//	
	/**
	 * 学生在手机端查看成绩
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viewScoreApp")
	@ResponseBody
	public  ServerResponse<User> loginApp(String userId, HttpServletRequest request,HttpServletResponse response){
		
		return studentService.viewScoreApp(userId);
	}
	
	/**
	 * 学生在手机端查看最终选题
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/finalSelectionApp")
	@ResponseBody
	public ServerResponse<Student> finalSelectionApp(String userId, HttpServletRequest request,HttpServletResponse response){
		List<Student> students2 = commonService.findBy("Student", "id", userId);
		if(students2.size() > 0) {
			Topics topic = new Topics();
			if(students2.get(0).getTopics() != null) {
				students2.get(0).getTopics().getId();
				topic.setTopicsName(students2.get(0).getTopics().getTopicsName());
				topic.setIntroduce(students2.get(0).getTopics().getIntroduce());
				Teacher t = new Teacher();
				t.setTelephone(students2.get(0).getTopics().getTeacher().getTelephone());
				t.setName(students2.get(0).getTopics().getTeacher().getName());
				t.setEmail(students2.get(0).getTopics().getTeacher().getEmail());
				t.setQq(students2.get(0).getTopics().getTeacher().getQq());
				
				topic.setTeacher(t);
			}
			
			SubTopic sub = new SubTopic();
			if(students2.get(0).getSubTopic() != null) {
				sub.setSubName(students2.get(0).getSubTopic().getSubName());
			}
			
			
			
			
			
			Student stu = new Student();
			stu.setTopics(topic);
			stu.setSubTopic(sub);
			commonService.closeSession();
			return ServerResponse.response(200, "获取成功", stu);
		} 
		commonService.closeSession();
			
		return ServerResponse.response(201, "获取失败", new Student());
	}
	
	
	/**
	 * 学生在手机端查看题目，选择题目
	 * @return
	 */
	@RequestMapping("/viewTopicsStudentApp")
	@ResponseBody
	public ServerResponse<Set<Topics>> viewTopicsStudentApp(String userId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		List<Topics> topics = null;
		List<Student> students = (List<Student>) commonService.find("Student", userId);
		commonService.closeSession();
		if(students.size() > 0) {
			Setting setting = students.get(0).getClazz().getDirection().getSpceialty().getGrade().getSetting();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (setting != null) {
				
			
				if(students.get(0).getTopics() == null) {
					try {
	//					当前时间
						Date now = new Date();
	//					第一轮选题开始，结束时间
						Date oneStart = sdf.parse(setting.getOneSelectStartTime().replace("T"," ").concat(":00"));
						Date oneEnd = sdf.parse(setting.getOneSelectEndTimeOne().replace("T"," ").concat(":00"));
						
	//					第二轮选题开始，结束时间
						Date twoStart = sdf.parse(setting.getTwoSelectStartTime().replace("T"," ").concat(":00"));
						Date twoEnd = sdf.parse(setting.getTwoSelectEndTimeOne().replace("T"," ").concat(":00"));
						
	//					在第一轮选题时间之间
						if(now.getTime() > oneStart.getTime() && now.getTime() < oneEnd.getTime() ) {
							topics =  studentService.viewTopic(students.get(0), 1, 0, 1000);
							
	//					在第二轮选题时间之间
						} else if(now.getTime() > twoStart.getTime() && now.getTime() < twoEnd.getTime() ) {
							topics = studentService.viewTopic(students.get(0), 2, 0, 1000);
	//					不是选题时间
						} else {
							return ServerResponse.response(203, "不是选题的时间！", null);
						}
						
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Teacher t = null;
					for(Topics obj :topics) {
						t = new Teacher();
						obj.setDirections(null);
						obj.setGrade(null);
						obj.setIntentionTopics(null);
						obj.setStudents(null);
						obj.setSubTopic(null);
						
						t.setName(obj.getTeacher().getName());
						obj.setTeacher(t);
					}
					
					return null;
				} else {
					return ServerResponse.response(201, "已选择题目！", null);
				}
			} else {
				return ServerResponse.response(203, "不是选题的时间！", null);
			}
			
		} else {
			return ServerResponse.response(202, "用户信息错误", null);
		}
	}
	
	
	/**
	 * 查看意向题目APP
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/viewIntentionTopicApp")
	@ResponseBody
	public ServerResponse<List<IntentionTopic>> viewIntentionTopicApp(String studentId, HttpServletRequest request,HttpServletResponse response,HttpSession session){
		
		List<IntentionTopic> intentionTopics = commonService.findBy("IntentionTopic", "studentId", studentId);
		Teacher t = null;
		for(int i=0;i<intentionTopics.size();i++) {
			intentionTopics.get(i).getTopic().getTopicsName();
			intentionTopics.get(i).setStudent(null);
			intentionTopics.get(i).getTopic().setDirections(null);
			intentionTopics.get(i).getTopic().setGrade(null);
			intentionTopics.get(i).getTopic().setStudents(null);
			intentionTopics.get(i).getTopic().setSubTopic(null);
			intentionTopics.get(i).getTopic().setIntentionTopics(null);
			intentionTopics.get(i).getTopic().setStudents(null);
//			intentionTopics.get(i).getTopic().setTeacher(null);
			
			t = new Teacher();
			t.setName(intentionTopics.get(i).getTopic().getTeacher().getName());
			intentionTopics.get(i).getTopic().setTeacher(t);
		}
		commonService.closeSession();
		return ServerResponse.response(200, "获取成功", intentionTopics);
	}
	
	
}
