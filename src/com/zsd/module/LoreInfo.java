package com.zsd.module;

/**
 * LoreInfo entity. @author MyEclipse Persistence Tools
 */

public class LoreInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Chapter chapter;
	private String loreName;
	private String lorePyCode;
	private Integer inUse;
	private Integer freeStatus;
	private Integer loreOrder;
	private Integer mainLoreId;
	private String loreCode;

	// Constructors

	/** default constructor */
	public LoreInfo() {
	}

	/** full constructor */
	public LoreInfo(Chapter chapter, String loreName, String lorePyCode,
			Integer inUse, Integer freeStatus, Integer loreOrder,
			Integer mainLoreId, String loreCode) {
		this.chapter = chapter;
		this.loreName = loreName;
		this.lorePyCode = lorePyCode;
		this.inUse = inUse;
		this.freeStatus = freeStatus;
		this.loreOrder = loreOrder;
		this.mainLoreId = mainLoreId;
		this.loreCode = loreCode;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Chapter getChapter() {
		return this.chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public String getLoreName() {
		return this.loreName;
	}

	public void setLoreName(String loreName) {
		this.loreName = loreName;
	}

	public String getLorePyCode() {
		return this.lorePyCode;
	}

	public void setLorePyCode(String lorePyCode) {
		this.lorePyCode = lorePyCode;
	}

	public Integer getInUse() {
		return this.inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public Integer getFreeStatus() {
		return this.freeStatus;
	}

	public void setFreeStatus(Integer freeStatus) {
		this.freeStatus = freeStatus;
	}

	public Integer getLoreOrder() {
		return this.loreOrder;
	}

	public void setLoreOrder(Integer loreOrder) {
		this.loreOrder = loreOrder;
	}

	public Integer getMainLoreId() {
		return this.mainLoreId;
	}

	public void setMainLoreId(Integer mainLoreId) {
		this.mainLoreId = mainLoreId;
	}

	public String getLoreCode() {
		return this.loreCode;
	}

	public void setLoreCode(String loreCode) {
		this.loreCode = loreCode;
	}

}