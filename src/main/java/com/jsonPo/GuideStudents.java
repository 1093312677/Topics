package com.jsonPo;

public class GuideStudents {
	private String topicsName;//题目名称
	private Long id;
	private String no;//学号
	private String name;
	private String sex;
	private String qq;
	private String telephone;
	private String email;
	private String remark;
	private int swap;//是否允许调剂
	private String clazz;
	private String direction;
	private String specialty;
	private String grade;
	
	
	
	public GuideStudents() {
		super();
	}
	public GuideStudents(String topicsName, int id, String no, String name, String sex, String qq, String telephone,
			String email, String remark, int swap, String clazz, String direction, String specialty, String grade) {
		super();
		this.topicsName = topicsName;
		this.no = no;
		this.name = name;
		this.sex = sex;
		this.qq = qq;
		this.telephone = telephone;
		this.email = email;
		this.remark = remark;
		this.swap = swap;
		this.clazz = clazz;
		this.direction = direction;
		this.specialty = specialty;
		this.grade = grade;
	}
	public String getTopicsName() {
		return topicsName;
	}
	public void setTopicsName(String topicsName) {
		this.topicsName = topicsName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getSwap() {
		return swap;
	}
	public void setSwap(int swap) {
		this.swap = swap;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
}
