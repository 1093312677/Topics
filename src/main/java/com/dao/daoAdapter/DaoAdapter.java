package com.dao.daoAdapter;

import java.util.List;

import com.dao.DaoInterface;

public class DaoAdapter<T> implements DaoInterface<T>{

	@Override
	public boolean save(T entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(T entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<T> view(String gradeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean calcelGroup(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(T entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean swapInDepart(String state, String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean swapInTeacher(String state, String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean leavMessage(String id, String message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List findTeacherGroup(String teacherId) {
		return null;
	}

	@Override
	public List findStuTeachGroup(long gradeId, long teacherId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findBy(String table, String col, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTeacherNum(long departmentId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List view(String id, int page, int eachPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getTeacherStuNum(String gradeId, long teacherId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNum(String table, String col, String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List findBy(String table, String col, String value, int eachPage, int page) {
		// TODO Auto-generated method stub
		return null;
	}


}
