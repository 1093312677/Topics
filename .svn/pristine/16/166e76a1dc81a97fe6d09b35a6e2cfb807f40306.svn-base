package com.service;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.GroupTimeAndPlace;
import com.dao.baseDaoFactory.BaseDAOFactory;
import com.dao.impl.CommonDaoImpl;
import com.dao.impl.GroupDaoImpl;
import com.dao.impl.TeacherDaoImpl;
import com.entity.Grade;
import com.entity.Group;
import com.entity.StuTeachGroup;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.TeacherGroup;
import com.entity.TimeAndPlace;
/**
 * 关于group的逻辑处理
 * @author kone
 *	2017.3.30
 */
@Service
public class GroupService {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	
	/**
	 * 关闭session
	 */
	public void colseSession(){
		if( session.isOpen() ) {
			session.close();
		}
	}
	
	/**
	 * 保存group
	 * @param group
	 * @return
	 */
	public boolean save(Group group){
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			GroupDaoImpl dao =  (GroupDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("GroupDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			保存分组
			dao.save(group);
			session.getTransaction().commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			if( session.isOpen() ) {
				session.close();
			}
		}
	}
	/**
	 * 查看group
	 * @param group
	 * @return
	 */
	public List<Group> viewGroup(String gradeId){
		List<Group> groups = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			GroupDaoImpl dao =  (GroupDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("GroupDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			
			groups = dao.view(gradeId);
			session.getTransaction().commit();
			
			return groups;
		}catch(Exception e){
			e.printStackTrace();
			return groups;
		} 
	}
	/**
	 * 删除group
	 * @param group
	 * @return
	 */
	public boolean deleteGroup(Group group){
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			CommonDaoImpl dao =  (CommonDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("CommonDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
			dao.delete(group);
			
			session.getTransaction().commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		} 
	}
	/**
	 * 删除group
	 * @param id
	 * @return
	 */
	public boolean cancelGroup(String id){
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			GroupDaoImpl dao =  (GroupDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("GroupDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
			dao.calcelGroup(id);
			session.getTransaction().commit();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} 
	}
	/**
	 * 通过 id 查找group
	 * @param id
	 * @return
	 */
	public List<Group> findById(String id){
		List<Group> groups = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			GroupDaoImpl dao =  (GroupDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("GroupDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
			groups = dao.findById(id);
			
			for(int i=0;i<groups.get(0).getTeacherGroup().size();i++) {
				groups.get(0).getTeacherGroup().get(i).getTeacher().getName();
			}
			session.getTransaction().commit();
			
			return groups;
		}catch(Exception e){
			e.printStackTrace();
			return groups;
		} 
	}
	
	/**
	 * 更新group
	 * @param id
	 * @return
	 */
	public boolean updateGroup(Group group){
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			CommonDaoImpl dao =  (CommonDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("CommonDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			
			dao.update(group);
			session.getTransaction().commit();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} 
	}
	
	/**
	 * 设置答辩组之前的查看
	 * @param group
	 * @return
	 */
	public List<Group> setViewGroup(String gradeId){
		List<Group> groups = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			GroupDaoImpl dao =  (GroupDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("GroupDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			
			groups = dao.view(gradeId);
			session.getTransaction().commit();
			for(int i=0;i<groups.size();i++) {
				for(int j=0;j<groups.size();j++) {
					if( groups.get(j).getAnsGroup() != null){
						if(groups.get(j).getAnsGroup().getId() == groups.get(i).getId()) {
//							System.out.println("--------------------"+groups.get(j).getAnsGroup().getId());
							groups.get(i).setAnswerGroup(1);
						}
					}
					
				}
			}
			
			return groups;
		}catch(Exception e){
			e.printStackTrace();
			return groups;
		} 
	}
	
	/**
	 * 给组添加老师，先查看老师
	 * @param id
	 * @return
	 */
	public List<Teacher> setViewTeacherGroup(String id,String gradeId){
		List<Teacher> teachers = null;
		List<Teacher> teachers2 = new ArrayList<Teacher>();
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			TeacherDaoImpl dao =  (TeacherDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("teacher");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			
			teachers = dao.view(id, 0, 100000);
			
			
//			去掉已经在小组的教师
			int flag = 0;
			for(int i=0;i<teachers.size();i++) {
				if(teachers.get(i).getTeacherGroup() != null){
					for(int j=0;j<teachers.get(i).getTeacherGroup().size();j++){
						if(teachers.get(i).getTeacherGroup().get(j).getGroup().getGrade().getId() == Integer.valueOf(gradeId)) {
//							如果老师在本年级已经分配小组，标记为已分配
							flag = 1;
						}
					}
				}
//				老师未分配，加入可以分配列表
				if(flag == 0){
					teachers2.add(teachers.get(i));
				} else {
					flag = 0;
				}
				
				
			}
			session.getTransaction().commit();
			return teachers2;
		}catch(Exception e){
			e.printStackTrace();
			return teachers2;
		} 
	}
	/**
	 * 给组添加老师
	 * @param id
	 * @return
	 */
	public boolean setTeacherGroup(long []teacherId,String groupId, long teacherHeaderId){
		Teacher teacher = null;
		
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取Dao
			CommonDaoImpl dao =  (CommonDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("CommonDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
			
			Group group = new Group();
			group.setId(Integer.valueOf(groupId));
			
			TeacherGroup teacherGroup = null;
			for(int i=0;i<teacherId.length;i++) {
				teacher = new Teacher();
				teacher.setId(teacherId[i]);
				
				teacherGroup = new TeacherGroup();
				teacherGroup.setGroup(group);
				teacherGroup.setTeacher(teacher);
//				设置组长
				if(teacherHeaderId == teacherId[i]) {
					teacherGroup.setIsLeader(1);
				}
				
				dao.save(teacherGroup);
				
				if( i%20 == 0) {
					session.flush();
					session.clear();
				}
			}
		 
			
			
			session.getTransaction().commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} 
	}
	
	/**
	 * 修改组长
	 * @param id
	 * @return
	 */
	public boolean changeTeacherLeader(int state,int id) {
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			GroupDaoImpl dao =  (GroupDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("GroupDao");
			
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			
			dao.changeLeader(state, id);
			session.getTransaction().commit();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} 
	}
	/**
	 * 删除teacher group
	 * @param group
	 * @return
	 */
	public boolean deleteTeacherGroup(TeacherGroup teacherGroup){
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			CommonDaoImpl dao =  (CommonDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("CommonDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			
			dao.delete(teacherGroup);
			session.getTransaction().commit();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} 
	}
	
	/**
	 * 查看分组成员
	 * @param group
	 * @return
	 */
	public List<TeacherGroup> viewGroupMember(long gradeId, long teacherId){
		List<TeacherGroup> teacherGroup2= new ArrayList<TeacherGroup>();
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			GroupDaoImpl dao =  (GroupDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("GroupDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			
			List<TeacherGroup> teacherGroup= null;
			teacherGroup = dao.findTeacherGroup(String.valueOf(teacherId));
			
			for(int i=0;i<teacherGroup.size();i++){
//				判断是否是组长，同时是选择的年级
				if(teacherGroup.get(i).getIsLeader() == 1 && teacherGroup.get(i).getGroup().getGrade().getId() == gradeId) {
					teacherGroup2 = teacherGroup.get(i).getGroup().getTeacherGroup();
					for(int j=0;j<teacherGroup.get(i).getGroup().getTeacherGroup().size();j++){
						teacherGroup.get(i).getGroup().getTeacherGroup().get(j).getTeacher().getName();
					}
				}
			}
			session.getTransaction().commit();
			
			return teacherGroup2;
		}catch(Exception e){
			e.printStackTrace();
			return teacherGroup2;
		} 
	}
	
	/**
	 * 分配学生
	 * @param group
	 * @return
	 */
	public List<Student> setStudentGroup(String groupId, long gradeId){
		List<Student> students = new ArrayList<Student>();
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			GroupDaoImpl dao =  (GroupDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("GroupDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			
			List<Group> group= null;
//			group.get(0).getTeacherGroup().get(0).getTeacher().getTopics().get(0).getStudents().get(0).getName();
			group = dao.findById(groupId);
			
//			对方答辩组的所有老师
			for(int i=0;i<group.get(0).getTeacherGroup().size();i++){
//				对方答辩组老师出的题目
				int topicsSize = group.get(0).getTeacherGroup().get(i).getTeacher().getTopics().size();
//				获取当前学生
				for(int j=0;j<topicsSize;j++){
//					答辩组老师出的题目是当前年级的
					if( group.get(0).getTeacherGroup().get(i).getTeacher().getTopics().get(j).getGrade().getId() == gradeId) {
						List<Student> temp = group.get(0).getTeacherGroup().get(i).getTeacher().getTopics().get(j).getStudents();
						int studentSize = temp.size();
						for(int k=0;k<studentSize;k++) {
//							如果学生已经存在分组，去除
							if(temp.get(k).getStuTeachGroup() == null)
								students.add(temp.get(k));
						}
					}
				}
				
			}
			session.getTransaction().commit();
			
			return students;
		}catch(Exception e){
			e.printStackTrace();
			return students;
		} 
	}
	/**
	 * 保存学生分组
	 * @param gradeId
	 * @param teacherId
	 * @param studentId
	 * @return
	 */
	public boolean setStuTeachGroup(long gradeId, long teacherId, int[] studentId){
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			CommonDaoImpl dao =  (CommonDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("CommonDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			保存
			Teacher teacher = new Teacher();
			teacher.setId(teacherId);
			Grade grade = new Grade();
			grade.setId(gradeId);
			Student student = null;
			StuTeachGroup stuTeachGroup = null;
			
			for(int i=0;i<studentId.length;i++){
				student = new Student();
				student.setId(studentId[i]);
				
				stuTeachGroup = new StuTeachGroup();
				stuTeachGroup.setStudent(student);
				stuTeachGroup.setGrade(grade);
				stuTeachGroup.setTeacher(teacher);
				dao.save(stuTeachGroup);
			}
			session.getTransaction().commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			if( session.isOpen() ) {
				session.close();
			}
		}
	}
	
	/**
	 * 查找老师对应的学生
	 * @param gradeId
	 * @param teacherId
	 * @return
	 */
	public List<StuTeachGroup> viewStudentGroup(long gradeId, long teacherId){
		List<StuTeachGroup> stuTeachGroups= new ArrayList<StuTeachGroup>();
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			GroupDaoImpl dao =  (GroupDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("GroupDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			
			stuTeachGroups = dao.findStuTeachGroup(gradeId, teacherId);
			
			session.getTransaction().commit();
			
			return stuTeachGroups;
		}catch(Exception e){
			e.printStackTrace();
			return stuTeachGroups;
		} 
	}
	/**
	 * 将学生从老师处移除
	 * @param id
	 * @return
	 */
	public boolean deleteStudentGroup(long id){
		StuTeachGroup stuTeachGroup = new StuTeachGroup();
		stuTeachGroup.setId(id);
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			CommonDaoImpl dao =  (CommonDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("CommonDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			
			dao.delete(stuTeachGroup);
			session.getTransaction().commit();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} 
	}
	
	/**
	 * 学生查看分组
	 * @param gradeId
	 * @param teacherId
	 * @return
	 */
	public Group studentGroup(long id, long gradeId){
		List<StuTeachGroup> stuTeachGroups = new ArrayList<StuTeachGroup>();
		Group group = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			GroupDaoImpl dao =  (GroupDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("GroupDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			
			stuTeachGroups = dao.findBy("StuTeachGroup", "studentId", String.valueOf(id));
			
			
			if(stuTeachGroups.size() > 0) {
				int size = stuTeachGroups.get(0).getTeacher().getTeacherGroup().size();
				for(int i=0;i<size;i++) {
//					判断当前老师是否是当前年级老师
					if(stuTeachGroups.get(0).getTeacher().getTeacherGroup().get(i).getGroup().getGrade().getId() == gradeId){
						group = stuTeachGroups.get(0).getTeacher().getTeacherGroup().get(i).getGroup();
						for(int j=0;j<group.getTeacherGroup().size();j++){
							group.getTeacherGroup().get(j);
						}
					}
				}
			}
			
			
			session.getTransaction().commit();
			
			return group;
		}catch(Exception e){
			e.printStackTrace();
			return group;
		} 
	}
	
	/**
	 * 查看分组答辩时间和地点
	 * @param group
	 * @return
	 */
	public GroupTimeAndPlace viewTimeAndPlace(long gradeId, long teacherId){
		Group group = null;
		GroupTimeAndPlace groupTimeAndPlace = new GroupTimeAndPlace();
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			GroupDaoImpl dao =  (GroupDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("GroupDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			
			List<TeacherGroup> teacherGroup= null;
			teacherGroup = dao.findTeacherGroup(String.valueOf(teacherId));
			
			for(int i=0;i<teacherGroup.size();i++){
//				判断是否是组长，同时是选择的年级
				if(teacherGroup.get(i).getGroup().getGrade().getId() == gradeId) {
					if(teacherGroup.get(i).getIsLeader() == 1) {
						groupTimeAndPlace.setLeader(1);
					} else {
						groupTimeAndPlace.setLeader(0);
					}
					group = teacherGroup.get(i).getGroup();
					groupTimeAndPlace.setGroup(group);
				}
			}
			session.getTransaction().commit();
			
			return groupTimeAndPlace;
		}catch(Exception e){
			e.printStackTrace();
			return groupTimeAndPlace;
		} 
	}
	/**
	 * 增加或者修改答辩时间和地点
	 * @param timeAndPlace
	 * @param groupId
	 * @param check
	 * @return
	 */
	public boolean updateTimeAndPlace(TimeAndPlace timeAndPlace, int groupId, int check){
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			CommonDaoImpl dao =  (CommonDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("CommonDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
			Group group = new Group();
			group.setId(groupId);
			timeAndPlace.setGroup(group);
			
			if(check == 0) {
				dao.save(timeAndPlace);
			} else {
				dao.update(timeAndPlace);
			}
			
			session.getTransaction().commit();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} 
	}
	
	/**
	 * 导出教师分组情况
	 * @param gradeId
	 * @param departmentId
	 * @return
	 */
	public HSSFWorkbook exportTeacherGroup(String gradeId, String departmentId) {
		 List<Teacher> teachers = null;
		 
		//创建HSSFWorkbook对象(excel的文档对象)
	     HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("教师分组表");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1=sheet.createRow(0);
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell=row1.createCell(0);
		      //设置单元格内容
		cell.setCellValue("教师分组表");
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,12));
		
		//在sheet里创建第二行
		HSSFRow row2=sheet.createRow(1);    
       //创建单元格并设置单元格内容
	    row2.createCell(0).setCellValue("工号");
	    row2.createCell(1).setCellValue("姓名");    
	    row2.createCell(2).setCellValue("性别");
		row2.createCell(3).setCellValue("电话");  
		row2.createCell(4).setCellValue("所在组"); 
		row2.createCell(5).setCellValue("答辩时间"); 
		row2.createCell(6).setCellValue("答辩地点"); 
		
		HSSFRow row = null;
		 
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			CommonDaoImpl dao =  (CommonDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("CommonDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
			teachers = dao.findBy("Teacher", "departmentId", departmentId);
			
			for(int i=0;i<teachers.size();i++) {
				if(teachers.get(i).getTopics() != null) {
					row = sheet.createRow(i+2);
					row.createCell(0).setCellValue(teachers.get(i).getNo());
					row.createCell(1).setCellValue(teachers.get(i).getName());
					row.createCell(2).setCellValue(teachers.get(i).getSex());
					row.createCell(3).setCellValue(teachers.get(i).getTelephone());
					for(int j=0;j<teachers.get(i).getTeacherGroup().size();j++) {
						if(teachers.get(i).getTeacherGroup().get(j).getGroup().getGrade().getId() == Long.valueOf(gradeId)) {
							row.createCell(4).setCellValue(teachers.get(i).getTeacherGroup().get(j).getGroup().getGroupName());
							row.createCell(5).setCellValue(teachers.get(i).getTeacherGroup().get(j).getGroup().getTimeAndPlace().getTime());
							row.createCell(6).setCellValue(teachers.get(i).getTeacherGroup().get(j).getGroup().getTimeAndPlace().getPlace());
							
						}
					}
				 }
			}
			session.getTransaction().commit();
			return wb;
		}catch(Exception e){
			e.printStackTrace();
			return wb;
		} 
	}
	
	/**
	 * 导出学生分组情况
	 * @param gradeId
	 * @param departmentId
	 * @return
	 */
	public HSSFWorkbook exportStudentGroup(String gradeId) {
		 List<Student> students = null;
		 
		//创建HSSFWorkbook对象(excel的文档对象)
	     HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("学生分组表");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1=sheet.createRow(0);
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell=row1.createCell(0);
		      //设置单元格内容
		cell.setCellValue("学生分组表");
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,12));
		
		//在sheet里创建第二行
		HSSFRow row2=sheet.createRow(1);    
       //创建单元格并设置单元格内容
	    row2.createCell(0).setCellValue("学号");
	    row2.createCell(1).setCellValue("姓名");    
	    row2.createCell(2).setCellValue("性别");
		row2.createCell(3).setCellValue("电话"); 
		row2.createCell(4).setCellValue("班级");
		row2.createCell(5).setCellValue("方向"); 
		row2.createCell(6).setCellValue("专业"); 
		row2.createCell(7).setCellValue("年级"); 
		row2.createCell(8).setCellValue("所在分组"); 
		row2.createCell(9).setCellValue("答辩时间"); 
		row2.createCell(10).setCellValue("答辩地点"); 
		
		HSSFRow row = null;
		 
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			CommonDaoImpl dao =  (CommonDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("CommonDao");
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
			students = dao.viewStudents(gradeId, 0, 10000);
			
			for(int i=0;i<students.size();i++) {
				row = sheet.createRow(i+2);
				row.createCell(0).setCellValue(students.get(i).getNo());
				row.createCell(1).setCellValue(students.get(i).getName());
				row.createCell(2).setCellValue(students.get(i).getSex());
				row.createCell(3).setCellValue(students.get(i).getTelephone());
				row.createCell(4).setCellValue(students.get(i).getClazz().getClassName());
				row.createCell(5).setCellValue(students.get(i).getClazz().getDirection().getDirectionName());
				row.createCell(6).setCellValue(students.get(i).getClazz().getDirection().getSpceialty().getSpecialtyName());
				row.createCell(7).setCellValue(students.get(i).getClazz().getDirection().getSpceialty().getGrade().getGradeName());
				if(students.get(i).getStuTeachGroup() != null) {
					for(int j=0;j<students.get(i).getStuTeachGroup().getTeacher().getTeacherGroup().size();j++) {
						if(students.get(i).getStuTeachGroup().getTeacher().getTeacherGroup().get(j).getGroup().getGrade().getId() == Long.valueOf(gradeId)) {
							row.createCell(8).setCellValue(students.get(i).getStuTeachGroup().getTeacher().getTeacherGroup().get(j).getGroup().getGroupName());
							row.createCell(9).setCellValue(students.get(i).getStuTeachGroup().getTeacher().getTeacherGroup().get(j).getGroup().getTimeAndPlace().getTime());
							row.createCell(10).setCellValue(students.get(i).getStuTeachGroup().getTeacher().getTeacherGroup().get(j).getGroup().getTimeAndPlace().getPlace());
							
						}
					}
				}
				
			}
			session.getTransaction().commit();
			return wb;
		}catch(Exception e){
			e.printStackTrace();
			return wb;
		} 
		
	}
	
}
