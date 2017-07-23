package com.guo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.entity.Specialty;
import com.entity.Student;
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

	@Override
	public List<Specialty> inspection(String specialtyName,long gradeId) {
		List<Specialty>specialties=new ArrayList<>();
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Specialty s where s.specialtyName=:specialtyName and s.grade=:gradeId";
			Query query=session.createQuery(hql);
			query.setString("specialtyName", specialtyName);
			query.setLong("gradeId", gradeId);
			specialties=(List<Specialty>)query.list();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return specialties;
	}

}
