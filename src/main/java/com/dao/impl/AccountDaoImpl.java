package com.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.AccountDao;
import com.entity.Clazz;
import com.entity.Department;
import com.entity.Direction;
import com.entity.Grade;
import com.entity.Student;
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

	@Override
	public Student getStudentInfo(String no) {
		hql = "SELECT new com.entity.Student( id,  no,  name,  sex,  qq,  telephone,  email,  remark,  swapInDepa)"
				+ " FROM Student as stu"
				+ " WHERE "
				+ " stu.no=:no";
		Student stu = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setString("no", no);
			stu = (Student) query.uniqueResult();
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if( session.isOpen() ) {
				session.close();
			}
		}
		return stu;
	}

	@Override
	public Clazz getClazzByStudentId(Long studentId) {
		hql = "SELECT new com.entity.Clazz(clz.id,clz.className)"
				+ " FROM"
				+ " Clazz as clz, "
				+ " Student as stu "
				+ " WHERE"
				+ " clz.id=stu.clazz.id"
				+ " AND"
				+ " stu.id=:studentId";
		Clazz clazz = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setLong("studentId", studentId);
			clazz = (Clazz) query.uniqueResult();
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if( session.isOpen() ) {
				session.close();
			}
		}
		return clazz;
	}

	@Override
	public Direction getDirectionByClazzId(Long clazzId) {
		hql = "SELECT new com.entity.Direction(dire.id, dire.directionName)"
				+ " FROM"
				+ " Direction as dire, "
				+ " Clazz as clazz "
				+ " WHERE"
				+ " dire.id=clazz.direction.id"
				+ " AND"
				+ " clazz.id=:clazzId";
		Direction direction = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setLong("clazzId", clazzId);
			direction = (Direction) query.uniqueResult();
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if( session.isOpen() ) {
				session.close();
			}
		}
		return direction;
	}

	@Override
	public Grade getGradeByDirectionId(Long directionId) {
		hql = "SELECT new com.entity.Grade( grade.id, grade.gradeName)"
				+ " FROM"
				+ " Direction as dire, "
				+ " Grade as grade "
				+ " WHERE"
				+ " grade.id=dire.spceialty.grade.id"
				+ " AND"
				+ " dire.id=:directionId";
		Grade grade = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setLong("directionId", directionId);
			grade = (Grade) query.uniqueResult();
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if( session.isOpen() ) {
				session.close();
			}
		}
		return grade;
	}

	@Override
	public Department getDepartmentByGradeId(Long gradeId) {
		hql = "SELECT new com.entity.Department( department.id, department.departmentName)"
				+ " FROM"
				+ " Department as department, "
				+ " Grade as grade "
				+ " WHERE"
				+ " department.id=grade.department.id"
				+ " AND"
				+ " grade.id=:gradeId";
		Department department = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setLong("gradeId", gradeId);
			department = (Department) query.uniqueResult();
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if( session.isOpen() ) {
				session.close();
			}
		}
		return department;
	}
	
}
