package com.dao.baseDaoFactory;

import com.dao.DaoInterface;
import com.dao.baseDaoFactory.mysqlDaoFactory.MysqlDAOFactory;

public abstract class BaseDAOFactory<T>{
	public static BaseDAOFactory<Object> getInstance(){
		return new MysqlDAOFactory<Object>();
	}
	public abstract DaoInterface<T> getDaoInterface(String name);
	
}
