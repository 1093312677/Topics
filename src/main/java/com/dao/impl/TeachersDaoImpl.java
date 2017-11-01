package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ITeacherDao;
import com.entity.Grade;
import com.entity.Teacher;
import com.entity.Topics;

@Repository
public class TeachersDaoImpl implements ITeacherDao{
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private String hql;
	
	
	public void closeSession(){
//		if(session.isOpen()) {
//			session.close();
//		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Grade> viewGrades(Long departmentId) {
		List<Grade> grades = null;
		hql = "SELECT new Grade(id, gradeName) "
				+ " FROM Grade as g "
				+ " WHERE g.department.id=:departmentId"
				+ " ORDER BY id DESC";
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setCacheable(true);
			query.setLong("departmentId", departmentId);
			grades = query.list();
			
			return grades;
		}catch(Exception e){
			return grades;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> viewTeachers(Long departmentId, Integer num, Integer size) {
		List<Teacher> teachers = null;
		hql = "SELECT teacher FROM Teacher as teacher"
				+ " WHERE "
				+ " teacher.department.id=:departmentId"
				+ " AND "
				+ " teacher.user.privilege = 3";
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setCacheable(true);
			query.setLong("departmentId", departmentId);
			query.setFirstResult(num*size);
			query.setMaxResults(size);
			teachers = query.list();
			
			return teachers;
		}catch(Exception e){
			return teachers;
		}
	}


	@Override
	public Integer getTeachersCount(Long departmentId) {
		hql = "SELECT COUNT(*) FROM Teacher as teacher"
				+ " WHERE "
				+ " teacher.department.id=:departmentId"
				+ " AND "
				+ " teacher.user.privilege = 3";
		Integer count = 0;
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setCacheable(true);
			query.setLong("departmentId", departmentId);
			count = ((Number)query.uniqueResult()).intValue();
			
			return count;
		}catch(Exception e){
			return count;
		}
	}

	@Transactional
	@Override
	public boolean updateTopicState(Long topicId, int state) {
		hql = "UPDATE Topics"
				+ " SET state=:state "
				+ " WHERE "
				+ " id=:topicId";
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setLong("topicId", topicId);
			query.setLong("state", state);
			query.executeUpdate();
			
			return true;
		}catch(Exception e){
			throw new ServiceException("error");
		}
	}


	@Override
	public Topics getTopicIsSelect(Long topicId) {
		hql = "SELECT new Topics( id,  topicsName,  selectedStudent,  enableSelect)"
				+ " FROM "
				+ " Topics "
				+ " WHERE "
				+ " id =:id";
		Topics topic = null;
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setLong("id", topicId);
			topic = (Topics) query.uniqueResult();
			return topic;
		}catch(Exception e){
			return topic;
		} 
	}

	@Transactional
	@Override
	public boolean confirmSelect(Long topicId, Long studentId) {
		hql = "UPDATE "
				+ " Topics "
				+ " SET "
				+ " selectedStudent = selectedStudent+1 "
				+ " WHERE "
				+ " id="+topicId;
		String hql2 = "UPDATE "
				+ " student"
				+ " SET"
				+ "  topicsId = "+topicId
				+ " WHERE "
				+ " id="+studentId;
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			session.createSQLQuery(hql2).executeUpdate();
			return true;
		}catch(Exception e){
			throw new ServiceException("error");
		}
		
	}


	@Transactional
	@Override
	public boolean noAuditTopic(Long topicId, String reason) {
		hql = "UPDATE Topics"
				+ " SET state=3"
				+ " ,reason=:reason "
				+ " WHERE "
				+ " id=:topicId";
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setLong("topicId", topicId);
			query.setString("reason", reason);
			query.executeUpdate();
			
			return true;
		}catch(Exception e){
			throw new ServiceException("error");
		}
	}
	
	
}
