package com.zsd.module;

/**
 * LoreQuestionErrorInfo entity. @author MyEclipse Persistence Tools
 */

public class LoreQuestionErrorInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LoreQuestion loreQuestion;
	private User user;
	private String errorType;
	private String content;
	private String addDate;
	private Integer coin;
	private String operateUserName;
	private String operateDate;
	private Integer checkStatus;
	private String remark;

	// Constructors

	/** default constructor */
	public LoreQuestionErrorInfo() {
	}

	/** full constructor */
	public LoreQuestionErrorInfo(LoreQuestion loreQuestion, User user,
			String errorType, String content, String addDate, Integer checkStatus, Integer coin,
			String operateUserName, String operateDate,String remark) {
		this.loreQuestion = loreQuestion;
		this.user = user;
		this.errorType = errorType;
		this.content = content;
		this.addDate = addDate;
		this.checkStatus = checkStatus;
		this.coin = coin;
		this.operateUserName = operateUserName;
		this.operateDate = operateDate;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LoreQuestion getLoreQuestion() {
		return this.loreQuestion;
	}

	public void setLoreQuestion(LoreQuestion loreQuestion) {
		this.loreQuestion = loreQuestion;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getErrorType() {
		return this.errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddDate() {
		return this.addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public Integer getCoin() {
		return this.coin;
	}

	public void setCoin(Integer coin) {
		this.coin = coin;
	}

	public String getOperateUserName() {
		return this.operateUserName;
	}

	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}

	public String getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}