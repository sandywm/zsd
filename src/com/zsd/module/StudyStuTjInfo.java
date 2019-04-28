package com.zsd.module;

/**
 * StudyStuTjInfo entity. @author MyEclipse Persistence Tools
 */

public class StudyStuTjInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Subject subject;
	private User user;
	private Integer liaojieTotalNum;
	private Integer liaojieSuccNum;
	private Integer lijieTotalNum;
	private Integer lijieSuccNum;
	private Integer yyTotalNum;
	private Integer yySuccNum;
	private String studyDate;

	// Constructors

	/** default constructor */
	public StudyStuTjInfo() {
	}

	/** full constructor */
	public StudyStuTjInfo(Subject subject, User user, Integer liaojieTotalNum,
			Integer liaojieSuccNum, Integer lijieTotalNum,
			Integer lijieSuccNum, Integer yyTotalNum, Integer yySuccNum,
			String studyDate) {
		this.subject = subject;
		this.user = user;
		this.liaojieTotalNum = liaojieTotalNum;
		this.liaojieSuccNum = liaojieSuccNum;
		this.lijieTotalNum = lijieTotalNum;
		this.lijieSuccNum = lijieSuccNum;
		this.yyTotalNum = yyTotalNum;
		this.yySuccNum = yySuccNum;
		this.studyDate = studyDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getLiaojieTotalNum() {
		return this.liaojieTotalNum;
	}

	public void setLiaojieTotalNum(Integer liaojieTotalNum) {
		this.liaojieTotalNum = liaojieTotalNum;
	}

	public Integer getLiaojieSuccNum() {
		return this.liaojieSuccNum;
	}

	public void setLiaojieSuccNum(Integer liaojieSuccNum) {
		this.liaojieSuccNum = liaojieSuccNum;
	}

	public Integer getLijieTotalNum() {
		return this.lijieTotalNum;
	}

	public void setLijieTotalNum(Integer lijieTotalNum) {
		this.lijieTotalNum = lijieTotalNum;
	}

	public Integer getLijieSuccNum() {
		return this.lijieSuccNum;
	}

	public void setLijieSuccNum(Integer lijieSuccNum) {
		this.lijieSuccNum = lijieSuccNum;
	}

	public Integer getYyTotalNum() {
		return this.yyTotalNum;
	}

	public void setYyTotalNum(Integer yyTotalNum) {
		this.yyTotalNum = yyTotalNum;
	}

	public Integer getYySuccNum() {
		return this.yySuccNum;
	}

	public void setYySuccNum(Integer yySuccNum) {
		this.yySuccNum = yySuccNum;
	}

	public String getStudyDate() {
		return this.studyDate;
	}

	public void setStudyDate(String studyDate) {
		this.studyDate = studyDate;
	}

}