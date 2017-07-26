package com.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.record.formula.functions.T;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ISettingDao;
import com.dao.IStudentDao;
import com.dao.ITeacherDao;
import com.dao.impl.DaoImpl;
import com.entity.Grade;
import com.entity.Setting;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.Topics;

@Service
public class TeacherService {
	@Autowired
	private DaoImpl daoImpl;
	
	@Autowired
	private ITeacherDao teacherDao;
	
	@Autowired
	private IStudentDao studentDao;
	
	@Autowired
	private ISettingDao settingDao;
	
	public void closeSession(){
		daoImpl.closeSession();
	}
	/**
	 * 查看选择了该老师题目的学生
	 * @param teacher
	 * @param gradeId
	 * @return
	 */
	public Set<Topics> viewSelected(Long teacherId, Long gradeId){
		int batch = 0;
		int choice = 0;
		int bc[] = getBatchChoice(gradeId);
		batch = bc[0];
		choice = bc[1];
		List<Topics> topics = (List<Topics>) daoImpl.findBy("Topics", "teacherId", String.valueOf(teacherId));
		
		Set<Topics> topics2 = new HashSet<Topics>();
		Topics topic = null;
		
		for(int i=0;i<topics.size();i++){
			if( topics.get(i).getDirections().size() > 0) {
				boolean flag = false;
				int count = 0;//统计有多少个意向学生
//				判断是否是当前年级
				if(Long.valueOf(gradeId) == topics.get(i).getDirections().get(0).getSpceialty().getGrade().getId() ) {
					flag = false;
					for(int j=0;j<topics.get(i).getIntentionTopics().size();j++){
//						根据设定的时间来判断当前是第几轮选题 ,和第几志愿,,,并且此学生未选题
						boolean isBatch = topics.get(i).getIntentionTopics().get(j).getBatch() == batch;
						boolean isChoice = topics.get(i).getIntentionTopics().get(j).getChoice() == choice;
						boolean notTopic = topics.get(i).getIntentionTopics().get(j).getStudent().getTopics() == null;
						if(isBatch && isChoice && notTopic){
							flag = true;
							count++;
//							break;
						} else { //判断当前是第几轮选题 ,和第几志愿结束
							topics.get(i).getIntentionTopics().get(j).setStudent(null);  //如果不是当前批次的，或者当前志愿的学生，清除掉
						}
						
//						如果有当前志愿，和当前轮次的题目
					} //遍历意向题目结束
					topics.get(i).setIntentionNumber(count);
					if(flag) {
						topics2.add(topics.get(i));
					}
				} // 判断是否是当前年级结束
			}//遍历题目
		}
		
		daoImpl.closeSession();
		return topics2;
	}
	
