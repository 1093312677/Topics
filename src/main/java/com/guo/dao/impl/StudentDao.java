package com.guo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.entity.Clazz;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.Topics;
import com.entity.User;
import com.guo.dao.IStudentDao;
@Repository(value="studentDao1")
public class StudentDao extends BaseDao implements IStudentDao {
	private Session session;
	//根据学号获得学生
	public Student gets(String no) {
		Student student=null;
		try{
			session=getSession();
			session.beginTransaction();
			String hql="from Student stu where stu.no=:no";
			Query query=session.createQuery(hql);
			query.setString("no", no);
			student=(Student) query.uniqueResult();
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		} 
		return student;
	}
/**
 * private Integer id;
	private String no;//学号
	private String name;
	private String sex;
	private String age;
	private String qq;
	private String telephone;
	private String email;
 */
	//修改部分学生信息
	@Override
	public int update(Student stu) {
		try{
			session=getSession();
			session.beginTransaction();
			String hql="update Student s set s.qq=:qq,s.telephone=:phone,s.email=:email where s.no=:no";
			Query query=session.createQuery(hql);
			query.setString("qq", stu.getQq());
			query.setString("phone", stu.getTelephone());
			query.setString("email", stu.getEmail());
			query.setString("no", stu.getNo());
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
//根据id得到学生
public Student get(int id) {
	Student student=null;
	try{
		session=getSession();
		session.beginTransaction();
		String hql="from Student stu where stu.id=:id";
		Query query=session.createQuery(hql);
		query.setInteger("id", id);
		student=(Student) query.uniqueResult();
		session.getTransaction().commit();			
	}catch(Exception e){
		e.printStackTrace();
	} 
	return student;
}
@Override
public void closeSession() {
	if(session.isOpen()) session.close();
}
@Override
/* private Integer id;
	private String no;//学号
	private String name;
	private String sex;
	private String age;
	private String qq;
	private String telephone;
	private String email;
	private int swapInDepa;//是否允许在系内调配
	private Clazz clazz;
	修改学生所有信息
 */
public int updateInfo(Student stu,long clazzId) {
	try{
		session=getSession();
		session.beginTransaction();
		String hql="update Student s set s.no=:no,s.name=:name,s.sex=:sex,s.qq=:qq,s.telephone=:phone,s.email=:email,s.clazz=:clazz where s.id=:id";
		Query query=session.createQuery(hql);
		query.setLong("id", stu.getId());
		query.setString("no", stu.getNo());
		query.setString("name", stu.getName());
		query.setString("sex", stu.getSex());
		query.setString("qq", stu.getQq());
		query.setString("phone", stu.getTelephone());
		query.setString("email", stu.getEmail());
		query.setLong("clazz", clazzId);
		query.executeUpdate();
		Student student=(Student) session.get(Student.class,(int)stu.getId());
		query=session.createSQLQuery("update user  set username=:name where id=:id");
		query.setString("name", stu.getNo());
		query.setLong("id",student.getUser().getId());
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
public List<Student> getStudents(String name,String queryBy,long gradeId) {
	List<Student> students=new ArrayList<Student>();
	try{
		session=getSession();
		session.beginTransaction();
		String hql="from Student stu where stu.name like :name and stu.clazz.direction.spceialty.grade.id=:gradeId";
		if(queryBy.equals("no")) hql="from Student stu where stu.no=:name and stu.clazz.direction.spceialty.grade.id=:gradeId";
		if(queryBy.equals("id")) hql="from Student stu where stu.id=:name";
		Query query=session.createQuery(hql);
		query.setString("name", "%"+name+"%");
		if(queryBy.equals("no")||queryBy.equals("id"))query.setString("name", name);
		if(!queryBy.equals("id"))query.setLong("gradeId", gradeId);
		students=(List<Student>) query.list();
		session.getTransaction().commit();			
	}catch(Exception e){
		e.printStackTrace();
	} 
	return students;
}
@Override
public List<Topics> findTopicBy(String pk, String findType, long directionId) {
	List<Topics> topics=new ArrayList<Topics>();
	try{
		session=getSession();
		session.beginTransaction();
		String sql=null;
		if(findType.equals("teachername")){
			sql="select * from topics where selectedStudent !=enableSelect and teacherId in (select id from teacher where name like :pk) and id in (select topics_id from t_topic_direction where directions_id=:directionId)";
		}else if(findType.equals("topicName")){
			sql="select * from topics where selectedStudent !=enableSelect and topicsName like :pk and id in (select topics_id from t_topic_direction where directions_id=:directionId)";
		}
		Query query=session.createSQLQuery(sql).addEntity(Topics.class);
		query.setString("pk", "%"+pk+"%");
		query.setLong("directionId", directionId);
		topics=query.list();
		session.getTransaction().commit();
	}catch(Exception e){
		e.printStackTrace();
	} 
	return topics;
}
@Override
public Student isSelected(long id) {
	Student student=new Student();
	try{
		session=getSession();
		session.beginTransaction();
		String hql="from Student s where s.id=:id";
		Query query=session.createQuery(hql);
		query.setLong("id", id);
		student=(Student) query.uniqueResult();
		session.getTransaction().commit();
	}catch(Exception e){
		e.printStackTrace();
	} 
	return student;
}
}
