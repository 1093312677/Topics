package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 所以提交的文档
 * @author kone
 * 2017.3.28
 */
@Entity
@Table(name="form")
public class Form {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String openingReport;//开题报告
	private String interimReport;//中期报告
	private String fileName;//毕业提交文件路径
	
	private String interimEvalForm;//指导教师评价表
	private String reviewEvalForm;//评阅老师评价表
	private String reviewTable;//	组长评阅表
	private String replyRecord;//	答辩记录表
	
	private String time;//提交文件时间
//	和学生表实现一对一
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="studentId")
	private Student student;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOpeningReport() {
		return openingReport;
	}
	public void setOpeningReport(String openingReport) {
		this.openingReport = openingReport;
	}
	public String getInterimReport() {
		return interimReport;
	}
	public void setInterimReport(String interimReport) {
		this.interimReport = interimReport;
	}
	public String getInterimEvalForm() {
		return interimEvalForm;
	}
	public void setInterimEvalForm(String interimEvalForm) {
		this.interimEvalForm = interimEvalForm;
	}
	public String getReviewTable() {
		return reviewTable;
	}
	public void setReviewTable(String reviewTable) {
		this.reviewTable = reviewTable;
	}
	public String getReplyRecord() {
		return replyRecord;
	}
	public void setReplyRecord(String replyRecord) {
		this.replyRecord = replyRecord;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getReviewEvalForm() {
		return reviewEvalForm;
	}
	public void setReviewEvalForm(String reviewEvalForm) {
		this.reviewEvalForm = reviewEvalForm;
	}
	
	
}
