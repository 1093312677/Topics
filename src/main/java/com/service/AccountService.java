package com.service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.Information;
import com.common.RedisTool;
import com.common.ServerResponse;
import com.dao.AccountDao;
import com.dao.IDao;
import com.dao.impl.DaoImpl;
import com.entity.Clazz;
import com.entity.Department;
import com.entity.Direction;
import com.entity.Grade;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.User;
@Service
public class AccountService<T> {
	@Resource
	private IDao daoImpl;
	@Autowired
	private AccountDao accountDao;
	public void setDaoImpl(DaoImpl<T> daoImpl) {
		this.daoImpl = daoImpl;
	}
	public String login(){
		
		return "yes";
	}
	
	private Logger logger = Logger.getLogger(AccountService.class);
	/**
	 * save Entity
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
	@Transactional
	public User login(User user) {
		if(user == null || user.getUsername() == null) {
			return null;
		}
		
		User user1 = accountDao.login(user.getUsername());
		if(user1 != null){
			if(user1.getPassword() != null && user.getPassword() != null) {
				if(user1.getPassword().equals(user.getPassword())){
					return user1;
				}else{
					return null;
				}
			} else {
				return null;
			}
			
		}else{
			return null;
		}
	}
	
	/**
	 * 获取学生的基本信息
	 * @param no
	 * @return
	 */
	public Student getStudentInfor(String no) {
		return accountDao.getStudentInfo(no);
	}
	
	/**
	 * 通过学生id获取该学的班级
	 * @param studentId
	 * @return
	 */
	public Clazz getClassByStudentId(Long studentId) {
		
		return accountDao.getClazzByStudentId(studentId);
	}
	
	/**
	 * 通过班级id获取方向信息
	 * @param clazzId
	 * @return
	 */
	public Direction getDirectionByClazzId(Long clazzId) {
		Direction direction = null;
		
		String key = "direction"+clazzId;
		Object obj= null;
		obj = RedisTool.getReids(key);
		if(obj == null) {
			direction = accountDao.getDirectionByClazzId(clazzId);
			RedisTool.setRedis(key, 60*60, direction);
		} else {
			direction = (Direction) RedisTool.getReids(key);
		}
		return direction;
	}
	
	/**
	 * 通过方向id获取年级信息
	 * @param directionId
	 * @return
	 */
	public Grade getGradeByDirectionId(Long directionId) {
		Grade grade = null;
		String key = "grade"+directionId;
		Object obj= null;
		obj = RedisTool.getReids(key);
		if(obj == null) {
			grade = accountDao.getGradeByDirectionId(directionId);
			RedisTool.setRedis(key, 60*60, grade);
		} else {
			grade = (Grade) RedisTool.getReids(key);
		}
		
		return grade;
	}
	
	/**
	 * 通过方向id获取年级信息
	 * @return
	 */
	public Department getDepartmentByGradeId(Long gradeId) {
		Department department = null;
		String key = "department"+gradeId;
		Object obj= null;
		obj = RedisTool.getReids(key);
		if(obj == null) {
			logger.info("query department from database "+gradeId);
			department = accountDao.getDepartmentByGradeId(gradeId);
			RedisTool.setRedis(key, 60*60, department);
		} else {
			logger.info("query department from redis "+gradeId);
			department = (Department) RedisTool.getReids(key);
		}
		
		return department;
	}
	
	/**
	 * loginApp
	 * @param user
	 * @return
	 */
	public ServerResponse<Information> loginApp(User user) {
		List<User> users = (List<User>) daoImpl.login(user);
		
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

	private static char[] chs = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
	private static final int NUMBER_OF_CHS = 4;
    private static final int IMG_WIDTH = 65;
    private static final int IMG_HEIGHT = 25;
	/**
	 * 获取验证码
	 * @return
	 */
	public String getRandomCode(HttpServletResponse response) {
		BufferedImage img = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		Color c = new Color(255, 255, 255);
		g.setColor(c);
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);

		Random r = new Random();
		StringBuffer sb = new StringBuffer();                                           // 用于保存验证码字符串
		int index;                                                                      // 数组的下标
		for (int i = 0; i < NUMBER_OF_CHS; i++) {
			index = r.nextInt(chs.length);                                              // 随机一个下标
			g.setColor(new Color(r.nextInt(88), r.nextInt(210), r.nextInt(150)));       // 随机一个颜色
			g.drawString(chs[index] + "", 15 * i + 3, 18);                              // 画出字符
			sb.append(chs[index]);                                                      // 验证码字符串
		}

//		产生干扰点
		for (int i=0;i<50;i++) {
			int x = r.nextInt(IMG_WIDTH);
			int y = r.nextInt(IMG_HEIGHT);
			g.setColor(getColor());
			g.drawOval(x,y,1,1);
		}

// 随机产生干扰线条
		for (int i=0;i<r.nextInt(3)+3;i++) {
			int x1 = r.nextInt(IMG_WIDTH)%15;
			int y1 = r.nextInt(IMG_HEIGHT);
			int x2 = (int) (r.nextInt(IMG_WIDTH)%40+IMG_WIDTH*0.7);
			int y2 = r.nextInt(IMG_HEIGHT);
			g.setColor(getColor());
			g.drawLine(x1, y1, x2, y2);
		}

		try {
			ImageIO.write(img, "jpg", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/***
	 * @return 随机返回一种颜色
	 */
	private Color getColor()
	{
		int R=(int) (Math.random()*255);
		int G=(int) (Math.random()*255);
		int B=(int) (Math.random()*255);
		return new Color(R,G,B);
	}


	/**
	 * 验证验证码
	 * @param request
	 * @param session
	 */
	public void checkCode(HttpServletRequest request, HttpSession session, HttpServletResponse response, String code) {
		if(null == code || "".equals(code) || code.length() != 4) {
			try {
				request.setAttribute("loginMessage", "errorCode");
				request.getRequestDispatcher("../index.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if(code.length() == 4) {
				char [] arrs = code.toCharArray();
				String codeT = (String) session.getAttribute("code");
				char [] arrs2 = codeT.toCharArray();
				boolean flag = true;
				for(int i=0;i<4;i++) {
					if(!String.valueOf(arrs[i]).toLowerCase().equals(String.valueOf(arrs2[i]).toLowerCase())) {
						flag = false;
					}
				}

				if(!flag) {
					try {
						request.setAttribute("loginMessage", "errorCode");
						request.getRequestDispatcher("../index.jsp").forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		}
		return;
	}
}
