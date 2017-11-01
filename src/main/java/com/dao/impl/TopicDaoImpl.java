package com.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ITopicDao;
import com.entity.Grade;
import com.entity.SubTopic;
import com.entity.Teacher;
import com.entity.TeacherAutoSelect;
import com.entity.TeacherGroup;
import com.entity.Topics;

@Repository
public class TopicDaoImpl implements ITopicDao{
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private String hql;
	
	
	public void closeSession(){
//		if(session.isOpen()) {
//			session.close();
//		}
	}
	/**
	 * 查看未通过题目
	 * @param gradeId
	 * @param teacherId
	 * @return
	 */
	public List<Topics> viewNotThoughtTopic(String gradeId, long teacherId, String state) {
		hql = "SELECT topics FROM Topics as topics join topics.grade as grade  WHERE grade.id = "+gradeId+" AND topics.teacherId = "+teacherId+" AND topics.state = "+state;
		
		List<Topics> topics = null;
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			topics = query.list();
		}catch(Exception e){
			
		}
		return topics;
	}
	
	public List<Topics> findByTwo(String table, String col, String value, String col2, String value2) {
		hql = "from "+table+" where "+col+"='"+value+"' AND "+col2+" ='"+value2+"'";
		List<Topics> entitys = new ArrayList<Topics>();
		try{
			session = sessionFactory.getCurrentSession();
			entitys = session.createQuery(hql).list();
			return entitys;
		}catch(Exception e){
			return entitys;
		}
	}
	
	/**
	 * 系主任查看题目
	 * @param gradeId
	 * @param teacherId
	 * @return
	 */
	public List<Topics> viewTopic(String gradeId, String state, int page, int eachPage) {
		hql = "SELECT topics FROM Topics as topics join topics.grade as grade  WHERE grade.id = "+gradeId+"  AND topics.state = "+state;
		List<Topics> topics = null;
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setCacheable(true);
			query.setFirstResult(page);
			query.setMaxResults(eachPage);
			topics = query.list();
		}catch(Exception e){
			
		}
		return topics;
	}
	/**
	 * 系主任查看题目数量
	 * @param gradeId
	 * @param teacherId
	 * @return
	 */
	public int viewTopicNum(String gradeId, String state) {
		hql = "SELECT COUNT(*) FROM Topics as topics join topics.grade as grade  WHERE grade.id = "+gradeId+"  AND topics.state = "+state;
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			return ((Number)query.uniqueResult()).intValue();
		}catch(Exception e){
			return 0;
		}
		
	}
	/**
	 * 退选毕业选题
	 * @param gradeId
	 * @param state
	 * @return
	 */
	@Transactional
	public boolean withdrawalTopic(String studentId, String topicId) {
		
		try{
			session = sessionFactory.getCurrentSession();
//			更新学生题目状态
			hql = "update Student set topicsId = null where id = "+studentId;
			session.createQuery(hql).executeUpdate();
//			更新选题人数
			hql = "update Topics set selectedStudent = selectedStudent-1 where id = "+topicId;
			session.createQuery(hql).executeUpdate();
			return true;
		}catch(Exception e){
			throw new ServiceException("error");
		}
		
	}
	
	
	/**
	 * 查看题目详情
	 * @param topicId
	 * @return
	 */
	public Topics viewTopicDetials(String topicId) {
		hql = "FROM Topics where id="+topicId;
		Topics topic = null;
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);//.setResultTransformer(Transformers.aliasToBean(Topics.class));
			topic = (Topics) query.uniqueResult();
		}catch(Exception e){
			
		}
		return topic;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Topics> teacherViewTopicsApp(String teacherId, String gradeId, int status) {
		String hql = "SELECT tp FROM Topics as tp JOIN tp.teacher as te JOIN tp.grade as grade WHERE tp.state ="+ status +" AND te.id ="+teacherId +" AND grade.id = "+gradeId;
		List<Topics> topics = null;
		try{
			session = sessionFactory.getCurrentSession();
			topics = session.createQuery(hql).list();
		} catch(Exception e){
			
		} 
		return topics;
	}
	@Override
	public List<Grade> teacherViewGradesApp(String departmentId) {
		String hql = "SELECT new com.entity.Grade(grade.id, grade.gradeName) FROM Grade grade , Department as depart WHERE grade.department.id = depart.id and depart.id = "+departmentId;
		List<Grade> grades = null;
		try{
			session = sessionFactory.getCurrentSession();
			grades = session.createQuery(hql).list();
		} catch(Exception e){
			
		} 
		return grades;
	}
	@Override
	public TeacherGroup viewTeacherGroup(String teacherId, String gradeId) {
		String hql = "SELECT teacherGroup FROM TeacherGroup as teacherGroup "
				+ "WHERE "
				+ "teacherGroup.group.grade.id = "+gradeId
				+ "AND "
				+ "teacherGroup.teacher.id = "+teacherId;
		TeacherGroup groupAndTime = null;
		try{
			session = sessionFactory.getCurrentSession();
			groupAndTime = (TeacherGroup) session.createQuery(hql).uniqueResult();
		} catch(Exception e){
			
		} 
		return groupAndTime;
	}
	@Override
	public List<Topics> viewGuideStudents(String teacherId, String gradeId) {
		String hql = "SELECT topics FROM Topics as topics "
				+ " WHERE "
				+ " topics.teacher.id = "+teacherId
				+ " AND"
				+ " topics.grade.id = "+gradeId;
		List<Topics> topics = null;
		try{
			session = sessionFactory.getCurrentSession();
			topics = session.createQuery(hql).list();
		} catch(Exception e){
			
		}
		return topics;
	}
	@Override
	public TeacherAutoSelect viewAutoSelect(String teacherId, String gradeId) {
		String hql = "SELECT teacherAutoSelect FROM TeacherAutoSelect as teacherAutoSelect "
				+ " WHERE "
				+ " teacherAutoSelect.teacher.id = "+teacherId
				+ " AND"
				+ " teacherAutoSelect.grade.id = "+gradeId;
		TeacherAutoSelect teacherAutoSelect = null;
		try{
			session = sessionFactory.getCurrentSession();
			teacherAutoSelect = (TeacherAutoSelect) session.createQuery(hql).uniqueResult();
		} catch(Exception e){
			
		} 
		return teacherAutoSelect;
	}
	@Transactional
	@Override
	public boolean updateAutoSelect(String teacherId, String gradeId, int status) {
		String hql = "UPDATE TeacherAutoSelect as teacherAutoSelect SET autoSelect="+status
				+ " WHERE "
				+ " teacherAutoSelect.teacher.id = "+teacherId
				+ " AND"
				+ " teacherAutoSelect.grade.id = "+gradeId;
		try{
			session = sessionFactory.getCurrentSession();
			session.createQuery(hql).executeUpdate();
			return true;
		} catch(Exception e){
			throw new ServiceException("error");
		}
	}
	@Transactional
	@Override
	public boolean addAutoSelect(TeacherAutoSelect teacherAutoSelect) {
		try{
			session = sessionFactory.getCurrentSession();
			session.save(teacherAutoSelect);
			return true;
		} catch(Exception e){
			return false;
		}
	}
	@Override
	public List<Topics> getTopics(Session session, String gradeId) {
		hql = "SELECT"
					+ " topics "
				+ " FROM "
					+ " Topics as topics "
				+ " WHERE"
					+ " selectedStudent < enableSelect"
					+ " AND "
					+ " topics.grade.id=:gradeId "
					+ " AND"
					+ " topics.state = 1";
		List<Topics> topics = null;
		try{
			Query query = session.createQuery(hql);
			query.setString("gradeId", gradeId);
			topics = query.list();
			for(int i=0;i<topics.size();i++) {
				if(topics.get(i).getDirections() != null) {
					for(int j=0;j<topics.get(i).getDirections().size();j++) {
						System.out.println(topics.get(i).getDirections().get(j).getDirectionName());
					}
				}
			}
			return topics;
		} catch(Exception e){
			return topics;
		} 
	}
	@Override
	public Integer getTopicCount(Long directionId) {
		hql = "select COUNT(*) from "
			+ " Topics as topics "
		+ " join "
			+ " topics.directions as dire "
		+ " where "
			+ " topics.state = 1 "
		+ " AND "
			+ " topics.enableSelect > topics.selectedStudent"
		+ " AND "
			+ " dire.id=:directionId";
		Integer count = 0;
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setLong("directionId", directionId);
			query.setCacheable(true);
			count = ((Number)query.uniqueResult()).intValue();
		}catch(Exception e){
		} 
		return count;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Topics> studentGetTopics(Long directionId, int batch, int num, int size) {
		hql = "select topics from "
				+ " Topics as topics "
			+ " join "
				+ " topics.directions as dire "
			+ " where "
				+ " topics.state = 1 "
			+ " AND "
				+ " topics.enableSelect > topics.selectedStudent"
			+ " AND "
				+ " dire.id=:directionId";
	List<Topics> topics = null;
	List<Topics> topics2 = new ArrayList<Topics>();
	Topics topic = null;
	Teacher teacher = null;
	try{
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(num*size);
		query.setMaxResults(size);
		query.setCacheable(true);
		query.setLong("directionId", directionId);
		topics = query.list();
		int count = 0;//统计有多少个意向学生
		for(int i=0;i<topics.size();i++) {
			topic = new Topics();
			topic.setId(topics.get(i).getId());
			topic.setTaskBookName(topics.get(i).getTaskBookName());
			topic.setTopicsName(topics.get(i).getTopicsName());
			topic.setEnableSelect(topics.get(i).getEnableSelect());
			topic.setSelectedStudent(topics.get(i).getSelectedStudent());
			topic.setIntroduce(topics.get(i).getIntroduce());
			
			teacher = new Teacher();
			teacher.setName(topics.get(i).getTeacher().getName());
			teacher.setId(topics.get(i).getTeacher().getId());
			topic.setTeacher(teacher);
			
			count = 0;
			String hql2 = "SELECT COUNT(*) "
					+ " FROM "
						+ " IntentionTopic AS intentionTopic"
					+ " WHERE "
						+ " intentionTopic.batch = "+batch
					+ " AND "
						+ "intentionTopic.topic.id="+topics.get(i).getId();
			Query query2 = session.createQuery(hql2);
			query2.setCacheable(true);
			count = ((Number)query2.uniqueResult()).intValue();
			topic.setIntentionNumber(count);
			
			topics2.add(topic);
		}
		return topics2;
	}catch(Exception e){
		return topics2;
	}
	}
	@Override
	public Topics getStudentTopic(Long studentId) {
		hql = "SELECT new Topics( id,  topicsName,  introduce,  taskBookName)"
				+ " FROM "
				+ " Topics as topic,"
				+ " Student as student "
				+ " WHERE "
				+ " student.topics.id = topic.id "
				+ " AND "
				+ " student.id=:studentId";
		Topics topic = null;
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setLong("studentId", studentId);
			topic = (Topics) query.uniqueResult();
		}catch(Exception e){
		}
		return topic;
	}
	@Override
	public SubTopic getStudentSubTopic(Long studentId) {
		hql = "SELECT new SubTopic( id,  subName,  taskBookName)"
				+ " FROM "
				+ " SubTopic as sub"
				+ " WHERE "
				+ " sub.student.id=:studentId";
		SubTopic topic = null;
		try{
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setLong("studentId", studentId);
			topic = (SubTopic) query.uniqueResult();
		}catch(Exception e){
		} 
		return topic;
	}
	@Override
	public Topics getTopicById(Long topicId) {
		session = sessionFactory.getCurrentSession();
		Topics topic = (Topics) session.load(Topics.class, topicId);
		return topic;
	}
	
	
	
}
