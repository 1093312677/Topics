package com.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setLong("studentId", studentId);
			score = (Score) query.uniqueResult();
		}catch(Exception e) {
			e.printStackTrace();
		} 
		return score;
	}
	@Transactional
	@Override
	public boolean updateScore(Score score) {
		try{
			session = sessionFactory.getCurrentSession();
			session.update(score);
			return true;
		}catch(Exception e) {
			throw new ServiceException("error");
		}
	}
	@Transactional
	@Override
	public boolean saveScore(Score score) {
		try{
			session = sessionFactory.getCurrentSession();
			session.save(score);
			return true;
		}catch(Exception e) {
			throw new ServiceException("error");
		}
	}
	
}
