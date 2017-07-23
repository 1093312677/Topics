package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.IGradeDao;
import com.entity.Grade;

/**
 * 关于年级的逻辑处理
 * @author kone
 *	2017.7.19
 */
@Service
public class GradeService {
	
	@Autowired
	private IGradeDao gradeDao;
	
	public List<Grade> viewGrades(Long departmentId) {
		return gradeDao.viewGrades(departmentId);
	}
	 
}