	public int[] getBatchChoice(Long gradeId) {
		Setting setting = settingDao.getSetting(gradeId);
		int batch = 0;
		int choice = 0;
//		查询时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		当前时间
		Date now = new Date();
		try {
//			第一次选题第一志愿
			Date oneAdmissionStartTime = sdf.parse(setting.getOneAdmissionStartTime().replace("T"," ").concat(":00"));
			Date oneFirstChoiceDeadline = sdf.parse(setting.getOneFirstChoiceDeadline().replace("T"," ").concat(":00"));
			
//			第二志愿
			Date oneSecondChoiceDeadline = sdf.parse(setting.getOneSecondChoiceDeadline().replace("T"," ").concat(":00"));
			
//			第三志愿
			Date oneThirdChoiceDeadline = sdf.parse(setting.getOneThirdChoiceDeadline().replace("T"," ").concat(":00"));
			
//			比较
			if(now.getTime() > oneAdmissionStartTime.getTime() && now.getTime() < oneFirstChoiceDeadline.getTime() ) {
				batch = 1;
				choice = 1;
			} else if (now.getTime() > oneFirstChoiceDeadline.getTime() && now.getTime() < oneSecondChoiceDeadline.getTime()) {
				batch = 1;
				choice = 2;
			} else if (now.getTime() > oneSecondChoiceDeadline.getTime() && now.getTime() < oneThirdChoiceDeadline.getTime()) {
				batch = 1;
				choice = 3;
			}
			
			
//			第二次选题第一志愿
			Date twoAdmissionStartTime = sdf.parse(setting.getTwoAdmissionStartTime().replace("T"," ").concat(":00"));
			Date twoFirstChoiceDeadline = sdf.parse(setting.getTwoFirstChoiceDeadline().replace("T"," ").concat(":00"));
			
//			第二志愿
			Date twoSecondChoiceDeadline = sdf.parse(setting.getTwoSecondChoiceDeadline().replace("T"," ").concat(":00"));
			
//			第三志愿
			Date twoThirdChoiceDeadline = sdf.parse(setting.getTwoThirdChoiceDeadline().replace("T"," ").concat(":00"));
			
//			比较
			if(now.getTime() > twoAdmissionStartTime.getTime() && now.getTime() < twoFirstChoiceDeadline.getTime() ) {
				batch = 2;
				choice = 1;
			} else if (now.getTime() > twoFirstChoiceDeadline.getTime() && now.getTime() < twoSecondChoiceDeadline.getTime()) {
				batch = 2;
				choice = 2;
			} else if (now.getTime() > twoSecondChoiceDeadline.getTime() && now.getTime() < twoThirdChoiceDeadline.getTime()) {
				batch = 2;
				choice = 3;
			}
			
////			第三次选题第一志愿
//			Date threeAdmissionStartTime = sdf.parse(setting.getThreeAdmissionStartTime().replace("T"," ").concat(":00"));
//			Date threeFirstChoiceDeadline = sdf.parse(setting.getThreeFirstChoiceDeadline().replace("T"," ").concat(":00"));
//			
////			第二志愿
//			Date threeSecondChoiceDeadline = sdf.parse(setting.getThreeSecondChoiceDeadline().replace("T"," ").concat(":00"));
//			
////			第三志愿
//			Date threeThirdChoiceDeadline = sdf.parse(setting.getThreeThirdChoiceDeadline().replace("T"," ").concat(":00"));
//			
////			比较
//			if(now.getTime() > threeAdmissionStartTime.getTime() && now.getTime() < threeFirstChoiceDeadline.getTime() ) {
//				batch = 3;
//				choice = 1;
//			} else if (now.getTime() > threeFirstChoiceDeadline.getTime() && now.getTime() < threeSecondChoiceDeadline.getTime()) {
//				batch = 3;
//				choice = 2;
//			} else if (now.getTime() > threeSecondChoiceDeadline.getTime() && now.getTime() < threeThirdChoiceDeadline.getTime()) {
//				batch = 3;
//				choice = 3;
//			}
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int [] bc = new int[2];
		bc[0] = batch;
		bc[1] = choice;
		return bc;
	}
	
	/**
	 * 取认选择学生
	 * @param topicId
	 * @param studentId
	 * @return
	 */
	public boolean confirmStudent(Long topicId, Long studentId) {
		Topics topic = teacherDao.getTopicIsSelect(topicId);
//		选择的人数小于可选学生人数
		if ( topic.getEnableSelect() > topic.getSelectedStudent() ) {
//				选择此学生
			if(teacherDao.confirmSelect(topicId, studentId)) {
				return true;
			}
			return false;
		}else{
			return false;
		}
	}
	
	/**
	 * 查看未选题的学生
	 * @param gradeId
	 * @param page
	 * @param eachPage
	 * @return
	 */
	public List<T> viewNotSelected(String gradeId, int page, int eachPage) {
		return daoImpl.viewNotSelected(gradeId, page, eachPage);
	}
	/**
	 * 获取未选题学生个数
	 * @param gradeId
	 * @return
	 */
	public int getNotSelectedNum(String gradeId) {
		return daoImpl.getNotSelectedNum(gradeId);
	}
	
	public List<Student> getStudents(Long gradeId, int num, int size) {
		
		return studentDao.getStudents(gradeId, num, size);
	}
	
	/**
	 * export student last select 
	 * @param gradeId
	 * @return
	 */
	public HSSFWorkbook exportStudentsLastSelect(Long gradeId) {
		
		List<Student> students = studentDao.getStudents(gradeId, 0, 100000);
		//创建HSSFWorkbook对象(excel的文档对象)
	     HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("学生最终选题信息表");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1=sheet.createRow(0);
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell=row1.createCell(0);
		      //设置单元格内容
		cell.setCellValue("学生最终选题信息表");
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,12));
		
