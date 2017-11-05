package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.dao.daoAdapter.DaoAdapter;
import com.entity.Student;
import org.springframework.transaction.annotation.Transactional;

/**
 * 通用的dao
 * @author kone
 *
 * @param <T>
 */
@Component
public class CommonDaoImpl<T> extends DaoAdapter<T>{
	private Session session;
	String hql = "";
	public void setSession(Session session){
		this.session = session;
	}
	@Transactional
	@Override
	public boolean save(T entity) {
		session.save(entity);
		return false;
	}

	@Override
	public boolean delete(T entity) {
		session.delete(entity);
		return false;
	}
	
	@Override
	public boolean update(T entity) {
		session.update(entity);
		return false;
	}
	
	@Override
	public List findBy(String table, String col, String value) {
		hql ="FROM "+table+" WHERE "+col+"="+value;
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	@Override
	public int getNum(String table, String col, String value) {
		hql = "SELECT COUNT(*) FROM "+table+" WHERE "+col+"="+value;
		Query query =  session.createQuery(hql);
//		query.setString(0, table);
//		query.setString(1, value);
		return ((Number)query.uniqueResult()).intValue();
	}
	
	@Override
	public List findBy(String table, String col, String value, int page, int eachPage) {
		hql = "FROM "+table+" WHERE "+col+"="+value;
		Query query =  session.createQuery(hql);
//		query.setString(0, table);
//		query.setString(1, value);
		
		query.setMaxResults(eachPage);
		query.setFirstResult(page);
		
		return query.list();
	}
	
	/**
	 * check this grade student
	 * @param gradeId
	 * @return
	 */
	public List<T> viewStudents(String gradeId, int page, int eachPage) {
		hql = "select students from Student as students "
				+ " where "
				+ " students.topics = null"
				+ " AND "
				+ " students.swapInDepa = 0 "
				+ " AND "
				+ " students.clazz.direction.spceialty.grade.id="+gradeId;
		List<T> entitys = null;
		Query query = session.createQuery(hql);
		query.setFirstResult(page);
		query.setMaxResults(eachPage);
		entitys = query.list();
		return entitys;
	}
	
	/**
	 * check the number of students in this grade
	 * @return
	 */
	public int getStudentsNum(String gradeId) {
		hql = "SELECT COUNT(*) FROM Student as students join students.clazz.direction.spceialty.grade as grade where grade.id="+gradeId;
		Query query =  session.createQuery(hql);
		return ((Number)query.uniqueResult()).intValue();
	}
	
	public List findAll(String table) {
		hql ="FROM "+table;
		Query query = session.createQuery(hql);
		return query.list();
	}
	/**
	 * 更新学生题目
	 * @param studentId
	 * @param topicId
	 */
	public void updateTopic(String studentId, String topicId) {
		hql = "UPDATE Student set topicsId = "+topicId+" WHERE id="+studentId;
		session.createQuery(hql).executeUpdate();
	}
	/**
	 * 更新题目选择的数量
	 * @param topicId
	 */
	public void updateTopicAddOne(String topicId) {
		hql = "UPDATE Topics set selectedStudent = selectedStudent + 1 WHERE id="+topicId;
		session.createQuery(hql).executeUpdate();
	}
	
	public void updateSql(String id) {
		String sql = "update Student set topicsId = null where topicsId="+id;
		session.createSQLQuery(sql).executeUpdate();
	}
	
	public void updateSubTopic(String id) {
		String sql = "update SubTopic set studentId = null where topicId="+id;
		session.createSQLQuery(sql).executeUpdate();
	}
	
	public List<Student> getStudents(Long gradeId) {
		hql = "SELECT student "
				+ " FROM "
					+ " Student as student "
				+ " WHERE "
					+ " student.clazz.direction.spceialty.grade.id =:gradeId";
		List<Student> students = null;
		Query query = session.createQuery(hql);
		query.setLong("gradeId", gradeId);
		students = query.list();
		return students;
	}
	
}
