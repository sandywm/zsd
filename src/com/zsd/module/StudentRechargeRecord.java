package com.zsd.module;

/**
 * StudentRechargeRecord entity. @author MyEclipse Persistence Tools
 */

public class StudentRechargeRecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private Integer rcgMoney;
	private String rcgType;
	private String rcgDate;

	// Constructors

	/** default constructor */
	public StudentRechargeRecord() {
	}

	/** full constructor */
	public StudentRechargeRecord(User user, Integer rcgMoney, String rcgType,
			String rcgDate) {
		this.user = user;
		this.rcgMoney = rcgMoney;
		this.rcgType = rcgType;
		this.rcgDate = rcgDate;
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

	public Integer getRcgMoney() {
		return this.rcgMoney;
	}

	public void setRcgMoney(Integer rcgMoney) {
		this.rcgMoney = rcgMoney;
	}

	public String getRcgType() {
		return this.rcgType;
	}

	public void setRcgType(String rcgType) {
		this.rcgType = rcgType;
	}

	public String getRcgDate() {
		return this.rcgDate;
	}

	public void setRcgDate(String rcgDate) {
		this.rcgDate = rcgDate;
	}

}