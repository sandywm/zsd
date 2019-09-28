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
	private String orderNo;
	private User user;
	private Integer payType;
	private Integer payMoney;
	private Integer ntsId;
	private String addDate;
	private Integer buyDays;
	private String orderDetail;
	private Integer comStatus;
	private String comDate;
	private Integer payUserId;
	private Integer payUserRoleId;
	
	// Constructors

	/** default constructor */
	public StudentPayOrderInfo() {
	
	}
	
	/** full constructor */
	public StudentPayOrderInfo(String orderNo, User user,
			Integer payType, Integer payMoney, Integer ntsId, String addDate,
			Integer buyDays, String orderDetail, Integer comStatus,
			String comDate, Integer payUserId, Integer payUserRoleId) {
		this.orderNo = orderNo;
		this.user = user;
		this.payType = payType;
		this.payMoney = payMoney;
		this.ntsId = ntsId;
		this.addDate = addDate;
		this.buyDays = buyDays;
		this.orderDetail = orderDetail;
		this.comStatus = comStatus;
		this.comDate = comDate;
		this.payUserId = payUserId;
		this.payUserRoleId = payUserRoleId;
	}

	// Property accessors
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Integer payMoney) {
		this.payMoney = payMoney;
	}

	public Integer getNtsId() {
		return ntsId;
	}

	public void setNtsId(Integer ntsId) {
		this.ntsId = ntsId;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public Integer getBuyDays() {
		return buyDays;
	}

	public void setBuyDays(Integer buyDays) {
		this.buyDays = buyDays;
	}

	public String getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Integer getComStatus() {
		return comStatus;
	}

	public void setComStatus(Integer comStatus) {
		this.comStatus = comStatus;
	}

	public String getComDate() {
		return comDate;
	}

	public void setComDate(String comDate) {
		this.comDate = comDate;
	}

	public Integer getPayUserId() {
		return payUserId;
	}

	public void setPayUserId(Integer payUserId) {
		this.payUserId = payUserId;
	}

	public Integer getPayUserRoleId() {
		return payUserRoleId;
	}

	public void setPayUserRoleId(Integer payUserRoleId) {
		this.payUserRoleId = payUserRoleId;
	}

}