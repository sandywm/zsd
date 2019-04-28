package com.zsd.module;

/**
 * RoleUserInfo entity. @author MyEclipse Persistence Tools
 */

public class RoleUserInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private RoleInfo roleInfo;

	// Constructors

	/** default constructor */
	public RoleUserInfo() {
	}

	/** full constructor */
	public RoleUserInfo(User user, RoleInfo roleInfo) {
		this.user = user;
		this.roleInfo = roleInfo;
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

}