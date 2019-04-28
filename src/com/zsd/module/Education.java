package com.zsd.module;

import java.util.HashSet;
import java.util.Set;

/**
 * Education entity. @author MyEclipse Persistence Tools
 */

public class Education implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Edition edition;
	private GradeSubject gradeSubject;
	private Integer eduOrder;
	private Integer displayStatus;
	private String eduVolume;
	private String eduImg;

	// Constructors

	/** default constructor */
	public Education() {
	}

	/** minimal constructor */
	public Education(Edition edition, GradeSubject gradeSubject,
			Integer eduOrder, Integer displayStatus, String eduVolume) {
		this.edition = edition;
		this.gradeSubject = gradeSubject;
		this.eduOrder = eduOrder;
		this.displayStatus = displayStatus;
		this.eduVolume = eduVolume;
	}

	/** full constructor */
	public Education(Edition edition, GradeSubject gradeSubject,
			Integer eduOrder, Integer displayStatus, String eduVolume,
			String eduImg) {
		this.edition = edition;
		this.gradeSubject = gradeSubject;
		this.eduOrder = eduOrder;
		this.displayStatus = displayStatus;
		this.eduVolume = eduVolume;
		this.eduImg = eduImg;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Edition getEdition() {
		return this.edition;
	}

	public void setEdition(Edition edition) {
		this.edition = edition;
	}

	public GradeSubject getGradeSubject() {
		return this.gradeSubject;
	}

	public void setGradeSubject(GradeSubject gradeSubject) {
		this.gradeSubject = gradeSubject;
	}

	public Integer getEduOrder() {
		return this.eduOrder;
	}

	public void setEduOrder(Integer eduOrder) {
		this.eduOrder = eduOrder;
	}

	public Integer getDisplayStatus() {
		return this.displayStatus;
	}

	public void setDisplayStatus(Integer displayStatus) {
		this.displayStatus = displayStatus;
	}

	public String getEduVolume() {
		return this.eduVolume;
	}

	public void setEduVolume(String eduVolume) {
		this.eduVolume = eduVolume;
	}

	public String getEduImg() {
		return this.eduImg;
	}

	public void setEduImg(String eduImg) {
		this.eduImg = eduImg;
	}
}