package com.zsd.module;

/**
 * QuestionInfo entity. @author MyEclipse Persistence Tools
 */

public class QuestionInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Subject subject;
	private User user;
	private NetTeacherInfo netTeacherInfo;
	private String queTitle;
	private String queContent;
	private String queTime;
	private String queReplyContent;
	private String queReplyTime;
	private Integer readStatus;

	// Constructors

	/** default constructor */
	public QuestionInfo() {
	}

	/** minimal constructor */
	public QuestionInfo(Subject subject, User user,
			NetTeacherInfo netTeacherInfo, String queTitle, String queContent,
			String queTime, Integer readStatus) {
		this.subject = subject;
		this.user = user;
		this.netTeacherInfo = netTeacherInfo;
		this.queTitle = queTitle;
		this.queContent = queContent;
		this.queTime = queTime;
		this.readStatus = readStatus;
	}

	/** full constructor */
	public QuestionInfo(Subject subject, User user,
			NetTeacherInfo netTeacherInfo, String queTitle, String queContent,
			String queTime, String queReplyContent, String queReplyTime,
			Integer readStatus) {
		this.subject = subject;
		this.user = user;
		this.netTeacherInfo = netTeacherInfo;
		this.queTitle = queTitle;
		this.queContent = queContent;
		this.queTime = queTime;
		this.queReplyContent = queReplyContent;
		this.queReplyTime = queReplyTime;
		this.readStatus = readStatus;
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

	public NetTeacherInfo getNetTeacherInfo() {
		return this.netTeacherInfo;
	}

	public void setNetTeacherInfo(NetTeacherInfo netTeacherInfo) {
		this.netTeacherInfo = netTeacherInfo;
	}

	public String getQueTitle() {
		return this.queTitle;
	}

	public void setQueTitle(String queTitle) {
		this.queTitle = queTitle;
	}

	public String getQueContent() {
		return this.queContent;
	}

	public void setQueContent(String queContent) {
		this.queContent = queContent;
	}

	public String getQueTime() {
		return this.queTime;
	}

	public void setQueTime(String queTime) {
		this.queTime = queTime;
	}

	public String getQueReplyContent() {
		return this.queReplyContent;
	}

	public void setQueReplyContent(String queReplyContent) {
		this.queReplyContent = queReplyContent;
	}

	public String getQueReplyTime() {
		return this.queReplyTime;
	}

	public void setQueReplyTime(String queReplyTime) {
		this.queReplyTime = queReplyTime;
	}

	public Integer getReadStatus() {
		return this.readStatus;
	}

	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}

}