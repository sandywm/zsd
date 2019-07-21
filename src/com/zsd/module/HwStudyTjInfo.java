package com.zsd.module;

/**
 * HwStudyTjInfo entity. @author MyEclipse Persistence Tools
 */

public class HwStudyTjInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private SendHwInfo sendHwInfo;
	private User user;
	private Integer comStatus;
	private String comDate;
	private Integer hwScore;
	private Integer succNum;
	private Integer errorNum;
	private Integer allNum;

	// Constructors

	/** default constructor */
	public HwStudyTjInfo() {
	}

	/** full constructor */
	public HwStudyTjInfo(SendHwInfo sendHwInfo, User user, Integer comStatus,
			String comDate, Integer hwScore, Integer succNum, Integer errorNum,
			Integer allNum) {
		this.sendHwInfo = sendHwInfo;
		this.user = user;
		this.comStatus = comStatus;
		this.comDate = comDate;
		this.hwScore = hwScore;
		this.succNum = succNum;
		this.errorNum = errorNum;
		this.allNum = allNum;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SendHwInfo getSendHwInfo() {
		return this.sendHwInfo;
	}

	public void setSendHwInfo(SendHwInfo sendHwInfo) {
		this.sendHwInfo = sendHwInfo;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getComStatus() {
		return this.comStatus;
	}

	public void setComStatus(Integer comStatus) {
		this.comStatus = comStatus;
	}

	public String getComDate() {
		return this.comDate;
	}

	public void setComDate(String comDate) {
		this.comDate = comDate;
	}

	public Integer getHwScore() {
		return this.hwScore;
	}

	public void setHwScore(Integer hwScore) {
		this.hwScore = hwScore;
	}

	public Integer getSuccNum() {
		return this.succNum;
	}

	public void setSuccNum(Integer succNum) {
		this.succNum = succNum;
	}

	public Integer getErrorNum() {
		return this.errorNum;
	}

	public void setErrorNum(Integer errorNum) {
		this.errorNum = errorNum;
	}

	public Integer getAllNum() {
		return this.allNum;
	}

	public void setAllNum(Integer allNum) {
		this.allNum = allNum;
	}
}