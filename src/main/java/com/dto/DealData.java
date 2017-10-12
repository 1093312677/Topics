package com.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.common.Pagination;
import com.entity.Clazz;
import com.entity.College;
import com.entity.Department;
import com.entity.Direction;
import com.entity.Grade;
import com.entity.Setting;
import com.entity.Specialty;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.Topics;
import com.entity.User;
/**
 * 对数据进行处理，拆，装数据
 * @author kone
 * 2017-1-12
 */
@Component(value="dealData2")
public class DealData<T> {
//	@Autowired
//	private Pagination pagination;
	
	public Pagination getPagination(List<T> entity,Pagination pagination){
		//pagination.setEachPage(15);//每页多少条
//		int size = entity.size();
//		pagination.setTotleSize(size);//设置总条数数
		int totelPage = pagination.getTotleSize()/pagination.getEachPage();
		if(pagination.getTotleSize()%pagination.getEachPage()>0){
			totelPage +=1;
		}
		pagination.setTotlePage(totelPage);//设置总页数
		
		pagination.setPage(pagination.getPage()+1);//设置当前页
		
		return pagination;
	}
	/**
	 * 处理grade数据，只返回年级名称和id
	 * @param grades
	 * @return
	 */
	public List<Grade> dealGradeData(List<Grade> grades){
		Grade grade;
		List<Grade> grades2 = new ArrayList<Grade>();
		for(int i=0;i<grades.size();i++){
			grade = new Grade();
			grade.setGradeName(grades.get(i).getGradeName());
			grade.setId(grades.get(i).getId());
			Department department=new Department();
			department.setDepartmentName(grades.get(i).getDepartment().getDepartmentName());
			grade.setDepartment(department);
			
			grades2.add(grade);
		}
		
		return grades2;
	}
	/**
	 * 处理Department数据，只返回年级名称和id
	 * @param departments
	 * @return
	 */
	public List<Department> dealDepartmentData(List<Department> departments){
		List<Department> department2 = new ArrayList<Department>();
		Department department;
		for(int i=0;i<departments.size();i++){
			department = new Department();
			department.setDepartmentName(departments.get(i).getDepartmentName());
			College college=new College();
			college.setId(departments.get(i).getCollege().getId());
			college.setCollegeName(departments.get(i).getCollege().getCollegeName());
			department.setCollege(college);
			department.setId(departments.get(i).getId());
			department2.add(department);
		}
		return department2;
	}
	
	/**
	 * 处理Specialty数据，只返回年级名称和id
	 * @param specialtys
	 * @return
	 */
	public List<Specialty> dealSpecialtyData(List<Specialty> specialtys){
		List<Specialty> specialtys2 = new ArrayList<Specialty>();
		Specialty specialty;
		for(int i=0;i<specialtys.size();i++){
			specialty = new Specialty();
			specialty.setSpecialtyName(specialtys.get(i).getSpecialtyName());
			specialty.setId(specialtys.get(i).getId());
			Grade grade=new Grade();
			grade.setId(specialtys.get(i).getGrade().getId());
			grade.setGradeName(specialtys.get(i).getGrade().getGradeName());
			specialty.setGrade(grade);
			
			specialtys2.add(specialty);
			
		}
		return specialtys2;
	}
	/**
	 * 处理Specialty数据，只返回年级名称和id
	 * @param specialtys
	 * @return
	 */
	public List<Direction> dealDirectionData(List<Direction> directions){
		List<Direction> directions2 = new ArrayList<Direction>();
		Direction direction;
		for(int i=0;i<directions.size();i++){
			direction = new Direction();
			direction.setDirectionName(directions.get(i).getDirectionName());
			direction.setId(directions.get(i).getId());
			Specialty specialty=new Specialty();
			specialty.setId(directions.get(i).getSpceialty().getId());
			specialty.setSpecialtyName(directions.get(i).getSpceialty().getSpecialtyName());
			direction.setSpceialty(specialty);
		
			directions2.add(direction);
			
		}
		return directions2;
	}
	/**
	 * 处理Clazz数据，只返回年级名称和id
	 * @param specialtys
	 * @return
	 */
	  public List<Clazz> dealClazzData( List<Clazz> clazzs){
		  List<Clazz> clazz2 = new ArrayList<Clazz>();
		  Clazz clazz;
		  for(int i=0;i<clazzs.size();i++){
			  clazz = new Clazz();
			  clazz.setClassName(clazzs.get(i).getClassName());
			  clazz.setId(clazzs.get(i).getId());
			  Direction direction=new Direction();
			  direction.setId(clazzs.get(i).getDirection().getId());
			  direction.setDirectionName(clazzs.get(i).getDirection().getDirectionName());
			  clazz.setDirection(direction);
			  
			  clazz2.add(clazz);
		  }
		return clazz2;
	  }
	  
