package com.zsd.module;

/**
 * UserClassInfo entity. @author MyEclipse Persistence Tools
 */

public class UserClassInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private RoleInfo roleInfo;
	private ClassInfo classInfo;
	private Integer subjectId;
	private String subjectName;
	private Integer appUserId;
	private String appUserInfo;
	private Integer status;

	// Constructors

	/** default constructor */
	public UserClassInfo() {
	}

	/** minimal constructor */
	public UserClassInfo(User user, RoleInfo roleInfo, ClassInfo classInfo,
			Integer subjectId) {
		this.user = user;
		this.roleInfo = roleInfo;
		this.classInfo = classInfo;
		this.subjectId = subjectId;
	}

	/** full constructor */
	public UserClassInfo(User user, RoleInfo roleInfo, ClassInfo classInfo,
			Integer subjectId, String subjectName, Integer appUserId,
			String appUserInfo, Integer status) {
		this.user = user;
		this.roleInfo = roleInfo;
		this.classInfo = classInfo;
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.appUserId = appUserId;
		this.appUserInfo = appUserInfo;
		this.status = status;
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

	public RoleInfo getRoleInfo() {
		return this.roleInfo;
	}

	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo = roleInfo;
	}

	public ClassInfo getClassInfo() {
		return this.classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public Integer getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getAppUserId() {
		return this.appUserId;
	}

	public void setAppUserId(Integer appUserId) {
		this.appUserId = appUserId;
	}

	public String getAppUserInfo() {
		return this.appUserInfo;
	}

	public void setAppUserInfo(String appUserInfo) {
		this.appUserInfo = appUserInfo;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}