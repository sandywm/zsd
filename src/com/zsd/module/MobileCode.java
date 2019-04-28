package com.zsd.module;

/**
 * MobileCode entity. @author MyEclipse Persistence Tools
 */

public class MobileCode implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String mobile;
	private String code;
	private String sendTime;
	private Integer sendNumber;
	private Integer status;
	private Integer purpose;

	// Constructors

	/** default constructor */
	public MobileCode() {
	}

	/** full constructor */
	public MobileCode(String mobile, String code, String sendTime,
			Integer sendNumber, Integer status, Integer purpose) {
		this.mobile = mobile;
		this.code = code;
		this.sendTime = sendTime;
		this.sendNumber = sendNumber;
		this.status = status;
		this.purpose = purpose;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getSendNumber() {
		return this.sendNumber;
	}

	public void setSendNumber(Integer sendNumber) {
		this.sendNumber = sendNumber;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPurpose() {
		return this.purpose;
	}

	public void setPurpose(Integer purpose) {
		this.purpose = purpose;
	}

}