package com.zsd.module;

/**
 * News entity. @author MyEclipse Persistence Tools
 */

public class News implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String newsTitle;
	private String newsContent;
	private String newsType;
	private Integer hotStatus;
	private String addUserAccount;
	private String addTime;

	// Constructors

	/** default constructor */
	public News() {
	}

	/** full constructor */
	public News(String newsTitle, String newsContent, String newsType,
			Integer hotStatus, String addUserAccount, String addTime) {
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.newsType = newsType;
		this.hotStatus = hotStatus;
		this.addUserAccount = addUserAccount;
		this.addTime = addTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNewsTitle() {
		return this.newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return this.newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getNewsType() {
		return this.newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public Integer getHotStatus() {
		return this.hotStatus;
	}

	public void setHotStatus(Integer hotStatus) {
		this.hotStatus = hotStatus;
	}

	public String getAddUserAccount() {
		return this.addUserAccount;
	}

	public void setAddUserAccount(String addUserAccount) {
		this.addUserAccount = addUserAccount;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}