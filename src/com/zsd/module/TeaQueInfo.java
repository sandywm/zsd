package com.zsd.module;

/**
 * TeaQueInfo entity. @author MyEclipse Persistence Tools
 */

public class TeaQueInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private LoreInfo loreInfo;
	private Integer queNum;
	private String queTitle;
	private String queSub;
	private Integer optNum;
	private String queAnswer;
	private String queResolution;
	private String queType;
	private String queType2;
	private Integer inUse;
	private String operateUserDate;

	// Constructors

	/** default constructor */
	public TeaQueInfo() {
	}

	/** full constructor */
	public TeaQueInfo(User user, LoreInfo loreInfo, Integer queNum,
			String queTitle, String queSub, Integer optNum,String queAnswer,
			String queResolution, String queType, String queType2,
			Integer inUse, String operateUserDate) {
		this.user = user;
		this.loreInfo = loreInfo;
		this.queNum = queNum;
		this.queTitle = queTitle;
		this.queSub = queSub;
		this.optNum = optNum;
		this.queAnswer = queAnswer;
		this.queResolution = queResolution;
		this.queType = queType;
		this.queType2 = queType2;
		this.inUse = inUse;
		this.operateUserDate = operateUserDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoreInfo getLoreInfo() {
		return this.loreInfo;
	}

	public void setLoreInfo(LoreInfo loreInfo) {
		this.loreInfo = loreInfo;
	}

	public Integer getQueNum() {
		return this.queNum;
	}

	public void setQueNum(Integer queNum) {
		this.queNum = queNum;
	}

	public String getQueTitle() {
		return this.queTitle;
	}

	public void setQueTitle(String queTitle) {
		this.queTitle = queTitle;
	}

	public String getQueSub() {
		return this.queSub;
	}

	public void setQueSub(String queSub) {
		this.queSub = queSub;
	}

	public String getQueAnswer() {
		return this.queAnswer;
	}

	public void setQueAnswer(String queAnswer) {
		this.queAnswer = queAnswer;
	}

	public String getQueResolution() {
		return this.queResolution;
	}

	public void setQueResolution(String queResolution) {
		this.queResolution = queResolution;
	}

	public String getQueType() {
		return this.queType;
	}

	public void setQueType(String queType) {
		this.queType = queType;
	}

	public String getQueType2() {
		return this.queType2;
	}

	public void setQueType2(String queType2) {
		this.queType2 = queType2;
	}

	public Integer getInUse() {
		return this.inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public String getOperateUserDate() {
		return this.operateUserDate;
	}

	public void setOperateUserDate(String operateUserDate) {
		this.operateUserDate = operateUserDate;
	}

	public Integer getOptNum() {
		return optNum;
	}

	public void setOptNum(Integer optNum) {
		this.optNum = optNum;
	}

}