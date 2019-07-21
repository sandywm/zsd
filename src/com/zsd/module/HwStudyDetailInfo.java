package com.zsd.module;

/**
 * HwStudyDetailInfo entity. @author MyEclipse Persistence Tools
 */

public class HwStudyDetailInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private HwStudyTjInfo hwStudyTjInfo;
	private Integer queId;
	private String queArea;
	private String myAnswer;
	private Integer result;
	private String addTime;

	// Constructors

	/** default constructor */
	public HwStudyDetailInfo() {
	}

	/** full constructor */
	public HwStudyDetailInfo(HwStudyTjInfo hwStudyTjInfo, Integer queId,
			String queArea, String myAnswer, Integer result, String addTime) {
		this.hwStudyTjInfo = hwStudyTjInfo;
		this.queId = queId;
		this.queArea = queArea;
		this.myAnswer = myAnswer;
		this.result = result;
		this.addTime = addTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public HwStudyTjInfo getHwStudyTjInfo() {
		return this.hwStudyTjInfo;
	}

	public void setHwStudyTjInfo(HwStudyTjInfo hwStudyTjInfo) {
		this.hwStudyTjInfo = hwStudyTjInfo;
	}

	public Integer getQueId() {
		return this.queId;
	}

	public void setQueId(Integer queId) {
		this.queId = queId;
	}

	public String getQueArea() {
		return this.queArea;
	}

	public void setQueArea(String queArea) {
		this.queArea = queArea;
	}

	public String getMyAnswer() {
		return this.myAnswer;
	}

	public void setMyAnswer(String myAnswer) {
		this.myAnswer = myAnswer;
	}

	public Integer getResult() {
		return this.result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}