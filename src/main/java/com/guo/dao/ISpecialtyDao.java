package com.guo.dao;

import java.util.List;

import com.entity.Specialty;

public interface ISpecialtyDao {
	public Specialty get(int specialtyId);
	public void closeSession();
	public int updateInfo(Specialty specialty);
	public List<Specialty> inspection(String specialtyName,long gradeId);
}
