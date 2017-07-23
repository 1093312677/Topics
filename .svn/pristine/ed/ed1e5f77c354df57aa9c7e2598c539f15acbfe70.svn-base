package com.dao.baseDaoFactory.mysqlDaoFactory;

import com.dao.DaoInterface;
import com.dao.baseDaoFactory.BaseDAOFactory;
import com.dao.impl.CommonDaoImpl;
import com.dao.impl.GroupDaoImpl;
import com.dao.impl.SwapDaoImpl;
import com.dao.impl.TeacherDaoImpl;

public class MysqlDAOFactory<T> extends BaseDAOFactory<T>{

	@Override
	public DaoInterface<T> getDaoInterface(String name) {
		if("GroupDao".equals(name)){
			return new GroupDaoImpl();
		}else if("CommonDao".equals(name)){
			return new CommonDaoImpl<T>();
		}else if("teacher".equals(name)){
			return new TeacherDaoImpl<T>();
		}
		else if("SwapDao".equals(name)){
			return new SwapDaoImpl();
		}
		return null;
	}

}
