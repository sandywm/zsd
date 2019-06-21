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
	private User parent;
	private User stu;

	// Constructors

	/** default constructor */
	public StudentParentInfo() {
	}

	/** full constructor */
	public StudentParentInfo(User parent, User stu) {
		this.parent = parent;
		this.stu = stu;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getParent() {
		return parent;
	}

	public void setParent(User parent) {
		this.parent = parent;
	}

	public User getStu() {
		return stu;
	}

	public void setStu(User stu) {
		this.stu = stu;
	}

	

}