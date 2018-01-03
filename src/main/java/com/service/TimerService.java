package com.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 定时触发的一些事件逻辑处理
 * @author kone
 *
 */
import org.springframework.transaction.annotation.Transactional;

import com.common.StudentAndScore;
import com.dao.impl.TimerDaoImpl;
import com.dto.DealData;
import com.entity.CheckViewGrade;
import com.entity.CourseAndGrade;
import com.entity.Grade;
import com.entity.IntentionTopic;
import com.entity.Student;
import com.entity.Teacher;
@Service
public class TimerService {
	@Autowired
	private TimerDaoImpl timerDao;
	
	private DealData dealData = new DealData();
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private static Logger logger = Logger.getLogger(TimerService.class);
	@Transactional
	public void test() {
		try{
			session = sessionFactory.getCurrentSession();
			timerDao.setSession(session);
			List<Grade> grades = timerDao.getGrade();
//			for(int i=0;i<grades.size();i++) {
//				System.out.println(grades.get(i).getGradeName());
//			}
			int select;
			for(int i=0;i<grades.size();i++) {
				select = grades.get(i).getIsSelect11();
//				第一轮，第一自愿
				String startTime1 = grades.get(i).getSetting().getOneAdmissionStartTime();
				String endTime1 = grades.get(i).getSetting().getOneFirstChoiceDeadline();
				if(dealData.isNow(startTime1, endTime1)) {
					autoSelect(grades, i, select, "isSelect11", 1, 1);
				}
				select = grades.get(i).getIsSelect12();
//				第一轮，第2自愿
				String startTime2 = grades.get(i).getSetting().getOneFirstChoiceDeadline();
				String endTime2 = grades.get(i).getSetting().getOneSecondChoiceDeadline();
				if(dealData.isNow(startTime2, endTime2)) {
					autoSelect(grades, i, select, "isSelect12", 1, 2);
				}
//				第一轮，第3自愿
				select = grades.get(i).getIsSelect13();
				String startTime3 = grades.get(i).getSetting().getOneSecondChoiceDeadline();
				String endTime3 = grades.get(i).getSetting().getOneThirdChoiceDeadline();
				if(dealData.isNow(startTime3, endTime3)) {
					autoSelect(grades, i, select, "isSelect13", 1, 3);
				}
				
				
//				第二轮，第一自愿
				select = grades.get(i).getIsSelect21();
				String startTime21 = grades.get(i).getSetting().getTwoAdmissionStartTime();
				String endTime21 = grades.get(i).getSetting().getTwoFirstChoiceDeadline();
				if(dealData.isNow(startTime21, endTime21)) {
					autoSelect(grades, i, select, "isSelect21", 2, 1);
				}
//				第二轮，第2自愿
				select = grades.get(i).getIsSelect22();
				String startTime22 = grades.get(i).getSetting().getTwoFirstChoiceDeadline();
				String endTime22 = grades.get(i).getSetting().getTwoSecondChoiceDeadline();
				if(dealData.isNow(startTime22, endTime22)) {
					autoSelect(grades, i, select, "isSelect22", 2, 2);
				}
//				第二轮，第3自愿
				select = grades.get(i).getIsSelect23();
				String startTime23 = grades.get(i).getSetting().getTwoSecondChoiceDeadline();
				String endTime23 = grades.get(i).getSetting().getTwoThirdChoiceDeadline();
				if(dealData.isNow(startTime23, endTime23)) {
					autoSelect(grades, i, select, "isSelect23", 2, 3);
				}
					
			}
		}catch(Exception e) {
//			throw new ServiceException("error");
		}
		
	}
	
