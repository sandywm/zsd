package com.zsd.module;

/**
 * LoreRelateInfo entity. @author MyEclipse Persistence Tools
 */

public class LoreRelateInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LoreInfo loreInfoByRootLoreId;
	private LoreInfo loreInfoByLoreId;

	// Constructors

	/** default constructor */
	public LoreRelateInfo() {
	}

	/** full constructor */
	public LoreRelateInfo(LoreInfo loreInfoByRootLoreId,
			LoreInfo loreInfoByLoreId) {
		this.loreInfoByRootLoreId = loreInfoByRootLoreId;
		this.loreInfoByLoreId = loreInfoByLoreId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LoreInfo getLoreInfoByRootLoreId() {
		return this.loreInfoByRootLoreId;
	}

	public void setLoreInfoByRootLoreId(LoreInfo loreInfoByRootLoreId) {
		this.loreInfoByRootLoreId = loreInfoByRootLoreId;
	}

	public LoreInfo getLoreInfoByLoreId() {
		return this.loreInfoByLoreId;
	}

	public void setLoreInfoByLoreId(LoreInfo loreInfoByLoreId) {
		this.loreInfoByLoreId = loreInfoByLoreId;
	}

}