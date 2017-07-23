package com.guo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.entity.Clazz;
import com.entity.Topics;
import com.guo.dao.ITopicDao;

@Repository(value="topicDao1")
public class TopicDao extends BaseDao implements ITopicDao {
	Session session;
	@Override
	public Topics geTopic(long id) {
		Topics topics=new Topics();
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Topics t where t.id=:id";
			Query query=session.createQuery(hql);
			query.setLong("id", id);
			topics=(Topics) query.uniqueResult();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return topics;
	}

	@Override
	public void closeSession() {
		if(session.isOpen())session.close();
	}

	@Override
	public void updateInfo(Topics topics,long directionIds[]) {
		try{
			session=getSession();
			session.beginTransaction();
			String hql="update Topics t set t.topicsName=:topicsName ,t.introduce=:introduce,t.enableSelect=:enableSelect where t.id=:id";
			Query query=session.createQuery(hql);
			query.setString("topicsName", topics.getTopicsName());
			query.setString("introduce", topics.getIntroduce());
			query.setLong("enableSelect", topics.getEnableSelect());
			query.setLong("id",topics.getId());
			query.executeUpdate();
			query=session.createSQLQuery("delete from t_topic_direction where topics_id=:id");
			query.setLong("id", topics.getId());
			query.executeUpdate();
			for(int i=0;i<directionIds.length;i++){
				query=session.createSQLQuery("insert into t_topic_direction value(:topicId,:directionId)");
				query.setLong("topicId", topics.getId());
				query.setLong("directionId", directionIds[i]);
				query.executeUpdate();
			}
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session.isOpen()) session.close();
		}
	}

	@Override
	public List<Topics> findtopics(String primaryKey,String year) {
		List<Topics>topics=null;
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Topics t where t.teacher.name=:name and t.grade.gradeName=:year";
			Query query=session.createQuery(hql);
			query.setString("name", primaryKey);
			query.setString("year", year);
			topics=query.list();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return topics;
	}

	@Override
	public List<Topics> findtopicsByName(String primaryKey, String year) {
		List<Topics>topics=null;
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Topics t where t.topicsName like :name and t.grade.gradeName=:year";
			Query query=session.createQuery(hql);
			primaryKey="%"+primaryKey+"%";
			query.setString("name", primaryKey);
			query.setString("year", year);
			topics=query.list();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return topics;
	}
}
