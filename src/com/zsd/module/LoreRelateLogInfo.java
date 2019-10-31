package com.zsd.module;

/**
 * LoreRelateLogInfo entity. @author MyEclipse Persistence Tools
 */

public class LoreRelateLogInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LoreInfo loreInfo;
	private String relateType;
	private Integer relateStatus;
	private String relateResult;
	private String relateTime;
	private String relateUser;

	// Constructors

	/** default constructor */
	public LoreRelateLogInfo() {
	}

	/** minimal constructor */
	public LoreRelateLogInfo(LoreInfo loreInfo, String relateType,
			Integer relateStatus) {
		this.loreInfo = loreInfo;
		this.relateType = relateType;
		this.relateStatus = relateStatus;
	}

	/** full constructor */
	public LoreRelateLogInfo(LoreInfo loreInfo, String relateType,
			Integer relateStatus, String relateResult, String relateTime,
			String relateUser) {
		this.loreInfo = loreInfo;
		this.relateType = relateType;
		this.relateStatus = relateStatus;
		this.relateResult = relateResult;
		this.relateTime = relateTime;
		this.relateUser = relateUser;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LoreInfo getLoreInfo() {
		return this.loreInfo;
	}

	public void setLoreInfo(LoreInfo loreInfo) {
		this.loreInfo = loreInfo;
	}

	public String getRelateType() {
		return this.relateType;
	}

	public void setRelateType(String relateType) {
		this.relateType = relateType;
	}

	public Integer getRelateStatus() {
		return this.relateStatus;
	}

	public void setRelateStatus(Integer relateStatus) {
		this.relateStatus = relateStatus;
	}

	public String getRelateResult() {
		return this.relateResult;
	}

	public void setRelateResult(String relateResult) {
		this.relateResult = relateResult;
	}

	public String getRelateTime() {
		return this.relateTime;
	}

	public void setRelateTime(String relateTime) {
		this.relateTime = relateTime;
	}

	public String getRelateUser() {
		return this.relateUser;
	}

	public void setRelateUser(String relateUser) {
		this.relateUser = relateUser;
	}

}