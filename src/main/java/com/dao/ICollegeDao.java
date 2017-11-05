package com.dao;

import com.entity.College;
import com.entity.Department;

public interface ICollegeDao {
	/**
	 * 查看学院详情
	 * @param collegeId
	 * @return
	 */
	public College viewCollege(Long collegeId);
	
	
}
