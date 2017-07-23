package com.entity;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 设置相关信息
 * @author kone
 *
 */
@Entity
@Table(name="setting")
public class Setting {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String commitTopicStartTime;// 提交题目开始时间
	private String commitTopicEndTime;// 提交题目结束时间
	private String commitAttachStartTime;//毕业论文提交时间
	private String commitAttachEndTime;//毕业论文提交结束时间
	
	private String oneSelectStartTime;// 学生选题开始时间          (第一次)
	private String oneSelectEndTimeOne;// 学生选题结束时间        (第一次)
	private String oneAdmissionStartTime;//教师录取志愿起始时间  (第一次)
	private String oneFirstChoiceDeadline;// 第一志愿录取截止时间  (第一次)
	private String oneSecondChoiceDeadline;// 第二志愿录取截止时间  (第一次)
	private String oneThirdChoiceDeadline;// 第三志愿录取截止时间  (第一次)
	
	private String twoSelectStartTime;// 学生选题开始时间          (第二次)
	private String twoSelectEndTimeOne;// 学生选题结束时间        (第二次)
	private String twoAdmissionStartTime;//教师录取志愿起始时间  (第二次)
	private String twoFirstChoiceDeadline;// 第一志愿录取截止时间  (第二次)
	private String twoSecondChoiceDeadline;// 第二志愿录取截止时间  (第二次)
	private String twoThirdChoiceDeadline;// 第三志愿录取截止时间  (第二次)
	
//	private String threeSelectStartTime;// 学生选题开始时间          (第三次)
//	private String threeSelectEndTimeOne;// 学生选题结束时间        (第三次)
//	private String threeAdmissionStartTime;//教师录取志愿起始时间  (第三次)
//	private String threeFirstChoiceDeadline;// 第一志愿录取截止时间  (第三次)
//	private String threeSecondChoiceDeadline;// 第二志愿录取截止时间  (第三次)
//	private String threeThirdChoiceDeadline;// 第三志愿录取截止时间  (第三次)
	
	
	private String checkStartTime;// 中期检查时间开始
	private String checkEndTime;// 中期检查时间结束
	
	private String defenceStartTime;// 答辩开始时间
	private String defenceEndTime;// 答辩结束时间
	
	private String openReportStartTime;//开题报告提交开始时间
	private String openReportEndTime;//开题报告提交结束时间
	
	private String midReportStartTime;//中期告提交开始时间
	private String midReportEndTime;//中期报告提交结束时间
	
	private String midReviewStartTime;//中期评阅开始时间
	private String midReviewEndTime;//中期评阅结束时间
	
	private String replyResultsStartTime;//答辩结果提交开始时间
	private String replyResultsEndTime;//答辩结果提交结束时间
	
