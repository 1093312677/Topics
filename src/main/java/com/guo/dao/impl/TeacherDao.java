package com.guo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.entity.Student;
import com.entity.Teacher;
import com.guo.dao.ITeacherDao;
@Repository(value="teacherDao1")
public class TeacherDao extends BaseDao implements ITeacherDao {
	Session session=null;
	@Override
	public Teacher get(String no) {
		Teacher teacher=new Teacher();
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Teacher t where t.no=:no";
			Query query=session.createQuery(hql);
			query.setString("no", no);
			teacher=(Teacher) query.uniqueResult();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return teacher;
	}

	@Override
	public int update(Teacher teacher) {
		try{
			session=getSession();
			session.beginTransaction();
			String hql="update Teacher t set t.qq=:qq,t.telephone=:phone,t.email=:email,t.remark=:remark where t.no=:no";
			Query query=session.createQuery(hql);
			query.setString("qq", teacher.getQq());
			query.setString("phone", teacher.getTelephone());
			query.setString("email", teacher.getEmail());
			query.setString("no", teacher.getNo());
			query.setString("remark", teacher.getRemark());
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
	public void closeSession() {
		if(session.isOpen()){
			session.close();
		}
	}
/*
 	private String no;//工号
	private String name;
	private String sex;
	private String qq;
	private String position;//职称
	private String telephone;
	private String email;
	private String privilege;//权限
 */
	@Override
	public int updateInfo(Teacher teacher) {
		try{
			session=getSession();
			session.beginTransaction();
			String hql="update Teacher t set t.no=:no,t.name=:name,t.sex=:sex,t.position=:position, t.qq=:qq,t.telephone=:phone,t.email=:email,t.remark=:remark where t.id=:id";
			Query query=session.createQuery(hql);
			query.setLong("id", teacher.getId());
			query.setString("no", teacher.getNo());
			query.setString("name", teacher.getName());
			query.setString("sex", teacher.getSex());
			query.setString("position", teacher.getPosition());
			query.setString("qq", teacher.getQq());
			query.setString("phone", teacher.getTelephone());
			query.setString("email", teacher.getEmail());
			query.setString("remark", teacher.getRemark());
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
		return 0;
	}

	@Override
	public Teacher get(int id) {
		Teacher teacher=new Teacher();
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Teacher t where t.id=:id";
			Query query=session.createQuery(hql);
			query.setInteger("id", id);
			teacher=(Teacher) query.uniqueResult();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return teacher;
	}

	@Override
	public List<Teacher> teachers(String primaryKey, String findby,long departmentId) {
		List<Teacher>teachers=new ArrayList<>();
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Teacher tea where tea.name like:primaryKey and tea.department.id=:departmentId";
			if(findby.equals("no")) hql="from Teacher tea where tea.no=:primaryKey and tea.department.id=:departmentId";
			else if(findby.equals("id")) hql="from Teacher tea where tea.id=:primaryKey and  tea.department.id=:departmentId";
			Query query=session.createQuery(hql);
			if(findby.equals("no")||findby.equals("id"))query.setString("primaryKey", primaryKey);
			else query.setString("primaryKey", "%"+primaryKey+"%");
			query.setLong("departmentId", departmentId);
			teachers=(List<Teacher>) query.list();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		} 
		return teachers;
	}

	@Override
	public int updateAllInfo(Teacher teacher, long dempartmentId) {
		try{
			session=getSession();
			session.beginTransaction();
			String hql="update Teacher t set t.no=:no,t.name=:name,t.sex=:sex,t.position=:position, t.qq=:qq,t.telephone=:phone,t.email=:email,t.remark=:remark,t.department=:department where t.id=:id";
			Query query=session.createQuery(hql);
			query.setLong("id", teacher.getId());
			query.setString("no", teacher.getNo());
			query.setString("name", teacher.getName());
			query.setString("sex", teacher.getSex());
			query.setString("position", teacher.getPosition());
			query.setString("qq", teacher.getQq());
			query.setString("phone", teacher.getTelephone());
			query.setString("email", teacher.getEmail());
			query.setString("remark", teacher.getRemark());
			query.setLong("department", dempartmentId);
			query.executeUpdate();
			Teacher teacher2=(Teacher) session.get(Teacher.class, teacher.getId());
			query=session.createSQLQuery("update user  set username=:name where id=:id");
			query.setString("name", teacher.getNo());
			query.setLong("id", teacher2.getUser().getId());
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
		return 0;
	}

	@Override
	public List<Teacher> inspection(String teacherno) {
		List<Teacher>teachers=new ArrayList<>();
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Teacher tea where tea.no=:no";
			Query query=session.createQuery(hql);
			query.setString("no", teacherno);
			teachers=(List<Teacher>) query.list();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		} 
		return teachers;
	}	
}
