package com.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.ISettingDao;
import com.entity.Setting;

@Repository
public class SettingDaoImpl implements ISettingDao{
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private String hql;
	
	
	public void closeSession(){
		if(session.isOpen()) {
			session.close();
		}
	}
	@Override
	public Setting getSetting(Long gradeId) {
		Setting setting = null;
		hql = "SELECT setting FROM Setting as setting "
				+ " WHERE setting.grade.id=:gradeId";
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setLong("gradeId", gradeId);
			setting = (Setting) query.uniqueResult();
		}catch(Exception e){
			
		}
		return setting;
	}
	
}
