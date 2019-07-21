package com.zsd.module;

/**
 * HwQueInfo entity. @author MyEclipse Persistence Tools
 */

public class HwQueInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LoreInfo loreInfo;
	private BuffetTypeInfo buffetTypeInfo;
	private Integer num;
	private String title;
	private String subject;
	private String answer;
	private String resolution;
	private String queType;
	private Integer orders;
	private Integer inUse;
	private String operateUserName;
	private String operateUserDate;

	// Constructors

	/** default constructor */
	public HwQueInfo() {
	}

	/** minimal constructor */
	public HwQueInfo(LoreInfo loreInfo, BuffetTypeInfo buffetTypeInfo,
			Integer num, String title, String subject, String answer,
			Integer orders, Integer inUse) {
		this.loreInfo = loreInfo;
		this.buffetTypeInfo = buffetTypeInfo;
		this.num = num;
		this.title = title;
		this.subject = subject;
		this.answer = answer;
		this.orders = orders;
		this.inUse = inUse;
	}

	/** full constructor */
	public HwQueInfo(LoreInfo loreInfo, BuffetTypeInfo buffetTypeInfo,
			Integer num, String title, String subject, String answer,
			String resolution, String queType, Integer orders, Integer inUse,
			String operateUserName, String operateUserDate) {
		this.loreInfo = loreInfo;
		this.buffetTypeInfo = buffetTypeInfo;
		this.num = num;
		this.title = title;
		this.subject = subject;
		this.answer = answer;
		this.resolution = resolution;
		this.queType = queType;
		this.orders = orders;
		this.inUse = inUse;
		this.operateUserName = operateUserName;
		this.operateUserDate = operateUserDate;
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

	public BuffetTypeInfo getBuffetTypeInfo() {
		return this.buffetTypeInfo;
	}

	public void setBuffetTypeInfo(BuffetTypeInfo buffetTypeInfo) {
		this.buffetTypeInfo = buffetTypeInfo;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getResolution() {
		return this.resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getQueType() {
		return this.queType;
	}

	public void setQueType(String queType) {
		this.queType = queType;
	}

	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public Integer getInUse() {
		return this.inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public String getOperateUserName() {
		return this.operateUserName;
	}

	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}

	public String getOperateUserDate() {
		return this.operateUserDate;
	}

	public void setOperateUserDate(String operateUserDate) {
		this.operateUserDate = operateUserDate;
	}
}