package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.dao.daoAdapter.DaoAdapter;
import com.entity.CheckViewGrade;

public class CourseDaoImpl extends DaoAdapter{
	private Session session;
	private String hql = "";
	public void setSession(Session session){
		this.session = session;
	}
	
	public List<CheckViewGrade> getcheckViewGrade(String gradeId, long teacherId) {
//		List<CheckViewGrade> checkViewGrade
		hql = "FROM CheckViewGrade WHERE gradeId=? AND teacherId = ?";
		Query query = session.createQuery(hql);
		query.setString(0, gradeId);
		query.setFloat(1, teacherId);
		
		return query.list();
	}

}
