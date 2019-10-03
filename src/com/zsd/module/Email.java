package com.zsd.module;

/**
 * Email entity. @author MyEclipse Persistence Tools
 */

public class Email implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User userByToUserId;
	private User userBySendUserId;
	private String emailTitle;
	private String emailContent;
	private String sendTime;
	private String emailType;
	private Integer readStatus;

	// Constructors

	/** default constructor */
	public Email() {
	}

	/** full constructor */
	public Email(User userBySendUserId, User userByToUserId, String emailTitle,
			String emailContent, String sendTime, String emailType,Integer readStatus) {
		this.userByToUserId = userByToUserId;
		this.userBySendUserId = userBySendUserId;
		this.emailTitle = emailTitle;
		this.emailContent = emailContent;
		this.sendTime = sendTime;
		this.emailType = emailType;
		this.readStatus = readStatus;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserByToUserId() {
		return this.userByToUserId;
	}

	public void setUserByToUserId(User userByToUserId) {
		this.userByToUserId = userByToUserId;
	}

	public User getUserBySendUserId() {
		return this.userBySendUserId;
	}

	public void setUserBySendUserId(User userBySendUserId) {
		this.userBySendUserId = userBySendUserId;
	}

	public String getEmailTitle() {
		return this.emailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}

	public String getEmailContent() {
		return this.emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public String getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getEmailType() {
		return this.emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public Integer getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}

}