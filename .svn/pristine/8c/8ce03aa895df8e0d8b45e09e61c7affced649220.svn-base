package com.guo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.entity.Grade;
import com.entity.Student;
import com.guo.dao.IGradeDao;
@Repository(value="gradeDao1")
public class GradeDao extends BaseDao implements IGradeDao {
	Session session=null;
	@Override
	public Grade getgrade(long gradeId) {
		Grade grade=null;
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Grade g where g.id=:id";
			Query query=session.createQuery(hql);
			query.setLong("id", gradeId);
			grade=(Grade) query.uniqueResult();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return grade; 
	}

	@Override
	public void closeSession() {
		session.close();
	}

	@Override
	public int updateInfo(Grade grade) {
		try{
			session=getSession();
			session.beginTransaction();
			String hql="update Grade g set g.gradeName=:gradeName where g.id=:id";
			Query query=session.createQuery(hql);
			query.setString("gradeName",grade.getGradeName());
			query.setLong("id", grade.getId());
			query.executeUpdate();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			{
				if(session.isOpen()){
					session.close();
				}
			}
		}
		return 0;
	}

	@Override
	public List<Grade> inspection(long departmentId, String gradeName) {
		List<Grade>grades=new ArrayList<Grade>();
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Grade g where g.gradeName=:gradeName and g.department=:department";
			Query query=session.createQuery(hql);
			query.setString("gradeName",gradeName);
			query.setLong("department",departmentId);
			grades=(List<Grade>)query.list();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return grades;
	}

}
