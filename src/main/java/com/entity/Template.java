package com.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 模板表，上传所以与毕业设计相关的模板
 * @author kone
 * 2017.3.28
 */
@Entity
@Table(name="template")
public class Template implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String tempName;//模板名称
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	
}