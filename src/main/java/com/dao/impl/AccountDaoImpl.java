package com.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.AccountDao;
import com.entity.User;
@Repository
public class AccountDaoImpl implements AccountDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	
	private String hql;
	
	/**
	 * close session
	 */
	public void closeSession(){
		if(session.isOpen())
			session.close();
	}
	
	@Override
	/**
	 * 更新密码
	 */
	public boolean updatePw(String userId, String pw) {
		String hql="update User u set u.password=:pw where u.id=:id";
		
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setString("pw", pw);
			query.setString("id", userId);
			query.executeUpdate();
			session.getTransaction().commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} finally {
			if( session.isOpen() ) {
				session.close();
			}
		}
	}

	@Override
	public boolean updateInfor(String table, String qq, String email, String telephone, String userId) {
		hql = "update "+table+" set qq=:qq,email=:email,telephone=:telephone where id=:id";
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setString("qq", qq);
			query.setString("email", email);
			query.setString("telephone", telephone);
			query.setString("id", userId);
			query.executeUpdate();
			session.getTransaction().commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} finally {
			if( session.isOpen() ) {
				session.close();
			}
		}
	}

	@Override
	public User login(String username) {
		User user = null;
		hql = "SELECT new User( id,  username,  password,  privilege)"
				+ " FROM User "
				+ " WHERE username =:username";
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setString("username", username);
			user = (User) query.uniqueResult();
			session.getTransaction().commit();
			return user;
		}catch(Exception e){
			e.printStackTrace();
			return user;
		} finally {
			if( session.isOpen() ) {
				session.close();
			}
		}
	}
	
}