	  public List<Direction> dealDirections(List<Specialty> specialtys ){
			for(int i=0;i<specialtys.size();i++){
				//List<Direction> directions = daoImpl.findBy("Direction", "specialtyId", String.valueOf(specialtys.get(i).getId()));
				
			}
			return null;
	  }
	  public List<Topics> dealTopics(List<Topics> topics){
			List<Topics>topics2=new ArrayList<>();
			Topics topic=null;
		  for(int i=0;i<topics.size();i++){
				topic=new Topics();
				topic.setId(topics.get(i).getId());
				topic.setTopicsName(topics.get(i).getTopicsName());
				topic.setEnableSelect(topics.get(i).getEnableSelect());
				topic.setIntentionNumber(topics.get(i).getIntentionNumber());
				topic.setSelectedStudent(topics.get(i).getSelectedStudent());
				topic.setTaskBookName(topics.get(i).getTaskBookName());
				topic.setIntroduce(topics.get(i).getIntroduce());
				topic.setState(topics.get(i).getState());
				Teacher teacher=new Teacher();
				teacher.setId(topics.get(i).getTeacher().getId());
				teacher.setName(topics.get(i).getTeacher().getName());
				topic.setTeacher(teacher);
				topics2.add(topic);
			}
			return topics2;
	  }
	  public List<Student> dealStudent(List<Student> students){
		  List<Student> students2=new ArrayList<Student>();
		  Student student;
		  for(int i=0;i<students.size();i++){
			  student=new Student();
			  student.setId(students.get(i).getId());
			  student.setNo(students.get(i).getNo());
			  student.setName(students.get(i).getName());
			  student.setQq(students.get(i).getQq());
			  student.setTelephone(students.get(i).getTelephone());
			  student.setEmail(students.get(i).getEmail());
			  student.setSex(students.get(i).getSex());
			  Clazz clazz=new Clazz();
			  clazz.setId(students.get(i).getClazz().getId());
			  clazz.setClassName(students.get(i).getClazz().getClassName());
			  Direction direction=new Direction();
			  direction.setId(students.get(i).getClazz().getDirection().getId());
			  direction.setDirectionName(students.get(i).getClazz().getDirection().getDirectionName());
			  clazz.setDirection(direction);
			  Specialty specialty=new Specialty();
			  specialty.setId(students.get(i).getClazz().getDirection().getSpceialty().getId());
			  specialty.setSpecialtyName(students.get(i).getClazz().getDirection().getSpceialty().getSpecialtyName());
			  direction.setSpceialty(specialty);
			  Grade grade=new Grade();
			  grade.setId(students.get(i).getClazz().getDirection().getSpceialty().getGrade().getId());
			  grade.setGradeName(students.get(i).getClazz().getDirection().getSpceialty().getGrade().getGradeName());
			  specialty.setGrade(grade);
			  Department department=new Department();
			  department.setDepartmentName(students.get(i).getClazz().getDirection().getSpceialty().getGrade().getDepartment().getDepartmentName());
			  grade.setDepartment(department);
			  College college=new College();
			  college.setCollegeName(students.get(i).getClazz().getDirection().getSpceialty().getGrade().getDepartment().getCollege().getCollegeName());
			  department.setCollege(college);
			  student.setClazz(clazz);
			  User user=new User();
			  user.setId(students.get(i).getUser().getId());
			  student.setUser(user);
			  students2.add(student);
		  }
		  return students2;
	  }
	  public List<Teacher> dealTeacher(List<Teacher> teachers){
		  List<Teacher> teachers2=new ArrayList<Teacher>();
		  Teacher teacher;
		  for(int i=0;i<teachers.size();i++){
			 teacher=new Teacher();
			 teacher.setId(teachers.get(i).getId());
			 teacher.setNo(teachers.get(i).getNo());
			 teacher.setName(teachers.get(i).getName());
			 teacher.setSex(teachers.get(i).getSex());
			 teacher.setQq(teachers.get(i).getQq());
			 teacher.setEmail(teachers.get(i).getEmail());
			 teacher.setTelephone(teachers.get(i).getTelephone());
			 teacher.setRemark(teachers.get(i).getRemark());
			 teacher.setPosition(teachers.get(i).getPosition());
			 Department department=new Department();
			 department.setDepartmentName(teachers.get(i).getDepartment().getDepartmentName());
			 department.setId(teachers.get(i).getDepartment().getId());
			 College college=new College();
			 college.setId(teachers.get(i).getDepartment().getCollege().getId());
			 college.setCollegeName( teachers.get(i).getDepartment().getCollege().getCollegeName());
			 department.setCollege(college);
			 teacher.setDepartment(department);
			 User user=new User();
			 user.setId(teachers.get(i).getUser().getId());
			 teacher.setUser(user);
			 teachers2.add(teacher);
		  }
		  return teachers2;
	  }
	  
	  
	  /**
	   * 通过设置的时间获取当前是第几次选题
	   * @param setting
	   * @return
	   */
	  public int getBatch(Setting setting){
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
//				当前时间
				Date now = new Date();
//				第一轮选题开始，结束时间
				Date oneStart = sdf.parse(setting.getOneSelectStartTime().replace("T"," ").concat(":00"));
				Date oneEnd = sdf.parse(setting.getOneSelectEndTimeOne().replace("T"," ").concat(":00"));
				
//				第二轮选题开始，结束时间
				Date twoStart = sdf.parse(setting.getTwoSelectStartTime().replace("T"," ").concat(":00"));
				Date twoEnd = sdf.parse(setting.getTwoSelectEndTimeOne().replace("T"," ").concat(":00"));
				
//				第三轮选题开始，结束时间
//				Date threeStart = sdf.parse(setting.getThreeSelectStartTime().replace("T"," ").concat(":00"));
//				Date threeEnd = sdf.parse(setting.getThreeSelectEndTimeOne().replace("T"," ").concat(":00"));
				
//				在第一轮选题时间之间
				if(now.getTime() > oneStart.getTime() && now.getTime() < oneEnd.getTime() ) {
					return 1;
					
//				在第二轮选题时间之间
				} else if(now.getTime() > twoStart.getTime() && now.getTime() < twoEnd.getTime() ) {
					return 2;
					
//				在第三轮选题时间之间
//				} else if(now.getTime() > threeStart.getTime() && now.getTime() < threeEnd.getTime() ) {
//					return 3;
//					
//				不是选题时间
				} else {
					return 0;
				}
			} catch (Exception e){}
			return 0;
	  }
	  
	  /**
	   * 判断时间段是否在当前时间里面
	   * @param startTime
	   * @param endTime
	   * @return
	   */
	  public boolean isNow(String startTime, String endTime) {
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  if((startTime != null|| startTime != "")&&(endTime != null|| endTime != "")) {
			  
			try {
//				当前时间
				Date now = new Date();
//				第一轮选题开始，结束时间
				Date oneStart = sdf.parse(startTime.replace("T"," ").concat(":00"));
				Date oneEnd = sdf.parse(endTime.replace("T"," ").concat(":00"));
				if(now.getTime() > oneStart.getTime() && now.getTime() < oneEnd.getTime() ) {
					return true;
				}
			} catch (Exception e){
				return false;
			}
		  }
		  return false;
	  }
	  
}
