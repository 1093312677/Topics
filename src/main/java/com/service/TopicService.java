package com.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 题目的相关逻辑处理
 * @author kone
 * 2017.4.23
 */
import org.springframework.web.multipart.MultipartFile;

import com.common.ServerResponse;
import com.dao.ISettingDao;
import com.dao.impl.DaoImpl;
import com.dao.impl.TopicDaoImpl;
import com.dto.DealData;
import com.dto.GroupAndTime;
import com.entity.Grade;
import com.entity.Setting;
import com.entity.Student;
import com.entity.SubTopic;
import com.entity.Teacher;
import com.entity.TeacherAutoSelect;
import com.entity.TeacherGroup;
import com.entity.Topics;
import com.jsonPo.GuideStudents;
@Service
public class TopicService {
	@Autowired
	private DaoImpl daoImpl;
	
	@Autowired
	private TopicDaoImpl topicDaoImpl;
	
	@Autowired
	private ISettingDao settingDao;
	
	@Autowired
	private DealData dealData;
	
	public void closeSession(){
		topicDaoImpl.closeSession();
	}
	
	/**
	 * 判断当前是否是上传题目时间
	 * @param gradeId
	 * @return
	 */
	public boolean goAddTopic(Long gradeId) {
		Setting setting = settingDao.getSetting(gradeId);
		if(setting == null) {
			return false;
		}
		String startTime = setting.getCommitTopicStartTime();
		String endTime = setting.getCommitTopicEndTime();
	    boolean isNow = dealData.isNow(startTime, endTime);
		return isNow;
	}
	