		//在sheet里创建第二行
		HSSFRow row2=sheet.createRow(1);    
       //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("学号");
        row2.createCell(1).setCellValue("姓名");    
        row2.createCell(2).setCellValue("性别");
		row2.createCell(3).setCellValue("电话");  
		row2.createCell(4).setCellValue("方向"); 
		row2.createCell(5).setCellValue("专业");
		row2.createCell(6).setCellValue("年级"); 
		row2.createCell(7).setCellValue("系"); 
		row2.createCell(8).setCellValue("题目名称"); 
		row2.createCell(9).setCellValue("指导老师"); 
		row2.createCell(10).setCellValue("指导老师电话"); 
		row2.createCell(11).setCellValue("指导老师QQ"); 
		
		HSSFRow row = null;
		for (int i=0;i<students.size();i++) {
			row = sheet.createRow(i+2);
			row.createCell(0).setCellValue(students.get(i).getNo());
			row.createCell(1).setCellValue(students.get(i).getName());
			row.createCell(2).setCellValue(students.get(i).getSex());
			row.createCell(3).setCellValue(students.get(i).getTelephone());
			row.createCell(4).setCellValue(students.get(i).getClazz().getDirection().getDirectionName());
			row.createCell(5).setCellValue(students.get(i).getClazz().getDirection().getSpceialty().getSpecialtyName());
			row.createCell(6).setCellValue(students.get(i).getClazz().getDirection().getSpceialty().getGrade().getGradeName());
			row.createCell(7).setCellValue(students.get(i).getClazz().getDirection().getSpceialty().getGrade().getDepartment().getDepartmentName());
			if (students.get(i).getTopics() != null) {
				row.createCell(8).setCellValue(students.get(i).getTopics().getTopicsName());
				row.createCell(9).setCellValue(students.get(i).getTopics().getTeacher().getName());
				row.createCell(10).setCellValue(students.get(i).getTopics().getTeacher().getTelephone());
				row.createCell(11).setCellValue(students.get(i).getTopics().getTeacher().getQq());
			}
		}
		
	   
		
//		获取完数据后关闭session
	   studentDao.closeSession();
		
		return wb;
	}
	/**
	 * check the number of students in this grade
	 * @return
	 */
	public int getStudentsNum(Long gradeId) {
		return daoImpl.getStudentsNum(gradeId);
	}
	/**
	 * get dean's number
	 * @return
	 */
	public int getDeanNum() {
		return daoImpl.getDeanNum();
	}
	/**
	 * get dean
	 * @param page
	 * @param eachPage
	 * @return
	 */
	public List<Teacher> getDean(int page, int eachPage) {
		return (List<Teacher>) daoImpl.getDean(page, eachPage);
	}
	
	/**
	 * The teacher checks the time
	 * @param gradeId
	 * @return
	 */
	public Setting viewTime(String gradeId) {
		List<Setting> settings = (List<Setting>) daoImpl.findBy("Setting", "gradeId", gradeId);
		daoImpl.closeSession();
		if(settings.size() >0 ) {
			return settings.get(0);
		} else {
			return new Setting();
		}
	}
	
	/**
	 * 查看年级
	 * @param departmentId
	 * @return
	 */
	public List<Grade> viewGrade(Long departmentId) {
		
		return teacherDao.viewGrades(departmentId);
	}
	
	/**
	 * 查看教师
	 * @param departmentId
	 * @return
	 */
	public List<Teacher> viewTeachers(Long departmentId, Integer num, Integer size) {
		
		return teacherDao.viewTeachers(departmentId, num, size);
	}
	
	public Integer getTeachersCount(Long departmentId) {
		return teacherDao.getTeachersCount(departmentId);
	}
	
	/**
	 * 查看未选题学生
	 * @param gradeId
	 * @return
	 */
	public List<Student> getStudentsNotSelect(Long gradeId, Integer num, Integer size) {
		return studentDao.getStudentsNotSelect(gradeId, num, size);
	}
	
	/**
	 * 查看未选题学生人数
	 * @param gradeId
	 * @return
	 */
	public Integer getStudentsNotSelectCount(Long gradeId) {
		return studentDao.getStudentsNotSelectCount(gradeId);
	}
	
	/**
	 * 更新题目状态
	 * @param topicId
	 * @param state
	 * @return
	 */
	public boolean updateTopicState(Long topicId, int state) {
		return teacherDao.updateTopicState(topicId, state);
	}
}
