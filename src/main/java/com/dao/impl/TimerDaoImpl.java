package com.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.CourseAndGrade;
import com.entity.Grade;


@Repository
public class TimerDaoImpl {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	
	private String hql;
	
	/**
	 * close session
	 */
	public void closeSession(){
//		if(session.isOpen())
//			session.close();
	}
	
	public long findMaxGrade() {
		hql = "select max(id) from Grade";
		long id = 0;
		try{
			session = sessionFactory.getCurrentSession();
			id = (Long) session.createQuery(hql).uniqueResult();
			return id;
		}catch(Exception e){
			return id;
		}
	}
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public List<Grade> getGrade() {
		List<Grade> grades = new ArrayList<Grade>();
		hql = "From Grade";
		try{
//			session = sessionFactory.openSession();
			grades = session.createQuery(hql).list();
			return grades;
		}catch(Exception e){
			return grades;
		} 
	}
	
	public void setStudentTopic(long studentId, long topicId) {
//		session = sessionFactory.getCurrentSession();
//		session.beginTransaction();
		hql ="update Student set topicsId = "+topicId+" where id="+studentId;
		session.createQuery(hql).executeUpdate();
//		session.getTransaction().commit();
	}
	/**
	 * 更新年级上遍历的次数
	 * @param select
	 */
	public void updateTopicTimes(String select) {
		hql = "update Grade set "+select+" = "+select+"+1";
		session.createQuery(hql).executeUpdate();
	}
	
	public List<CourseAndGrade> findCourseAndGrade(String no) {
		hql = "from CourseAndGrade where no ="+no;
		return session.createQuery(hql).list();
	}
	
	/**
	 * 更新题目选择人数
	 * @param gradeId
	 */
	public void updateTopicSelected(long gradeId) {
		hql = "update Topics set selectedStudent = selectedStudent + 1 where id="+gradeId;
		session.createQuery(hql).executeUpdate();
	}
}
