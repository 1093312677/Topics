package com.dao;

import com.entity.Form;

public interface IFormDao {
	/**
	 * 学生查看自己的form
	 * @param studentId
	 * @return
	 */
	public Form getStudentForm(Long studentId);
	
	
}
