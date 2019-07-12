package com.zsd.module;

/**
 * AbstractGuideStudyInfo entity provides the base persistence definition of the
 * GuideStudyInfo entity. @author MyEclipse Persistence Tools
 */

public class GuideStudyInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private StudyLogInfo studyLogInfo;
	private LoreQuestionSubInfo loreQuestionSubInfo;
	private String addTaskDate;
	private Integer guideStatus;
	private String guideDate;
	private Integer guideUserId;

	// Constructors

	/** default constructor */
	public GuideStudyInfo() {
	}

	/** full constructor */
	public GuideStudyInfo(StudyLogInfo studyLogInfo,
			LoreQuestionSubInfo loreQuestionSubInfo, String addTaskDate,
			Integer guideStatus, String guideDate, Integer guideUserId) {
		this.studyLogInfo = studyLogInfo;
		this.loreQuestionSubInfo = loreQuestionSubInfo;
		this.addTaskDate = addTaskDate;
		this.guideStatus = guideStatus;
		this.guideDate = guideDate;
		this.guideUserId = guideUserId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StudyLogInfo getStudyLogInfo() {
		return this.studyLogInfo;
	}

	public void setStudyLogInfo(StudyLogInfo studyLogInfo) {
		this.studyLogInfo = studyLogInfo;
	}

	public LoreQuestionSubInfo getLoreQuestionSubInfo() {
		return this.loreQuestionSubInfo;
	}

	public void setLoreQuestionSubInfo(LoreQuestionSubInfo loreQuestionSubInfo) {
		this.loreQuestionSubInfo = loreQuestionSubInfo;
	}

	public String getAddTaskDate() {
		return this.addTaskDate;
	}

	public void setAddTaskDate(String addTaskDate) {
		this.addTaskDate = addTaskDate;
	}

	public Integer getGuideStatus() {
		return this.guideStatus;
	}

	public void setGuideStatus(Integer guideStatus) {
		this.guideStatus = guideStatus;
	}

	public String getGuideDate() {
		return this.guideDate;
	}

	public void setGuideDate(String guideDate) {
		this.guideDate = guideDate;
	}

	public Integer getGuideUserId() {
		return this.guideUserId;
	}

	public void setGuideUserId(Integer guideUserId) {
		this.guideUserId = guideUserId;
	}

}