package com.zsd.module;

/**
 * NetTeacherStudioNewsInfo entity. @author MyEclipse Persistence Tools
 */

public class NetTeacherStudioNewsInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private NetTeacherStudioInfo netTeacherStudioInfo;
	private String newsTitle;
	private String newsContent;
	private String sendTime;

	// Constructors

	/** default constructor */
	public NetTeacherStudioNewsInfo() {
	}

	/** full constructor */
	public NetTeacherStudioNewsInfo(NetTeacherStudioInfo netTeacherStudioInfo,
			String newsTitle, String newsContent, String sendTime) {
		this.netTeacherStudioInfo = netTeacherStudioInfo;
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.sendTime = sendTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NetTeacherStudioInfo getNetTeacherStudioInfo() {
		return this.netTeacherStudioInfo;
	}

	public void setNetTeacherStudioInfo(
			NetTeacherStudioInfo netTeacherStudioInfo) {
		this.netTeacherStudioInfo = netTeacherStudioInfo;
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

	public String getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

}