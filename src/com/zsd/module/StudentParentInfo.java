package com.zsd.module;

/**
 * StudentParentInfo entity. @author MyEclipse Persistence Tools
 */

public class StudentParentInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User userByParentId;
	private User userByStuId;

	// Constructors

	/** default constructor */
	public StudentParentInfo() {
	}

	/** full constructor */
	public StudentParentInfo(User userByParentId, User userByStuId) {
		this.userByParentId = userByParentId;
		this.userByStuId = userByStuId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserByParentId() {
		return this.userByParentId;
	}

	public void setUserByParentId(User userByParentId) {
		this.userByParentId = userByParentId;
	}

	public User getUserByStuId() {
		return this.userByStuId;
	}

	public void setUserByStuId(User userByStuId) {
		this.userByStuId = userByStuId;
	}

}