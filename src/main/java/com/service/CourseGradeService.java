package com.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.common.Course;
import com.common.WorkbookTool;
import com.dao.impl.CommonDaoImpl;
import com.dao.impl.CourseDaoImpl;
import com.entity.CheckViewGrade;
import com.entity.CourseAndGrade;
import com.entity.Grade;
import com.entity.Teacher;
/**
 * 课程相关的逻辑处理
 * @author kone
 * 2017-4-12
 */

@Service
public class CourseGradeService {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	@Autowired
	private CommonDaoImpl commonDaoImpl;
	@Autowired
	private CourseDaoImpl courseDaoImpl;
	
	@Transactional
	public List<CourseAndGrade> saveGrade(MultipartFile file, long gradeId){
		
		Grade gradee = new Grade();
		gradee.setId(gradeId);
		
		List<CourseAndGrade> grades = new ArrayList<CourseAndGrade>();
		List<CourseAndGrade> grades2 = new ArrayList<CourseAndGrade>();
		WorkbookTool tool = new WorkbookTool();
		String fileType1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		String fileType2 = "application/vnd.ms-excel";
//		判断是否是表格的两种格式
		if (fileType1.equals(file.getContentType()) || fileType2.equals(file.getContentType())) {
			String origName = file.getOriginalFilename();
//			2.读取文件
			try {
				Workbook workbook = tool.getWorkbook(origName, file.getInputStream()); 
				Sheet sheet = workbook.getSheetAt(0);  
		        int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数  
		        //遍历每一行  
		        for (int r = 1; r < rowCount; r++) {  
		            Row row = sheet.getRow(r);  
		            //遍历每一列  
		            CourseAndGrade grade = new CourseAndGrade();
		            grade.setGrade(gradee);
//	                                                学号
			        String no = "";
			        Cell usernameCell = row.getCell(0);
			        no = tool.getValue(usernameCell);
			        grade.setNo(no);
//                	姓名
                	Cell nameCell = row.getCell(1);
                	grade.setName(tool.getValue(nameCell));
                	
//                	课程名称
                	Cell courseNameCell = row.getCell(2);
                	grade.setCourseName(tool.getValue(courseNameCell));
                	
//                	课程性质
                	Cell courseNatureCell = row.getCell(3);
                	grade.setCourseNature(tool.getValue(courseNatureCell));
                	
//                	学分
                	Cell creditCell = row.getCell(4);
                	grade.setCredit(Float.valueOf(tool.getValue(creditCell)));
//                	课程成绩分数
                	Cell scoreCell = row.getCell(5);
					try {
						grade.setScore(Float.valueOf(tool.getValue(scoreCell)));
					} catch (Exception e) {
						continue;
					}

                	
                	grades.add(grade);
		        }
//		        for(CourseAndGrade cg : grades) {
//					this.addCourse(cg);
//				}

		        session = sessionFactory.getCurrentSession();
    			commonDaoImpl.setSession(session);
    			for(int i=0;i<grades.size();i++) {

			        try{
			        	commonDaoImpl.save(grades.get(i));
			        	if(i % 10 == 0) {
			        		session.flush();
			        		session.clear();
			        	}
	            	}catch(Exception e){
	            		grades2.add(grades.get(i));
	        			e.printStackTrace();
	        		}
    			}
		        return grades2;
			} catch (Exception e) {
//				throw new ServiceException("error");
				e.printStackTrace();
			} 
		}
		return grades2;
	}
	
