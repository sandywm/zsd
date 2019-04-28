package com.zsd.module;

/**
 * ApplyClassInfo entity. @author MyEclipse Persistence Tools
 */

public class ApplyClassInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private ClassInfo classInfo;
	private String applyTime;
	private String cancelTime;
	private String cancelReason;

	// Constructors

	/** default constructor */
	public ApplyClassInfo() {
	}

	/** minimal constructor */
	public ApplyClassInfo(User user, ClassInfo classInfo, String applyTime) {
		this.user = user;
		this.classInfo = classInfo;
		this.applyTime = applyTime;
	}

	/** full constructor */
	public ApplyClassInfo(User user, ClassInfo classInfo, String applyTime,
			String cancelTime, String cancelReason) {
		this.user = user;
		this.classInfo = classInfo;
		this.applyTime = applyTime;
		this.cancelTime = cancelTime;
		this.cancelReason = cancelReason;
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

	public ClassInfo getClassInfo() {
		return this.classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public String getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getCancelTime() {
		return this.cancelTime;
	}

	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getCancelReason() {
		return this.cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

}