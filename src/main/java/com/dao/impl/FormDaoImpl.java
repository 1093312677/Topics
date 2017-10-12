package com.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.spi.ServiceException;
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
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setLong("studentId", studentId);
			query.setCacheable(true);
			form = (Form) query.uniqueResult();
		} catch(Exception e) {
			
		} 
		return form;
	}
	@Override
	public boolean updateForm(Form form) {
		try{
			session = sessionFactory.getCurrentSession();
			session.update(form);
			return true;
		}catch(Exception e) {
			throw new ServiceException("error");
		} 
	}
	@Override
	public boolean saveForm(Form form) {
		try{
			session = sessionFactory.getCurrentSession();
			session.save(form);
			return true;
		}catch(Exception e) {
			throw new ServiceException("error");
		}
	}
	
	
}