	private void autoSelect(List<Grade> grades, int i, int select, String isSelect, int batch, int choice) {
//		三次去判断
//		if(select <= 3) {
		if(1 == 1){
			List<Teacher> teachers = grades.get(i).getDepartment().getTeachers();
			for(int j=0;j<teachers.size();j++) {
//				教师需要查看的课程成绩
				List<CheckViewGrade> checkViewGrades = teachers.get(j).getCheckViewGrade();
//				判断教师是否是设置自动选题
				for(int jj=0;jj<teachers.get(j).getTeacherAutoSelects().size();jj++) {
					if(teachers.get(j).getTeacherAutoSelects().get(jj).getAutoSelect() == 1 &&teachers.get(j).getTeacherAutoSelects().get(jj).getGrade().getId() == grades.get(i).getId()) {
						//当前年级的老师没有设置自动选题
						logger.info("------------设置自动选题教师------------"+teachers.get(i).getNo()+"----"+teachers.get(j).getName() );
						for(int k=0;k<teachers.get(j).getTopics().size();k++) {
//							出的题目是当前年级的
							if(teachers.get(j).getTopics().get(k).getGrade().getId() == grades.get(i).getId()) {
//								获取题目下面所有意向题目
								List<IntentionTopic> intents = teachers.get(j).getTopics().get(k).getIntentionTopics();
								List<StudentAndScore> stuScore = new ArrayList<StudentAndScore>();
								for(int kk = 0;kk<intents.size();kk++) {
//									判断是否是第一轮选题，第一志愿,学生未选择题目
									if(intents.get(kk).getBatch() == batch && intents.get(kk).getChoice() == choice && intents.get(kk).getStudent().getTopics() == null) {
//										System.out.println(intents.get(kk).getStudent().getName());
//										logger.debug(intents.get(kk).getStudent().getName());
//										获取意向题目的学生成绩
										Student student = intents.get(kk).getStudent();
										List<CourseAndGrade> courseAndGrades = timerDao.findCourseAndGrade(student.getNo());
//										for(int caa = courseAndGrades.size()-1;caa>=0;caa--) {
//											System.out.println(courseAndGrades.get(caa).getCourseName()+" --------------------------- "+courseAndGrades.get(caa).getName());
//										
//										}
										StudentAndScore studentAndScore = new StudentAndScore();
										
										studentAndScore.setStudentId(student.getId());
										
//										如果教师没有选择需要查看的课程，便将学生全部课程进行比较
										float sum = 0;
										if(checkViewGrades.size() == 0) {
											for(int cag = 0;cag<courseAndGrades.size();cag++) {
												sum += courseAndGrades.get(cag).getScore();
											}
											if(courseAndGrades.size() != 0 ){
												studentAndScore.setScore(sum/courseAndGrades.size() );
											} else {
												studentAndScore.setScore(0 );
											}
											
										} else {
											int c = 0;
											for(int cvg = 0;cvg<checkViewGrades.size();cvg++) {
												for(int cag = 0;cag<courseAndGrades.size();cag++) {
//													判断教师选择的课程和学生的课程一致，并且是当前年级
													if(checkViewGrades.get(cvg).getCourseName().equals(courseAndGrades.get(cag).getCourseName()) && checkViewGrades.get(cvg).getGradeId().equals(String.valueOf(grades.get(i).getId()))) {
														c ++;
														sum += courseAndGrades.get(cag).getScore();
													}
												}
											}
											if(c != 0) {
												studentAndScore.setScore(sum/c );
											} else {
												studentAndScore.setScore(0 );
											}
											
											
										}
										
										stuScore.add(studentAndScore);
									}
								}
//								获取该题目下所有学生的成绩，进行排名，选择
//								获取题目剩余的可选学生数
								int  number = teachers.get(j).getTopics().get(k).getEnableSelect() - teachers.get(j).getTopics().get(k).getSelectedStudent();
//								如果选择学生比可选学生少，选择所有学生
								if(number >= stuScore.size()) {
									for(int ss = 0;ss < stuScore.size(); ss++ ) {
//										System.out.println("topic "+teachers.get(j).getTopics().get(k).getId()+" stu "+stuScore.get(ss).getStudentId()+"------------less------"+stuScore.get(ss).getScore());
										timerDao.setStudentTopic(stuScore.get(ss).getStudentId(), teachers.get(j).getTopics().get(k).getId());
										timerDao.updateTopicSelected(teachers.get(j).getTopics().get(k).getId());
//										session.flush();
//										session.clear();
									}
									
								} else {
//									将学生按照成绩排序
									Collections.sort(stuScore,new Comparator<StudentAndScore>() {
							            @Override
							            public int compare(StudentAndScore s1, StudentAndScore s2) {
//							            	降序
							            	if(s1.getScore() < s2.getScore()) {
							        			return 1;
							        		} else if(s1.getScore() > s2.getScore()) {
							        			return -1;
							        		} else {
							        			return 1;
							        		}
							            }
							        });
									int count = 0;
//									将选择最大的学生数
									for(int get=0;get<stuScore.size();get++) {
										if(count == number) {
											break;
										}
										if(stuScore.get(get).getScore() >= 0) {
											count ++;
											timerDao.setStudentTopic(stuScore.get(get).getStudentId(), teachers.get(j).getTopics().get(k).getId());
											timerDao.updateTopicSelected(teachers.get(j).getTopics().get(k).getId());
//											session.flush();
//											session.clear();
//											System.out.println("topic"+teachers.get(j).getTopics().get(k).getId()+" stu "+stuScore.get(get).getStudentId()+"------"+stuScore.get(get).getScore());
										}
									}
									
								}
								
							}
						}
					} else {
						//当前年级的老师没有设置自动选题
						logger.info("未设置自动选题教师------------"+teachers.get(i).getNo()+"----"+teachers.get(j).getName() );
					}
						
				}
				
				
			}
			
//			自动选择了一次后，进行更新操作次数
//			timerDao.updateTopicTimes(isSelect);
//			session.flush();
//			session.clear();
			
		}

	}

}
