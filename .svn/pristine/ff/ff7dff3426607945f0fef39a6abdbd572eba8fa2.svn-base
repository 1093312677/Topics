package com.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.QueryCondition;
import com.dao.IDao;
import com.entity.Direction;
import com.entity.User;

public class DaoImpl<T> implements IDao<T>{
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
	
	/**
	 * save entity
	 * @param entity
	 * @return
	 */
	@Override
	public boolean save(T entity) {
		try{
			session = sessionFactory.openSession();
			session.save(entity);
			
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
	/**
	 * view Entity
	 * @param table 
	 * @param page
	 * @param eachPage
	 * @return List<T> entitys
	 */
	@Override
	public List<T> view(String table, int page, int eachPage) {
		List<T> entitys = new ArrayList<T>();
		try{
			hql = "From "+table;
			session = sessionFactory.openSession();
			session.beginTransaction();
//			分页查询
			Query query = session.createQuery(hql);
			query.setFirstResult(page);  //设置当前记录条数
			query.setMaxResults(eachPage); //设置每页的记录条数
			entitys = query.list();
			session.getTransaction().commit();
			return entitys;
		}catch(Exception e){
			e.printStackTrace();
			return entitys;
		}
	}
	/**
	 * delete entity
	 * @param entity
	 * @return
	 */
	@Override
	public boolean delete(T entity) {
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(entity);
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
	/**
	 * find entity
	 * @param id
	 * @param table
	 * @author kone
	 * @return
	 */
	@Override
	public List<T> find(String table, String id) {
		hql = "from "+table+" where id='"+id+"'";
		List<T> entitys = new ArrayList<T>();
		try{
			session = sessionFactory.openSession();
//			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			entitys = session.createQuery(hql).list();
			session.getTransaction().commit();
			return entitys;
		}catch(Exception e){
			return entitys;
		}finally {
			if( session.isOpen() ) {
				session.close();
			}
		}
	}
	/**
	 * update entity
	 * @param entity
	 * @return
	 */
	@Override
	public boolean update(T entity) {
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(entity);
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
	/**
	 * query by two conditions
	 * @param table
	 * @param col
	 * @param value
	 * @return
	 */
	@Override
	public List<T> findBy(String table, String col, String value, int page, int eachPage ) {
		hql = "from "+table+" where "+col+"='"+value+"'";
		List<T> entitys = new ArrayList<T>();
		try{
			session = sessionFactory.openSession();
//			分页查询
			Query query = session.createQuery(hql);
			query.setFirstResult(page);  //设置当前记录条数
			query.setMaxResults(eachPage); //设置每页的记录条数
			entitys = query.list();
			return entitys;
		}catch(Exception e){
			return entitys;
		}
	}
	
	/**
	 * query by two conditions
	 * @param table
	 * @param col
	 * @param value
	 * @return
	 */
	@Override
	public List<T> findBy(String table, String col, String value) {
		hql = "from "+table+" where "+col+"='"+value+"'";
		List<T> entitys = new ArrayList<T>();
		try{
			session = sessionFactory.openSession();
			entitys = session.createQuery(hql).list();
			return entitys;
		}catch(Exception e){
			return entitys;
		}
	}
	
	/**
	 * login
	 * @param user
	 * @return
	 */
	@Override
	public List<T> login(User user) {
		List<T> entitys = new ArrayList<T>();
		try{
			hql = "From User WHERE username='"+user.getUsername()+"'";
			session = sessionFactory.openSession();
			session.beginTransaction();
			entitys = session.createQuery(hql).list();
			session.getTransaction().commit();
			return entitys;
		}catch(Exception e){
			e.printStackTrace();
			return entitys;
		}
	}
	/**
	 * get count 
	 * @param table
	 * @return
	 */
	@Override
	public int getCount(String table) {
		hql = "SELECT COUNT(*) FROM "+table;
		int count = 0;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query =  session.createQuery(hql);
			session.getTransaction().commit();
			return ((Number)query.uniqueResult()).intValue();
		}catch(Exception e){
			e.printStackTrace();
			return count;
		} finally {
			if( session.isOpen() ) {
				session.close();
			}
		}
	}
	
	/**
	 * get count 
	 * @param table
	 * @return
	 */
	@Override
	public int getCount(String table, String col, String value) {
		hql = "SELECT COUNT(*) FROM "+table+" WHERE "+col+" = '"+value+"'";
		int count = 0;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query =  session.createQuery(hql);
			session.getTransaction().commit();
			return ((Number)query.uniqueResult()).intValue();
		}catch(Exception e){
			e.printStackTrace();
			return count;
		} finally {
			if( session.isOpen() ) {
				session.close();
			}
		}
	}
	
	/**
	 * find by two conditions
	 * @param table
	 * @param col
	 * @param value
	 * @param col2
	 * @param value2
	 * @return
	 */
	@Override
	public List<T> findByTwo(String table, String col, String value, String col2, String value2) {
		hql = "from "+table+" where "+col+"='"+value+"' AND "+col2+" ='"+value2+"'";
		List<T> entitys = new ArrayList<T>();
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			entitys = session.createQuery(hql).list();
			session.getTransaction().commit();
			return entitys;
		}catch(Exception e){
			return entitys;
		}
	}
	/**
	 * view topics
	 * @param directions
	 * @return
	 */
	@Override
	public List<T> viewTopic(List<Direction> directions) {
		hql = "select topics from Topics as topics join topics.directions as dire where dire.id=";
		if(directions.size() == 1){
			hql += directions.get(0).getId();
		}
		if(directions.size()>1){
			for(int i=1;i<directions.size();i++){
				hql = hql + " or dire.id ="+directions.get(i).getId();
			}
		}
		List<T> entitys = new ArrayList<T>();
		try{
			session = sessionFactory.openSession();
//			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			entitys = session.createQuery(hql).list();
			session.getTransaction().commit();
			return entitys;
		}catch(Exception e){
			return entitys;
		}
	}
	/**
	 * find use 1 to 4 condition
	 * @param queryCondition
	 * @return
	 */
	@Override
	public List<T> findByFree(QueryCondition queryCondition) {
		if(queryCondition.getConunt() == 2){
			hql = "from "+queryCondition.getTable()+" where "+queryCondition.getRow1()+"='"+queryCondition.getValue1()+"' AND "+queryCondition.getRow2()+" ='"+queryCondition.getValue2()+"'";
		}else if(queryCondition.getConunt() == 3){
			hql = "from "+queryCondition.getTable()+" where "+queryCondition.getRow1()+"='"+queryCondition.getValue1()+"' AND "+queryCondition.getRow2()+" ='"+queryCondition.getValue2()+"' AND "+queryCondition.getRow3()+" ='"+queryCondition.getValue3()+"'";
		}else{
			hql = "from "+queryCondition.getTable()+" where "+queryCondition.getRow1()+"='"+queryCondition.getValue1()+"' AND "+queryCondition.getRow2()+" ='"+queryCondition.getValue2()+"' AND "+queryCondition.getRow3()+" ='"+queryCondition.getValue3()+"' AND "+queryCondition.getRow4()+" ='"+queryCondition.getValue4()+"'";
		}
		List<T> entitys = new ArrayList<T>();
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			entitys = session.createQuery(hql).list();
			session.getTransaction().commit();
			return entitys;
		}catch(Exception e){
			return entitys;
		}
	}
	/**
	 * update use 1 to 4 condition,last one update
	 * @param queryCondition
	 * @return
	 */
	@Override
	public boolean updateByFree(QueryCondition queryCondition) {
		if( queryCondition.getConunt() == 1 ) {
			hql = "UPDATE "+queryCondition.getTable()
		    +" SET "+queryCondition.getRow4()+"="+queryCondition.getValue4()
			+" where "+queryCondition.getRow1()+"='"+queryCondition.getValue1()+"'";
		} else {
			hql = "UPDATE "+queryCondition.getTable()
		    +" SET "+queryCondition.getRow4()+"="+queryCondition.getValue4()
			+" where "+queryCondition.getRow1()+"='"+queryCondition.getValue1()
			+"' AND "+queryCondition.getRow2()+" ='"+queryCondition.getValue2()
			+"' AND "+queryCondition.getRow3()+" ='"+queryCondition.getValue3()+"'";
		}
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.createQuery(hql).executeUpdate();
			session.getTransaction().commit();
			return true;
		}catch(Exception e){
			return false;
		} finally {
			if( session.isOpen() ) {
				session.close();
			}
		}
	}
	/**
	 * check this grade student
	 * @param gradeId
	 * @return
	 */
	@Override
	public List<T> viewStudents(String gradeId, int page, int eachPage) {
		hql = "select students from Student as students join students.clazz.direction.spceialty.grade as grade where grade.id="+gradeId;
		List<T> entitys = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setFirstResult(page);
			query.setMaxResults(eachPage);
			entitys = query.list();
			session.getTransaction().commit();
			return entitys;
		}catch(Exception e){
		}
		return entitys;
	}
	/**
	 * check the number of students in this grade
	 * @return
	 */
	@Override
	public int getStudentsNum(String gradeId) {
		hql = "SELECT COUNT(*) FROM Student as students join students.clazz.direction.spceialty.grade as grade where grade.id="+gradeId;
		int count = 0;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query =  session.createQuery(hql);
			session.getTransaction().commit();
			return ((Number)query.uniqueResult()).intValue();
		}catch(Exception e){
			e.printStackTrace();
			return count;
		} finally {
			if( session.isOpen() ) {
				session.close();
			}
		}
	}
	/**
	 * check students are not select topics
	 * @param gradeId
	 * @param page
	 * @param eachPage
	 * @return
	 */
	@Override
	public List<T> viewNotSelected(String gradeId, int page, int eachPage) {
		hql = "SELECT students FROM Student as students join"
				+ " students.clazz.direction.spceialty.grade "
				+ "as grade where grade.id="+gradeId+" AND students.topicsId = null";
		List<T> entitys = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setFirstResult(page);
			query.setMaxResults(eachPage);
			entitys = query.list();
			session.getTransaction().commit();
			return entitys;
		}catch(Exception e){
		}
		return entitys;
	}
	/**
	 * check the number of student not select topics
	 * @param gradeId
	 * @return
	 */
	@Override
	public int getNotSelectedNum(String gradeId) {
		hql = "SELECT COUNT(*) FROM (Student as students where students.topicsId = null) join"
				+ " students.clazz.direction.spceialty.grade "
				+ "as grade where grade.id="+gradeId;
		int count = 0;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query =  session.createQuery(hql);
			session.getTransaction().commit();
			return ((Number)query.uniqueResult()).intValue();
		}catch(Exception e){
			e.printStackTrace();
			return count;
		} finally {
			if( session.isOpen() ) {
				session.close();
			}
		}
	}
	/**
	 * batch import entity
	 * @param entitys
	 * @return
	 */
	@Override
	public boolean batchImport(List<T> entitys) {
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			for (int i=0;i<entitys.size();i++) {
				session.save(entitys.get(i));
				if (i % 50 ==0 ) {
					session.flush();
					session.clear();
				}
			}
			
			session.flush();
			session.clear();
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
	public boolean saveOrUpdate(T entity) {
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(entity);
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
	/**
	 * save entity and get id
	 * @param entity
	 * @return
	 */
	@Override
	public String saveGetId(T entity) {
		try{
			session = sessionFactory.openSession();
			Serializable id = session.save(entity);
			
			return id.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		} finally {
			if( session.isOpen() ) {
				session.close();
			}
		}
	}
	/**
	 * insert into table use sql
	 * @param value1
	 * @param value2
	 * @return
	 */
	@Override
	public boolean insertSql(String value1, String value2) {
		String sql = "insert into t_topic_direction values('"+value1+"','"+value2+"')";
		try{
			session = sessionFactory.openSession();
			session.createSQLQuery(sql).executeUpdate();
			
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

}
