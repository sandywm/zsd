package com.zsd.module;

/**
 * SysFeeInfo entity. @author MyEclipse Persistence Tools
 */

public class SysFeeInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer fee;
	private Integer monthRange;
	private Integer feeType;
	private Integer schoolType;
	private Integer showStatus;

	// Constructors

	/** default constructor */
	public SysFeeInfo() {
	}

	/** full constructor */
	public SysFeeInfo(Integer fee, Integer monthRange, Integer feeType,
			Integer schoolType,Integer showStatus) {
		this.fee = fee;
		this.monthRange = monthRange;
		this.feeType = feeType;
		this.schoolType = schoolType;
		this.showStatus = showStatus;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFee() {
		return this.fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Integer getMonthRange() {
		return this.monthRange;
	}

	public void setMonthRange(Integer monthRange) {
		this.monthRange = monthRange;
	}

	public Integer getFeeType() {
		return this.feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	public Integer getSchoolType() {
		return this.schoolType;
	}

	public void setSchoolType(Integer schoolType) {
		this.schoolType = schoolType;
	}

	public Integer getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Integer showStatus) {
		this.showStatus = showStatus;
	}

}