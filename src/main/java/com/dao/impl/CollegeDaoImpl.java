package com.dao.impl;

import com.dao.ICollegeDao;
import com.dao.IDepartmentDao;
import com.entity.College;
import com.entity.Department;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="collegeDao")
public class CollegeDaoImpl implements ICollegeDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	private String hql = "";
	@Override
	public College viewCollege(Long collegeId) {
		List<College> collegeList = null;
		hql = "SELECT new College(id, departmentName) "
				+ " FROM College as d "
				+ " WHERE d.id=:departmentId";
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setCacheable(true);
			query.setLong("departmentId", collegeId);
			collegeList = query.list();

			return null;
		}catch(Exception e){
			return null;
		}
	}
}
