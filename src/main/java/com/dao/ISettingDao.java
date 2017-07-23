package com.dao;

import com.entity.Setting;

public interface ISettingDao {
	
	/**
	 * 查询设置
	 * @param gradeId
	 * @return
	 */
	public Setting getSetting(Long gradeId);
}
