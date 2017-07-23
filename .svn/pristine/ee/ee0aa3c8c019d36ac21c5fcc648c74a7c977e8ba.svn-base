package com.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.common.QueryCondition;
import com.dao.impl.DaoImpl;
import com.entity.CheckViewGrade;
import com.entity.Clazz;
import com.entity.CourseAndGrade;
import com.entity.Direction;
import com.entity.IntentionTopic;
import com.entity.Setting;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.Topics;
import com.entity.User;

@Service
public class StudentService<T> {
	@Autowired
	private DaoImpl<T> daoImpl;
	@Autowired
	private CourseGradeService courseGradeService;
	/**
	 * student view topics
	 * @param directions
	 * @return
	 */
	public Set<Topics> viewTopic(Student student, int batch) {
		Direction direction = null;
		direction = student.getClazz().getDirection();
		List<Direction> directions = new ArrayList<Direction>();
		directions.add(direction);
		
//		将获取的数据封装进集合，出去重复的
		Set<Topics> set = new HashSet<Topics>();
		List<Topics> list = (List<Topics>) daoImpl.viewTopic(directions);
		
		
		for(int i=0;i<list.size();i++){
			set.add(list.get(i));
		}
		int count = 0;
		for(Topics t:set){
			count = 0;//统计有多少个意向学生
			for(int i=0;i<t.getIntentionTopics().size();i++){
//				根据设定的时间来判断当前是第几轮选题
				if(t.getIntentionTopics().get(i).getBatch() == batch){
					count++;
				}
				t.setIntentionNumber(count);
			}
		}
		daoImpl.closeSession();
		return set;
	}

	/**
	 * select intention topic
	 * @param student
	 * @param choice
	 * @param setting
	 * @param topic
	 * @return
	 */
	public int selectIntentionTopic(Student student,int choice,int batch,Topics topic){
		QueryCondition queryCondition = new QueryCondition();
		queryCondition.setTable("IntentionTopic");
		queryCondition.setConunt(2);
		queryCondition.setRow1("batch");
		queryCondition.setValue1(String.valueOf(batch));
		
		queryCondition.setRow2("studentId");
		queryCondition.setValue2(String.valueOf(student.getId()));
		List<IntentionTopic> intentionTopics =null;
		
		intentionTopics = (List<IntentionTopic>) daoImpl.findByFree(queryCondition);
		daoImpl.closeSession();
		
		int flag = 0;
		for(int i=0;i<intentionTopics.size();i++){
			int cho = intentionTopics.get(i).getChoice();
			int topicId = (int) intentionTopics.get(i).getTopic().getId();
//			如果都一样表示已经选择了该题目的该志愿
			if(cho == choice&&topicId == topic.getId()){
				flag = 4;
				break;
			}else if(cho != choice&&topicId == topic.getId()){
//			题目相同，志愿不同，是否更改志愿
				flag = 2;
				break;
			}else if(cho == choice&&topicId != topic.getId()){
//			志愿相同，题目不同，是否更改题目
				flag = 3;
				break;
			}
		}
		if(flag == 0)
			flag = 1;
		return flag;
	}
	
