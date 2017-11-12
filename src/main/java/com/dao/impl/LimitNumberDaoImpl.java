package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.IGradeDao;
import com.dao.ILimitNumberDao;
import com.entity.Grade;
import com.entity.LimitNumber;
import com.exception.ServiceExecption;
@Repository
public class LimitNumberDaoImpl implements ILimitNumberDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	private String hql = "";
	@Override
	public List<LimitNumber> getLimitNumbers(Long gradeId, Long teacherId) {
		hql = "FROM LimitNumber as li"
				+ " WHERE "
				+ " li.grade.id=:gradeId "
				+ " AND li.teacher.id=:teacherId";
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setLong("gradeId", gradeId);
		query.setLong("teacherId", teacherId);
		return query.list();
	}
	
	@Transactional
	@Override
	public boolean updateLimitNumber(Long limitId, int number) {
		hql = "UPDATE LimitNumber"
				+ " SET alreadyNumber=:alreadyNumber"
				+ " WHERE id=:limitId";
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setLong("limitId", limitId);
			query.setLong("alreadyNumber", number);
			query.executeUpdate();
			return true;
		} catch(Exception e) {
			throw new ServiceExecption("error");
		}
	}
}
