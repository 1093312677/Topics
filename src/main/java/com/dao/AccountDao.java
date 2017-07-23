package com.dao;

import com.entity.User;

public interface AccountDao {
	public boolean updatePw(String userId, String pw);
	public boolean updateInfor(String table,String qq, String email, String telephone, String userId);
	
	/**
	 * 登录，先查询是否存在该用户名
	 * @param username
	 * @return
	 */
	public User login(String username);
}
