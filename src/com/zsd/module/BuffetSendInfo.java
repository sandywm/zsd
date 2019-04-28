package com.zsd.module;


/**
 * BuffetSendInfo entity. @author MyEclipse Persistence Tools
 */

public class BuffetSendInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private StudyLogInfo studyLogInfo;
	private User user;
	private String sendTime;
	private Integer studyResult;
	private Integer sendMode;
	private Integer comNumber;
	private Integer sendNumber;

	// Constructors

	/** default constructor */
	public BuffetSendInfo() {
	}


	/** full constructor */
	public BuffetSendInfo(StudyLogInfo studyLogInfo, User user,
			String sendTime, Integer studyResult, Integer sendMode,
			Integer comNumber, Integer sendNumber) {
		this.studyLogInfo = studyLogInfo;
		this.user = user;
		this.sendTime = sendTime;
		this.studyResult = studyResult;
		this.sendMode = sendMode;
		this.comNumber = comNumber;
		this.sendNumber = sendNumber;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StudyLogInfo getStudyLogInfo() {
		return this.studyLogInfo;
	}

	public void setStudyLogInfo(StudyLogInfo studyLogInfo) {
		this.studyLogInfo = studyLogInfo;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getStudyResult() {
		return this.studyResult;
	}

	public void setStudyResult(Integer studyResult) {
		this.studyResult = studyResult;
	}

	public Integer getSendMode() {
		return this.sendMode;
	}

	public void setSendMode(Integer sendMode) {
		this.sendMode = sendMode;
	}

	public Integer getComNumber() {
		return this.comNumber;
	}

	public void setComNumber(Integer comNumber) {
		this.comNumber = comNumber;
	}

	public Integer getSendNumber() {
		return this.sendNumber;
	}

	public void setSendNumber(Integer sendNumber) {
		this.sendNumber = sendNumber;
	}

}