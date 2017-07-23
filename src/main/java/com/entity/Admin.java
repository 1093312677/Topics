package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.common.BaseEntity;

@Entity
@Table(name="admin")
/**
 * 管理员实体类
 * @author kone
 * 2017-1-7
 */
public class Admin extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private int privilege;//权限
	private String userame;
	private String password;
	private String telephone;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getPrivilege() {
		return privilege;
	}
	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}
	public String getUserame() {
		return userame;
	}
	public void setUserame(String userame) {
		this.userame = userame;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
}
