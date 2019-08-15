package com.zsd.module;

/**
 * HwTraceStudyLogInfo entity. @author MyEclipse Persistence Tools
 */

public class HwTraceStudyLogInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private HwStudyTjInfo hwStudyTjInfo;
	private User user;
	private LoreInfo loreInfo;
	private Integer access;
	private String addTime;
	private Integer step;
	private Integer stepComplete;
	private Integer guideStatus;
	private Integer isFinish;
	private String sysAssess;
	private String teaAssess;
	private Integer taskNumber;
	private Integer finalScore;

	// Constructors

	/** default constructor */
	public HwTraceStudyLogInfo() {
	}

	/** full constructor */
	public HwTraceStudyLogInfo(HwStudyTjInfo hwStudyTjInfo, User user,LoreInfo loreInfo,
			Integer access, String addTime, Integer step, Integer stepComplete,
			Integer guideStatus, Integer isFinish, String sysAssess,
			String teaAssess, Integer taskNumber, Integer finalScore) {
		this.hwStudyTjInfo = hwStudyTjInfo;
		this.user = user;
		this.loreInfo = loreInfo;
		this.access = access;
		this.addTime = addTime;
		this.step = step;
		this.stepComplete = stepComplete;
		this.guideStatus = guideStatus;
		this.isFinish = isFinish;
		this.sysAssess = sysAssess;
		this.teaAssess = teaAssess;
		this.taskNumber = taskNumber;
		this.finalScore = finalScore;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public HwStudyTjInfo getHwStudyTjInfo() {
		return hwStudyTjInfo;
	}

	public void setHwStudyTjInfo(HwStudyTjInfo hwStudyTjInfo) {
		this.hwStudyTjInfo = hwStudyTjInfo;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoreInfo getLoreInfo() {
		return loreInfo;
	}

	public void setLoreInfo(LoreInfo loreInfo) {
		this.loreInfo = loreInfo;
	}

	public Integer getAccess() {
		return this.access;
	}

	public void setAccess(Integer access) {
		this.access = access;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getStep() {
		return this.step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Integer getStepComplete() {
		return this.stepComplete;
	}

	public void setStepComplete(Integer stepComplete) {
		this.stepComplete = stepComplete;
	}

	public Integer getGuideStatus() {
		return this.guideStatus;
	}

	public void setGuideStatus(Integer guideStatus) {
		this.guideStatus = guideStatus;
	}

	public Integer getIsFinish() {
		return this.isFinish;
	}

	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}

	public String getSysAssess() {
		return this.sysAssess;
	}

	public void setSysAssess(String sysAssess) {
		this.sysAssess = sysAssess;
	}

	public String getTeaAssess() {
		return this.teaAssess;
	}

	public void setTeaAssess(String teaAssess) {
		this.teaAssess = teaAssess;
	}

	public Integer getTaskNumber() {
		return this.taskNumber;
	}

	public void setTaskNumber(Integer taskNumber) {
		this.taskNumber = taskNumber;
	}

	public Integer getFinalScore() {
		return this.finalScore;
	}

	public void setFinalScore(Integer finalScore) {
		this.finalScore = finalScore;
	}
}