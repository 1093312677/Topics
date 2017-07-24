package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.ITeacherDao;
import com.entity.Grade;
import com.entity.Teacher;

@Repository
public class TeachersDaoImpl implements ITeacherDao{
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private String hql;
	
	
	public void closeSession(){
		if(session.isOpen()) {
			session.close();
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Grade> viewGrades(Long departmentId) {
		List<Grade> grades = null;
		hql = "SELECT new Grade(id, gradeName) "
				+ " FROM Grade as g "
				+ " WHERE g.department.id=:departmentId";
		try{
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			Query query = session.createQuery(hql);
			query.setCacheable(true);
			query.setLong("departmentId", departmentId);
			grades = query.list();
			session.getTransaction().commit();
			
			return grades;
		}catch(Exception e){
			return grades;
		} finally{
			if(session.isOpen()) {
				session.close();
			}
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
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			Query query = session.createQuery(hql);
			query.setCacheable(true);
			query.setLong("departmentId", departmentId);
			query.setFirstResult(num*size);
			query.setMaxResults(size);
			teachers = query.list();
			session.getTransaction().commit();
			
			return teachers;
		}catch(Exception e){
			return teachers;
		} finally{
			if(session.isOpen()) {
				session.close();
			}
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
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			Query query = session.createQuery(hql);
			query.setCacheable(true);
			query.setLong("departmentId", departmentId);
			count = ((Number)query.uniqueResult()).intValue();
			session.getTransaction().commit();
			
			return count;
		}catch(Exception e){
			return count;
		} finally{
			if(session.isOpen()) {
				session.close();
			}
		}
	}


	@Override
	public boolean updateTopicState(Long topicId, int state) {
		hql = "UPDATE Topics"
				+ " SET state=:state "
				+ " WHERE "
				+ " id=:topicId";
		try{
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			Query query = session.createQuery(hql);
			query.setLong("topicId", topicId);
			query.setLong("state", state);
			query.executeUpdate();
			session.getTransaction().commit();
			
			return true;
		}catch(Exception e){
			return false;
		} finally{
			if(session.isOpen()) {
				session.close();
			}
		}
	}
	
	
}
