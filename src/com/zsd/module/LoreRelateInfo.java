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
	private LoreInfo loreInfo;
	private LoreInfo rootLoreInfo;

	// Constructors

	/** default constructor */
	public LoreRelateInfo() {
	}

	public LoreRelateInfo(LoreInfo loreInfo, LoreInfo rootLoreInfo) {
		this.loreInfo = loreInfo;
		this.rootLoreInfo = rootLoreInfo;
	}

	// Property accessors
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LoreInfo getLoreInfo() {
		return loreInfo;
	}

	public void setLoreInfo(LoreInfo loreInfo) {
		this.loreInfo = loreInfo;
	}

	public LoreInfo getRootLoreInfo() {
		return rootLoreInfo;
	}

	public void setRootLoreInfo(LoreInfo rootLoreInfo) {
		this.rootLoreInfo = rootLoreInfo;
	}

}