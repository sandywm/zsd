package com.zsd.module;

/**
 * GradeSubject entity. @author MyEclipse Persistence Tools
 */

public class GradeSubject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Subject subject;
	private String gradeName;
	private Integer schoolType;
	private Integer displayStatus;

	// Constructors

	/** default constructor */
	public GradeSubject() {
	}

	/** full constructor */
	public GradeSubject(Subject subject, String gradeName, Integer schoolType,
			Integer displayStatus) {
		this.subject = subject;
		this.gradeName = gradeName;
		this.schoolType = schoolType;
		this.displayStatus = displayStatus;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getGradeName() {
		return this.gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public Integer getSchoolType() {
		return this.schoolType;
	}

	public void setSchoolType(Integer schoolType) {
		this.schoolType = schoolType;
	}

	public Integer getDisplayStatus() {
		return this.displayStatus;
	}

	public void setDisplayStatus(Integer displayStatus) {
		this.displayStatus = displayStatus;
	}
}