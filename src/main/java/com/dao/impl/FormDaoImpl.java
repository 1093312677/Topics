package com.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.IFormDao;
import com.entity.Form;
@Repository
public class FormDaoImpl implements IFormDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	private String hql = "";
	public void setSession(Session session){
		this.session = session;
	}
	@Override
	public Form getStudentForm(Long studentId) {
		hql = "SELECT new Form(id,  openingReport,  interimReport,  interimEvalForm,reviewEvalForm,  reviewTable,  replyRecord, fileName)"
				+ " FROM "
				+ " Form as form "
				+ " WHERE"
				+ " form.student.id=:studentId";
		Form form = null;
		try{
			session = sessionFactory.openSession();
//			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setLong("studentId", studentId);
			query.setCacheable(true);
			form = (Form) query.uniqueResult();
//			session.getTransaction().commit();
		} catch(Exception e) {
			
		} finally {
			if(session.isOpen()) {
//				session.close();
			}
		}
		return form;
	}
	@Override
	public boolean updateForm(Form form) {
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(form);
			session.getTransaction().commit();
			return true;
		}catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			if(session.isOpen() ) {
				session.close();
			}
		}
	}
	@Override
	public boolean saveForm(Form form) {
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(form);
			session.getTransaction().commit();
			return true;
		}catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			if(session.isOpen() ) {
				session.close();
			}
		}
	}
	
	
}
