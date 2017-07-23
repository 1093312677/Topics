package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.dao.daoAdapter.DaoAdapter;
import com.entity.Group;

public class TeacherDaoImpl<Teacher> extends DaoAdapter<Teacher> {
	private Session session;
	private String hql = "";
	public void setSession(Session session){
		this.session = session;
	}
	@Override
	public List<Teacher> view(String id, int page, int eachPage) {
		hql ="FROM Teacher WHERE departmentId="+id;
		Query query = session.createQuery(hql);
		query.setFirstResult(page);
		query.setMaxResults(eachPage);
		@SuppressWarnings("unchecked")
		List<Teacher> teachers = query.list();
		return teachers;
	}
//	public List<Group> view(String gradeId) {
//		List<Group> entitys;
//		hql = "FROM Group WHERE gradeId = "+gradeId;
//		entitys = session.createQuery(hql).list();
//		
//		return entitys;
//	}
	
	@Override
	public int getTeacherNum(long departmentId) {
		hql = "SELECT COUNT(*) FROM Teacher where departmentId = ?";
		Query query = session.createQuery(hql);
		query.setLong(0, departmentId);
		((Number)query.uniqueResult()).intValue();
		return ((Number)query.uniqueResult()).intValue();
	}

	@Override
	public List getTeacherStuNum(String gradeId, long teacherId) {
		hql ="FROM LimitNumber WHERE gradeId = ? AND teacherId = ?";
		Query query = session.createQuery(hql);
		query.setString(0, gradeId);
		query.setLong(1, teacherId);
		return query.list();
	}
}
