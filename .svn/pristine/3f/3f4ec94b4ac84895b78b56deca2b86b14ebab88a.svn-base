package com.guo.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.entity.Direction;
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
}
