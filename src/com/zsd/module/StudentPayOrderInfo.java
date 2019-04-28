package com.zsd.module;

/**
 * StudentPayOrderInfo entity. @author MyEclipse Persistence Tools
 */

public class StudentPayOrderInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private String payType;
	private Integer payMoney;
	private Integer ntsId;
	private String addDate;
	private String endDate;
	private Integer clearStatus;
	private String clearDate;
	private String cancelDate;
	private Integer comStatus;
	private String comDate;

	// Constructors

	/** default constructor */
	public StudentPayOrderInfo() {
	}

	/** minimal constructor */
	public StudentPayOrderInfo(User user, String payType, Integer payMoney,
			Integer clearStatus, Integer comStatus) {
		this.user = user;
		this.payType = payType;
		this.payMoney = payMoney;
		this.clearStatus = clearStatus;
		this.comStatus = comStatus;
	}

	/** full constructor */
	public StudentPayOrderInfo(User user, String payType, Integer payMoney,
			Integer ntsId, String addDate, String endDate, Integer clearStatus,
			String clearDate, String cancelDate, Integer comStatus,
			String comDate) {
		this.user = user;
		this.payType = payType;
		this.payMoney = payMoney;
		this.ntsId = ntsId;
		this.addDate = addDate;
		this.endDate = endDate;
		this.clearStatus = clearStatus;
		this.clearDate = clearDate;
		this.cancelDate = cancelDate;
		this.comStatus = comStatus;
		this.comDate = comDate;
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

	public String getPayType() {
		return this.payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Integer getPayMoney() {
		return this.payMoney;
	}

	public void setPayMoney(Integer payMoney) {
		this.payMoney = payMoney;
	}

	public Integer getNtsId() {
		return this.ntsId;
	}

	public void setNtsId(Integer ntsId) {
		this.ntsId = ntsId;
	}

	public String getAddDate() {
		return this.addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getClearStatus() {
		return this.clearStatus;
	}

	public void setClearStatus(Integer clearStatus) {
		this.clearStatus = clearStatus;
	}

	public String getClearDate() {
		return this.clearDate;
	}

	public void setClearDate(String clearDate) {
		this.clearDate = clearDate;
	}

	public String getCancelDate() {
		return this.cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public Integer getComStatus() {
		return this.comStatus;
	}

	public void setComStatus(Integer comStatus) {
		this.comStatus = comStatus;
	}

	public String getComDate() {
		return this.comDate;
	}

	public void setComDate(String comDate) {
		this.comDate = comDate;
	}

}