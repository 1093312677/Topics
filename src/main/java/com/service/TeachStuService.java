package com.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.SimilarAlgorithm;
import com.common.Similarity;
import com.dao.IDao;
import com.dao.IStudentDao;
import com.dao.ITeacherAutoSelectDao;
import com.dao.ITopicDao;
import com.dao.impl.CommonDaoImpl;
import com.entity.Form;
import com.entity.Grade;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.TeacherAutoSelect;
import com.entity.Topics;
/**
 * <p>教师和学生相关逻辑处理</p>
 * @author kone
 * 2017.4.18
 */
@Service
public class TeachStuService {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	
	@Autowired
	private IDao daoImpl;
	
	@Autowired
	private CommonDaoImpl commonDaoImpl;
	
	@Autowired
	private IStudentDao studentDao;
	
	@Autowired
	private ITopicDao topicDao;
	
	@Autowired
	private ITeacherAutoSelectDao teacherAutoSelectDao;
	
	public void closeSession(){
		if(session.isOpen()) {
			session.close();
		}
	}
	
	public void closeStudentSession() {
		studentDao.closeSession();
	}
	
	/**
	 * 查看指导学生
	 * @param teacherId
	 * @return
	 */
	public List<Student> viewGuideStudent(Long teacherId, Long gradeId) {
		List<Student> students =  new ArrayList<Student>();
		students = studentDao.getGuideStudent(teacherId, gradeId);
		return students;
	}
	
	/**
	 * 获取未选题学生人数
	 * @param gradeId
	 * @return
	 */
	public int getStudentsNum(Long gradeId) {
		return studentDao.getLastSelectStudentsCount(gradeId);
	}
	
	/**
	 * 查看学生
	 * @param gradeId
	 * @return
	 */
	public List<Student> viewStudents(Long gradeId, int num, int size) {
		List<Student> students =  new ArrayList<Student>();
		students = studentDao.getStudents(gradeId, num, size);
		
		
		return students;
	}
	
