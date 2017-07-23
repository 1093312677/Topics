package com.guo.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.entity.Specialty;
import com.entity.Student;
import com.guo.dao.ISpceialtyDao;
import com.guo.dao.ISpecialtyDao;

@Repository(value="specialtyDao1")
public class SpecialtyDao extends BaseDao implements ISpecialtyDao{
	private Session session=null;
	@Override
	public Specialty get(int specialtyId) {
		Specialty specialty=null;
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Specialty s where s.id=:id";
			Query query=session.createQuery(hql);
			query.setLong("id", specialtyId);
			specialty=(Specialty) query.uniqueResult();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		} 
		return specialty;
	}

	@Override
	public void closeSession() {
		session.close();
	}

	@Override
	public int updateInfo(Specialty specialty) {
		try{
			session=getSession();
			session.beginTransaction();
			String hql="update Specialty s set s.specialtyName=:specialtyName where s.id=:id";
			Query query=session.createQuery(hql);
			query.setString("specialtyName", specialty.getSpecialtyName());
			query.setLong("id", specialty.getId());
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
