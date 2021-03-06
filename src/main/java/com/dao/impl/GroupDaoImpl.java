package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.dao.daoAdapter.DaoAdapter;
import com.entity.Group;
import com.entity.StuTeachGroup;
import com.entity.TeacherGroup;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDaoImpl extends DaoAdapter{
	@Autowired
	private SessionFactory sessionFactory;

	private Session session;
	private String hql = "";
	public void setSession(Session session){
		this.session = session;
	}
	public boolean save(Group entity) {
		session.save(entity);
		return true;
	}
	
	public List<Group> view(String gradeId) {
		List<Group> entitys;
		hql = "FROM Group WHERE gradeId = "+gradeId;
		entitys = session.createQuery(hql).list();
		
		return entitys;
	}
	/**
	 * 取消组的答辩组
	 */
	@Override
	public boolean calcelGroup(String id) {
		hql = "UPDATE groupp SET ansGroup_id =NULL WHERE id = "+id;
		session.createSQLQuery(hql).executeUpdate();
		return false;
	}
	
	@Override
	public List<Group> findById(String id) {
		hql ="FROM Group WHERE id="+id;
		@SuppressWarnings("unchecked")
		List<Group> groups = session.createQuery(hql).list();
		return groups;
	}
	/**
	 * 修改组长
	 * @return
	 */
	public boolean changeLeader(int state,int id){
		hql = "UPDATE TeacherGroup SET isLeader ="+state+" WHERE id ="+id;
		session.createSQLQuery(hql).executeUpdate();
		return false;
	}
	/**
	 * 教师查看分组
	 */
	@Override
	public  List<TeacherGroup> findTeacherGroup(String teacherId) {
		session = sessionFactory.getCurrentSession();
		hql ="FROM TeacherGroup WHERE teacherId="+teacherId;
		List<TeacherGroup> teacherGroup = session.createQuery(hql).list();
		return teacherGroup;
	}

	@Override
	public List<StuTeachGroup> findStuTeachGroup(long gradeId, long teacherId) {
		hql ="FROM StuTeachGroup WHERE teacherId="+teacherId+" AND gradeId ="+gradeId;
		@SuppressWarnings("unchecked")
		List<StuTeachGroup> stuTeachGroups = session.createQuery(hql).list();
		return stuTeachGroups;
	}

	@Override
	public List findBy(String table, String col, String value) {
		hql ="FROM "+table+" WHERE "+col+"="+value;
		Query query = session.createQuery(hql);
		return query.list();
	}
}