	private String instructorReviewEndTime;//指导教师评阅提交开始时间
	private String instructorReviewStartTime;//指导教师评阅提交结束时间
	
	
	
	
	private String selectTopicMethod;// 选题方式
	private String obedienceAllocation;//是否服从调配
	
//	和年级实现一对一
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="gradeId")
	private Grade grade;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCommitTopicStartTime() {
		return commitTopicStartTime;
	}
	public void setCommitTopicStartTime(String commitTopicStartTime) {
		this.commitTopicStartTime = commitTopicStartTime;
	}
	public String getCommitTopicEndTime() {
		return commitTopicEndTime;
	}
	public void setCommitTopicEndTime(String commitTopicEndTime) {
		this.commitTopicEndTime = commitTopicEndTime;
	}
	public String getCommitAttachStartTime() {
		return commitAttachStartTime;
	}
	public void setCommitAttachStartTime(String commitAttachStartTime) {
		this.commitAttachStartTime = commitAttachStartTime;
	}
	public String getCommitAttachEndTime() {
		return commitAttachEndTime;
	}
	public void setCommitAttachEndTime(String commitAttachEndTime) {
		this.commitAttachEndTime = commitAttachEndTime;
	}
	public String getCheckStartTime() {
		return checkStartTime;
	}
	public void setCheckStartTime(String checkStartTime) {
		this.checkStartTime = checkStartTime;
	}
	public String getCheckEndTime() {
		return checkEndTime;
	}
	public void setCheckEndTime(String checkEndTime) {
		this.checkEndTime = checkEndTime;
	}
	public String getDefenceStartTime() {
		return defenceStartTime;
	}
	public void setDefenceStartTime(String defenceStartTime) {
		this.defenceStartTime = defenceStartTime;
	}
	public String getDefenceEndTime() {
		return defenceEndTime;
	}
	public void setDefenceEndTime(String defenceEndTime) {
		this.defenceEndTime = defenceEndTime;
	}
	public String getSelectTopicMethod() {
		return selectTopicMethod;
	}
	public void setSelectTopicMethod(String selectTopicMethod) {
		this.selectTopicMethod = selectTopicMethod;
	}
	public String getObedienceAllocation() {
		return obedienceAllocation;
	}
	public void setObedienceAllocation(String obedienceAllocation) {
		this.obedienceAllocation = obedienceAllocation;
	}
	public String getOneSelectStartTime() {
		return oneSelectStartTime;
	}
	public void setOneSelectStartTime(String oneSelectStartTime) {
		this.oneSelectStartTime = oneSelectStartTime;
	}
	public String getOneSelectEndTimeOne() {
		return oneSelectEndTimeOne;
	}
	public void setOneSelectEndTimeOne(String oneSelectEndTimeOne) {
		this.oneSelectEndTimeOne = oneSelectEndTimeOne;
	}
	public String getOneAdmissionStartTime() {
		return oneAdmissionStartTime;
	}
	public void setOneAdmissionStartTime(String oneAdmissionStartTime) {
		this.oneAdmissionStartTime = oneAdmissionStartTime;
	}
	public String getOneFirstChoiceDeadline() {
		return oneFirstChoiceDeadline;
	}
	public void setOneFirstChoiceDeadline(String oneFirstChoiceDeadline) {
		this.oneFirstChoiceDeadline = oneFirstChoiceDeadline;
	}
	public String getOneSecondChoiceDeadline() {
		return oneSecondChoiceDeadline;
	}
	public void setOneSecondChoiceDeadline(String oneSecondChoiceDeadline) {
		this.oneSecondChoiceDeadline = oneSecondChoiceDeadline;
	}
	public String getOneThirdChoiceDeadline() {
		return oneThirdChoiceDeadline;
	}
	public void setOneThirdChoiceDeadline(String oneThirdChoiceDeadline) {
		this.oneThirdChoiceDeadline = oneThirdChoiceDeadline;
	}
	public String getTwoSelectStartTime() {
		return twoSelectStartTime;
	}
	public void setTwoSelectStartTime(String twoSelectStartTime) {
		this.twoSelectStartTime = twoSelectStartTime;
	}
	public String getTwoSelectEndTimeOne() {
		return twoSelectEndTimeOne;
	}
	public void setTwoSelectEndTimeOne(String twoSelectEndTimeOne) {
		this.twoSelectEndTimeOne = twoSelectEndTimeOne;
	}
	public String getTwoAdmissionStartTime() {
		return twoAdmissionStartTime;
	}
	public void setTwoAdmissionStartTime(String twoAdmissionStartTime) {
		this.twoAdmissionStartTime = twoAdmissionStartTime;
	}
	public String getTwoFirstChoiceDeadline() {
		return twoFirstChoiceDeadline;
	}
	public void setTwoFirstChoiceDeadline(String twoFirstChoiceDeadline) {
		this.twoFirstChoiceDeadline = twoFirstChoiceDeadline;
	}
	public String getTwoSecondChoiceDeadline() {
		return twoSecondChoiceDeadline;
	}
	public void setTwoSecondChoiceDeadline(String twoSecondChoiceDeadline) {
		this.twoSecondChoiceDeadline = twoSecondChoiceDeadline;
	}
	public String getTwoThirdChoiceDeadline() {
		return twoThirdChoiceDeadline;
	}
	public void setTwoThirdChoiceDeadline(String twoThirdChoiceDeadline) {
		this.twoThirdChoiceDeadline = twoThirdChoiceDeadline;
	}
