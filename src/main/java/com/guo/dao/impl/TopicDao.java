package com.guo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.entity.Clazz;
import com.entity.LimitNumber;
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
	public long updateInfo(Topics topics,long directionIds[], String privilege) {
		long gradeId=0;
		try{
			session=getSession();
			session.beginTransaction();
			String hql1="from Topics t where id=:id";
			Query query2=session.createQuery(hql1);
			query2.setLong("id", topics.getId());
			Topics topics2=(Topics) query2.uniqueResult();
			gradeId=topics2.getGrade().getId();
			hql1="from LimitNumber l where l.teacher=:teacherId";
			query2=session.createQuery(hql1);
			long teacherId=topics2.getTeacher().getId();
			query2.setLong("teacherId",teacherId );
			LimitNumber limitNumber=(LimitNumber) query2.uniqueResult();
			int num=limitNumber.getAlreadyNumber()-topics2.getEnableSelect()+topics.getEnableSelect();
			String sql="update LimitNumber l set l.alreadyNumber=:num where l.teacher=:teacherId and l.grade=:gradeId";
			query2=session.createQuery(sql);
			query2.setInteger("num", num);
			query2.setLong("teacherId", teacherId);
			query2.setLong("gradeId", gradeId);
			query2.executeUpdate();
			
//			如果是教师更新，将题目设置为审核状态
			String hql = "";
			if("3".equals(privilege)) {
				hql="update Topics t set t.topicsName=:topicsName ,t.introduce=:introduce,t.enableSelect=:enableSelect, t.state=2 where t.id=:id";
			} else if("2".equals(privilege)) {
				hql="update Topics t set t.topicsName=:topicsName ,t.introduce=:introduce,t.enableSelect=:enableSelect where t.id=:id";
			}
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
		return gradeId;
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
