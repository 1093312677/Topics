package com.guo.dao;

import java.util.List;

import com.entity.User;

public interface IUserDao {
	public User get(String username);
	public int update(String newpw,User user);
	public List<User> alluser();
	public void closeSession();
	public int resetPw(long userID);
	public void addAdmin(User user);
	public List<User> inspection(String username);
}
