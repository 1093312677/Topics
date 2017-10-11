package com.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.common.WorkbookTool;
import com.dao.ISettingDao;
import com.dao.IStudentDao;
import com.dao.impl.CommonDaoImpl;
import com.entity.Clazz;
import com.entity.Department;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.User;
/**
 * 处理导入信息的逻辑
 * @author kone
 * 2017.4.20
 */
@Service
public class ImportService {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	@Autowired
	private CommonDaoImpl commonDaoImpl;
	
	@Autowired
	private IStudentDao studentDao;
	/**
	 * 导入教师信息
	 * @param file
	 * @param departmentId
	 * @return
	 */
	public List<Teacher> importTeacher(MultipartFile file, long departmentId) {
		List<Teacher> teachers = new ArrayList<Teacher>();
		List<Teacher> teachersError = new ArrayList<Teacher>();
		List<Teacher> teachersIn = new ArrayList<Teacher>();
		
		List<Teacher> teachersInTemp = new ArrayList<Teacher>();
		Department department = null;
//		获取整理好的教师信息
		teachersInTemp = getTeacherFile(file);
//		排查表格相同项目
		for(int i=teachersInTemp.size()-1;i>0;i--) {
			int flag = 0;
			if(teachersInTemp.get(i).getNo() == null || teachersInTemp.get(i).getNo() == "") {
				flag = 1;
				teachersInTemp.get(i).setRemark("工号为空");
				teachersError.add(teachersInTemp.get(i));
			} else {
				for(int j=0;j<i;j++) {
					if(teachersInTemp.get(i).getNo().equals(teachersInTemp.get(j).getNo())) {
						flag = 1;
//						如果出现相同的工号，加入错误队列
						teachersInTemp.get(i).setRemark("文件中出现相同工号");
						teachersInTemp.get(j).setRemark("文件中出现相同工号");
						teachersError.add(teachersInTemp.get(i));
						teachersError.add(teachersInTemp.get(j));
						break;
					}
				}
			}
			
			
			if(flag == 0) {
				teachersIn.add(teachersInTemp.get(i));
			}
		}
		
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			commonDaoImpl.setSession(session);
//			查找所有系
			List<Department> departments = commonDaoImpl.findAll("Department");
			for(int i=teachersIn.size() - 1;i>=0;i--) {
				int flag = 0;
				for(int j=0;j<departments.size();j++) {
					if(teachersIn.get(i).getDepartment().getDepartmentName().equals(departments.get(j).getDepartmentName())) {
//					  设置系相应的id
						department = new Department();
						department.setDepartmentName(teachersIn.get(i).getDepartment().getDepartmentName());
					    department.setId(departments.get(j).getId());
						teachersIn.get(i).setDepartment(department);
						flag = 1;
						break;
					}
				}
//				遍历后没有相同的系名称，表示不存在该系，加入错误队列
				if(flag == 0) {
//					如果出现相同的工号，加入错误队列
					teachersIn.get(i).setRemark("系统中不存在该系");
					teachersError.add(teachersIn.get(i));
					teachersIn.remove(i);

				}
			}
//			查找出教师
			teachers = commonDaoImpl.findBy("Teacher", "departmentId", String.valueOf(departmentId));
			for(int i=0;i<teachersIn.size();i++) {
				int flag = 0;
				for(int j=0;j<teachers.size();j++) {
					if(teachers.get(j).getNo().equals(teachersIn.get(i).getNo())) {
						teachersIn.get(i).setRemark("系统中已存在该教师");
						teachersError.add(teachersIn.get(i));
						flag = 1;
						break;
					}
				}
//				表示不存在重复的,保存
				if(flag == 0) {
					User user = new User();
					user.setPassword("123456");
					user.setPrivilege("3");
					user.setUsername(teachersIn.get(i).getNo());
					teachersIn.get(i).setUser(user);
					commonDaoImpl.save(teachersIn.get(i));
					if(i%10 == 0) {
						session.clear();
						session.flush();
					}
				}
			}
			if(session.isOpen()) {
				session.getTransaction().commit();
			}
		} catch(Exception e) {
			
		}  finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		return teachersError;
	}
	
	
	private List<Teacher> getTeacherFile( MultipartFile file ){
		List<Teacher> teachersIn = new ArrayList<Teacher>();
		Teacher teacher = null;
		Department department = null;
		
		WorkbookTool tool = new WorkbookTool();
		String fileType1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		String fileType2 = "application/vnd.ms-excel";
		try{
	//		判断是否是表格的两种格式
			if (fileType1.equals(file.getContentType()) || fileType2.equals(file.getContentType())) {
				String origName = file.getOriginalFilename();
	//			2.读取文件
					Workbook workbook = tool.getWorkbook(origName, file.getInputStream()); 
					Sheet sheet = workbook.getSheetAt(0);  
			        int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数  
			        //遍历每一行  
			        for (int r = 0; r < rowCount; r++) {  
			            Row row = sheet.getRow(r); 
			            teacher = new Teacher();
//						遍历每一列  
//			                                   工号
			            Cell noCell = row.getCell(0);
	                	teacher.setNo(tool.getValue(noCell));

	//                	姓名
	                	Cell nameCell = row.getCell(1);
	                	teacher.setName(tool.getValue(nameCell));
	                	
	//                	性别
	                	Cell sexCell = row.getCell(2);
	                	teacher.setSex(tool.getValue(sexCell));
	                	
	//                	教师职称
	                	Cell positionCell = row.getCell(3);
	                	teacher.setPosition(tool.getValue(positionCell));
	                	
	//                	所属系
	                	Cell departmentCell = row.getCell(4);
	                	department = new Department();
	                	department.setDepartmentName(tool.getValue(departmentCell));
	                	teacher.setDepartment(department);
	//                	qq
	                	Cell qqCell = row.getCell(5);
	                	teacher.setQq(tool.getValue(qqCell));
//	                	电话
	                	Cell telCell = row.getCell(6);
	                	teacher.setTelephone(tool.getValue(telCell));
//	                	邮箱
	                	Cell emailCell = row.getCell(7);
	                	teacher.setEmail(tool.getValue(emailCell));
	                	
	                	teachersIn.add(teacher);
			        }
			}
		}catch(Exception e) {
			
		}
		
		return teachersIn;
	}
	
	
	/**
	 * 下载上传错误信息
	 * @param teachers
	 * @return
	 */
	public HSSFWorkbook exportErrorTeacher(List<Teacher> teachers) {
		//创建HSSFWorkbook对象(excel的文档对象)
	     HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("导入教师错误表");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1=sheet.createRow(0);
		//在sheet里创建第1行
		HSSFRow row2=sheet.createRow(0);    
       //创建单元格并设置单元格内容
	    row2.createCell(0).setCellValue("工号");
	    row2.createCell(1).setCellValue("姓名");    
	    row2.createCell(2).setCellValue("性别");
	    row2.createCell(3).setCellValue("教师职称");
	    row2.createCell(4).setCellValue("所属系");
		row2.createCell(5).setCellValue("QQ");  
		row2.createCell(6).setCellValue("电话"); 
		row2.createCell(7).setCellValue("邮箱"); 
		row2.createCell(8).setCellValue("错误信息"); 
		
		HSSFRow row = null;
		 
		for(int i=0;i<teachers.size();i++) {
			row = sheet.createRow(i+1);
			row.createCell(0).setCellValue(teachers.get(i).getNo());
			row.createCell(1).setCellValue(teachers.get(i).getName());
			row.createCell(2).setCellValue(teachers.get(i).getSex());
			row.createCell(3).setCellValue(teachers.get(i).getPosition());
			row.createCell(4).setCellValue(teachers.get(i).getDepartment().getDepartmentName());
			row.createCell(5).setCellValue(teachers.get(i).getQq());
			row.createCell(6).setCellValue(teachers.get(i).getTelephone());
			row.createCell(7).setCellValue(teachers.get(i).getEmail());
			row.createCell(8).setCellValue(teachers.get(i).getRemark());
		}
		return wb;
	}
	
	
	
	
	
	
	/**
	 * 导入学生信息
	 * @param file
	 * @param departmentId
	 * @return
	 */
	public List<Student> importStudent(MultipartFile file, long gradeId) {
		List<Student> students = new ArrayList<Student>();
		List<Student> studentsError = new ArrayList<Student>();
		List<Student> studentsIn = new ArrayList<Student>();
		
//		查找出学生
		students = studentDao.getAllStudents();
		
		List<Student> studentsInTemp = new ArrayList<Student>();
		Clazz clazz = null;
//		获取整理好的教师信息
		studentsInTemp = getStudentFile(file);
//		排查表格相同项目
		for(int i=studentsInTemp.size()-1;i>0;i--) {
			int flag = 0;
			if(studentsInTemp.get(i).getNo() == null || studentsInTemp.get(i).getNo() == "") {
				flag = 1;
				studentsInTemp.get(i).setRemark("学号为空");
				studentsError.add(studentsInTemp.get(i));
			} else {
				for(int j=0;j<i;j++) {
					if(studentsInTemp.get(i).getNo().equals(studentsInTemp.get(j).getNo())) {
						flag = 1;
//						如果出现相同的工号，加入错误队列
						studentsInTemp.get(i).setRemark("文件中出现相同学号");
						studentsInTemp.get(j).setRemark("文件中出现相同学号");
						studentsError.add(studentsInTemp.get(i));
						studentsError.add(studentsInTemp.get(j));
						break;
					}
				}
			}
			
			
			if(flag == 0) {
				studentsIn.add(studentsInTemp.get(i));
			}
		}
		
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			commonDaoImpl.setSession(session);
//			查找所有系
			List<Clazz> clazzs = commonDaoImpl.findAll("Clazz");
			for(int i=studentsIn.size() - 1;i>=0;i--) {
				int flag = 0;
				for(int j=0;j<clazzs.size();j++) {
					if(studentsIn.get(i).getClazz().getClassName().equals(clazzs.get(j).getClassName())) {
//					  设置相应的id
						clazz = new Clazz();
						clazz.setClassName(studentsIn.get(i).getClazz().getClassName());
						clazz.setId(clazzs.get(j).getId());
						studentsIn.get(i).setClazz(clazz);;
						flag = 1;
						break;
					}
				}
//				遍历后没有相同的系名称，表示不存在该系，加入错误队列
				if(flag == 0) {
//					如果出现相同的工号，加入错误队列
					studentsIn.get(i).setRemark("系统中不存在该班级");
					studentsError.add(studentsIn.get(i));
					studentsIn.remove(i);

				}
			}

			for(int i=0;i<studentsIn.size();i++) {
				int flag = 0;
				for(int j=0;j<students.size();j++) {
					if(students.get(j).getNo().equals(studentsIn.get(i).getNo())) {
						studentsIn.get(i).setRemark("系统中已存在该学生");
						studentsError.add(studentsIn.get(i));
						flag = 1;
						break;
					}
				}
//				表示不存在重复的,保存
				if(flag == 0) {
					User user = new User();
					user.setPassword("123456");
					user.setPrivilege("4");
					user.setUsername(studentsIn.get(i).getNo());
					studentsIn.get(i).setUser(user);
					commonDaoImpl.save(studentsIn.get(i));
					if(i%20 == 0) {
						session.clear();
						session.flush();
					}
				}
			}
			if(session.isOpen()) {
				session.getTransaction().commit();
			}
		} catch(Exception e) {
			
		}  finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		return studentsError;
	}
	
	
	private List<Student> getStudentFile( MultipartFile file ){
		List<Student> studentsIn = new ArrayList<Student>();
		Student student = null;
		Clazz clazz = null;
		
		WorkbookTool tool = new WorkbookTool();
		String fileType1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		String fileType2 = "application/vnd.ms-excel";
		try{
	//		判断是否是表格的两种格式
			if (fileType1.equals(file.getContentType()) || fileType2.equals(file.getContentType())) {
				String origName = file.getOriginalFilename();
	//			2.读取文件
					Workbook workbook = tool.getWorkbook(origName, file.getInputStream()); 
					Sheet sheet = workbook.getSheetAt(0);  
			        int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数  
			        //遍历每一行  
			        for (int r = 0; r < rowCount; r++) {  
			            Row row = sheet.getRow(r); 
			            student = new Student();
//						遍历每一列  
//			                                   学号
			            Cell noCell = row.getCell(0);
			            student.setNo(tool.getValue(noCell));

	//                	姓名
	                	Cell nameCell = row.getCell(1);
	                	student.setName(tool.getValue(nameCell));
	                	
	//                	性别
	                	Cell sexCell = row.getCell(3);
	                	student.setSex(tool.getValue(sexCell));
	                	
	//                	所属班级
	                	Cell clazzCell = row.getCell(2);
	                	clazz = new Clazz();
	                	clazz.setClassName(tool.getValue(clazzCell));
	                	student.setClazz(clazz);
	//                	qq
	                	Cell qqCell = row.getCell(4);
	                	student.setQq(tool.getValue(qqCell));
//	                	电话
	                	Cell telCell = row.getCell(5);
	                	student.setTelephone(tool.getValue(telCell));
//	                	邮箱
	                	Cell emailCell = row.getCell(6);
	                	student.setEmail(tool.getValue(emailCell));
	                	
	                	studentsIn.add(student);
			        }
			}
		}catch(Exception e) {
			
		}
		
		return studentsIn;
	}
	
	
	/**
	 * 下载上传错误信息
	 * @param teachers
	 * @return
	 */
	public HSSFWorkbook exportErrorStudent(List<Student> students) {
		//创建HSSFWorkbook对象(excel的文档对象)
	     HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("导入学生错误表");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1=sheet.createRow(0);
		 
		//在sheet里创建第1行
		HSSFRow row2=sheet.createRow(0);    
       //创建单元格并设置单元格内容
	    row2.createCell(0).setCellValue("学号");
	    row2.createCell(1).setCellValue("姓名");    
	    row2.createCell(2).setCellValue("班级");
	    row2.createCell(3).setCellValue("性别");
		row2.createCell(4).setCellValue("QQ");  
		row2.createCell(5).setCellValue("电话"); 
		row2.createCell(6).setCellValue("邮箱"); 
		row2.createCell(7).setCellValue("错误信息"); 
		
		HSSFRow row = null;
		 
		for(int i=0;i<students.size();i++) {
			row = sheet.createRow(i+1);
			row.createCell(0).setCellValue(students.get(i).getNo());
			row.createCell(1).setCellValue(students.get(i).getName());
			row.createCell(2).setCellValue(students.get(i).getClazz().getClassName());
			row.createCell(3).setCellValue(students.get(i).getSex());
			row.createCell(4).setCellValue(students.get(i).getQq());
			row.createCell(5).setCellValue(students.get(i).getTelephone());
			row.createCell(6).setCellValue(students.get(i).getEmail());
			row.createCell(7).setCellValue(students.get(i).getRemark());
		}
		return wb;
	}
}
