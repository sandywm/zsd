package com.zsd.module;

/**
 * StudyTaskInfo entity. @author MyEclipse Persistence Tools
 */

public class StudyTaskInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private StudyLogInfo studyLogInfo;
	private Integer taskNum;
	private String taskName;
	private Integer coin;
	private String addTime;

	// Constructors

	/** default constructor */
	public StudyTaskInfo() {
	}

	/** full constructor */
	public StudyTaskInfo(StudyLogInfo studyLogInfo, Integer taskNum,
			String taskName, Integer coin, String addTime) {
		this.studyLogInfo = studyLogInfo;
		this.taskNum = taskNum;
		this.taskName = taskName;
		this.coin = coin;
		this.addTime = addTime;
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

	public Integer getTaskNum() {
		return this.taskNum;
	}

	public void setTaskNum(Integer taskNum) {
		this.taskNum = taskNum;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getCoin() {
		return this.coin;
	}

	public void setCoin(Integer coin) {
		this.coin = coin;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}