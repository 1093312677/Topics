package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Information;
import com.common.ServerResponse;
import com.dao.AccountDao;
import com.dao.impl.DaoImpl;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.User;
@Service
public class AccountService<T> {
	@Autowired
	private DaoImpl<T> daoImpl;
	@Autowired
	private AccountDao accountDao;
	public DaoImpl<T> getDaoImpl() {
		return daoImpl;
	}
	public void setDaoImpl(DaoImpl<T> daoImpl) {
		this.daoImpl = daoImpl;
	}
	public String login(){
		
		return "yes";
	}
	/**
	 * close session
	 */
	public void closeSession(){
		daoImpl.closeSession();
	}
	/**
	 * save Entity
	 * @param T entitys
	 * @author kone
	 * @return boolean
	 */
	public boolean save(T entity){
		
		return daoImpl.save(entity);
	}
	
	/**
	 * view Entity
	 * @param table 
	 * @param page
	 * @param eachPage
	 * @author kone
	 * @return List<T> entitys
	 */
	public List<T> view(String table, int page, int eachPage) {
		
		return daoImpl.view(table, page, eachPage);
	}
	
	/**
	 * delete entity
	 * @param entity
	 * @author kone
	 * @return
	 */
	public boolean delete(T entity) {
		
		return daoImpl.delete(entity);
	}
	/**
	 * find entity
	 * @param id
	 * @param table
	 * @author kone
	 * @return
	 */
	public List<T> find(String table, String id) {
		
		return daoImpl.find(table, id);
	}
	/**
	 * update entity
	 * @param entity
	 * @return
	 */
	public boolean update(T entity) {
		return daoImpl.update(entity);
	}
	
	/**
	 * query by two conditions
	 * @param table
	 * @param col
	 * @param value
	 * @return
	 */
	public List<T> findBy(String table, String col, String value) {
		return daoImpl.findBy(table, col, value);
	}
	
	
	/**
	 * login
	 * @param user
	 * @return
	 */
	public User login(User user) {
		User user1 = accountDao.login(user.getUsername());
		if(user1 != null){
			if(user1.getPassword().equals(user.getPassword())){
				return user1;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * loginApp
	 * @param user
	 * @return
	 */
	public ServerResponse<Information> loginApp(User user) {
		List<User> users = (List<User>) daoImpl.login(user);
		
		daoImpl.closeSession();
		if(users.size()>0){
			if(users.get(0).getPassword().equals(user.getPassword())){
				List<Student> students = null;
				List<Teacher> teachers = null;
				Information infor = new Information();
				if("4".equals(users.get(0).getPrivilege())) {
					students = (List<Student>) daoImpl.findBy("Student", "no",users.get(0).getUsername());
					infor.setNo(students.get(0).getNo());
					infor.setClazz(students.get(0).getClazz().getClassName());
					infor.setDirection(students.get(0).getClazz().getDirection().getDirectionName());
					infor.setSpecialty(students.get(0).getClazz().getDirection().getSpceialty().getSpecialtyName());
					infor.setGrade(students.get(0).getClazz().getDirection().getSpceialty().getGrade().getGradeName());
					infor.setGradeId(students.get(0).getClazz().getDirection().getSpceialty().getGrade().getId());
					infor.setDepartment(students.get(0).getClazz().getDirection().getSpceialty().getGrade().getDepartment().getDepartmentName());
					infor.setDepartmentId(students.get(0).getClazz().getDirection().getSpceialty().getGrade().getDepartment().getId());
					infor.setCollege(students.get(0).getClazz().getDirection().getSpceialty().getGrade().getDepartment().getCollege().getCollegeName());
					
					infor.setName(students.get(0).getName());
					infor.setEmail(students.get(0).getEmail());
					infor.setId((int) students.get(0).getId());
					infor.setPassword(students.get(0).getUser().getPassword());
					infor.setSex(students.get(0).getSex());
					infor.setQq(students.get(0).getQq());
					infor.setTelephone(students.get(0).getTelephone());
					infor.setRemark(students.get(0).getRemark());
					infor.setPrivilege(students.get(0).getUser().getPrivilege());
					infor.setSwapInDepa(students.get(0).getSwapInDepa());
					
					infor.setuId(students.get(0).getUser().getId());
				} else if("3".equals(users.get(0).getPrivilege())) {
					teachers = (List<Teacher>) daoImpl.findBy("Teacher", "no",users.get(0).getUsername());
					if(teachers.size() > 0) {
						infor.setNo(teachers.get(0).getNo());
						infor.setDepartment(teachers.get(0).getDepartment().getDepartmentName());
						infor.setDepartmentId(teachers.get(0).getDepartment().getId());
						infor.setCollege(teachers.get(0).getDepartment().getCollege().getCollegeName());
						
						infor.setName(teachers.get(0).getName());
						infor.setEmail(teachers.get(0).getEmail());
						infor.setId((int) teachers.get(0).getId());
						infor.setPassword(teachers.get(0).getUser().getPassword());
						infor.setSex(teachers.get(0).getSex());
						infor.setQq(teachers.get(0).getQq());
						infor.setTelephone(teachers.get(0).getTelephone());
						infor.setRemark(teachers.get(0).getRemark());
						infor.setPrivilege(teachers.get(0).getUser().getPrivilege());
						
						infor.setuId(teachers.get(0).getUser().getId());
					}
				}
				
				
				
				return ServerResponse.response(200, "ok", infor);
			}else{
				return ServerResponse.response(201, "密码错误", new Information());
			}
		}else{
			return ServerResponse.response(202, "无此用户", new Information());
		}
	}
	
	/**
	 * 更新密码
	 * @param userId
	 * @param pw
	 * @return
	 */
	public boolean changePw(String userId, String pw) {
		return accountDao.updatePw(userId, pw);
	}
	
	/**
	 * 更新信息
	 * @param userId
	 * @param pw
	 * @return
	 */
	public boolean updateInfor(String privilege, String qq, String email, String telephone, String userId) {
		boolean result = false;
		if("4".equals(privilege)) {
			result = accountDao.updateInfor("Student", qq, email, telephone, userId);
		} else if("3".equals(privilege)) {
			result = accountDao.updateInfor("Teacher", qq, email, telephone, userId);
		}
		return result;
	}
}
