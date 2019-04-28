package com.zsd.module;

import java.util.HashSet;
import java.util.Set;

/**
 * Chapter entity. @author MyEclipse Persistence Tools
 */

public class Chapter implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;
	private Education education;
	private String chapterName;
	private Integer chapterOrder;

	// Constructors

	/** default constructor */
	public Chapter() {
	}


	/** full constructor */
	public Chapter(Education education, String chapterName,
			Integer chapterOrder) {
		this.education = education;
		this.chapterName = chapterName;
		this.chapterOrder = chapterOrder;
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

	public String getChapterName() {
		return this.chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public Integer getChapterOrder() {
		return this.chapterOrder;
	}

	public void setChapterOrder(Integer chapterOrder) {
		this.chapterOrder = chapterOrder;
	}
}