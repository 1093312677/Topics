package com.guo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.entity.Clazz;
import com.entity.College;
import com.guo.dao.ICollegeDao;
@Repository(value="collegeDao1")
public class CollegeDao extends BaseDao implements ICollegeDao {
	Session session;
	@Override
	public List<College> inspection(String collegeName) {
		List<College> colleges=new ArrayList<>();
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from College college where college.collegeName=:name";
			Query query=session.createQuery(hql);
			query.setString("name", collegeName);
			colleges=(List<College>)query.list();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		} 
		return colleges;
	}

	@Override
	public void cloesSession() {
		if(session.isOpen()) session.close();
	}

}
