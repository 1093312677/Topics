package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dao.daoAdapter.DaoAdapter;
@Repository
public class SwapDaoImpl extends DaoAdapter{
	private Session session;
	private String hql = "";
	public void setSession(Session session){
		this.session = session;
	}

	@Override
	public boolean swapInDepart(String state, String id) {
		hql = "UPDATE Student SET swapInDepa = ? WHERE id=?";
		Query query = session.createQuery(hql);
		query.setString(0, state);
		query.setString(1, id);
		query.executeUpdate();
		return false;
	}
	
	@Override
	public boolean swapInTeacher(String state, String id) {
		hql = "UPDATE IntentionTopic SET swap = ? WHERE id=?";
		Query query = session.createQuery(hql);
		query.setString(0, state);
		query.setString(1, id);
		query.executeUpdate();
		return false;
	}
	@Override
	public boolean leavMessage(String id, String message) {
		hql = "UPDATE IntentionTopic SET message = ? WHERE id=?";
		Query query = session.createQuery(hql);
		query.setString(0, message);
		query.setString(1, id);
		query.executeUpdate();
		return false;
	}
	
	
	public List getTopicDirection(long directionId, int page, int eachPage) {
		hql = "select topics from Topics as topics join topics.directions as direc where direc.id=?";
		Query query = session.createQuery(hql);
		query.setLong(0, directionId);
		query.setFirstResult(page);
		query.setMaxResults(eachPage);
		return query.list();
		
	}
}