	/**
	 * save intention topic
	 * @param student
	 * @param choice
	 * @param setting
	 * @param topic
	 * @return
	 */
	public IntentionTopic getIntentionTopic(Student student,int choice,int batch,Topics topic){
		IntentionTopic intent = new IntentionTopic();
		intent.setBatch(batch);
		
		intent.setChoice(choice);
		intent.setStudent(student);
		intent.setTopic(topic);
		
		return intent;
	}
	/**
	 * updateIntentionTopic
	 * @param student
	 * @param choice
	 * @param setting
	 * @param topic
	 * @return
	 */
	public boolean updateIntentionTopic(Student student,int choice,Setting setting,Topics topic,int type){
		QueryCondition queryCondition = new QueryCondition();
		queryCondition.setTable("IntentionTopic");
		queryCondition.setConunt(4);
		queryCondition.setRow1("batch");
		queryCondition.setValue1("1");
		
		queryCondition.setRow3("studentId");
		queryCondition.setValue3(String.valueOf(student.getId()));
//		1为更新志愿
		if(type==1){
//			设置顺序
			queryCondition.setRow2("topicId");
			queryCondition.setValue2(String.valueOf(topic.getId()));
			
			queryCondition.setRow4("choice");
			queryCondition.setValue4(String.valueOf(choice));
		}else{
//		2为更新题目
			queryCondition.setRow4("topicId");
			queryCondition.setValue4(String.valueOf(topic.getId()));
			
			queryCondition.setRow2("choice");
			queryCondition.setValue2(String.valueOf(choice));
		}
		
		
		if(daoImpl.updateByFree(queryCondition)){
			daoImpl.closeSession();
			return true;
		}else{
			return false;
		}
	}
	/**
	 * getIntentionTopicDelete
	 * @param student
	 * @param choice
	 * @param setting
	 * @param topic
	 * @param type
	 * @return
	 */
	public IntentionTopic getIntentionTopicDelete(Student student,int choice,int batch, Topics topic,int type){
		List<IntentionTopic> intentionTopics =null;
		
		QueryCondition queryCondition = new QueryCondition();
		queryCondition.setTable("IntentionTopic");
		queryCondition.setConunt(3);
		queryCondition.setRow1("batch");
		queryCondition.setValue1(String.valueOf(batch));
		
		queryCondition.setRow2("studentId");
		queryCondition.setValue2(String.valueOf(student.getId()));
//		1为更新志愿
		if(type==1){
//			设置顺序
			queryCondition.setRow3("topicId");
			queryCondition.setValue3(String.valueOf(topic.getId()));
		}else{
//		2为更新题目
			queryCondition.setRow3("choice");
			queryCondition.setValue3(String.valueOf(choice));
		}
		intentionTopics = (List<IntentionTopic>) daoImpl.findByFree(queryCondition);
		daoImpl.closeSession();
		
		if(intentionTopics.size()>0){
			intentionTopics.get(0).setTopic(null);
			intentionTopics.get(0).setStudent(null);
			return intentionTopics.get(0);
		}
		return null;
	}
	
	
	/**
	 * check this grade student
	 * @param gradeId
	 * @return
	 */
	public List<T> viewStudents(String gradeId, int page, int eachPage) {
		return daoImpl.viewStudents(gradeId, page, eachPage);
	}
	/**
	 * check the number of students in this grade
	 * @return
	 */
	public int getStudentsNum(String gradeId) {
		return daoImpl.getStudentsNum(gradeId);
	}
	
	
	/**
	 * 批量导入学生
	 * @param file
	 * @param path
	 * @return
	 */
	public List<Student> batchImportStudent(MultipartFile file, String path){
//		封装学生
		List<Student> students = new ArrayList<Student>();
		Student student = null;
//		学生登录表
		User user = null;
		String fileName = "";
		String fileType1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		String fileType2 = "application/vnd.ms-excel";
//		判断是否是表格的两种格式
		if (fileType1.equals(file.getContentType()) || fileType2.equals(file.getContentType())) {
			String origName = file.getOriginalFilename();
//			2.读取文件
			try {
				Workbook workbook = getWorkbook(origName, file.getInputStream()); 
				Sheet sheet = workbook.getSheetAt(0);  
		        int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数  
		        //遍历每一行  
		        Clazz clazz = null;
		        for (int r = 1; r < rowCount; r++) {  
		            Row row = sheet.getRow(r);  
//		            int cellCount = row.getPhysicalNumberOfCells(); //获取总列数  
		            //遍历每一列  
		            student = new Student();
		            student.setId(0);
			        user = new User();
			        user.setPassword("123456");
			        user.setPrivilege("4");
			        
			        student.setUser(user);
			        
//	                                                将学号设置为用户名 
			        String no = "";
			        Cell usernameCell = row.getCell(0);
//			        if (getValue(usernameCell).contains(".")) {
//			        	 no = getValue(usernameCell).substring(0, getValue(usernameCell).lastIndexOf('.'));
//			        } else {
//			        	no = getValue(usernameCell);
//			        }
			        no = getValue(usernameCell);
                	user.setUsername(no);
                	student.setNo(no);
//                	姓名
                	Cell nameCell = row.getCell(1);
                	student.setName(getValue(nameCell));
//                	班级
                	String clazzId = "0";
                	clazz = new Clazz();
                	Cell clazCell = row.getCell(2);
                	if (getValue(clazCell).contains(".")) {
                		clazzId = getValue(clazCell).substring(0, getValue(clazCell).lastIndexOf('.'));
			        } else {
			        	clazzId = getValue(clazCell);
			        }
                	;
                	clazz.setId(Long.valueOf(clazzId));
                	student.setClazz(clazz);
//                	性别
                	Cell sexCell = row.getCell(3);
                	student.setSex(getValue(sexCell));
//                	qq
                	Cell qqCell = row.getCell(4);
                	student.setQq(getValue(qqCell));
//                	电话
                	Cell teleCell = row.getCell(5);
                	student.setTelephone(getValue(teleCell));
//                	邮箱
                	Cell emailCell = row.getCell(6);
                	student.setEmail(getValue(emailCell));
                	
//                	判断是否存在此学生
                	int count = daoImpl.getCount("Student", "no", no);
                	if (count > 0) {
                		
                	} else {
                		students.add(student);
                	}
		        }
		        
		        return students;
			} catch (Exception e) {
				e.printStackTrace();
			} //文件流  
			
		}
		return students;
	}
	/**
	 * 通过不同的excel格式获取不同的workbook
	 * @param fileName
	 * @param is
	 * @return
	 */
	public Workbook getWorkbook(String fileName, InputStream is) {
		Workbook wb = null;
		try{
			String sub = fileName.substring(fileName.lastIndexOf('.'));
	        if (".xlsx".equals(sub)) {
	        	wb = new XSSFWorkbook(is);
	        } else {
	        	wb = new HSSFWorkbook(is);
	        }
	   }catch(Exception e){
	        e.printStackTrace();   
	   }
		
		return wb;
	}
	/**
	 * 通过判断单元格格式获取字符串
	 * @param cell
	 * @return
	 */
	public String getValue(Cell cell) {
		if(cell==null){
            return ""; 
        }else{
        //判断单元格的数据类型
        switch (cell.getCellType()) {  
            case Cell.CELL_TYPE_NUMERIC: // 数字  
            	return String.valueOf(Integer.valueOf((int) cell.getNumericCellValue()));
            case Cell.CELL_TYPE_STRING: // 字符串  
            	return String.valueOf(cell.getStringCellValue() );  
            case Cell.CELL_TYPE_BOOLEAN: // Boolean  
            	return String.valueOf(cell.getBooleanCellValue() );  
            case Cell.CELL_TYPE_FORMULA: // 公式  
            	return String.valueOf(cell.getCellFormula() );  
            case Cell.CELL_TYPE_BLANK: // 空值  
            	 return "";   
            case Cell.CELL_TYPE_ERROR: // 故障  
            	 return "";  
            default:  
            	 return "";   
            }  
        }
	}
	/**
	 * 教师查看学生信息，课程是教师需要的课程
	 * @param courseAndGrades
	 * @param teacher
	 * @param gradeId
	 * @param no
	 * @return
	 */
	public List<CourseAndGrade> getCourseAndGradesFilter(List<CourseAndGrade> courseAndGrades, Teacher teacher, String gradeId, String no ) {
//		获取教师需要查看的课程
		List<CheckViewGrade> checkViewGrade = courseGradeService.viewCourseChoice(teacher, gradeId);
//		用来存储符合的课程
		List<CourseAndGrade> courseAndGrades2 = new ArrayList<CourseAndGrade>();
		for(int i=0;i<checkViewGrade.size();i++) {
			for(int j=0;j<courseAndGrades.size();j++) {
//				满足条件的课程取出
				if(checkViewGrade.get(i).getCourseName().equals(courseAndGrades.get(j).getCourseName())) {
					courseAndGrades2.add(courseAndGrades.get(j));
				}
			}
		}
		return courseAndGrades2;
	}
	
	
}


