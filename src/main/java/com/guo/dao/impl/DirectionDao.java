package com.guo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.entity.Direction;
import com.entity.Grade;
import com.entity.Specialty;
import com.guo.dao.IDirectionDao;

@Repository(value="directionDao1")
public class DirectionDao extends BaseDao implements IDirectionDao {
	Session session=null;
	

	@Override
	public Direction get(long directionId) {
		Direction direction=null;
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Direction d where d.id=:id";
			Query query=session.createQuery(hql);
			query.setLong("id", directionId);
			direction=(Direction) query.uniqueResult();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		} 
		return direction;
	}

	@Override
	public int updateInfo(Direction direction) {
		try{
			session=getSession();
			session.beginTransaction();
			String hql="update Direction d set d.directionName=:directionName where d.id=:id";
			Query query=session.createQuery(hql);
			query.setString("directionName",direction.getDirectionName());
			query.setLong("id", direction.getId());
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
	public void closeSession() {
		session.close();
	}

	@Override
	public List<Direction> inspection(String directionName, long specialtyId) {
		List<Direction>directions=new ArrayList<>();
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Direction d where d.directionName=:directionName and d.spceialty=:specialtyId";
			Query query=session.createQuery(hql);
			query.setString("directionName",directionName);
			query.setLong("specialtyId", specialtyId);
			directions=(List<Direction>)query.list();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return directions;
	}

	@Override
	public List<Direction> directionsByGrade(long gradeId) {
		List<Direction>directions=new ArrayList<>();
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Direction d where d.spceialty.grade=:gradeId";
			Query query=session.createQuery(hql);
			query.setLong("gradeId", gradeId);
			directions=(List<Direction>)query.list();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return directions;
	}

	@Override
	public List<Direction> findDirection(String year,long departmentId) {
		List<Direction>directions=null;
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Direction d where d.spceialty.grade.gradeName=:year and d.spceialty.grade.department.id=:departmentId";
			Query query=session.createQuery(hql);
			query.setString("year", year);
			query.setLong("departmentId", departmentId);
			directions=(List<Direction>)query.list();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return directions;
	}
}
