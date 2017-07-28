package com.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 用户实体类
 * @author kone
 * 2017-01-12
 */
@Entity
@Table(name="user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String username;
	private String password;
	private String privilege;
	
	@OneToOne(mappedBy="user",cascade={CascadeType.REFRESH,CascadeType.REMOVE})
	@Basic(fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Teacher teacher;
	public long getId() {
		return id;
	}
	
	
	public User() {
		super();
	}


	public User(long id, String username, String password, String privilege) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.privilege = privilege;
	}


	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
}
