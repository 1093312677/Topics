package com.guo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.entity.Clazz;
import com.entity.Department;
import com.guo.dao.IDepartmentDao;
@Repository(value="departmentDao1")
public class DepartmentDao extends BaseDao implements IDepartmentDao {

	Session session=null;
	@Override
	public List<Department> departments(long departmentId) {
		List<Department>departments=new ArrayList<>();
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Department dep where dep.id=:id";
			Query query=session.createQuery(hql);
			query.setLong("id", departmentId);
			departments=(List<Department>) query.list();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		} 
		return departments;
	}

	@Override
	public void closeSession() {
		if(session.isOpen()) session.close();
	}

	@Override
	public int updateInfo(Department department) {
		try{
			session=getSession();
			session.beginTransaction();
			String hql="update Department dep set dep.departmentName=:name where dep.id=:id";
			Query query=session.createQuery(hql);
			query.setString("name", department.getDepartmentName());
			query.setLong("id", department.getId());
			query.executeUpdate();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session.isOpen()) session.close();
		}
		return 0;
	}

}
