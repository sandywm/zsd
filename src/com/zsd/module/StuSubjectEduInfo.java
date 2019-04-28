package com.zsd.module;

/**
 * StuSubjectEduInfo entity. @author MyEclipse Persistence Tools
 */

public class StuSubjectEduInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Education education;
	private Subject subject;
	private User user;
	private String addDate;

	// Constructors

	/** default constructor */
	public StuSubjectEduInfo() {
	}

	/** full constructor */
	public StuSubjectEduInfo(Education education, Subject subject, User user,
			String addDate) {
		this.education = education;
		this.subject = subject;
		this.user = user;
		this.addDate = addDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Education getEducation() {
		return this.education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAddDate() {
		return this.addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

}