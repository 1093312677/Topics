package com.guo.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.entity.Clazz;
import com.entity.Student;
import com.guo.dao.IClazzDao;

@Repository(value="clazzDao1")
public class ClazzDao extends BaseDao implements IClazzDao {
	Session session=null;
	@Override
	public Clazz get(long clazzId) {
		Clazz clazz=null;
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Clazz clazz where clazz.id=:id";
			Query query=session.createQuery(hql);
			query.setLong("id", clazzId);
			clazz=(Clazz) query.uniqueResult();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		} 
		return clazz;
	}

	@Override
	public void closeSession() {
		if(session.isOpen()) session.close();
	}

	@Override
	public int updateInfo(Clazz clazz) {
		try{
			session=getSession();
			session.beginTransaction();
			String hql="update Clazz clazz set clazz.className=:className where clazz.id=:id";
			Query query=session.createQuery(hql);
			query.setString("className",clazz.getClassName());
			query.setLong("id", clazz.getId());
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

}
