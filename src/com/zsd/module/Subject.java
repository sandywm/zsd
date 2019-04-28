package com.zsd.module;

/**
 * Subject entity. @author MyEclipse Persistence Tools
 */

public class Subject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String subName;
	private Integer subOrder;
	private Integer displayStatus;
	private String imgUrl;

	// Constructors

	/** default constructor */
	public Subject() {
	}

	/** minimal constructor */
	public Subject(String subName, Integer subOrder, Integer displayStatus) {
		this.subName = subName;
		this.subOrder = subOrder;
		this.displayStatus = displayStatus;
	}

	/** full constructor */
	public Subject(String subName, Integer subOrder, Integer displayStatus,
			String imgUrl) {
		this.subName = subName;
		this.subOrder = subOrder;
		this.displayStatus = displayStatus;
		this.imgUrl = imgUrl;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubName() {
		return this.subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public Integer getSubOrder() {
		return this.subOrder;
	}

	public void setSubOrder(Integer subOrder) {
		this.subOrder = subOrder;
	}

	public Integer getDisplayStatus() {
		return this.displayStatus;
	}

	public void setDisplayStatus(Integer displayStatus) {
		this.displayStatus = displayStatus;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}