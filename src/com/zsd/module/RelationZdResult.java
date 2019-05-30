package com.zsd.module;

/**
 * RelationZdResult entity. @author MyEclipse Persistence Tools
 */

public class RelationZdResult implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private StudyLogInfo studyLogInfo;
	private LoreInfo loreInfo;
	private Integer zdxzdFlag;
	private Integer studyFlag;
	private Integer zczdFlag;
	private Integer studyTimes;
	private Integer zczdTimes;

	// Constructors

	/** default constructor */
	public RelationZdResult() {
	}

	/** full constructor */
	public RelationZdResult(StudyLogInfo studyLogInfo, LoreInfo loreInfo,
			Integer zdxzdFlag, Integer studyFlag, Integer zczdFlag,
			Integer studyTimes, Integer zczdTimes) {
		this.studyLogInfo = studyLogInfo;
		this.loreInfo = loreInfo;
		this.zdxzdFlag = zdxzdFlag;
		this.studyFlag = studyFlag;
		this.zczdFlag = zczdFlag;
		this.studyTimes = studyTimes;
		this.zczdTimes = zczdTimes;
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

	public LoreInfo getLoreInfo() {
		return this.loreInfo;
	}

	public void setLoreInfo(LoreInfo loreInfo) {
		this.loreInfo = loreInfo;
	}

	public Integer getZdxzdFlag() {
		return this.zdxzdFlag;
	}

	public void setZdxzdFlag(Integer zdxzdFlag) {
		this.zdxzdFlag = zdxzdFlag;
	}

	public Integer getStudyFlag() {
		return this.studyFlag;
	}

	public void setStudyFlag(Integer studyFlag) {
		this.studyFlag = studyFlag;
	}

	public Integer getZczdFlag() {
		return this.zczdFlag;
	}

	public void setZczdFlag(Integer zczdFlag) {
		this.zczdFlag = zczdFlag;
	}

	public Integer getStudyTimes() {
		return this.studyTimes;
	}

	public void setStudyTimes(Integer studyTimes) {
		this.studyTimes = studyTimes;
	}

	public Integer getZczdTimes() {
		return this.zczdTimes;
	}

	public void setZczdTimes(Integer zczdTimes) {
		this.zczdTimes = zczdTimes;
	}

}