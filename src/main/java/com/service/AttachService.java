package com.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dao.IFormDao;
import com.dao.IScoreDao;
import com.dao.ISettingDao;
import com.dao.IStudentDao;
import com.dao.ITopicDao;
import com.dao.impl.AttachDaoImpl;
import com.dao.impl.CommonDaoImpl;
import com.dto.AttachDTO;
import com.entity.Form;
import com.entity.Score;
import com.entity.Setting;
import com.entity.StuTeachGroup;
import com.entity.Student;
import com.entity.SubTopic;
import com.entity.TeacherGroup;
import com.entity.Topics;

/**
 * 附件提交逻辑处理（开题报告，中期报告）
 * @author kone
 * 2017-4-13
 */
@Service
public class AttachService {
	@Autowired
	private AttachDaoImpl attachDaoImpl;
	@Autowired
	private CommonDaoImpl commonDaoImpl;
	
	@Autowired
	private IFormDao formDao;
	
	@Autowired
	private IStudentDao studentDao;
	
	@Autowired
	private ITopicDao topicDao;
	
	@Autowired
	private ISettingDao settingDao;
	
	@Autowired
	private IScoreDao scoreDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	
	private Logger logger = Logger.getLogger(AttachService.class);
	
	public boolean isSelectTopic(Long studentId) {
		Student student = studentDao.studentIsSelectTopic(studentId);
		if(student != null)
			return true;
		return false;
	}
	
	/**
	 * 获取学生，得到文档信息
	 * @param id
	 * @return
	 */
	public Form getForm(Long studentId){
		Form form = formDao.getStudentForm(studentId);
		return form;
	}
	
