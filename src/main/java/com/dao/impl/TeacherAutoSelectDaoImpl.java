package com.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ITeacherAutoSelectDao;
import com.entity.TeacherAutoSelect;
@Repository
public class TeacherAutoSelectDaoImpl implements ITeacherAutoSelectDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	private String hql = "";
	public void setSession(Session session){
		this.session = session;
	}
	@Override
	public TeacherAutoSelect getTeacherAutoSelect(Long gradeId, Long teacherId) {
		hql = "SELECT new TeacherAutoSelect(id, autoSelect) "
				+ " FROM TeacherAutoSelect as tas"
				+ " WHERE tas.teacher.id=:teacherId "
				+ " AND "
				+ " tas.grade.id=:gradeId";
		TeacherAutoSelect tas = null;
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setLong("teacherId", teacherId);
			query.setLong("gradeId", gradeId);
			tas = (TeacherAutoSelect) query.uniqueResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tas;
	}
	
	@Transactional
	@Override
	public boolean updateAutoSelect(int state, Long id) {
		hql = "UPDATE TeacherAutoSelect "
				+ " SET "
				+ " autoSelect=:state "
				+ " WHERE "
				+ " id=:id";
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setInteger("state", state);
			query.setLong("id", id);
			query.executeUpdate();
			
			return true;
		}catch(Exception e) {
			throw new ServiceException("error");
		}
	}
	@Transactional
	@Override
	public boolean saveTeacherAutoSelect(TeacherAutoSelect tas) {
		try{
			session = sessionFactory.getCurrentSession();
			session.save(tas);
			
			return true;
		}catch(Exception e) {
			throw new ServiceException("error");
		}
	}
	
}
