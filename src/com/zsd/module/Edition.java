package com.zsd.module;

/**
 * Edition entity. @author MyEclipse Persistence Tools
 */

public class Edition implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String ediName;
	private Integer ediOrder;
	private Integer showStatus;

	// Constructors

	/** default constructor */
	public Edition() {
	}

	/** full constructor */
	public Edition(String ediName, Integer ediOrder,Integer showStatus) {
		this.ediName = ediName;
		this.ediOrder = ediOrder;
		this.showStatus = showStatus;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEdiName() {
		return this.ediName;
	}

	public void setEdiName(String ediName) {
		this.ediName = ediName;
	}

	public Integer getEdiOrder() {
		return this.ediOrder;
	}

	public void setEdiOrder(Integer ediOrder) {
		this.ediOrder = ediOrder;
	}

	public Integer getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Integer showStatus) {
		this.showStatus = showStatus;
	}

}