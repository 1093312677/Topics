package com.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.IStudentDao;
import com.entity.IntentionTopic;
import com.entity.Student;
import com.entity.SubTopic;
import com.entity.Teacher;
import com.entity.TopicDirection;
import com.entity.Topics;

@Repository
public class StudentDaoImpl implements IStudentDao{
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private String hql;
	
	
	public void closeSession(){
		if(session.isOpen()) {
			session.close();
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<IntentionTopic> viewIntentions(Long id, Integer batch) {
		List<IntentionTopic> intentionTopics = null;
		hql = "SELECT intentionTopic FROM IntentionTopic as intentionTopic "
				+ " WHERE intentionTopic.student.id=:id"
				+ " AND intentionTopic.batch=:batch";
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setLong("id", id);
			query.setInteger("batch", batch);
			intentionTopics = query.list();
			session.getTransaction().commit();
		}catch(Exception e){
			
		}finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		return intentionTopics;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Topics> viewTopics(Long directionId) {
		String sql = "SELECT * FROM t_topic_direction"
				+ " WHERE directions_id=:directionId";
		
		List<Topics> topics = null;
		List<TopicDirection> topId = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createSQLQuery(sql).addEntity(TopicDirection.class);
			query.setLong("directionId", directionId);
			topId = query.list();
			session.getTransaction().commit();
		}catch(Exception e){
			
		}finally{
			if(session.isOpen()) {
//				session.close();
			}
		}
		for(TopicDirection t:topId) {
			System.out.println(t.getTopics_id());
		}
		return topics;
	}
	@Override
	public Student getStudent(Long id) {
		Student student = new Student();
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			String hql1 = "SELECT "
						+ " new com.entity.Student( student.id,  student.no,  student.name,student.topics) "
					+ " FROM "
						+ " Student as student "
					+ " WHERE "
						+ " student.id =:id";
			Query query1 = session.createQuery(hql1);
			query1.setLong("id", id);
			query1.setCacheable(true);
			Student student2 = (Student) query1.uniqueResult();
			student.setNo(student2.getNo());
			student.setName(student2.getName());
			
			Topics topics = new Topics();
			topics.setTopicsName(student2.getTopics().getTopicsName());
			topics.setIntroduce(student2.getTopics().getIntroduce());
			topics.setTaskBookName(student2.getTopics().getTaskBookName());
			
			Teacher t = new Teacher();
			t.setName(student2.getTopics().getTeacher().getName());
			
			topics.setTeacher(t);
			
			String hql2 = "SELECT "
					+ " new com.entity.SubTopic( id,  subName,  taskBookName) "
				+ " FROM "
					+ " SubTopic as subTopic "
				+ " WHERE "
					+ " subTopic.student.id =:id";
			Query query2 = session.createQuery(hql2);
			query2.setLong("id", id);
			query2.setCacheable(true);
			SubTopic subTopic = (SubTopic) query2.uniqueResult();
			
			student.setSubTopic(subTopic);
			student.setTopics(topics);
			
			session.getTransaction().commit();
		}catch(Exception e){
			
		}finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		return student;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudentsNotSelect(Long gradeId, Integer num, Integer size) {
		hql = "FROM Student as student"
				+ " WHERE "
				+ " student.topics = null"
				+ " AND"
				+ " student.clazz.direction.spceialty.grade.id =:gradeId";
		List<Student> students = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setLong("gradeId", gradeId);
			query.setCacheable(true);
			query.setFirstResult(num*size);
			query.setMaxResults(size);
			students = query.list();
			session.getTransaction().commit();
		}catch(Exception e){
			
		}finally{
			if(session.isOpen()) {
//				session.close();
			}
		}
		return students;
	}
	@Override
	public Integer getStudentsNotSelectCount(Long gradeId) {
		hql = "SELECT COUNT(*) "
				+ " FROM Student as student"
				+ " WHERE "
				+ " student.topics = null"
				+ " AND"
				+ " student.clazz.direction.spceialty.grade.id =:gradeId";
		Integer count = 0;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setLong("gradeId", gradeId);
			query.setCacheable(true);
			count = ((Number)query.uniqueResult()).intValue();
			session.getTransaction().commit();
		}catch(Exception e){
			
		}finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		return count;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudents(Long gradeId, int num, int size) {
		hql = "SELECT student "
				+ " FROM "
					+ " Student as student "
				+ " WHERE "
					+ " student.clazz.direction.spceialty.grade.id =:gradeId";
		List<Student> students = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setLong("gradeId", gradeId);
			query.setFirstResult(num*size);
			query.setMaxResults(size);
			students = query.list();
			
			
			for(Student s:students) {
				if(s.getTopics() != null) {
					s.getTopics().getTopicsName();
				}
			}
			session.getTransaction().commit();
		}catch(Exception e){
			
		}finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		
		return students;
	}
	@Override
	public Integer getLastSelectStudentsCount(Long gradeId) {
		hql = "SELECT COUNT(*)"
				+ " FROM "
					+ " Student as students "
				+ " WHERE "
					+ " students.clazz.direction.spceialty.grade.id="+gradeId;
		Integer count = 0;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setLong("gradeId", gradeId);
			query.setCacheable(true);
			count = ((Number)query.uniqueResult()).intValue();
			session.getTransaction().commit();
		}catch(Exception e){
			
		}finally{
			if(session.isOpen()) {
				session.close();
			}
		}
		return count;
	}
	@Override
	public Student getStudentBasicInfor(Long studentId) {
		return null;
	}
	
}