	/**
	 * 导出学生成绩
	 * @param gradeId
	 * @return
	 */
	public HSSFWorkbook exportStudentGrade(Long gradeId) {
		List<Student> students = studentDao.getStudents(gradeId, 0, 10000);
		
		//创建HSSFWorkbook对象(excel的文档对象)
	     HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("学生成绩表");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1=sheet.createRow(0);
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell=row1.createCell(0);
		      //设置单元格内容
		cell.setCellValue("学生成绩表");
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
		
		//在sheet里创建第二行
		HSSFRow row2=sheet.createRow(1);    
      //创建单元格并设置单元格内容
       row2.createCell(0).setCellValue("学号");
       row2.createCell(1).setCellValue("姓名");    
       row2.createCell(2).setCellValue("性别");
		row2.createCell(3).setCellValue("班级");  
		row2.createCell(4).setCellValue("方向"); 
		row2.createCell(5).setCellValue("题目名称");
		row2.createCell(6).setCellValue("指导教师评阅成绩"); 
		row2.createCell(7).setCellValue("小组评阅成绩"); 
		row2.createCell(8).setCellValue("答辩成绩"); 
		row2.createCell(9).setCellValue("等级"); 
		row2.createCell(10).setCellValue("总分"); 
		
		HSSFRow row = null;
		int count = 0;
		for (int j=0;j<students.size();j++) {
			row = sheet.createRow(count+2);
			
			row.createCell(0).setCellValue(students.get(j).getNo());
			row.createCell(1).setCellValue(students.get(j).getName());
			row.createCell(2).setCellValue(students.get(j).getSex());
			row.createCell(3).setCellValue(students.get(j).getClazz().getClassName());
			row.createCell(4).setCellValue(students.get(j).getClazz().getDirection().getDirectionName());
			if(students.get(j).getTopics() != null)
				row.createCell(5).setCellValue(students.get(j).getTopics().getTopicsName());
			
			if(students.get(j).getScore() != null) {
				float score1 = students.get(j).getScore().getMediumScore();
				float score2 = students.get(j).getScore().getHeadScore();
				float score3 = students.get(j).getScore().getReplyResult();
				row.createCell(6).setCellValue(score1);
				row.createCell(7).setCellValue(score2);
				row.createCell(8).setCellValue(score3);
				row.createCell(9).setCellValue(students.get(j).getScore().getLevel());
				if(students.get(j).getScore().getLevel() == null || "".equals(students.get(j).getScore().getLevel())) {
					row.createCell(10).setCellValue("评阅尚未完成");
				} else {
					row.createCell(10).setCellValue(score1 + score2 + score3);
				}
			}
				
			count++;
			
		}
		return wb;
	}
	/**
	 * 导出学生成绩
	 * @param gradeId
	 * @return
	 */
	public HSSFWorkbook exportNotSelectedStudent(Long gradeId) {
		List<Student> students = studentDao.getStudentsNotSelect(gradeId, 0, 10000);
		
		//创建HSSFWorkbook对象(excel的文档对象)
	     HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("未选题学生统计表");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1=sheet.createRow(0);
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell=row1.createCell(0);
		      //设置单元格内容
		cell.setCellValue("未选题学生统计表");
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
		
		//在sheet里创建第二行
		HSSFRow row2=sheet.createRow(1);    
      //创建单元格并设置单元格内容
       row2.createCell(0).setCellValue("学号");
       row2.createCell(1).setCellValue("姓名");    
       row2.createCell(2).setCellValue("性别");
		row2.createCell(3).setCellValue("班级");  
		row2.createCell(4).setCellValue("方向"); 
		row2.createCell(5).setCellValue("专业");
		row2.createCell(6).setCellValue("年级"); 
		
		HSSFRow row = null;
		int count = 0;
		for (int j=0;j<students.size();j++) {
			if(students.get(j).getTopics() == null) {
				row = sheet.createRow(count+2);
				
				row.createCell(0).setCellValue(students.get(j).getNo());
				row.createCell(1).setCellValue(students.get(j).getName());
				row.createCell(2).setCellValue(students.get(j).getSex());
				row.createCell(3).setCellValue(students.get(j).getClazz().getClassName());
				row.createCell(4).setCellValue(students.get(j).getClazz().getDirection().getDirectionName());
				row.createCell(5).setCellValue(students.get(j).getClazz().getDirection().getSpceialty().getSpecialtyName());
				row.createCell(6).setCellValue(students.get(j).getClazz().getDirection().getSpceialty().getGrade().getGradeName());
				
				count ++;
			}
			
		}
		studentDao.closeSession();
		return wb;
	}
	/**
	 * 系主任一键选题
	 * @return
	 */
	@Transactional
	public boolean automaticSelection(String gradeId) {
		List<Student> students = null;
		List<Topics> topics = null;
		try{
			session = sessionFactory.getCurrentSession();
//			传递session保证是同一个session进行事务处理
			commonDaoImpl.setSession(session); 
			students = commonDaoImpl.viewStudents(gradeId, 0, 100000);
			topics = topicDao.getTopics(session, gradeId);
			for(int i=0;i<students.size();i++) {
				if(students.get(i).getIntentionTopics() != null)
					System.out.println(students.get(i).getIntentionTopics().size());
			}
//			相似度判断函数
			Similarity s = new Similarity();
			
			for(int i=0;i<students.size();i++) {
				double max = 0;
				long topicIdMax = 0;

				int batch = 0;
				int choice = 0;
				
				double sim = -1;//相似度
				long topicId = 0;//题目id号
				
				double sim1 = -1;//相似度
				long topicId1 = 0;//题目id号
				
				double sim2 = -1;//相似度
				long topicId2 = 0;//题目id号
				
//					学生未选择意向题目,随机分配题目
				if(students.get(i).getIntentionTopics() == null || students.get(i).getIntentionTopics().size() == 0) {
//					System.out.println(students.get(i).getName());
					for(int cc=0;cc<topics.size();cc++) {
						int flag = 0;
						if(topics.get(cc).getEnableSelect() > topics.get(cc).getSelectedStudent()) {
//							判断题目是否符合学生的方向
							for(int dd = 0;dd < topics.get(cc).getDirections().size();dd++) {
								if(topics.get(cc).getDirections().get(dd).getId() == students.get(i).getClazz().getDirection().getId()) {
									topicIdMax = topics.get(cc).getId();
									flag = 1;
									break;
								}
							}
						}
						if(flag == 1) {
							break;
						}
					}
				} else {
					for(int j=0;j<students.get(i).getIntentionTopics().size();j++) {
						batch = students.get(i).getIntentionTopics().get(j).getBatch();
						choice = students.get(i).getIntentionTopics().get(j).getChoice();
//							第一轮许选题，第一志愿
						if( batch == 1 && choice == 1) {
							for(int k=0;k<topics.size();k++) {
//								判断题目是否可选
								if((topics.get(k).getEnableSelect() > topics.get(k).getSelectedStudent() )&& topics.get(k).getState() == 1) {
//									判断方向不为空
									if(topics.get(k).getDirections() != null) {
										for(int kk =0;kk<topics.get(k).getDirections().size();kk++) {
	//											学生的方向和题目的方向相同
											if(topics.get(k).getDirections().get(kk).getId() == students.get(i).getClazz().getDirection().getId()) {
												String intentName = students.get(i).getIntentionTopics().get(j).getTopic().getTopicsName();
												String topicsName = topics.get(k).getTopicsName();
												double result = 0;
												if(intentName != null && topicsName != null) {
													result = SimilarAlgorithm.getSimilarityRatio(intentName, topicsName);
												}
	//												相似度大的保存值及其题目的id号
												if(result >= sim) {
													sim = result;
													topicId = topics.get(k).getId();
												}
											}//学生的方向和题目的方向相同 end
										}
									}//判断方向不为空 end
									
								}//判断题目是否可选 end
							}
							
						} else if(batch == 1 && choice == 2) {
//								第一轮许选题，第2志愿
							for(int k=0;k<topics.size();k++) {
								if((topics.get(k).getEnableSelect() > topics.get(k).getSelectedStudent() )&& topics.get(k).getState() == 1) {
//									判断方向不为空
									if(topics.get(k).getDirections() != null) {
										for(int kk =0;kk<topics.get(k).getDirections().size();kk++) {
	//											学生的方向和题目的方向相同
											if(topics.get(k).getDirections().get(kk).getId() == students.get(i).getClazz().getDirection().getId()) {
												String intentName = students.get(i).getIntentionTopics().get(j).getTopic().getTopicsName();
												String topicsName = topics.get(k).getTopicsName();
												double result = 0;
												if(intentName != null && topicsName != null) {
//													result = s.SimilarDegree(intentName, topicsName);
													result = SimilarAlgorithm.getSimilarityRatio(intentName, topicsName);
												}
	//												相似度大的保存值及其题目的id号
												if(result >= sim1) {
													sim1 = result;
													topicId1 = topics.get(k).getId();
												}
											}
										}
									}//判断方向不为空 end
								}
							}
							
						} else if(batch == 2 && choice == 1) {
//								第2轮许选题，第1志愿
							for(int k=0;k<topics.size();k++) {
								if((topics.get(k).getEnableSelect() > topics.get(k).getSelectedStudent()) && topics.get(k).getState() == 1) {
//									判断方向不为空
									if(topics.get(k).getDirections() != null) {
										for(int kk =0;kk<topics.get(k).getDirections().size();kk++) {
	//											学生的方向和题目的方向相同
											if(topics.get(k).getDirections().get(kk).getId() == students.get(i).getClazz().getDirection().getId()) {
												String intentName = students.get(i).getIntentionTopics().get(j).getTopic().getTopicsName();
												String topicsName = topics.get(k).getTopicsName();
												double result = 0;
												if(intentName != null && topicsName != null) {
													result = SimilarAlgorithm.getSimilarityRatio(intentName, topicsName);
												}
	//												相似度大的保存值及其题目的id号
												if(result >= sim2) {
													sim2 = result;
													topicId2 = topics.get(k).getId();
												}
											}
										}
									} //判断方向不为空 end
								}
							}//第2轮许选题，第1志愿 end
							
						}
					}//遍历学生意向题目 end
//					选择学生意向题目相似度最大的进行调配
					max = sim;
					topicIdMax = topicId;
					if(sim1 > max) {
						max = sim1;
						topicIdMax = topicId1;
					}
					if(sim2 > max) {
						max = sim2;
						topicIdMax = topicId2;
					}
					
				}
//				成功找出合适的题目
				if(topicIdMax > 0) {
					String studentId = String.valueOf(students.get(i).getId());
//						更新学生的选题
						commonDaoImpl.updateTopic(studentId, String.valueOf(topicIdMax));
					if(i % 20 == 0) {
						session.flush();
						session.clear();
					}
//						题目的选择人数增加
						commonDaoImpl.updateTopicAddOne(String.valueOf(topicIdMax));
//						现在查询出来的题目人数增加
					for(int aa=topics.size() - 1;aa>=0;aa--) {
						if(topics.get(aa).getId() == topicIdMax) {
							topics.get(aa).setSelectedStudent(topics.get(aa).getSelectedStudent() + 1);
						}
					}
				}
					
			}
			
			return true;
		}catch(Exception e){
			throw new ServiceException("error");
		}
	}
	/**
	 * 教师查看自己是否是自动选题
	 * @param gradeId
	 * @return
	 */
	public boolean viewTeacherAutoSelect(Long gradeId, Long teacherId) {
		TeacherAutoSelect teacherAutoSelect =  teacherAutoSelectDao.getTeacherAutoSelect(gradeId, teacherId);
		if(teacherAutoSelect != null) {
			if(teacherAutoSelect.getAutoSelect() == 1) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 设置教师自动选题
	 * @param gradeId
	 * @param teacherId
	 * @return
	 */
	@Transactional
	public boolean setTeacherAutoSelect(Long gradeId, Long teacherId) {
		TeacherAutoSelect teacherAutoSelect =  teacherAutoSelectDao.getTeacherAutoSelect(gradeId, teacherId);
//		教师存在记录
		if(teacherAutoSelect != null) {
			if(teacherAutoSelect.getAutoSelect() == 0) {
				if(teacherAutoSelectDao.updateAutoSelect(1, teacherAutoSelect.getId()))
					return true;
			} else {
				if(teacherAutoSelectDao.updateAutoSelect(0, teacherAutoSelect.getId()))
					return true;
			}
		} else {
//			不存在记录，保存
			teacherAutoSelect = new TeacherAutoSelect();
			Grade grade = new Grade();
			Teacher teacher = new Teacher();
			grade.setId(gradeId);
			teacher.setId(teacherId);
			teacherAutoSelect.setGrade(grade);
			teacherAutoSelect.setTeacher(teacher);
			if(teacherAutoSelectDao.saveTeacherAutoSelect(teacherAutoSelect))
				return true;
		}
		
		return false;
		
	}
	/**
	 * 删除学生	
	 * @param studentId
	 * @return
	 */
	@Transactional
	public boolean deleteStudent(String studentId) {
		try{
			session = sessionFactory.getCurrentSession();
//			传递session保证是同一个session进行事务处理
			commonDaoImpl.setSession(session); 
			List<Student> students  = commonDaoImpl.findBy("Student", "id", studentId);
			List<Form> forms  = commonDaoImpl.findBy("Form", "studentId", studentId);
			if(students.size() > 0 ) {
				if(forms.size() > 0) {
					commonDaoImpl.delete(forms.get(0));
				}
				commonDaoImpl.delete(students.get(0));
				return true;
			} 
			return true;
		}catch(Exception e){
			throw new ServiceException("error");
		} 
	}
	
	
	/**
	 * 删除教师	
	 * @return
	 */
	@Transactional
	public boolean deleteTeacher(String teacherId) {
		try{
			session = sessionFactory.getCurrentSession();
//			传递session保证是同一个session进行事务处理
			commonDaoImpl.setSession(session); 
			List<Teacher> teachers  = commonDaoImpl.findBy("Teacher", "id", teacherId);
			for(int i=0;i<teachers.size();i++) {
				for(int j=0;j<teachers.get(i).getTopics().size();j++) {
					commonDaoImpl.updateSql(String.valueOf(teachers.get(i).getTopics().get(j).getId()));
					for(int k =0;k<teachers.get(i).getTopics().get(j).getSubTopic().size();k++) {
						commonDaoImpl.updateSubTopic(String.valueOf(teachers.get(i).getTopics().get(j).getId()));
					}
				}
				
			}
			if(teachers.size() > 0 ) {
				commonDaoImpl.delete(teachers.get(0));
				return true;
			} 
			return true;
		}catch(Exception e){
			throw new ServiceException("error");
		} 
		
	}
}
