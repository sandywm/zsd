package com.zsd.module;

/**
 * LexLoreRelateInfo entity. @author MyEclipse Persistence Tools
 */

public class LexLoreRelateInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LexInfo lexInfo;
	private LoreInfo loreInfo;

	// Constructors

	/** default constructor */
	public LexLoreRelateInfo() {
	}

	/** full constructor */
	public LexLoreRelateInfo(LexInfo lexInfo, LoreInfo loreInfo) {
		this.lexInfo = lexInfo;
		this.loreInfo = loreInfo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LexInfo getLexInfo() {
		return this.lexInfo;
	}

	public void setLexInfo(LexInfo lexInfo) {
		this.lexInfo = lexInfo;
	}

	public LoreInfo getLoreInfo() {
		return this.loreInfo;
	}

	public void setLoreInfo(LoreInfo loreInfo) {
		this.loreInfo = loreInfo;
	}

}