package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.IGradeDao;
import com.entity.Grade;
@Repository
public class GradeDaoImpl implements IGradeDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	private String hql = "";
	public void setSession(Session session){
		this.session = session;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Grade> viewGrades(Long departmentId) {
		List<Grade> grades = null;
		hql = "SELECT new Grade(id, gradeName) "
				+ " FROM Grade as g "
				+ " WHERE g.department.id=:departmentId";
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
}
