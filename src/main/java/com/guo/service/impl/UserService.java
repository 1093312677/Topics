package com.guo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.User;
import com.guo.dao.IUserDao;
import com.guo.service.IUserService;
@Service(value="userService1")
public class UserService implements IUserService {
	@Resource(name="userDao1")
	IUserDao userDao;
	@Override
	public int update(String newpw, String oldpw,String username) {
		int temp=0;
		User user=userDao.get(username);
		if(user.getPassword().equals(oldpw)){
			userDao.update(newpw, user);
			temp=1;
		}
		return temp;
	}
	@Override
	public List<User> alluser() {
		return userDao.alluser();
	}
	@Override
	public void closeSession() {
		userDao.closeSession();
	}
	@Override
	public void addAdmin(User user) {
		userDao.addAdmin(user);
	}
	@Override
	public int resetPw(long userID) {
		userDao.resetPw(userID);
		return 0;
	}
	@Override
	public int inspection(String userName) {
		int n=0;
		List<User>users=userDao.inspection(userName);
		if(users.size()>0) n=1;
		return n;
	}

	

}
