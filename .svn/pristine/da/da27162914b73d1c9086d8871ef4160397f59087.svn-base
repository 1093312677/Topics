package com.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 关于一些文档的上传
 * @author kone
 * 2017.4.19
 */
@Entity
@Table(name="document")
public class Document {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String documentName;
	private String randName;
	
//	和系实现多对一
	@ManyToOne
	@JoinColumn( name = "departmentId")
	@Basic(fetch=FetchType.LAZY)
	private Department department;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getRandName() {
		return randName;
	}

	public void setRandName(String randName) {
		this.randName = randName;
	}
	
	
}
