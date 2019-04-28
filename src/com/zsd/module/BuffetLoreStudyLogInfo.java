package com.zsd.module;

import java.util.HashSet;
import java.util.Set;

/**
 * BuffetLoreStudyLogInfo entity. @author MyEclipse Persistence Tools
 */

public class BuffetLoreStudyLogInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Subject subject;
	private User user;
	private BuffetStudyDetailInfo buffetStudyDetailInfo;
	private Integer isFinish;
	private Integer access;
	private String addTime;
	private Integer step;
	private Integer stepComlete;
	private Integer taskNumber;
	private Integer currentGold;
	private Integer finalScore;

	// Constructors

	/** default constructor */
	public BuffetLoreStudyLogInfo() {
	}

	/** minimal constructor */
	public BuffetLoreStudyLogInfo(Subject subject, User user,
			BuffetStudyDetailInfo buffetStudyDetailInfo, Integer isFinish,
			Integer access, String addTime, Integer step, Integer stepComlete,
			Integer taskNumber, Integer currentGold) {
		this.subject = subject;
		this.user = user;
		this.buffetStudyDetailInfo = buffetStudyDetailInfo;
		this.isFinish = isFinish;
		this.access = access;
		this.addTime = addTime;
		this.step = step;
		this.stepComlete = stepComlete;
		this.taskNumber = taskNumber;
		this.currentGold = currentGold;
	}

	/** full constructor */
	public BuffetLoreStudyLogInfo(Subject subject, User user,
			BuffetStudyDetailInfo buffetStudyDetailInfo, Integer isFinish,
			Integer access, String addTime, Integer step, Integer stepComlete,
			Integer taskNumber, Integer currentGold, Integer finalScore) {
		this.subject = subject;
		this.user = user;
		this.buffetStudyDetailInfo = buffetStudyDetailInfo;
		this.isFinish = isFinish;
		this.access = access;
		this.addTime = addTime;
		this.step = step;
		this.stepComlete = stepComlete;
		this.taskNumber = taskNumber;
		this.currentGold = currentGold;
		this.finalScore = finalScore;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BuffetStudyDetailInfo getBuffetStudyDetailInfo() {
		return this.buffetStudyDetailInfo;
	}

	public void setBuffetStudyDetailInfo(
			BuffetStudyDetailInfo buffetStudyDetailInfo) {
		this.buffetStudyDetailInfo = buffetStudyDetailInfo;
	}

	public Integer getIsFinish() {
		return this.isFinish;
	}

	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
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

	public Integer getStepComlete() {
		return this.stepComlete;
	}

	public void setStepComlete(Integer stepComlete) {
		this.stepComlete = stepComlete;
	}

	public Integer getTaskNumber() {
		return this.taskNumber;
	}

	public void setTaskNumber(Integer taskNumber) {
		this.taskNumber = taskNumber;
	}

	public Integer getCurrentGold() {
		return this.currentGold;
	}

	public void setCurrentGold(Integer currentGold) {
		this.currentGold = currentGold;
	}

	public Integer getFinalScore() {
		return this.finalScore;
	}

	public void setFinalScore(Integer finalScore) {
		this.finalScore = finalScore;
	}

}