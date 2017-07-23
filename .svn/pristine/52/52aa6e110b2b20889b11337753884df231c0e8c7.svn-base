package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.impl.DaoImpl;
import com.entity.User;
@Service
public class AccountService<T> {
	@Autowired
	private DaoImpl<T> daoImpl;
	
	public DaoImpl<T> getDaoImpl() {
		return daoImpl;
	}
	public void setDaoImpl(DaoImpl<T> daoImpl) {
		this.daoImpl = daoImpl;
	}
	public String login(){
		
		return "yes";
	}
	/**
	 * close session
	 */
	public void closeSession(){
		daoImpl.closeSession();
	}
	/**
	 * save Entity
	 * @param T entitys
	 * @author kone
	 * @return boolean
	 */
	public boolean save(T entity){
		
		return daoImpl.save(entity);
	}
	
	/**
	 * view Entity
	 * @param table 
	 * @param page
	 * @param eachPage
	 * @author kone
	 * @return List<T> entitys
	 */
	public List<T> view(String table, int page, int eachPage) {
		
		return daoImpl.view(table, page, eachPage);
	}
	
	/**
	 * delete entity
	 * @param entity
	 * @author kone
	 * @return
	 */
	public boolean delete(T entity) {
		
		return daoImpl.delete(entity);
	}
	/**
	 * find entity
	 * @param id
	 * @param table
	 * @author kone
	 * @return
	 */
	public List<T> find(String table, String id) {
		
		return daoImpl.find(table, id);
	}
	/**
	 * update entity
	 * @param entity
	 * @return
	 */
	public boolean update(T entity) {
		return daoImpl.update(entity);
	}
	
	/**
	 * query by two conditions
	 * @param table
	 * @param col
	 * @param value
	 * @return
	 */
	public List<T> findBy(String table, String col, String value) {
		return daoImpl.findBy(table, col, value);
	}
	
	
	/**
	 * login
	 * @param user
	 * @return
	 */
	public boolean login(User user) {
		List<User> users = (List<User>) daoImpl.login(user);
		daoImpl.closeSession();
		if(users.size()>0){
			if(users.get(0).getPassword().equals(user.getPassword())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
}
