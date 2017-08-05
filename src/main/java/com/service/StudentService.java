package com.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.common.RedisTool;
import com.common.ServerResponse;
import com.dao.IIntentionDao;
import com.dao.IScoreDao;
import com.dao.IStudentDao;
import com.dao.ITopicDao;
import com.dao.impl.DaoImpl;
import com.entity.CheckViewGrade;
import com.entity.Clazz;
import com.entity.CourseAndGrade;
import com.entity.IntentionTopic;
import com.entity.Score;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.Topics;
import com.entity.User;

@Service
public class StudentService {
	@Autowired
	private DaoImpl daoImpl;
	@Autowired
	private CourseGradeService courseGradeService;
	
	@Autowired
	private IStudentDao studentDao;
	
	@Autowired
	private IIntentionDao intentionDao;
	
	@Autowired
	private ITopicDao topicDao;
	
	@Autowired
	private IScoreDao scoreDao;
	
	private Logger logger = Logger.getLogger(StudentService.class);
	/**
	 * student view topics
	 * @param directions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Topics> viewTopic(Long directionId, int batch, int num, int size, String plateform) {
		String key = plateform+directionId+batch+"topics"+num;
		List<Topics> topics = null;
		Object obj= null;
		obj = RedisTool.getReids(key);
		if(obj == null) {
			logger.info("key->"+key+"---query database");
			topics = topicDao.studentGetTopics(directionId, batch, num, size);
			RedisTool.setRedis(key, 60, topics);
		} else {
			logger.info("key->"+key+"---query reids");
			topics = (List<Topics>) RedisTool.getReids(key);
		}
		
		
		
		return topics;
	}
	
	/**
	 * 学生查看题目的数量
	 * @param directionId
	 * @return
	 */
	public Integer getTopicCount(Long directionId) {
		
		return topicDao.getTopicCount(directionId);
	}
	
	/**
	 * 查看学生是否选择题目
	 * @param studentId
	 * @return
	 */
	public boolean isStudentSelect(Long studentId) {
		Student student = studentDao.studentIsSelectTopic(studentId);
		if(student != null) {
			return true;
		}
		return false;
	}
	
	public List<Topics> viewT(Student student, int batch) {
		return studentDao.viewTopics(student.getClazz().getDirection().getId());
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
		List<IntentionTopic> intentionTopics = null;
		intentionTopics = studentDao.viewIntentions(student.getId(), batch);
		
		int flag = 0;
		for(int i=0;i<intentionTopics.size();i++){
			int cho = intentionTopics.get(i).getChoice();
			Long topicId = intentionTopics.get(i).getTopic().getId();
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
//		未选择题目，直接选择题目
		if(flag == 0) {
			flag = 1;
			IntentionTopic intent = new IntentionTopic();
			intent.setBatch(batch);
			
			intent.setChoice(choice);
			intent.setStudent(student);
			intent.setTopic(topic);
			if(intentionDao.saveIntention(intent)){
				flag = 1;
			} else {
				flag = 0;
			}
				
		}
			
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
	public boolean updateIntentionTopic(Long studentId,Integer choice, Integer batch, Long topicId,Integer type){
		IntentionTopic intent = new IntentionTopic();
		intent.setBatch(batch);
		intent.setChoice(choice);
		
		Topics topic = new Topics();
		topic.setId(topicId);
		intent.setTopic(topic);
		
		Student student = new Student();
		student.setId(studentId);
		intent.setStudent(student);
		
		
		return intentionDao.choiceDeleteIntention(intent);
	}
	
	/**
	 * check this grade student
	 * @param gradeId
	 * @return
	 */
	public List viewStudents(String gradeId, int page, int eachPage) {
		return daoImpl.viewStudents(gradeId, page, eachPage);
	}
	
	/**
	 * get student
	 * @param studentId
	 * @return
	 */
	public Student getStudent(Long studentId) {
		return studentDao.getStudent(studentId);
	}
	
	/**
	 * check the number of students in this grade
	 * @return
	 */
	public int getStudentsNum(Long gradeId) {
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
		            student.setId(new Long(0));
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
		List<CheckViewGrade> checkViewGrade = courseGradeService.viewCourseChoice(teacher.getId(), gradeId);
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
	
	/**
	 * 手机端学生查看成绩
	 * @param userId
	 * @return
	 */
	public ServerResponse<Score> viewScoreApp(Long userId) {
		Score score = new Score();
		Score score2 = scoreDao.getScoreParam(userId);
		float s = score2.getMediumScore() +score2.getHeadScore() + score2.getReplyResult();
		score.setScore(s);
		return ServerResponse.response(200, "获取成功", score);
	}
	
	/**
	 * 学生查看成绩
	 * @param studentId
	 * @return
	 */
	public Score getScore(Long studentId) {
		
		return scoreDao.getScoreParam(studentId);
	}
	
	
}


