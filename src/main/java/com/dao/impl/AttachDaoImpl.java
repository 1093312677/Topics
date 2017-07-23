package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dao.daoAdapter.DaoAdapter;
/**
 * 附件提交持久层（开题报告，中期报告）
 * @author kone
 * 2017-4-13
 */
@Repository
public class AttachDaoImpl extends DaoAdapter{
	private Session session;
	private String hql = "";
	public void setSession(Session session){
		this.session = session;
	}
	
	public List findByTwo(String table, String col, String value, String col2, String value2) {
		hql = "from "+table+" where "+col+"='"+value+"' AND "+col2+" ='"+value2+"'";
		return session.createQuery(hql).list();
	}
	
	@Override
	public List findBy(String table, String col, String value) {
		hql ="FROM "+table+" WHERE "+col+"="+value;
		Query query = session.createQuery(hql);
		return query.list();
	}
}
