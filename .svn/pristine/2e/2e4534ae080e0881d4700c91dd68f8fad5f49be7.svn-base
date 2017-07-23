package com.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.impl.CommonDaoImpl;
import com.dao.impl.TeacherDaoImpl;
import com.entity.Grade;
import com.entity.LimitNumber;
import com.entity.Teacher;

/**
 * 关于题目人数限制的逻辑处理
 * @author kone
 *	2017.4.4
 */
@Service
public class LimitService {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	@Autowired
	private CommonDaoImpl commonDaoImpl;
	@Autowired
	private TeacherDaoImpl<Teacher> teacherDaoImpl;
	
	/**
	 * 设置教师可选人数
	 * @param teacherId
	 * @param gradeId
	 * @param number
	 * @return
	 */
	public int setNumber(long teacherId, long gradeId, int number){
		Teacher teacher = new Teacher();
		teacher.setId(teacherId);
		
		Grade grade = new Grade();
		grade.setId(gradeId);
		
		LimitNumber limit = new LimitNumber();
		limit.setTeacher(teacher);
		limit.setGrade(grade);
		limit.setNumber(number);
		
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			传递session保证是同一个session进行事务处理
			commonDaoImpl.setSession(session); 
			commonDaoImpl.save(limit);
			session.getTransaction().commit();
			return 1;
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 更新数量
	 * @param number
	 * @param limitId
	 * @return
	 */
	public int updateNumber(int number, String limitId) {
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			传递session保证是同一个session进行事务处理
			commonDaoImpl.setSession(session); 
			
			List<LimitNumber> limits = commonDaoImpl.findBy("LimitNumber", "id", limitId);
			if(limits.size() > 0) {
				LimitNumber limit = limits.get(0);
				limit.setNumber(number);
				commonDaoImpl.update(limit);
			}
			
			session.getTransaction().commit();
			return 1;
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
			return 0;
		}
		
	}
	
	/**
	 * 查看教师限制人数
	 * @param departmentId
	 * @param page
	 * @param eachPage
	 * @return
	 */
	public List<Teacher> viewTeacher(String gradeId, String departmentId, int page, int eachPage){
		List<Teacher> teachers = null;
		List<LimitNumber> limitNumbers = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			传递session保证是同一个session进行事务处理
			teacherDaoImpl.setSession(session); 
			teachers = teacherDaoImpl.view(departmentId, page, eachPage);
			for(int i=0;i<teachers.size();i++) {
				for(int j=0;j<teachers.get(i).getLimitNumbers().size();j++) {
					teachers.get(i).getLimitNumbers().get(j).getId();
					teachers.get(i).getLimitNumbers().get(j).getNumber();
//					判断是否是当前年级
					
					if(teachers.get(i).getLimitNumbers().get(j).getGrade().getId() == Long.valueOf(gradeId)) {
						limitNumbers = new ArrayList<LimitNumber>();
						limitNumbers.add(teachers.get(i).getLimitNumbers().get(j));
						teachers.get(i).setLimitNumbers(limitNumbers);
					} else {
						limitNumbers = new ArrayList<LimitNumber>();
						teachers.get(i).setLimitNumbers(limitNumbers);
					}
				}
			}
			session.getTransaction().commit();
			
			return teachers;
		}catch(Exception e){
			session.getTransaction().commit();
			e.printStackTrace();
			return teachers;
		} 
	}
	/**
	 * 获取教师总人数
	 * @param departmentId
	 * @return
	 */
	public int getTeacherCount (long departmentId) {
		int count = 0;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			传递session保证是同一个session进行事务处理
			teacherDaoImpl.setSession(session); 
			count = teacherDaoImpl.getTeacherNum(departmentId);
			session.getTransaction().commit();
			
			return count;
		}catch(Exception e){
			session.getTransaction().commit();
			e.printStackTrace();
			return count;
		} 
	}
	/**
	 * 获取老师在该年级下的指导学生认识
	 * @param gradeId
	 * @param teacherId
	 * @return
	 */
	public int getTeacherNum (String gradeId, long teacherId) {
		List<LimitNumber> limitNumber = null;
		int count = 0;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			传递session保证是同一个session进行事务处理
			teacherDaoImpl.setSession(session); 
			limitNumber = teacherDaoImpl.getTeacherStuNum(gradeId, teacherId);
			if(limitNumber.size() > 0 ) {
				count = limitNumber.get(0).getNumber() - limitNumber.get(0).getAlreadyNumber();
			}
			if(count < 0) {
				count  = 0;
			}
			return count;
		}catch(Exception e){
			session.getTransaction().commit();
			e.printStackTrace();
			return count;
		} 
	}
	
}
