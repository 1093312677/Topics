package com.dto;

public class AttachDTO {
	private String no;//学号
	private String name;
	private String openingReport;//开题报告
	private String interimReport;//中期报告
	private String fileName;//毕业提交文件路径
	
	private String interimEvalForm;//指导教师评价表
	private String reviewEvalForm;//评阅老师评价表
	private String reviewTable;//	组长评阅表
	private String replyRecord;//	答辩记录表
	
	private String topicsUrl;//题目路径
	private String subTopicUrl;//子题目路径
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getInterimEvalForm() {
		return interimEvalForm;
	}
	public void setInterimEvalForm(String interimEvalForm) {
		this.interimEvalForm = interimEvalForm;
	}
	public String getReviewEvalForm() {
		return reviewEvalForm;
	}
	public void setReviewEvalForm(String reviewEvalForm) {
		this.reviewEvalForm = reviewEvalForm;
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
	public String getTopicsUrl() {
		return topicsUrl;
	}
	public void setTopicsUrl(String topicsUrl) {
		this.topicsUrl = topicsUrl;
	}
	public String getSubTopicUrl() {
		return subTopicUrl;
	}
	public void setSubTopicUrl(String subTopicUrl) {
		this.subTopicUrl = subTopicUrl;
	}
	
	
}
