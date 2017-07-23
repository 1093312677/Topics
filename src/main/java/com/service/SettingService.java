package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ISettingDao;
import com.entity.Setting;

/**
 * 关于设置的逻辑处理
 * @author kone
 *	2017.7.19
 */
@Service
public class SettingService {
	
	@Autowired
	private ISettingDao settingDao;
	
	public Setting getSetting(Long gradeId) {
		return settingDao.getSetting(gradeId);
	}
	 
}
