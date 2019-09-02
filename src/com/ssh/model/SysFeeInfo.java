package com.ssh.model;

/**
 * SysFeeInfo entity. @author MyEclipse Persistence Tools
 */

public class SysFeeInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer fee;
	private Integer monthRange;
	private Integer feeType;
	private Integer schoolType;

	// Constructors

	/** default constructor */
	public SysFeeInfo() {
	}

	/** full constructor */
	public SysFeeInfo(Integer fee, Integer monthRange, Integer feeType,
			Integer schoolType) {
		this.fee = fee;
		this.monthRange = monthRange;
		this.feeType = feeType;
		this.schoolType = schoolType;
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

}