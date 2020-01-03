package com.zsd.module;

/**
 * ClassInfo entity. @author MyEclipse Persistence Tools
 */

public class ClassInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private School school;
	private String className;
	private String buildeClassDate;
	private Integer classNum;

	// Constructors

	/** default constructor */
	public ClassInfo() {
	}

	/** full constructor */
	public ClassInfo(School school, String className, String buildeClassDate,Integer classNum) {
		this.school = school;
		this.className = className;
		this.buildeClassDate = buildeClassDate;
		this.classNum = classNum;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public School getSchool() {
		return this.school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getBuildeClassDate() {
		return this.buildeClassDate;
	}

	public void setBuildeClassDate(String buildeClassDate) {
		this.buildeClassDate = buildeClassDate;
	}

	public Integer getClassNum() {
		return classNum;
	}

	public void setClassNum(Integer classNum) {
		this.classNum = classNum;
	}

}