	/**
	 * 查看未通过题目
	 * @param gradeId
	 * @param teacherId
	 * @return
	 */
	public List<Topics> viewNotThoughtTopic(String gradeId, long teacherId, String state) {
		List<Topics> topics = topicDaoImpl.findByTwo("Topics", "teacherId", String.valueOf(teacherId), "state", state);
		for(int i=topics.size() - 1;i >= 0;i--) {
			if(topics.get(i).getGrade().getId() != Long.valueOf(gradeId)) {
				
				topics.remove(i);
			}
		}
		for(int i=0;i<topics.size();i++) {
			for(int j=0;j<topics.get(i).getDirections().size();j++) {
				topics.get(i).getDirections().get(j).getDirectionName();
			}
		}
		return topics;
	}
	/**
	 * 更新或上传附件
	 * @param path
	 * @param id
	 * @param file
	 * @return
	 */
	public boolean addUpdateAttach(String path, String id, MultipartFile file) {
		List<Topics> topics = daoImpl.findBy("Topics", "id", id);
		daoImpl.closeSession();
		if(topics.size() > 0) {
			
			if(!file.isEmpty()) {
				String origName = file.getOriginalFilename();
				int newNameIndex = origName.lastIndexOf('.');
				String suffix = origName.substring(newNameIndex);
				long name = System.currentTimeMillis();
//				文件随机名称
				String fileName = String.valueOf(name)+(int)(Math.random()*10000)+suffix;
				File file3 = new File(path,fileName);
				
				String tempName = topics.get(0).getTaskBookName();
				
				topics.get(0).setTaskBookName(fileName);
				if(daoImpl.update(topics.get(0))) {
					if(topics.get(0).getTaskBookName() != null) {
						File file2 = new File(path, tempName);
						if(file2.exists()) {
							file2.delete();
						}
						
					}
					try {
						file.transferTo(file3);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return true;
				}
				
			}
		}
		return false;
	}
	
	
	/**
	 * 导出选题情况
	 * @param gradeId
	 * @return
	 */
	public HSSFWorkbook exportTopic(String gradeId) {
		List<Topics> topics = daoImpl.findByTwo("Topics", "gradeId", gradeId,"state","1");
		
		//创建HSSFWorkbook对象(excel的文档对象)
	     HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("选题情况表");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1=sheet.createRow(0);
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell=row1.createCell(0);
		      //设置单元格内容
		cell.setCellValue("选题情况表");
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
		
		//在sheet里创建第二行
		HSSFRow row2=sheet.createRow(1);    
      //创建单元格并设置单元格内容
       row2.createCell(0).setCellValue("编号");
       row2.createCell(1).setCellValue("题目名称");    
       row2.createCell(2).setCellValue("适用方向");
		row2.createCell(3).setCellValue("可选学生");  
		row2.createCell(4).setCellValue("已选学生"); 
		row2.createCell(5).setCellValue("待选学生");
		row2.createCell(6).setCellValue("指导老师"); 
		row2.createCell(7).setCellValue("发布时间"); 
		row2.createCell(8).setCellValue("题目简介"); 
		row2.createCell(9).setCellValue("是否上传任务书"); 
		
		HSSFRow row = null;
		for (int i=0;i<topics.size();i++) {
			row = sheet.createRow(i+2);
			row.createCell(0).setCellValue(topics.get(i).getId());
			row.createCell(1).setCellValue(topics.get(i).getTopicsName());
			String dire = "";
			for(int j=0;j<topics.get(i).getDirections().size();j++) {
				dire = dire+topics.get(i).getDirections().get(j).getDirectionName()+",";
			}
			row.createCell(2).setCellValue(dire);
			row.createCell(3).setCellValue(topics.get(i).getEnableSelect());
			row.createCell(4).setCellValue(topics.get(i).getSelectedStudent());
			row.createCell(5).setCellValue(topics.get(i).getEnableSelect() - topics.get(i).getSelectedStudent());
			row.createCell(6).setCellValue(topics.get(i).getTeacher().getName());
			row.createCell(7).setCellValue(topics.get(i).getTime());
			row.createCell(8).setCellValue(topics.get(i).getIntroduce());
			if (topics.get(i).getTaskBookName() == null || "".equals(topics.get(i).getTaskBookName())) {
				row.createCell(9).setCellValue("未上传任务书");
			} else {
				row.createCell(9).setCellValue("已上传任务书");
			}
		}
		daoImpl.closeSession();
		return wb;
	}
	
	
	/**
	 * 查看题目数量
	 * @param gradeId
	 * @param state
	 * @param page
	 * @param eachPage
	 * @return
	 */
	public int getTopicsNum (String gradeId, String state) {
		
		return topicDaoImpl.viewTopicNum(gradeId, state);
	}
	
	public List<Topics> getTopics (String gradeId, String state, int page, int eachPage) {
		
		return topicDaoImpl.viewTopic(gradeId, state, page, eachPage);
	}
	/**
	 * 删除未通过的题目
	 * @param topic
	 * @return
	 */
	public boolean deleteTopicNotThrought(Topics topic)	{
		
		return daoImpl.delete(topic);
	}
	
	/**
	 * 删除题目
	 * @param topic
	 * @return
	 */
	public boolean deleteTopic(String topicId)	{
		List<Topics> topics = daoImpl.find("Topics", topicId);
		daoImpl.closeSession();
		if(topics.size() > 0) {
			daoImpl.deleteSql(topicId);
			daoImpl.updateSql(topicId);
			if(daoImpl.delete(topics.get(0)))
				return true;
			return false;
		}
		
		return false;
	}
	
	/**
	 * 添加子题目
	 * @param topic
	 * @return
	 */
	public List<Student> addSubTopic(String topicId) {
		List<Student> students = daoImpl.findBy("Student", "topicsId", topicId);
		for(int i=0;i<students.size();i++) {
			students.get(i).getTopics().getTopicsName();
		}
		daoImpl.closeSession();
		return students;
	}
	
	/**
	 * 保存子题目
	 * @param topic
	 * @return
	 */
	public boolean saveSubTopic(Long studentId, long topicId, SubTopic subTopic, String path, MultipartFile file) {
		Student student = new Student();
		student.setId(studentId);
		Topics topic = new Topics();
		topic.setId(topicId);
		
		subTopic.setStudent(student);
		subTopic.setTopic(topic);
		if(!file.isEmpty()) {
			String origName = file.getOriginalFilename();
			int newNameIndex = origName.lastIndexOf('.');
			String suffix = origName.substring(newNameIndex);
			long name = System.currentTimeMillis();
//			文件随机名称
			String fileName = String.valueOf(name)+(int)(Math.random()*10000)+suffix;
			File file3 = new File(path,fileName);
			
//			查找是否存在
			List<SubTopic> subTopics = daoImpl.findByTwo("SubTopic", "topicId", String.valueOf(topicId), "studentId", String.valueOf(studentId));
			daoImpl.closeSession();
			if(subTopics.size() > 0) {
				subTopics.get(0).setSubName(subTopic.getSubName());
				subTopics.get(0).setTaskBookName(fileName);
				String t = subTopics.get(0).getTaskBookName();
				if(daoImpl.update(subTopics.get(0))) {
					File file2 = new File(path, t);
					if(file2.exists()) {
						file2.delete();
					}
					try {
						file.transferTo(file3);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
				}
				return false;
			} else {
				subTopic.setTaskBookName(fileName);
				if (daoImpl.save(subTopic) ) {
					try {
						file.transferTo(file3);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
				} 
				return false;
			}
		}
		
		return false;
	}
	
	/**
	 * 打包下载子题目
	 * @param response
	 * @param path
	 * @param gradeId
	 */
	public void downAttach(HttpServletResponse response, String path, String gradeId){
		List<Topics> topics = null;
		try{
			ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
//			查找出该年级学生
			topics = topicDaoImpl.viewTopic(gradeId, "1", 0, 10000);
			
			//创建HSSFWorkbook对象(excel的文档对象)
		     HSSFWorkbook wb = new HSSFWorkbook();
			//建立新的sheet对象（excel的表单）
			HSSFSheet sheet=wb.createSheet("子题目上传情况");
			//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
			HSSFRow row1=sheet.createRow(0);
			//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
			HSSFCell cell=row1.createCell(0);
			      //设置单元格内容
			cell.setCellValue("子题目上传情况");
			//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,12));
			
			//在sheet里创建第二行
			HSSFRow row2=sheet.createRow(1);    
	        //创建单元格并设置单元格内容
	        row2.createCell(0).setCellValue("子题目对应学生学号");
	        row2.createCell(1).setCellValue("子题目对应学生姓名");    
	        row2.createCell(2).setCellValue("主题目名称");
			row2.createCell(3).setCellValue("出题教师"); 
			row2.createCell(4).setCellValue("子题目名称"); 
			int tt = 0;
			HSSFRow row = null;
			for(int i=0;i<topics.size();i++) {
				for(int k=0;k<topics.get(i).getStudents().size();k++) {
					List<Student> students = topics.get(i).getStudents();
					if(students.get(k).getSubTopic() == null) {
						row = sheet.createRow(tt+2);
						row.createCell(0).setCellValue(students.get(k).getNo());
						row.createCell(1).setCellValue(students.get(k).getName());
						row.createCell(2).setCellValue(topics.get(i).getTopicsName());
						row.createCell(3).setCellValue(topics.get(i).getTeacher().getName());
						row.createCell(4).setCellValue("未上传子题目");
					} else {
						File file1 = new File(path,students.get(k).getSubTopic().getTaskBookName());
						String name = "";
						name += topics.get(i).getTopicsName()+"_";
						name += students.get(k).getSubTopic().getSubName()+"_";
						name += students.get(k).getNo()+"_"+students.get(k).getName();
						if(file1.exists()) {
							FileInputStream fis = new FileInputStream(file1);
//							获取后缀名
							int newNameIndex = file1.getName().lastIndexOf('.');
							String suffix = file1.getName().substring(newNameIndex);
							zos.putNextEntry(new ZipEntry("Topics/"+name+suffix));
							int len;
							byte[] buffer = new byte[1024];
							//读入需要下载的文件的内容，打包到zip文件
						    while((len = fis.read(buffer))!= -1) {
							  zos.write(buffer,0,len);
						    }
						    fis.close();
						}
					}
				}
			}
//			try {
//				response.setHeader("Content-Disposition", "attachment;filename="  
//				        + java.net.URLEncoder.encode("子题目上传情况"+ new SimpleDateFormat("yyyyMMddHH").format(new Date()) + ".xls", "utf-8"));
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			wb.write(response.getOutputStream());
//			
//			 try {
//					response.setHeader("Content-Disposition", "attachment;filename="  
//					        + java.net.URLEncoder.encode("SubTopics"+ new SimpleDateFormat("yyyyMMddHH").format(new Date()) + ".zip", "utf-8"));
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			
			zos.closeEntry();
			zos.flush();
			zos.close();
			
			topicDaoImpl.closeSession();
		} catch(Exception e) {
		}  
	}
	
	/**
	 * 查看选择该题目的学生
	 * @param topicId
	 * @return
	 */
	public List<Student> viewStudentSelected(String topicId) {
		List<Student> students = daoImpl.findBy("Student", "topicsId", topicId);
		daoImpl.closeSession();
		return students;
	}
	/**
	 * 退选学生毕业选题
	 * @param topicId
	 * @return
	 */
	public boolean withdrawalTopic(String studentId, String topicId) {
		return topicDaoImpl.withdrawalTopic(studentId, topicId);
	}
	
	/**
	 * 查看题目详情
	 * @param topicId
	 * @return
	 */
	public ServerResponse<Topics> viewTopicDetials(String topicId) {
		Topics topic = topicDaoImpl.viewTopicDetials(topicId);
		topic.setDirections(null);
		topic.setGrade(null);
		topic.setIntentionTopics(null);
		topic.setStudents(null);
		topic.setSubTopic(null);
		
		Teacher t = new Teacher();
		t.setName(topic.getTeacher().getName());
		t.setQq(topic.getTeacher().getQq());
		t.setPosition(topic.getTeacher().getPosition());
		t.setSex(topic.getTeacher().getSex());
		
		topic.setTeacher(t);
		
		topicDaoImpl.closeSession();
		return ServerResponse.response(200, "获取信息成功", topic);
	}
	
	/**
	 * 教师查看自己出的题目 app
	 * @param teacherId
	 * @param gradeId
	 * @param status 题目不同状态1通过审核，2在审核中，3未通审核
	 * @return
	 */
	public ServerResponse<List<Topics>> teacherViewTopicsApp(String teacherId, String gradeId, int status) {
		List<Topics> topics = topicDaoImpl.teacherViewTopicsApp(teacherId, gradeId, status);
		for(int i=0;i<topics.size();i++) {
			topics.get(i).setDirections(null);
//			int ds = topics.get(i).getDirections().size();
//			for(int j=0;j<ds;j++) {
//				topics.get(i).getDirections().get(j).setClazzs(null);
//				topics.get(i).getDirections().get(j).setSpceialty(null);
//				topics.get(i).getDirections().get(j).setTopics(null);
//			}
			topics.get(i).setGrade(null);
			topics.get(i).setIntentionTopics(null);
			topics.get(i).setStudents(null);
			topics.get(i).setSubTopic(null);
			topics.get(i).setTeacher(null);
		}
		
		topicDaoImpl.closeSession();
		return ServerResponse.response(200, "获取信息成功", topics);
	}
	
	/**
	 * 教师查看题目详情
	 * @param topicId
	 * @return
	 */
	public ServerResponse<Topics> teacherViewTopicDetials(String topicId) {
		Topics topic = topicDaoImpl.viewTopicDetials(topicId);
//		topic.setDirections(null);
		int ds = topic.getDirections().size();
		for(int j=0;j<ds;j++) {
			topic.getDirections().get(j).setClazzs(null);
			topic.getDirections().get(j).setSpceialty(null);
			topic.getDirections().get(j).setTopics(null);
		}
		topic.setGrade(null);
		topic.setIntentionTopics(null);
		topic.setStudents(null);
		topic.setSubTopic(null);
		
		Teacher t = new Teacher();
		t.setName(topic.getTeacher().getName());
		t.setQq(topic.getTeacher().getQq());
		t.setPosition(topic.getTeacher().getPosition());
		t.setSex(topic.getTeacher().getSex());
		
		topic.setTeacher(t);
		
		topicDaoImpl.closeSession();
		return ServerResponse.response(200, "获取信息成功", topic);
	}
	
	/**
	 * 教师查看之前查看年级 app
	 * @param departmentId
	 * @return
	 */
	public ServerResponse<List<Grade>> teacherViewGradesApp(String departmentId) {
		List<Grade> grades = topicDaoImpl.teacherViewGradesApp(departmentId);
		topicDaoImpl.closeSession();
		return ServerResponse.response(200, "获取信息成功", grades);
	}
	
	/**
	 * 教师查看分组和答辩时间和地点
	 * @param teacherId
	 * @return
	 */
	public ServerResponse<GroupAndTime> viewTeacherGroup(String teacherId, String gradeId) {
		TeacherGroup teacherGroup = topicDaoImpl.viewTeacherGroup(teacherId, gradeId);
		GroupAndTime groupAndTime = new GroupAndTime();
		if(teacherGroup != null) {
			groupAndTime.setGroupName(teacherGroup.getGroup().getGroupName());
			groupAndTime.setTime(teacherGroup.getGroup().getTimeAndPlace().getTime());
			groupAndTime.setPlace(teacherGroup.getGroup().getTimeAndPlace().getPlace());
		}
		topicDaoImpl.closeSession();
		return ServerResponse.response(200, "获取信息成功", groupAndTime);
	}
	
	
	/**
	 * 查看教师的指导学生
	 * @param teacherId
	 * @param gradeId
	 * @return
	 */
	public ServerResponse<List<GuideStudents>> viewGuideStudents(String teacherId, String gradeId) {
		List<GuideStudents> guideStudents = new ArrayList<GuideStudents>();
		List<Topics> topics = topicDaoImpl.viewGuideStudents(teacherId, gradeId);
		for(int i=0;i<topics.size();i++) {
			
			
			List<Student> stus = topics.get(i).getStudents();
			for(int j=0;j<stus.size();j++) {
				GuideStudents gs = new GuideStudents();
				gs.setTopicsName(topics.get(i).getTopicsName());
				
				gs.setId(stus.get(j).getId());
				
				gs.setNo(stus.get(j).getNo());
				gs.setName(stus.get(j).getName());
				gs.setSex(stus.get(j).getSex());
				gs.setQq(stus.get(j).getQq());
				gs.setTelephone(stus.get(j).getTelephone());
				gs.setEmail(stus.get(j).getEmail());
				gs.setRemark(stus.get(j).getRemark());
				
				gs.setClazz(stus.get(j).getClazz().getClassName());
				gs.setDirection(stus.get(j).getClazz().getDirection().getDirectionName());
				gs.setSpecialty(stus.get(j).getClazz().getDirection().getSpceialty().getSpecialtyName());
				gs.setGrade(stus.get(j).getClazz().getDirection().getSpceialty().getGrade().getGradeName());
				
				guideStudents.add(gs);
			}
			
		}
		topicDaoImpl.closeSession();
		return ServerResponse.response(200, "获取信息成功", guideStudents);
	}
	
	/**
	 * 手机端查看教师是否设置自动选择学生
	 * @param teacherId
	 * @param gradeId
	 * @return
	 */
	public ServerResponse<TeacherAutoSelect> viewAutoSelect(String teacherId, String gradeId) {
		TeacherAutoSelect teacherAutoSelect = topicDaoImpl.viewAutoSelect(teacherId, gradeId);
		TeacherAutoSelect teacherAutoSelect2 = new TeacherAutoSelect();
		if(teacherAutoSelect != null) {
			teacherAutoSelect2.setAutoSelect(teacherAutoSelect.getAutoSelect());
			teacherAutoSelect2.setId(teacherAutoSelect.getId());
		}
		return ServerResponse.response(200, "获取信息成功", teacherAutoSelect2);
	}
	/**
	 * 更新教师自动选择学生
	 * @param teacherId
	 * @param status 是否自动选题
	 * @param gradeId
	 * @return
	 */
	public int updateAutoSelect(String teacherId, String gradeId, int status) {
		TeacherAutoSelect teacherAutoSelect = topicDaoImpl.viewAutoSelect(teacherId, gradeId);
		boolean result = false;
//		先查询是否有该选项
		if(teacherAutoSelect == null) {
			Teacher t = new Teacher();
			t.setId(new Long(teacherId));
			Grade g = new Grade();
			g.setId(new Long(gradeId));
			TeacherAutoSelect teacherAutoSelect2 = new TeacherAutoSelect();
			teacherAutoSelect2.setTeacher(t);
			teacherAutoSelect2.setGrade(g);
			teacherAutoSelect2.setAutoSelect(status);
			result = topicDaoImpl.addAutoSelect(teacherAutoSelect2);
		} else { //存在该选项就直接更新
			result = topicDaoImpl.updateAutoSelect(teacherId, gradeId, status);
		}
		
		if(result) {
			return 200;
		}
		return -1;
	}
	
}
