package com.dao;

import java.util.List;

import com.common.QueryCondition;
import com.entity.Direction;
import com.entity.User;

public interface IDao<T> {
	
	/**
	 * save entity
	 * @param entity
	 * @return
	 */
	public boolean save(T entity);
	/**
	 * save entity and get id
	 * @param entity
	 * @return
	 */
	public String saveGetId(T entity);
	
	/**
	 * view Entity
	 * @param table 
	 * @param page
	 * @param eachPage
	 */
	public List<T> view(String table,int page,int eachPage);
	
	/**
	 * delete entity
	 * @param entity
	 * @return
	 */
	public boolean delete(T entity);
	
	/**
	 * find entity
	 * @param id
	 * @param table
	 * @author kone
	 * @return
	 */
	public List<T> find(String table,String id);
	/**
	 * update entity
	 * @param entity
	 * @return
	 */
	public boolean update(T entity);
	/**
	 * query by one condition
	 * @param table
	 * @param col
	 * @param value
	 * @return
	 */
	public List<T> findBy(String table,String col,String value);
	/**
	 * query by one condition
	 * @param table
	 * @param col
	 * @param value
	 * @return
	 */
	public List<T> findBy(String table,String col,String value, int page, int eachPage );
	/**
	 * login
	 * @param user
	 * @return
	 */
	public List<T> login(User user);
	
	/**
	 * get count 
	 * @param table
	 * @return
	 */
	public int getCount(String table);
	
	/**
	 * get count 
	 * @param table
	 * @return
	 */
	public int getCount(String table, String col, String value);
	
	/**
	 * find by two conditions
	 * @param table
	 * @param col
	 * @param value
	 * @param col2
	 * @param value2
	 * @return
	 */
	public List<T> findByTwo(String table,String col,String value,String col2,String value2);
	
	/**
	 * view topics
	 * @param directions
	 * @return
	 */
	public List<T> viewTopic(List<Direction> directions);
	
	/**
	 * find use 1 to 4 condition
	 * @param queryCondition
	 * @return
	 */
	public List<T> findByFree(QueryCondition queryCondition);
	
	/**
	 * update use 1 to 4 condition,last one update
	 * @param queryCondition
	 * @return
	 */
	public boolean updateByFree(QueryCondition queryCondition);
	
	/**
	 * check this grade student
	 * @param gradeId
	 * @return
	 */
	public List<T> viewStudents(String gradeId, int page, int eachPage);
	
	/**
	 * check the number of students in this grade
	 * @param gradeId
	 * @return
	 */
	public int getStudentsNum(String gradeId);
	
	/**
	 * check students are not select topics
	 * @param gradeId
	 * @param page
	 * @param eachPage
	 * @return
	 */
	public List<T> viewNotSelected(String gradeId, int page, int eachPage);
	/**
	 * check the number of student not select topics
	 * @param gradeId
	 * @return
	 */
	public int getNotSelectedNum(String gradeId);
	/**
	 * batch import entity
	 * @param entitys
	 * @return
	 */
	public boolean batchImport(List<T> entitys);
	
	public boolean saveOrUpdate(T entity);
	/**
	 * insert into table use sql
	 * @param value1
	 * @param value2
	 * @return
	 */
	public boolean insertSql(String value1, String value2);
}
