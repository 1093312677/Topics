package com.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.impl.CommonDaoImpl;
import com.entity.Student;
import com.entity.Topics;
/**
 * <p>教师和学生相关逻辑处理</p>
 * @author kone
 * 2017.4.18
 */
@Service
public class TeachStuService {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	
	@Autowired
	private CommonDaoImpl commonDaoImpl;
	public void closeSession(){
		if(session.isOpen()) {
			session.close();
		}
	}
	
	/**
	 * 查看指导学生
	 * @param teacherId
	 * @return
	 */
	public List<Student> viewGuideStudent(long teacherId) {
		List<Topics> topics =  null;
		List<Student> students =  new ArrayList<Student>();
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			传递session保证是同一个session进行事务处理
			commonDaoImpl.setSession(session); 
			topics = commonDaoImpl.findBy("Topics", "teacherId", String.valueOf(teacherId));
			for(int i=0;i<topics.size();i++) {
				students.addAll(topics.get(i).getStudents());
//				for(int j=0;j<topics.get(i).getStudents().size();j++) {
//					topics.get(i).getStudents().get(j).getTopics();
//				}
			}
			session.getTransaction().commit();
			
			return students;
		}catch(Exception e){
			e.printStackTrace();
			return students;
		} 
	}
	
	/**
	 * 获取学生人数
	 * @param gradeId
	 * @return
	 */
	public int getStudentsNum(String gradeId) {
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
//			传递session保证是同一个session进行事务处理
			commonDaoImpl.setSession(session); 
			int count = commonDaoImpl.getStudentsNum(gradeId);
			session.getTransaction().commit();
			return count;
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
			return 0;
		} finally{
			if(session.isOpen()) {
				session.close();
			}
		}
	}
	
	/**
	 * 查看学生
	 * @param gradeId
	 * @param page
	 * @param eachPage
	 * @return
	 */
	public List<Student> viewStudents(String gradeId, int page, int eachPage) {
		List<Student> students =  new ArrayList<Student>();
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			传递session保证是同一个session进行事务处理
			commonDaoImpl.setSession(session); 
			students = commonDaoImpl.viewStudents(gradeId, page, eachPage);
			for(int i=0;i<students.size();i++) {
				if(students.get(i).getTopics() != null) {
					System.out.println(students.get(i).getTopics().getTopicsName());
				}
			}
			session.getTransaction().commit();
			
			return students;
		}catch(Exception e){
			session.getTransaction().commit();
			e.printStackTrace();
			return students;
		} 
	}
}
