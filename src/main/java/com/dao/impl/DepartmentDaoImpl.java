package com.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.IDepartmentDao;
import com.entity.Department;
@Repository(value="departmentDao")
public class DepartmentDaoImpl implements IDepartmentDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	private String hql = "";
	public void setSession(Session session){
		this.session = session;
	}
	@Override
	public Department viewDepartment(Long departmentId) {
		Department department = null;
		hql = "SELECT new Department(id, departmentName) "
				+ " FROM Department as d "
				+ " WHERE d.id=:departmentId";
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setCacheable(true);
			query.setLong("departmentId", departmentId);
			department = (Department) query.uniqueResult();
			
			return department;
		}catch(Exception e){
			return department;
		}
	}
}