	/**
	 * 增加开题报告
	 * @param file
	 * @param path
	 * @param id
	 * @return
	 */
	public boolean addOpenReport(MultipartFile file, String path, Long studentId) {
		String fileName =null;
		File file2 = null;
		if(!file.isEmpty()){
			String origName = file.getOriginalFilename();
			int newNameIndex = origName.lastIndexOf('.');
			String suffix = origName.substring(newNameIndex);
			long name = System.currentTimeMillis();
//			文件随机名称
			fileName = String.valueOf(name)+suffix;
			file2 = new File(path,fileName);
//			获取学生的提交信息
			Form form = formDao.getStudentForm(studentId);
			try{
				Student stu = new Student();
				stu.setId(studentId);
				session = sessionFactory.openSession();
				session.beginTransaction();
				commonDaoImpl.setSession(session);
//					不存在开题报告保存
				if(form == null) {
					Form form1= new Form();
					form1.setStudent(stu);
					form1.setOpeningReport(fileName);
					session.save(form1);
				} else {
	//						更新之前删除之前文档
					String name2 = form.getOpeningReport();
					File file3 = new File(path+"/"+name2);
					if(file3.exists()) {
						file3.delete();
					}
	//					存在开题报告修改
					form.setOpeningReport(fileName);
					form.setStudent(stu);
					session.update(form);
				}
				session.getTransaction().commit();
			} catch(Exception e) {
				return false;
			}  finally{
				if(session.isOpen()) {
					session.close();
				}
			}
			
			try {
				file.transferTo(file2);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		
		return true;
	}
	
	
	/**
	 * 增加中期报告
	 * @param file
	 * @param path
	 * @param id
	 * @return
	 */
	public boolean addMidReport(MultipartFile file, String path, Long studentId) {
		String fileName =null;
		File file2 = null;
		if(!file.isEmpty()){
			String origName = file.getOriginalFilename();
			int newNameIndex = origName.lastIndexOf('.');
			String suffix = origName.substring(newNameIndex);
			long name = System.currentTimeMillis();
//			文件随机名称
			fileName = String.valueOf(name)+suffix;
			file2 = new File(path,fileName);
			
//			获取学生的提交信息
			Form form = formDao.getStudentForm(studentId);
			try{
				Student stu = new Student();
				stu.setId(studentId);
				session = sessionFactory.openSession();
				session.beginTransaction();
				commonDaoImpl.setSession(session);
//					不存在开题报告保存
				if(form == null) {
					Form form1 = new Form();
					form1.setStudent(stu);
					form1.setInterimReport(fileName);
					session.save(form1);
				} else {
//						更新之前删除之前文档
					String name2 = form.getInterimReport();
					File file3 = new File(path+"/"+name2);
					if(file3.exists()) {
						file3.delete();
					}
//					存在开题报告修改
					form.setInterimReport(fileName);
					form.setStudent(stu);
					session.update(form);
				}
				session.getTransaction().commit();
			} catch(Exception e) {
			}  finally{
				if(session.isOpen()) {
					session.close();
				}
			}
			
			try {
				file.transferTo(file2);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		
		return true;
	}
	
	
	
	
	/**
	 * 上传毕业论文
	 * @param file
	 * @param path
	 * @param id
	 * @return
	 */
	public boolean addSubmitThesis(MultipartFile file, String path, Long studentId) {
		String fileName =null;
		File file2 = null;
		if(!file.isEmpty()){
			String origName = file.getOriginalFilename();
			int newNameIndex = origName.lastIndexOf('.');
			String suffix = origName.substring(newNameIndex);
			long name = System.currentTimeMillis();
//			文件随机名称
			fileName = String.valueOf(name)+suffix;
			file2 = new File(path,fileName);
			
//			获取学生的提交信息
			Form form = formDao.getStudentForm(studentId);
			Student stu = new Student();
			stu.setId(studentId);
			try{
				session = sessionFactory.openSession();
				session.beginTransaction();
				commonDaoImpl.setSession(session);
//					不存在开题报告保存
				if(form == null) {
					Form form1 = new Form();
					form1.setStudent(stu);
//						毕业论文
					form1.setFileName(fileName);
					session.save(form1);
				} else {
//						更新之前删除之前文档
					String name2 = form.getFileName();
					File file3 = new File(path+"/"+name2);
					if(file3.exists()) {
						file3.delete();
					}
					
//					存在开题报告修改
					form.setStudent(stu);
//						毕业论文
					form.setFileName(fileName);
					session.update(form);
				}
				session.getTransaction().commit();
			} catch(Exception e) {
			}  finally{
				if(session.isOpen()) {
					session.close();
				}
			}
			
			try {
				file.transferTo(file2);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		
		return true;
	}
	/**
	 * 指导教师上传学生平时成绩，先查看
	 * @param teacherId
	 * @return
	 */
	public List<Student> instructorReview(String gradeId, long teacherId) {
		List<Student> students  = new ArrayList<Student>();
		List<Topics> topics = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			commonDaoImpl.setSession(session);
//			查找出教师对应的题目
			topics = commonDaoImpl.findBy("Topics", "teacherId", String.valueOf(teacherId));
//			获得选择了题目的学生
			for(int i=0;i<topics.size();i++) {
				if(topics.get(i).getGrade().getId() == Long.valueOf(gradeId)) {
					students.addAll(topics.get(i).getStudents());
				}
			}
			session.getTransaction().commit();
		} catch(Exception e) {
		}  finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		return students;
	}
	/**
	 * 获取年级的设置
	 * @param gradeId
	 * @return
	 */
	public Setting getSetting(Long gradeId) {
		Setting setting = null;
		setting = settingDao.getSetting(gradeId);
		return setting;
	}
	/**
	 * 指导教师提交评阅表
	 * @param path
	 * @param studentId
	 * @param mediumScore
	 * @param file
	 * @return
	 */
	public boolean submitInstructorReview(String path, long studentId, float mediumScore, MultipartFile file) {
		String fileName =null;
		File file2 = null;
		Student student = new Student();
		student.setId(studentId);
		if(!file.isEmpty()) {
			String origName = file.getOriginalFilename();
			int newNameIndex = origName.lastIndexOf('.');
			String suffix = origName.substring(newNameIndex);
			long name = System.currentTimeMillis();
//			文件随机名称
			fileName = String.valueOf(name)+(int)(Math.random()*10000)+suffix;
			file2 = new File(path,fileName);
			try{
//				查找出教师对应的题目
				Form form = formDao.getStudentForm(studentId);
				Score score = scoreDao.getScoreParam(studentId);
				if(score != null && form != null) {
//					如果更新，删除之前文件
					if(form.getInterimEvalForm() != null) {
						File file3 = new File(path,form.getInterimEvalForm());
						if(file3.exists()) {
							file3.delete();
						}
					}
					form.setInterimEvalForm(fileName);
					score.setMediumScore(mediumScore);
					file.transferTo(file2);
					form.setStudent(student);
					score.setStudent(student);
					formDao.updateForm(form);
					scoreDao.updateScore(score);
				} else if (score != null && form == null) {
//					form为空，直接保存
					Form form2 = new Form();
					form2.setInterimEvalForm(fileName);
					form2.setStudent(student);
					formDao.saveForm(form2);
					
//					score不为空，查询进行更新
					score.setMediumScore(mediumScore);
					scoreDao.updateScore(score);
					
					file.transferTo(file2);
					
				} else if (score == null && form != null) {
//					如果更新，删除之前文件
					if(form.getInterimEvalForm() != null) {
						File file3 = new File(path,form.getInterimEvalForm());
						if(file3.exists()) {
							file3.delete();
						}
					}
					
//					如果form不为空，查询出来进行更新，
					form.setInterimEvalForm(fileName);
					formDao.updateForm(form);
//					score为空，直接保存
					Score score2 = new Score();
					score2.setMediumScore(mediumScore);
					
					score2.setStudent(student);
					
					scoreDao.saveScore(score2);
					file.transferTo(file2);
					
				} else {
//					form为空，直接保存
					Form form2 = new Form();
					form2.setInterimEvalForm(fileName);
					form2.setStudent(student);
					formDao.saveForm(form2);
//					score为空，直接保存
					Score score2 = new Score();
					score2.setMediumScore(mediumScore);
					score2.setStudent(student);
					
					scoreDao.saveScore(score2);
					file.transferTo(file2);
				}
				
				return true;
			} catch(Exception e) {
				return false;
			}  
		}
		return false;
	}
	
	/**
	 * 中期组员查看评阅的学生
	 * @param gradeId
	 * @param teacherId
	 * @return
	 */
	public List<Student> midReview(String gradeId, long teacherId) {
		List<Student> students  = new ArrayList<Student>();
		List<Topics> topics = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			attachDaoImpl.setSession(session);
//			查找出教师对应的题目
			List<StuTeachGroup> stuTeachGroups = attachDaoImpl.findByTwo("StuTeachGroup", "teacherId", String.valueOf(teacherId), "gradeId", gradeId);
			for(int i=0;i<stuTeachGroups.size();i++) {
				students.add(stuTeachGroups.get(i).getStudent());
			}
			session.getTransaction().commit();
		} catch(Exception e) {
		}  finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		return students;
	}
	
	/**
	 * 评阅组成员进行评分
	 * @param path
	 * @param studentId
	 * @param score
	 * @param file
	 * @return
	 */
	public boolean submitMidReview(String path, long studentId, float score1, MultipartFile file) {
		String fileName =null;
		File file2 = null;
		Student student = new Student();
		student.setId(studentId);
		if(!file.isEmpty()) {
			String origName = file.getOriginalFilename();
			int newNameIndex = origName.lastIndexOf('.');
			String suffix = origName.substring(newNameIndex);
			long name = System.currentTimeMillis();
//			文件随机名称
			fileName = String.valueOf(name)+(int)(Math.random()*10000)+suffix;
			file2 = new File(path,fileName);
			try{
//				查找出教师对应的题目
				Form form = formDao.getStudentForm(studentId);
				Score score = scoreDao.getScoreParam(studentId);
				if(score != null && form != null) {
//					如果更新，删除之前文件
					if(form.getReviewEvalForm() != null) {
						File file3 = new File(path,form.getReviewEvalForm());
						if(file3.exists()) {
							file3.delete();
						}
					}
					form.setReviewEvalForm(fileName);
					score.setHeadScore(score1);
					file.transferTo(file2);
					form.setStudent(student);
					score.setStudent(student);
					formDao.updateForm(form);
					scoreDao.updateScore(score);
				} else if (score != null && form == null) {
//					form为空，直接保存
					Form form2 = new Form();
					form2.setReviewEvalForm(fileName);
					form2.setStudent(student);
					formDao.saveForm(form2);
					
//					score不为空，查询进行更新
					score.setHeadScore(score1);
					scoreDao.updateScore(score);
					
					file.transferTo(file2);
					
				} else if (score == null && form != null) {
//					如果更新，删除之前文件
					if(form.getReviewEvalForm() != null) {
						File file3 = new File(path,form.getReviewEvalForm());
						if(file3.exists()) {
							file3.delete();
						}
					}
					
//					如果form不为空，查询出来进行更新，
					form.setReviewEvalForm(fileName);
					formDao.updateForm(form);
//					score为空，直接保存
					Score score2 = new Score();
					score2.setHeadScore(score1);
					
					score2.setStudent(student);
					
					scoreDao.saveScore(score2);
					file.transferTo(file2);
					
				} else {
//					form为空，直接保存
					Form form2 = new Form();
					form2.setReviewEvalForm(fileName);
					form2.setStudent(student);
					formDao.saveForm(form2);
//					score为空，直接保存
					Score score2 = new Score();
					score2.setHeadScore(score1);
					score2.setStudent(student);
					
					scoreDao.saveScore(score2);
					file.transferTo(file2);
				}
				
				return true;
			} catch(Exception e) {
				return false;
			}  
		}
		return false;
	}
	
	/**
	 * <p>最后答辩组长提交学生答辩成绩</p>
	 * @param gradeId
	 * @param teacherId
	 * @return
	 */
	public List<Student> replyResults(String gradeId, long teacherId) {
		List<Student> students  = new ArrayList<Student>();
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			attachDaoImpl.setSession(session);
//			查找出教师对应的题目
			List<TeacherGroup> teacherGroups = attachDaoImpl.findBy("TeacherGroup", "teacherId", String.valueOf(teacherId));
			for(int i=0;i<teacherGroups.size();i++) {
//				查找教师所在的分组
				if(teacherGroups.get(i).getGroup().getGrade().getId() == Long.valueOf(gradeId)) {
//					查找教师是否是组长，如果是组长，查找组内成员，否则不查询
					if(teacherGroups.get(i).getIsLeader() == 1) {
//						通过该教师的教师分组，查找到该组内所有的教师
						List<TeacherGroup> teacherGroups2 = teacherGroups.get(i).getGroup().getTeacherGroup();
						for(int j=0;j<teacherGroups2.size();j++) {
//							获取所有教师需要评阅的学生
							for(int k = 0;k<teacherGroups2.get(j).getTeacher().getStuTeachGroups().size();k++) {
								students.add(teacherGroups2.get(j).getTeacher().getStuTeachGroups().get(k).getStudent());
							}
							
						}
					} else {
						students = null;
					}
					
					break;
				}
			}
			session.getTransaction().commit();
		} catch(Exception e) {
		}  finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		return students;
	}
	/**
	 * <p>小组长提交答辩记录表</p>
	 * @param path
	 * @param studentId
	 * @param score
	 * @param file
	 * @param level
	 * @return
	 */
	public boolean submitReplyResults(String path, long studentId, float score1, MultipartFile file, String level) {
		String fileName =null;
		File file2 = null;
		Student student = new Student();
		student.setId(studentId);
		if(!file.isEmpty()) {
			String origName = file.getOriginalFilename();
			int newNameIndex = origName.lastIndexOf('.');
			String suffix = origName.substring(newNameIndex);
			long name = System.currentTimeMillis();
//			文件随机名称
			fileName = String.valueOf(name)+(int)(Math.random()*10000)+suffix;
			file2 = new File(path,fileName);
			try{
//				查找出教师对应的题目
				Form form = formDao.getStudentForm(studentId);
				Score score = scoreDao.getScoreParam(studentId);
				if(score != null && form != null) {
//					如果更新，删除之前文件
					if(form.getReplyRecord() != null) {
						File file3 = new File(path,form.getReplyRecord());
						if(file3.exists()) {
							file3.delete();
						}
					}
					form.setReplyRecord(fileName);
					score.setReplyResult(score1);
					file.transferTo(file2);
					form.setStudent(student);
					
					score.setLevel(level);
					score.setStudent(student);
					formDao.updateForm(form);
					scoreDao.updateScore(score);
				} else if (score != null && form == null) {
//					form为空，直接保存
					Form form2 = new Form();
					form2.setReplyRecord(fileName);
					form2.setStudent(student);
					formDao.saveForm(form2);
					
//					score不为空，查询进行更新
					score.setReplyResult(score1);
					score.setLevel(level);
					scoreDao.updateScore(score);
					
					file.transferTo(file2);
					
				} else if (score == null && form != null) {
//					如果更新，删除之前文件
					if(form.getReplyRecord() != null) {
						File file3 = new File(path,form.getReplyRecord());
						if(file3.exists()) {
							file3.delete();
						}
					}
					
//					如果form不为空，查询出来进行更新，
					form.setReplyRecord(fileName);
					formDao.updateForm(form);
//					score为空，直接保存
					Score score2 = new Score();
					score2.setReplyResult(score1);
					score2.setLevel(level);
					score2.setStudent(student);
					
					scoreDao.saveScore(score2);
					file.transferTo(file2);
					
				} else {
//					form为空，直接保存
					Form form2 = new Form();
					form2.setReplyRecord(fileName);
					form2.setStudent(student);
					formDao.saveForm(form2);
//					score为空，直接保存
					Score score2 = new Score();
					score2.setReplyResult(score1);
					score2.setLevel(level);
					score2.setStudent(student);
					
					scoreDao.saveScore(score2);
					file.transferTo(file2);
				
				}
				
				return true;
			} catch(Exception e) {
				return false;
			} 
		}
		return false;
	}
	/**
	 * 打包下载所有文档
	 * @param response
	 * @param path
	 * @param gradeId
	 */
	public void downAttach(HttpServletResponse response, String path, Long gradeId){
		List<Student> students = null;
		List<AttachDTO> attachs = new ArrayList<AttachDTO>();
		AttachDTO attach = null;
		AttachDTO attach2 = null;
		
		ZipOutputStream zos = null;
		File file1 =  null;
		try{
			zos = new ZipOutputStream(response.getOutputStream());
			students = studentDao.getAllStudentBasicInfor(gradeId);
			Form form = null;
			Topics topic = null;
			SubTopic subTopic = null;
			for(int i=0;i<students.size();i++) {
				form = formDao.getStudentForm(students.get(i).getId());
				topic = topicDao.getStudentTopic(students.get(i).getId());
				
				subTopic = topicDao.getStudentSubTopic(students.get(i).getId());
				if(subTopic != null ){
					students.get(i).setSubTopic(subTopic); 
				}
				if(form != null) {
					students.get(i).setForm(form);
				}
				
				if(topic != null ){
					students.get(i).setTopics(topic);
				}
				
				
			}
			
			for(int i=0;i<students.size();i++) {
				System.out.println(students.get(i).getName());
				attach = new AttachDTO();
				attach.setName(students.get(i).getName());
				attach.setNo(students.get(i).getNo());
				if(students.get(i).getTopics() != null) {
					attach.setTopicsUrl(students.get(i).getTopics().getTaskBookName());
				}
				if(students.get(i).getSubTopic() != null){
					attach.setSubTopicUrl(students.get(i).getSubTopic().getTaskBookName());
				}
				
				if(students.get(i).getForm() != null) {
					attach.setOpeningReport(students.get(i).getForm().getOpeningReport());
					attach.setInterimReport(students.get(i).getForm().getInterimReport());
					attach.setFileName(students.get(i).getForm().getFileName());
					
					attach.setInterimEvalForm(students.get(i).getForm().getInterimEvalForm());
					attach.setReviewEvalForm(students.get(i).getForm().getReviewEvalForm());
					attach.setReplyRecord(students.get(i).getForm().getReplyRecord());
					
				}
				
				attach2 = new AttachDTO();
				attach2.setNo(attach.getNo());
				attach2.setName(attach.getName());
				String title = attach.getNo()+"_"+attach.getName();
//				开题报告
				if(attach.getOpeningReport() != null && attach.getOpeningReport() != "") {
					file1 = new File(path,attach.getOpeningReport());
					if(file1.exists()) {
						FileInputStream fis = new FileInputStream(file1);
//						获取后缀名
						int newNameIndex = file1.getName().lastIndexOf('.');
						String suffix = file1.getName().substring(newNameIndex);
						zos.putNextEntry(new ZipEntry("documents/"+title+"/"+title+"_开题报告"+suffix));
						int len;
						byte[] buffer = new byte[1024];
						//读入需要下载的文件的内容，打包到zip文件
					    while((len = fis.read(buffer))!= -1) {
						  zos.write(buffer,0,len);
					    }
					    fis.close();
					}
					attach2.setOpeningReport("1");
				} else {
					attach2.setOpeningReport("0");
				}
//				中期报告
				if(attach.getInterimReport() != null && attach.getInterimReport() != "") {
					file1 = new File(path,attach.getInterimReport());
					if(file1.exists()) {
						FileInputStream fis = new FileInputStream(file1);
//						获取后缀名
						int newNameIndex = file1.getName().lastIndexOf('.');
						String suffix = file1.getName().substring(newNameIndex);
						zos.putNextEntry(new ZipEntry("documents/"+title+"/"+title+"_中期报告"+suffix));
						int len;
						byte[] buffer = new byte[1024];
						//读入需要下载的文件的内容，打包到zip文件
					    while((len = fis.read(buffer))!= -1) {
						  zos.write(buffer,0,len);
					    }
					    fis.close();
					}
					attach2.setInterimReport("1");
				} else {
					attach2.setInterimReport("0");
				}
//		    	毕业论文
				if(attach.getFileName() != null && attach.getFileName() != "" ) {
					file1 = new File(path,attach.getFileName());
					if(file1.exists()) {
						FileInputStream fis = new FileInputStream(file1);
	//					获取后缀名
						int newNameIndex = file1.getName().lastIndexOf('.');
						String suffix = file1.getName().substring(newNameIndex);
						zos.putNextEntry(new ZipEntry("documents/"+title+"/"+title+"_毕业论文"+suffix));
						int len;
						byte[] buffer = new byte[1024];
						//读入需要下载的文件的内容，打包到zip文件
					    while((len = fis.read(buffer))!= -1) {
						  zos.write(buffer,0,len);
					    }
					    fis.close();
					}
					attach2.setFileName("1");
				} else {
					attach2.setFileName("0");
				}
	//			指导教师评价表
				if(attach.getInterimEvalForm() != null && attach.getInterimEvalForm() != "") {
					file1 = new File(path,attach.getInterimEvalForm());
					if(file1.exists()) {
						FileInputStream fis = new FileInputStream(file1);
	//					获取后缀名
						int newNameIndex = file1.getName().lastIndexOf('.');
						String suffix = file1.getName().substring(newNameIndex);
						zos.putNextEntry(new ZipEntry("documents/"+title+"/"+title+"_指导教师评阅表"+suffix));
						int len;
						byte[] buffer = new byte[1024];
						//读入需要下载的文件的内容，打包到zip文件
					    while((len = fis.read(buffer))!= -1) {
						  zos.write(buffer,0,len);
					    }
					    fis.close();
					}
					
					attach2.setInterimEvalForm("1");
				} else {
					attach2.setInterimEvalForm("0");
				}
	//			小组评价表
				if(attach.getReviewEvalForm() != null && attach.getReviewEvalForm() != "") {
					file1 = new File(path,attach.getReviewEvalForm());
					if(file1.exists()) {
						FileInputStream fis = new FileInputStream(file1);
	//					获取后缀名
						int newNameIndex = file1.getName().lastIndexOf('.');
						String suffix = file1.getName().substring(newNameIndex);
						zos.putNextEntry(new ZipEntry("documents/"+title+"/"+title+"_评阅人评阅表"+suffix));
						int len;
						byte[] buffer = new byte[1024];
						//读入需要下载的文件的内容，打包到zip文件
					    while((len = fis.read(buffer))!= -1) {
						  zos.write(buffer,0,len);
					    }
					    fis.close();
					}
					attach2.setReviewEvalForm("1");
				} else {
					attach2.setReviewEvalForm("0");
				}
	//			答辩评阅表
				if(attach.getReplyRecord() != null && attach.getReplyRecord() != "") {
					file1 = new File(path,attach.getReplyRecord());
					if(file1.exists()) {
						FileInputStream fis = new FileInputStream(file1);
	//					获取后缀名
						int newNameIndex = file1.getName().lastIndexOf('.');
						String suffix = file1.getName().substring(newNameIndex);
						zos.putNextEntry(new ZipEntry("documents/"+title+"/"+title+"_答辩评阅表"+suffix));
						int len;
						byte[] buffer = new byte[1024];
						//读入需要下载的文件的内容，打包到zip文件
					    while((len = fis.read(buffer))!= -1) {
						  zos.write(buffer,0,len);
					    }
					    fis.close();
					}
					
					attach2.setReplyRecord("1");
				} else {
					attach2.setReplyRecord("0");
				}
				
	//		主题目
				if(attach.getTopicsUrl() != null && attach.getTopicsUrl() != "") {
					
					file1 = new File(path,attach.getTopicsUrl());
					if(file1.exists()) {
						FileInputStream fis = new FileInputStream(file1);
		//				获取后缀名
						int newNameIndex = file1.getName().lastIndexOf('.');
						String suffix = file1.getName().substring(newNameIndex);
						zos.putNextEntry(new ZipEntry("documents/"+title+"/"+title+"_主题目"+suffix));
						int len;
						byte[] buffer = new byte[1024];
						//读入需要下载的文件的内容，打包到zip文件
					    while((len = fis.read(buffer))!= -1) {
						  zos.write(buffer,0,len);
					    }
					    fis.close();
					}
				}
	//		子题目
				if(attach.getSubTopicUrl() != null && attach.getSubTopicUrl() != "") {
					file1 = new File(path,attach.getSubTopicUrl());
					if(file1.exists()) {
						FileInputStream fis = new FileInputStream(file1);
		//				获取后缀名
						int newNameIndex = file1.getName().lastIndexOf('.');
						String suffix = file1.getName().substring(newNameIndex);
						zos.putNextEntry(new ZipEntry("documents/"+title+"/"+title+"_子题目"+suffix));
						int len;
						byte[] buffer = new byte[1024];
						//读入需要下载的文件的内容，打包到zip文件
					    while((len = fis.read(buffer))!= -1) {
						  zos.write(buffer,0,len);
					    }
					    fis.close();
					}
				} else {
//					未提交名单
				}
				
				
//				提交情况
				attachs.add(attach2);
					
			}
			
			notAttach(attachs, path);
			File file2 = new File(path,"table.xlsx");
			if(file2.exists()) {
				FileInputStream fis = new FileInputStream(file2);
//				获取后缀名
				zos.putNextEntry(new ZipEntry("documents/"+"表格提交情况统计.xlsx"));
				int len;
				byte[] buffer = new byte[1024];
				//读入需要下载的文件的内容，打包到zip文件
			    while((len = fis.read(buffer))!= -1) {
				  zos.write(buffer,0,len);
			    }
			    fis.close();
			    file2.delete();
			}
			
	
			zos.closeEntry();
			zos.flush();
			zos.close();
		} catch(Exception e) {
			
		} finally {
			
		}
	}
	
	public HSSFWorkbook notAttach(List<AttachDTO> attachs, String path) {
		//创建HSSFWorkbook对象(excel的文档对象)
	     HSSFWorkbook wb = new HSSFWorkbook();
	     
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("表格提交情况统计");
		// 四个参数分别是：起始行，起始列，结束行，结束列   
		HSSFRow row1=sheet.createRow(0);
		sheet.addMergedRegion(new Region(0,  (short) 0, 0, (short) 8));
		 row1.createCell(0).setCellValue("表格提交情况统计");
		//在sheet里创建第1行
		HSSFRow row2=sheet.createRow(1);
       //创建单元格并设置单元格内容
	    row2.createCell(0).setCellValue("学号");
	    row2.createCell(1).setCellValue("姓名");    
	    row2.createCell(2).setCellValue("开题报告");
	    row2.createCell(3).setCellValue("中期报告");
		row2.createCell(4).setCellValue("毕业论文");  
		row2.createCell(5).setCellValue("指导老师评阅表"); 
		row2.createCell(6).setCellValue("小组评阅表"); 
		row2.createCell(7).setCellValue("答辩评阅表"); 
		
		HSSFRow row = null;
		for(int i=0;i<attachs.size();i++) {
			row = sheet.createRow(i+2);
			row.createCell(0).setCellValue(attachs.get(i).getNo());
			row.createCell(1).setCellValue(attachs.get(i).getName());
			if(attachs.get(i).getOpeningReport().equals("1")) {
				row.createCell(2).setCellValue("1");
			} else {
				row.createCell(2).setCellValue("未提交");
			}
			
			if(attachs.get(i).getInterimReport().equals("1")) {
				row.createCell(3).setCellValue("1");
			} else {
				row.createCell(3).setCellValue("未提交");
			}
			
			if(attachs.get(i).getFileName().equals("1")) {
				row.createCell(4).setCellValue("1");
			} else {
				row.createCell(4).setCellValue("未提交");
			}
			
			if(attachs.get(i).getInterimEvalForm() .equals("1")) {
				row.createCell(5).setCellValue("1");
			} else {
				row.createCell(5).setCellValue("未提交");
			}
			if(attachs.get(i).getReviewEvalForm().equals("1")) {
				row.createCell(6).setCellValue("1");
			} else {
				row.createCell(6).setCellValue("未提交");
			}
			
			if(attachs.get(i).getReplyRecord().equals("1")) {
				row.createCell(7).setCellValue("1");
			} else {
				row.createCell(7).setCellValue("未提交");
			}
		}
		
		
		try {
			File file = new File(path,"table.xlsx");
			FileOutputStream fos = new FileOutputStream(file);
			wb.write(fos);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return wb;
	}
	
}
