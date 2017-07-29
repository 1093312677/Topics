package com.dao;

import java.util.List;

import com.entity.Form;

public interface IFormDao {
	/**
	 * 学生查看自己的form
	 * @param studentId
	 * @return
	 */
	public Form getStudentForm(Long studentId);
	
	/**
	 * 更新
	 * @param form
	 * @return
	 */
	public boolean updateForm(Form form);
	
	/**
	 * 保存form
	 * @param form
	 * @return
	 */
	public boolean saveForm(Form form);
	
}
