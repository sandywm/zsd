package com.zsd.module;

/**
 * TodayTask entity. @author MyEclipse Persistence Tools
 */

public class TodayTask implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LoreInfo loreInfo;
	private Integer zdxzdFlag;
	private Integer studyFlag;
	private Integer zczdFlag;
	private Integer studyTimes;
	private Integer zczdTimes;
	private String addDate;
	private User user;
	private String reviewData;
	private Integer parentUserId;
	private String parentConfirmDate;

	// Constructors

	/** default constructor */
	public TodayTask() {
	}

	/** full constructor */
	public TodayTask(LoreInfo loreInfo, Integer zdxzdFlag, Integer studyFlag,
			Integer zczdFlag, Integer studyTimes, Integer zczdTimes,
			String addDate, User user, String reviewData,
			Integer parentUserId, String parentConfirmDate) {
		this.loreInfo = loreInfo;
		this.zdxzdFlag = zdxzdFlag;
		this.studyFlag = studyFlag;
		this.zczdFlag = zczdFlag;
		this.studyTimes = studyTimes;
		this.zczdTimes = zczdTimes;
		this.addDate = addDate;
		this.user = user;
		this.reviewData = reviewData;
		this.parentUserId = parentUserId;
		this.parentConfirmDate = parentConfirmDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getAddDate() {
		return this.addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getParentUserId() {
		return this.parentUserId;
	}

	public void setParentUserId(Integer parentUserId) {
		this.parentUserId = parentUserId;
	}

	public String getParentConfirmDate() {
		return this.parentConfirmDate;
	}

	public void setParentConfirmDate(String parentConfirmDate) {
		this.parentConfirmDate = parentConfirmDate;
	}

	public String getReviewData() {
		return reviewData;
	}

	public void setReviewData(String reviewData) {
		this.reviewData = reviewData;
	}

}