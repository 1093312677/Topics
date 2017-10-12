package com.dao.impl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.IIntentionDao;
import com.entity.IntentionTopic;

@Repository
public class IntentionDaoImpl implements IIntentionDao{
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private String hql;
	
	
	public void closeSession(){
		if(session.isOpen()) {
			session.close();
		}
	}
	@Transactional
	@Override
	public boolean saveIntention(IntentionTopic intentionTopic) {
		try{
			session = sessionFactory.getCurrentSession();
			session.save(intentionTopic);
			return true;
		}catch(Exception e){
			throw new ServiceException("error");
		}
	}
	
	@Transactional
	@Override
	public boolean choiceDeleteIntention(IntentionTopic intentionTopic) {
		hql = "DELETE FROM IntentionTopic as intent"
				+ " WHERE batch=:batch "
				+ " AND choice=:choice "
				+ " AND intent.student.id=:studentId";
		
		String hql2 = "DELETE FROM IntentionTopic as intent"
				+ " WHERE batch=:batch1 "
				+ " AND intent.student.id=:studentId1 "
				+ " AND intent.topic.id=:topicId";
		try{
			session = sessionFactory.getCurrentSession();
//			先删除同批次志愿
			Query query = session.createQuery(hql);
			query.setInteger("batch", intentionTopic.getBatch());
			query.setInteger("choice", intentionTopic.getChoice());
			query.setLong("studentId", intentionTopic.getStudent().getId());
			query.executeUpdate();
//			再删除同批次同题目
			Query query2 = session.createQuery(hql2);
			query2.setInteger("batch1", intentionTopic.getBatch());
			query2.setLong("studentId1", intentionTopic.getStudent().getId());
			query2.setLong("topicId", intentionTopic.getTopic().getId());
			query2.executeUpdate();
//			最后增加志愿
			session.save(intentionTopic);
			
			return true;
		}catch(Exception e){
			throw new ServiceException("error");
		}
	}
	
}
