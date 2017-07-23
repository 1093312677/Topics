package com.guo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.entity.Student;
import com.entity.User;
import com.guo.dao.IUserDao;

@Repository(value="userDao1")
public class UserDao extends BaseDao implements IUserDao{
	private Session session;
	@Override
	public User get(String username) {
		User user=null;
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from User u where u.username=:username";
			Query query=session.createQuery(hql);
			query.setString("username",username);
			user=(User) query.uniqueResult();
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
		return user;
	}

	@Override
	public int update(String newpw, User user) {
		try{
			session=getSession();
			session.beginTransaction();
			String hql="update User u set u.password=:pw where u.id=:id";
			Query query=session.createQuery(hql);
			query.setString("pw", newpw);
			query.setLong("id", user.getId());
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
		return 1;
	}

	@Override
	public List<User> alluser() {
		List<User> allUser=new ArrayList<>();
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from User u where u.privilege=1";
			Query query=session.createQuery(hql);
			allUser=query.list();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return allUser;
	}

	@Override
	public void closeSession() {
		if(session.isOpen()) session.close();
	}

	@Override
	public void addAdmin(User user) {
		try{
			session=getSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session.isOpen()) session.close();
		}
		
	}

}