//	public String getThreeSelectStartTime() {
//		return threeSelectStartTime;
//	}
//	public void setThreeSelectStartTime(String threeSelectStartTime) {
//		this.threeSelectStartTime = threeSelectStartTime;
//	}
//	public String getThreeSelectEndTimeOne() {
//		return threeSelectEndTimeOne;
//	}
//	public void setThreeSelectEndTimeOne(String threeSelectEndTimeOne) {
//		this.threeSelectEndTimeOne = threeSelectEndTimeOne;
//	}
//	public String getThreeAdmissionStartTime() {
//		return threeAdmissionStartTime;
//	}
//	public void setThreeAdmissionStartTime(String threeAdmissionStartTime) {
//		this.threeAdmissionStartTime = threeAdmissionStartTime;
//	}
//	public String getThreeFirstChoiceDeadline() {
//		return threeFirstChoiceDeadline;
//	}
//	public void setThreeFirstChoiceDeadline(String threeFirstChoiceDeadline) {
//		this.threeFirstChoiceDeadline = threeFirstChoiceDeadline;
//	}
//	public String getThreeSecondChoiceDeadline() {
//		return threeSecondChoiceDeadline;
//	}
//	public void setThreeSecondChoiceDeadline(String threeSecondChoiceDeadline) {
//		this.threeSecondChoiceDeadline = threeSecondChoiceDeadline;
//	}
//	public String getThreeThirdChoiceDeadline() {
//		return threeThirdChoiceDeadline;
//	}
//	public void setThreeThirdChoiceDeadline(String threeThirdChoiceDeadline) {
//		this.threeThirdChoiceDeadline = threeThirdChoiceDeadline;
//	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public String getOpenReportStartTime() {
		return openReportStartTime;
	}
	public void setOpenReportStartTime(String openReportStartTime) {
		this.openReportStartTime = openReportStartTime;
	}
	public String getOpenReportEndTime() {
		return openReportEndTime;
	}
	public void setOpenReportEndTime(String openReportEndTime) {
		this.openReportEndTime = openReportEndTime;
	}
	public String getMidReportStartTime() {
		return midReportStartTime;
	}
	public void setMidReportStartTime(String midReportStartTime) {
		this.midReportStartTime = midReportStartTime;
	}
	public String getMidReportEndTime() {
		return midReportEndTime;
	}
	public void setMidReportEndTime(String midReportEndTime) {
		this.midReportEndTime = midReportEndTime;
	}
	public String getMidReviewStartTime() {
		return midReviewStartTime;
	}
	public void setMidReviewStartTime(String midReviewStartTime) {
		this.midReviewStartTime = midReviewStartTime;
	}
	public String getMidReviewEndTime() {
		return midReviewEndTime;
	}
	public void setMidReviewEndTime(String midReviewEndTime) {
		this.midReviewEndTime = midReviewEndTime;
	}
	public String getReplyResultsStartTime() {
		return replyResultsStartTime;
	}
	public void setReplyResultsStartTime(String replyResultsStartTime) {
		this.replyResultsStartTime = replyResultsStartTime;
	}
	public String getReplyResultsEndTime() {
		return replyResultsEndTime;
	}
	public void setReplyResultsEndTime(String replyResultsEndTime) {
		this.replyResultsEndTime = replyResultsEndTime;
	}
	public String getInstructorReviewEndTime() {
		return instructorReviewEndTime;
	}
	public void setInstructorReviewEndTime(String instructorReviewEndTime) {
		this.instructorReviewEndTime = instructorReviewEndTime;
	}
	public String getInstructorReviewStartTime() {
		return instructorReviewStartTime;
	}
	public void setInstructorReviewStartTime(String instructorReviewStartTime) {
		this.instructorReviewStartTime = instructorReviewStartTime;
	}
	
	
	
}
