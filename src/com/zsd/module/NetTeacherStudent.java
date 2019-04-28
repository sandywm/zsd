package com.zsd.module;

/**
 * NetTeacherStudent entity. @author MyEclipse Persistence Tools
 */

public class NetTeacherStudent implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private NetTeacherInfo netTeacherInfo;
	private String bindDate;
	private Integer bindStatus;
	private String endDate;
	private Integer clearStatus;
	private String clearDate;
	private String cancelDate;
	private Integer payStatus;

	// Constructors

	/** default constructor */
	public NetTeacherStudent() {
	}

	/** minimal constructor */
	public NetTeacherStudent(User user, NetTeacherInfo netTeacherInfo,
			String bindDate, Integer bindStatus, String endDate,
			Integer clearStatus, Integer payStatus) {
		this.user = user;
		this.netTeacherInfo = netTeacherInfo;
		this.bindDate = bindDate;
		this.bindStatus = bindStatus;
		this.endDate = endDate;
		this.clearStatus = clearStatus;
		this.payStatus = payStatus;
	}

	/** full constructor */
	public NetTeacherStudent(User user, NetTeacherInfo netTeacherInfo,
			String bindDate, Integer bindStatus, String endDate,
			Integer clearStatus, String clearDate, String cancelDate,
			Integer payStatus) {
		this.user = user;
		this.netTeacherInfo = netTeacherInfo;
		this.bindDate = bindDate;
		this.bindStatus = bindStatus;
		this.endDate = endDate;
		this.clearStatus = clearStatus;
		this.clearDate = clearDate;
		this.cancelDate = cancelDate;
		this.payStatus = payStatus;
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

	public NetTeacherInfo getNetTeacherInfo() {
		return this.netTeacherInfo;
	}

	public void setNetTeacherInfo(NetTeacherInfo netTeacherInfo) {
		this.netTeacherInfo = netTeacherInfo;
	}

	public String getBindDate() {
		return this.bindDate;
	}

	public void setBindDate(String bindDate) {
		this.bindDate = bindDate;
	}

	public Integer getBindStatus() {
		return this.bindStatus;
	}

	public void setBindStatus(Integer bindStatus) {
		this.bindStatus = bindStatus;
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

	public Integer getPayStatus() {
		return this.payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

}