	/**
	 * 获取课程成绩条数
	 * @return
	 */
	public int getGradeNum(long gradeId){
		session = sessionFactory.getCurrentSession();
//		传递session保证是同一个session进行事务处理
		commonDaoImpl.setSession(session); 
		int num = commonDaoImpl.getNum("CourseAndGrade", "gradeId", String.valueOf(gradeId));
		return num;
	}
	/**
	 * 查看学生课程和成绩
	 * @param gradeId
	 * @param page
	 * @param eachPage
	 * @return
	 */
	public List<CourseAndGrade> viewGrades(long gradeId, int page, int eachPage) {
		session = sessionFactory.getCurrentSession();
//		传递session保证是同一个session进行事务处理
		commonDaoImpl.setSession(session); 
		List<CourseAndGrade> course = commonDaoImpl.findBy("CourseAndGrade", "gradeId", String.valueOf(gradeId), page, eachPage);
		return course;
	}
	/**
	 * 查看课程
	 * @param gradeId
	 * @param page
	 * @param eachPage
	 * @return
	 */
	public List<Course> viewCourse(Long teacherId, long gradeId, int page, int eachPage) {
		session = sessionFactory.getCurrentSession();
//		传递session保证是同一个session进行事务处理
		commonDaoImpl.setSession(session); 
		List<CourseAndGrade> course = commonDaoImpl.findBy("CourseAndGrade", "gradeId", String.valueOf(gradeId), page, eachPage);
//		查找出教师已经选择的课程
		courseDaoImpl.setSession(session);
		List<CheckViewGrade> checkViewGrade = courseDaoImpl.getcheckViewGrade(String.valueOf(gradeId), teacherId);
//		创建common里面的course用来提取所有的课程名称和性质
		List<Course> courses = new ArrayList<Course>();
		Course cour = null;
		for(int i=0;i<course.size();i++) {
			int flag = 0;
			for(int j=0;j<checkViewGrade.size();j++) {
				if(checkViewGrade.get(j).getCourseName().equals(course.get(i).getCourseName())) {
					flag =  1;
				}
			}
			if(flag == 0) {
				cour = new Course();
				cour.setCourseName(course.get(i).getCourseName());
				cour.setCoursenature(course.get(i).getCourseNature());
				cour.setCredit(course.get(i).getCredit());
				courses.add(cour);
			}
			
		}
		
		for(int i=0;i<courses.size()-1;i++) {
			for(int j=courses.size()-1;j>i;j--) {
				if(courses.get(i).getCourseName().equals(courses.get(j).getCourseName())) {
					courses.remove(j);
				}
			}
		}
		
		return courses;
	}
	
	/**
	 * 设置教师选择的课程
	 * @param courseName
	 * @return
	 */
	@Transactional
	public boolean setViewCourse(Long teacherId, String []courseName, long gradeId) {
		Teacher teacher = new Teacher();
		teacher.setId(teacherId);
		
		CheckViewGrade checkViewGrade = null;
		List<CheckViewGrade> checkViewGrades = new ArrayList<CheckViewGrade>();
		for(int i=0;i<courseName.length;i++) {
			checkViewGrade = new CheckViewGrade();
			checkViewGrade.setTeacher(teacher);
			checkViewGrade.setCourseName(courseName[i]);
			checkViewGrade.setGradeId(String.valueOf(gradeId));
			
			checkViewGrades.add(checkViewGrade);
		}
		
		 session = sessionFactory.getCurrentSession();
//			传递session保证是同一个session进行事务处理
		 commonDaoImpl.setSession(session); 
		 for(int i=0;i<checkViewGrades.size();i++) {
		    try{
	        	commonDaoImpl.save(checkViewGrades.get(i));
	        	if(i % 10 == 0) {
	        		session.flush();
	        		session.clear();
	        	}
         	}catch(Exception e){
     			session.getTransaction().rollback();
     			e.printStackTrace();
     		}
		 }
		 
		return true;
	}
	/**
	 * 查看选择的课程
	 * @param gradeId
	 * @return
	 */
	public List<CheckViewGrade> viewCourseChoice(Long teacherId, String gradeId) {
		Teacher teacher = new Teacher();
		teacher.setId(teacherId);
		 session = sessionFactory.getCurrentSession();
//			传递session保证是同一个session进行事务处理
         courseDaoImpl.setSession(session); 
		
         List<CheckViewGrade> checkViewGrade = courseDaoImpl.getcheckViewGrade(gradeId, teacher.getId());
         
		return checkViewGrade;
		
	}
	
	/**
	 * 查看选择的课程
	 * @return
	 */
	@Transactional
	public boolean deleteCourseChoice(CheckViewGrade checkViewGrade) {
		 session = sessionFactory.getCurrentSession();
//			传递session保证是同一个session进行事务处理
         commonDaoImpl.setSession(session); 
         try{
        	 commonDaoImpl.delete(checkViewGrade);
             return true;
         }catch(Exception e) {
        	 throw new ServiceException("error");
         }
		
	}
	
	/**
	 * 添加课程
	 * @return
	 */
	@Transactional
	public boolean addCourse(CourseAndGrade courseAndGrade) {
		
         try{
          session = sessionFactory.getCurrentSession();
// 			传递session保证是同一个session进行事务处理
          commonDaoImpl.setSession(session); 
          commonDaoImpl.save(courseAndGrade);
          return true;
         }catch(Exception e) {
        	 throw new ServiceException("error");
         }
		
	}
	
}
