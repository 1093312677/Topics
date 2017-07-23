package com.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dao.impl.AttachDaoImpl;
import com.dao.impl.CommonDaoImpl;
import com.entity.Form;
import com.entity.Score;
import com.entity.Setting;
import com.entity.StuTeachGroup;
import com.entity.Student;
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
	private SessionFactory sessionFactory;
	private Session session;
	
	/**
	 * 获取学生，得到文档信息
	 * @param id
	 * @return
	 */
	public String getStudent(String id){
//		保存文档名称
		String re = null;
		List<Student> students  = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			commonDaoImpl.setSession(session);
			students = commonDaoImpl.findBy("Student", "id", id);
			if(students.size() > 0) {
				if(students.get(0).getForm() != null) {
					re = students.get(0).getForm().getOpeningReport();
				}
			}
		} catch(Exception e) {
		}  finally{
			session.getTransaction().commit();
		}
		return re;
	}
	
	/**
	 * 增加开题报告
	 * @param file
	 * @param path
	 * @param id
	 * @return
	 */
	public boolean addOpenReport(MultipartFile file, String path, String id) {
		String fileName =null;
		File file2 = null;
		List<Student> students  = null;
		if(!file.isEmpty()){
			String origName = file.getOriginalFilename();
			int newNameIndex = origName.lastIndexOf('.');
			String suffix = origName.substring(newNameIndex);
			long name = System.currentTimeMillis();
//			文件随机名称
			fileName = String.valueOf(name)+suffix;
			file2 = new File(path,fileName);
			
			try{
				session = sessionFactory.getCurrentSession();
				session.beginTransaction();
				commonDaoImpl.setSession(session);
				students = commonDaoImpl.findBy("Student", "id", id);
				if(students.size() > 0) {
//					不存在开题报告保存
					if(students.get(0).getForm() == null) {
						Form form = new Form();
						form.setStudent(students.get(0));
						form.setOpeningReport(fileName);
						session.save(form);
					} else {
//						更新之前删除之前文档
						String name2 = students.get(0).getForm().getOpeningReport();
						File file3 = new File(path+"/"+name2);
						if(file3.exists()) {
							file3.delete();
						}
//					存在开题报告修改
						Form form = students.get(0).getForm();
						form.setOpeningReport(fileName);
						form.setStudent(students.get(0));
						session.update(form);
					}
				}
			} catch(Exception e) {
				return false;
			}  finally{
				session.getTransaction().commit();
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
	 * 获取学生，得到中期报告信息
	 * @param id
	 * @return
	 */
	public String getStudentMidReport(String id){
//		保存文档名称
		String re = null;
		List<Student> students  = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			commonDaoImpl.setSession(session);
			students = commonDaoImpl.findBy("Student", "id", id);
			if(students.size() > 0) {
				if(students.get(0).getForm() != null) {
					re = students.get(0).getForm().getInterimReport();
				}
			}
		} catch(Exception e) {
//			session.getTransaction().commit();
		}  finally{
			session.getTransaction().commit();
		}
		return re;
	}
	
	/**
	 * 增加中期报告
	 * @param file
	 * @param path
	 * @param id
	 * @return
	 */
	public boolean addMidReport(MultipartFile file, String path, String id) {
		String fileName =null;
		File file2 = null;
		List<Student> students  = null;
		if(!file.isEmpty()){
			String origName = file.getOriginalFilename();
			int newNameIndex = origName.lastIndexOf('.');
			String suffix = origName.substring(newNameIndex);
			long name = System.currentTimeMillis();
//			文件随机名称
			fileName = String.valueOf(name)+suffix;
			file2 = new File(path,fileName);
			
			try{
				session = sessionFactory.getCurrentSession();
				session.beginTransaction();
				commonDaoImpl.setSession(session);
				students = commonDaoImpl.findBy("Student", "id", id);
				if(students.size() > 0) {
//					不存在开题报告保存
					if(students.get(0).getForm() == null) {
						Form form = new Form();
						form.setStudent(students.get(0));
						form.setInterimReport(fileName);
						session.save(form);
					} else {
//						更新之前删除之前文档
						String name2 = students.get(0).getForm().getInterimReport();
						File file3 = new File(path+"/"+name2);
						if(file3.exists()) {
							file3.delete();
						}
//					存在开题报告修改
						Form form = students.get(0).getForm();
						form.setInterimReport(fileName);
						form.setStudent(students.get(0));
						session.update(form);
					}
				}
			} catch(Exception e) {
			}  finally{
				session.getTransaction().commit();
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
	 * 获取学生，得到毕业论文信息
	 * @param id
	 * @return
	 */
	public String viewSubmitThesis(String id){
//		保存文档名称
		String re = null;
		List<Student> students  = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			commonDaoImpl.setSession(session);
			students = commonDaoImpl.findBy("Student", "id", id);
			if(students.size() > 0) {
				if(students.get(0).getForm() != null) {
					re = students.get(0).getForm().getFileName();
				}
			}
			
			
		} catch(Exception e) {
			
		} finally{
			session.getTransaction().commit();
		}
		return re;
	}
	
	/**
	 * 上传毕业论文
	 * @param file
	 * @param path
	 * @param id
	 * @return
	 */
	public boolean addSubmitThesis(MultipartFile file, String path, String id) {
		String fileName =null;
		File file2 = null;
		List<Student> students  = null;
		if(!file.isEmpty()){
			String origName = file.getOriginalFilename();
			int newNameIndex = origName.lastIndexOf('.');
			String suffix = origName.substring(newNameIndex);
			long name = System.currentTimeMillis();
//			文件随机名称
			fileName = String.valueOf(name)+suffix;
			file2 = new File(path,fileName);
			
			try{
				session = sessionFactory.getCurrentSession();
				session.beginTransaction();
				commonDaoImpl.setSession(session);
				students = commonDaoImpl.findBy("Student", "id", id);
				if(students.size() > 0) {
					
//					不存在开题报告保存
					if(students.get(0).getForm() == null) {
						Form form = new Form();
						form.setStudent(students.get(0));
//						毕业论文
						form.setFileName(fileName);
//						获取当前时间
//						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//						String time = df.format(new Date());
//						form.setTime(time);
						
						session.save(form);
					} else {
//						更新之前删除之前文档
						String name2 = students.get(0).getForm().getFileName();
						File file3 = new File(path+"/"+name2);
						if(file3.exists()) {
							file3.delete();
						}
						
//					存在开题报告修改
						Form form = students.get(0).getForm();
						form.setStudent(students.get(0));
//						毕业论文
						form.setFileName(fileName);
//						获取当前时间
//						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//						String time = df.format(new Date());
//						form.setTime(time);
						session.update(form);
					}
				}
			} catch(Exception e) {
			}  finally{
				session.getTransaction().commit();
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
			session = sessionFactory.getCurrentSession();
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
		} catch(Exception e) {
		}  finally{
			session.getTransaction().commit();
		}
		return students;
	}
	/**
	 * 获取年级的设置
	 * @param gradeId
	 * @return
	 */
	public List<Setting> getSetting(String gradeId) {
		List<Setting> settings = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			commonDaoImpl.setSession(session);
//			查找出教师对应的题目
			settings = commonDaoImpl.findBy("Setting", "gradeId", gradeId);
		} catch(Exception e) {
		}  finally{
			session.getTransaction().commit();
		}
		return settings;
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
		if(!file.isEmpty()) {
			String origName = file.getOriginalFilename();
			int newNameIndex = origName.lastIndexOf('.');
			String suffix = origName.substring(newNameIndex);
			long name = System.currentTimeMillis();
//			文件随机名称
			fileName = String.valueOf(name)+suffix;
			file2 = new File(path,fileName);
			try{
				session = sessionFactory.getCurrentSession();
				session.beginTransaction();
				commonDaoImpl.setSession(session);
//				查找出教师对应的题目
				List<Form> forms = commonDaoImpl.findBy("Form", "studentId", String.valueOf(studentId));
				List<Score> score = commonDaoImpl.findBy("Score", "studentId", String.valueOf(studentId));
				if(score.size() >0 && forms.size() > 0) {
//					如果更新，删除之前文件
					if(forms.get(0).getInterimEvalForm() != null) {
						File file3 = new File(path,forms.get(0).getInterimEvalForm());
						if(file3.exists()) {
							file3.delete();
						}
					}
					forms.get(0).setInterimEvalForm(fileName);
					score.get(0).setMediumScore(mediumScore);
					commonDaoImpl.update(forms.get(0));
					commonDaoImpl.update(score.get(0));
					file.transferTo(file2);
				} else if (score.size() > 0) {
//					form为空，直接保存
					Form form2 = new Form();
					Student student = new Student();
					student.setId((int)(studentId));
					form2.setInterimEvalForm(fileName);
					form2.setStudent(student);
					commonDaoImpl.save(form2);
					
//					score不为空，查询进行更新
					score.get(0).setMediumScore(mediumScore);
					commonDaoImpl.update(score.get(0));
					
					file.transferTo(file2);
					
				} else if (forms.size() > 0) {
//					如果更新，删除之前文件
					if(forms.get(0).getInterimEvalForm() != null) {
						File file3 = new File(path,forms.get(0).getInterimEvalForm());
						if(file3.exists()) {
							file3.delete();
						}
					}
					
//					如果form不为空，查询出来进行更新，
					forms.get(0).setInterimEvalForm(fileName);
					commonDaoImpl.update(forms.get(0));
//					score为空，直接保存
					Score score2 = new Score();
					score2.setMediumScore(mediumScore);
					
					Student student = new Student();
					student.setId((int)(studentId));
					
					score2.setStudent(student);
					
					commonDaoImpl.save(score2);
					file.transferTo(file2);
					
				} else {
//					form为空，直接保存
					Form form2 = new Form();
					Student student = new Student();
					student.setId((int)(studentId));
					form2.setInterimEvalForm(fileName);
					form2.setStudent(student);
					commonDaoImpl.save(form2);
//					score为空，直接保存
					Score score2 = new Score();
					score2.setMediumScore(mediumScore);
					score2.setStudent(student);
					
					commonDaoImpl.save(score2);
					file.transferTo(file2);
				}
				
				
				return true;
			} catch(Exception e) {
				return false;
			}  finally{
				session.getTransaction().commit();
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
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			attachDaoImpl.setSession(session);
//			查找出教师对应的题目
			List<StuTeachGroup> stuTeachGroups = attachDaoImpl.findByTwo("StuTeachGroup", "teacherId", String.valueOf(teacherId), "gradeId", gradeId);
			for(int i=0;i<stuTeachGroups.size();i++) {
				students.add(stuTeachGroups.get(i).getStudent());
			}
		} catch(Exception e) {
		}  finally{
			session.getTransaction().commit();
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
	public boolean submitMidReview(String path, long studentId, float score, MultipartFile file) {
		String fileName =null;
		File file2 = null;
		if(!file.isEmpty()) {
			String origName = file.getOriginalFilename();
			int newNameIndex = origName.lastIndexOf('.');
			String suffix = origName.substring(newNameIndex);
			long name = System.currentTimeMillis();
//			文件随机名称
			fileName = String.valueOf(name)+(int)(Math.random()*10000)+suffix;
			file2 = new File(path,fileName);
			try{
				session = sessionFactory.getCurrentSession();
				session.beginTransaction();
				commonDaoImpl.setSession(session);
//				查找出教师对应的题目
				List<Form> forms = commonDaoImpl.findBy("Form", "studentId", String.valueOf(studentId));
				List<Score> scores = commonDaoImpl.findBy("Score", "studentId", String.valueOf(studentId));
				if(scores.size() >0 && forms.size() > 0) {
//					如果更新，删除之前文件
					if(forms.get(0).getReviewEvalForm() != null) {
						File file3 = new File(path,forms.get(0).getReviewEvalForm());
						if(file3.exists()) {
							file3.delete();
						}
					}
					forms.get(0).setReviewEvalForm(fileName);
					scores.get(0).setHeadScore(score);
					commonDaoImpl.update(forms.get(0));
					commonDaoImpl.update(scores.get(0));
					file.transferTo(file2);
				} else if (scores.size() > 0) {
//					form为空，直接保存
					Form form2 = new Form();
					Student student = new Student();
					student.setId((int)(studentId));
					form2.setReviewEvalForm(fileName);
					form2.setStudent(student);
					commonDaoImpl.save(form2);
					
//					score不为空，查询进行更新
					scores.get(0).setHeadScore(score);
					commonDaoImpl.update(scores.get(0));
					
					file.transferTo(file2);
					
				} else if (forms.size() > 0) {
//					如果更新，删除之前文件
					if(forms.get(0).getReviewEvalForm() != null) {
						File file3 = new File(path,forms.get(0).getReviewEvalForm());
						if(file3.exists()) {
							file3.delete();
						}
					}
					
//					如果form不为空，查询出来进行更新，
					forms.get(0).setReviewEvalForm(fileName);
					commonDaoImpl.update(forms.get(0));
//					score为空，直接保存
					Score score2 = new Score();
					score2.setHeadScore(score);
					
					Student student = new Student();
					student.setId((int)(studentId));
					
					score2.setStudent(student);
					
					commonDaoImpl.save(score2);
					file.transferTo(file2);
					
				} else {
//					form为空，直接保存
					Form form2 = new Form();
					Student student = new Student();
					student.setId((int)(studentId));
					form2.setReviewEvalForm(fileName);
					form2.setStudent(student);
					commonDaoImpl.save(form2);
//					score为空，直接保存
					Score score2 = new Score();
					score2.setHeadScore(score);
					score2.setStudent(student);
					
					commonDaoImpl.save(score2);
					file.transferTo(file2);
				}
				return true;
			} catch(Exception e) {
				return false;
			}  finally{
				session.getTransaction().commit();
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
			session = sessionFactory.getCurrentSession();
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
		} catch(Exception e) {
		}  finally{
			session.getTransaction().commit();
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
	public boolean submitReplyResults(String path, long studentId, float score, MultipartFile file, String level) {
		String fileName =null;
		File file2 = null;
		if(!file.isEmpty()) {
			String origName = file.getOriginalFilename();
			int newNameIndex = origName.lastIndexOf('.');
			String suffix = origName.substring(newNameIndex);
			long name = System.currentTimeMillis();
//			文件随机名称
			fileName = String.valueOf(name)+(int)(Math.random()*10000)+suffix;
			file2 = new File(path,fileName);
			try{
				session = sessionFactory.getCurrentSession();
				session.beginTransaction();
				commonDaoImpl.setSession(session);
//				查找出教师对应的题目
				List<Form> forms = commonDaoImpl.findBy("Form", "studentId", String.valueOf(studentId));
				List<Score> scores = commonDaoImpl.findBy("Score", "studentId", String.valueOf(studentId));
				if(scores.size() >0 && forms.size() > 0) {
//					如果更新，删除之前文件
					if(forms.get(0).getReplyRecord() != null) {
						File file3 = new File(path,forms.get(0).getReplyRecord());
						if(file3.exists()) {
							file3.delete();
						}
					}
					forms.get(0).setReplyRecord(fileName);
					scores.get(0).setReplyResult(score);
					scores.get(0).setLevel(level);
					commonDaoImpl.update(forms.get(0));
					commonDaoImpl.update(scores.get(0));
					file.transferTo(file2);
				} else if (scores.size() > 0) {
//					form为空，直接保存
					Form form2 = new Form();
					Student student = new Student();
					student.setId((int)(studentId));
					form2.setReplyRecord(fileName);
					form2.setStudent(student);
					commonDaoImpl.save(form2);
					
//					score不为空，查询进行更新
					scores.get(0).setReplyResult(score);
					scores.get(0).setLevel(level);
					commonDaoImpl.update(scores.get(0));
					
					file.transferTo(file2);
					
				} else if (forms.size() > 0) {
//					如果更新，删除之前文件
					if(forms.get(0).getReviewEvalForm() != null) {
						File file3 = new File(path,forms.get(0).getReviewEvalForm());
						if(file3.exists()) {
							file3.delete();
						}
					}
					
//					如果form不为空，查询出来进行更新，
					forms.get(0).setReplyRecord(fileName);
					commonDaoImpl.update(forms.get(0));
//					score为空，直接保存
					Score score2 = new Score();
					score2.setReplyResult(score);
					score2.setLevel(level);
					
					Student student = new Student();
					student.setId((int)(studentId));
					
					score2.setStudent(student);
					
					commonDaoImpl.save(score2);
					file.transferTo(file2);
					
				} else {
//					form为空，直接保存
					Form form2 = new Form();
					Student student = new Student();
					student.setId((int)(studentId));
					form2.setReplyRecord(fileName);
					form2.setStudent(student);
					commonDaoImpl.save(form2);
//					score为空，直接保存
					Score score2 = new Score();
					score2.setReplyResult(score);
					score2.setStudent(student);
					score2.setLevel(level);
					commonDaoImpl.save(score2);
					file.transferTo(file2);
				}
				return true;
			} catch(Exception e) {
				return false;
			}  finally{
				session.getTransaction().commit();
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
	public void downAttach(HttpServletResponse response, String path, String gradeId){
		List<Student> students = null;
		try{
			ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			commonDaoImpl.setSession(session);
//			查找出该年级学生
			students = commonDaoImpl.viewStudents(gradeId, 0, 10000);
			for(int i=0;i<students.size();i++) {
				String title = students.get(i).getNo()+"_"+students.get(i).getName();
				if(students.get(i).getForm() != null) {
					Form form = students.get(i).getForm();
//					开题报告
					if(form.getOpeningReport() != null) {
						File file1 = new File(path,form.getOpeningReport());
						if(file1.exists()) {
							FileInputStream fis = new FileInputStream(file1);
//							获取后缀名
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
						
					}
//					中期报告
					if(form.getInterimReport() != null) {
						File file1 = new File(path,form.getInterimReport());
						if(file1.exists()) {
							FileInputStream fis = new FileInputStream(file1);
//							获取后缀名
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
					}
//					毕业论文
					if(form.getFileName() != null) {
						File file1 = new File(path,form.getFileName());
						if(file1.exists()) {
							FileInputStream fis = new FileInputStream(file1);
//							获取后缀名
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
					}
//					指导教师评价表
					if(form.getInterimEvalForm() != null) {
						File file1 = new File(path,form.getInterimEvalForm());
						if(file1.exists()) {
							FileInputStream fis = new FileInputStream(file1);
//							获取后缀名
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
					}
//					小组评价表
					if(form.getReviewEvalForm() != null) {
						File file1 = new File(path,form.getReviewEvalForm());
						if(file1.exists()) {
							FileInputStream fis = new FileInputStream(file1);
//							获取后缀名
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
					}
//					答辩评阅表
					if(form.getReplyRecord() != null) {
						File file1 = new File(path,form.getReplyRecord());
						if(file1.exists()) {
							FileInputStream fis = new FileInputStream(file1);
//							获取后缀名
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
					}
					
					
				}
				
				
				
			}
			zos.closeEntry();
			zos.flush();
			zos.close();
		} catch(Exception e) {
		}  finally{
			session.getTransaction().commit();
		}
	}
	
}
