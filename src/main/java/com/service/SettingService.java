package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.RedisTool;
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
	
	/**
	 * 教师或者学生查看时间，不需要时时更新，使用缓存
	 * @param gradeId
	 * @return
	 */
	public Setting getSetting(Long gradeId) {
		String key = "setting"+gradeId;
		
		Object obj= null;
		obj = RedisTool.getReids(key);
		
		Setting setting = null;
		if(obj == null) {
			setting = settingDao.getSetting(gradeId);
			setting.setGrade(null);
			RedisTool.setRedis(key, 60*60, setting);
		} else {
			setting = (Setting) RedisTool.getReids(key);
		}
		
		return setting;
	}
	
	/**
	 * 系主任查看时间，要时时更新，不使用缓存
	 * @param gradeId
	 * @return
	 */
	public Setting getSettingDean(Long gradeId) {
		Setting setting = null;
		setting = settingDao.getSetting(gradeId);
		return setting;
	}
	 
}
