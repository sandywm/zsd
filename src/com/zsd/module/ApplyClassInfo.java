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
	private Integer applyOpt;
	private String applyTime;
	private String classDetail;
	private Integer toUserId;
	private Integer checkStatus;
	private String checkRemark;
	private String checkTime;

	// Constructors

	/** default constructor */
	public ApplyClassInfo() {
	}

	/** full constructor */
	public ApplyClassInfo(User user, ClassInfo classInfo,Integer applyOpt,
			String applyTime, String classDetail, Integer toUserId,
			Integer checkStatus, String checkRemark,String checkTime) {
		this.user = user;
		this.classInfo = classInfo;
		this.applyOpt = applyOpt;
		this.applyTime = applyTime;
		this.classDetail = classDetail;
		this.toUserId = toUserId;
		this.checkStatus = checkStatus;
		this.checkRemark = checkRemark;
		this.checkTime = checkTime;
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

	public String getClassDetail() {
		return classDetail;
	}

	public void setClassDetail(String classDetail) {
		this.classDetail = classDetail;
	}

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getCheckRemark() {
		return checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getApplyOpt() {
		return applyOpt;
	}

	public void setApplyOpt(Integer applyOpt) {
		this.applyOpt = applyOpt;
	}
	
}