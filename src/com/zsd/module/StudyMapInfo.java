package com.zsd.module;

/**
 * StudyMapInfo entity. @author MyEclipse Persistence Tools
 */

public class StudyMapInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private LoreInfo loreInfo;
	private Integer currStep;

	// Constructors

	/** default constructor */
	public StudyMapInfo() {
	}

	/** full constructor */
	public StudyMapInfo(User user, LoreInfo loreInfo, Integer currStep) {
		this.user = user;
		this.loreInfo = loreInfo;
		this.currStep = currStep;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoreInfo getLoreInfo() {
		return this.loreInfo;
	}

	public void setLoreInfo(LoreInfo loreInfo) {
		this.loreInfo = loreInfo;
	}

	public Integer getCurrStep() {
		return this.currStep;
	}

	public void setCurrStep(Integer currStep) {
		this.currStep = currStep;
	}

}