package com.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.IScoreDao;
import com.entity.Score;
@Repository
public class ScoreDaoImpl implements IScoreDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	private String hql = "";
	public void setSession(Session session){
		this.session = session;
	}
	@Override
	public Score getScoreParam(Long studentId) {
		hql = "SELECT new Score(id,  score,  mediumScore,  headScore,  level,  replyScore, replyResult) "
				+ " FROM Score as score1"
				+ " WHERE"
				+ " score1.student.id=:studentId";
		Score score = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setLong("studentId", studentId);
			score = (Score) query.uniqueResult();
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen() ) {
				session.close();
			}
		}
		return score;
	}
	@Override
	public boolean updateScore(Score score) {
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(score);
			session.getTransaction().commit();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if(session.isOpen() ) {
				session.close();
			}
		}
	}
	@Override
	public boolean saveScore(Score score) {
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(score);
			session.getTransaction().commit();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if(session.isOpen() ) {
				session.close();
			}
		}
	}
	
}
