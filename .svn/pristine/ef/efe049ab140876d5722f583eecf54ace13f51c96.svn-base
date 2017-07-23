package com.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.baseDaoFactory.BaseDAOFactory;
import com.dao.impl.CommonDaoImpl;
import com.dao.impl.GroupDaoImpl;
import com.dao.impl.SwapDaoImpl;
import com.entity.Student;
import com.entity.Topics;

/**
 * 关于swap的逻辑处理
 * @author kone
 *	2017.3.30
 */
@Service
public class SwapService {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	
	@Autowired
	private CommonDaoImpl commonDaoImpl;
	@Autowired
	private SwapDaoImpl swapDaoImpl;
	
	/**
	 * 修改系内调配
	 * @param id
	 * @return
	 */
	public boolean swapInDepart(String state,String id) {
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			SwapDaoImpl dao =  (SwapDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("SwapDao");
			
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			
			dao.swapInDepart(state, id);
			session.getTransaction().commit();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} 
	}
	/**
	 * 修改老师调配
	 * @param id
	 * @return
	 */
	public boolean swapInTeacher(String state,String intentId) {
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			SwapDaoImpl dao =  (SwapDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("SwapDao");
			
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			
			dao.swapInTeacher(state, intentId);
			session.getTransaction().commit();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} 
	}
	/**
	 * 留言
	 * @param id
	 * @param message
	 * @return
	 */
	public boolean leavMessage(String id,String message) {
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			获取groupDao
			SwapDaoImpl dao =  (SwapDaoImpl) BaseDAOFactory.getInstance().getDaoInterface("SwapDao");
			
//			传递session保证是同一个session进行事务处理
			dao.setSession(session); 
//			
			dao.leavMessage(id, message);
			session.getTransaction().commit();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} 
	}
	/**
	 * 教师调剂前查看题目
	 * @param teacherId
	 * @param directionId
	 * @return
	 */
	public List<Topics> viewSwapTeacher(long teacherId, long directionId) {
		List<Topics> topics = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			传递session保证是同一个session进行事务处理
			commonDaoImpl.setSession(session); 
			topics = commonDaoImpl.findBy("Topics", "teacherId", String.valueOf(teacherId));
			for(int i=topics.size()-1;i>=0;i--) {
				int flag = 0;
				for(int j=0;j<topics.get(i).getDirections().size();j++) {
					if(topics.get(i).getDirections().get(j).getId() == directionId) {
						System.out.println(topics.get(i).getTopicsName());
						flag = 1;
					}
				}
				if(flag == 0) {
					topics.remove(i);
				}
			}
			session.getTransaction().commit();
			
			return topics;
		}catch(Exception e){
			e.printStackTrace();
			return topics;
		} 
	}
	
	/**
	 * 调剂学生-教师
	 * @param topicId
	 * @param studentId
	 * @return
	 */
	public boolean swapTeacher(long topicId, long studentId) {
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			传递session保证是同一个session进行事务处理
			commonDaoImpl.setSession(session); 
			List<Student> students = commonDaoImpl.findBy("Student", "id", String.valueOf(studentId));
			if(students.size() > 0) {
				Topics topic = new Topics();
				topic.setId(topicId);
				students.get(0).setTopics(topic);
				commonDaoImpl.update(students.get(0));
			}
//			更新题目数量状态
			List<Topics> topics = commonDaoImpl.findBy("Topics", "id", String.valueOf(topicId));
			if(topics.size() > 0) {
				topics.get(0).setSelectedStudent(topics.get(0).getSelectedStudent() + 1);
				commonDaoImpl.update(topics.get(0));
			}
			session.getTransaction().commit();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} 
	}
	/**
	 * 系主任查看还未选题的学生
	 * @param gradeId
	 * @param page
	 * @param eachPage
	 * @return
	 */
	public List<Student> viewSwapStudentDept(long gradeId, int page, int eachPage) {
		List<Student> students = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			传递session保证是同一个session进行事务处理
			commonDaoImpl.setSession(session); 
			students = commonDaoImpl.viewStudents(String.valueOf(gradeId), page, eachPage);
//			if(students.size() > 0) {
//				for(int i=students.size()-1;i>=0;i--) {
//					System.out.println(students.get(i).getName());
//					if(students.get(i).getTopics().getTopicsName() != null) {
//						students.remove(i);
//					}
//				}
//			}
			session.getTransaction().commit();
			
			return students;
		}catch(Exception e){
			e.printStackTrace();
			return students;
		} 
	}
	/**
	 * 获取该方向对应的题目
	 * @param gradeId
	 * @param page
	 * @param eachPage
	 * @return
	 */
	public List<Topics> viewSwapTopicDept(long directionId, int page, int eachPage) {
		List<Topics> topics =  null;
		try{
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
//			传递session保证是同一个session进行事务处理
			swapDaoImpl.setSession(session); 
			topics = swapDaoImpl.getTopicDirection(directionId, page, eachPage);
			for(int i=0;i<topics.size();i++) {
				for(int j=0;j<topics.get(i).getDirections().size();j++) {
					topics.get(i).getDirections().get(j).getDirectionName();
				}
			}
			session.getTransaction().commit();
			
			return topics;
		}catch(Exception e){
			e.printStackTrace();
			return topics;
		} 
	}
	
	
	 
}
