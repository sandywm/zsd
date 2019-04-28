package com.zsd.module;

/**
 * BuffetLoreRelateInfo entity. @author MyEclipse Persistence Tools
 */

public class BuffetLoreRelateInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Edition edition;
	private LoreInfo loreInfoByCurrLoreId;
	private LoreInfo loreInfoByLoreId;
	private BuffetQueInfo buffetQueInfo;

	// Constructors

	/** default constructor */
	public BuffetLoreRelateInfo() {
	}

	/** minimal constructor */
	public BuffetLoreRelateInfo(Edition edition, LoreInfo loreInfoByLoreId,
			BuffetQueInfo buffetQueInfo) {
		this.edition = edition;
		this.loreInfoByLoreId = loreInfoByLoreId;
		this.buffetQueInfo = buffetQueInfo;
	}

	/** full constructor */
	public BuffetLoreRelateInfo(Edition edition, LoreInfo loreInfoByCurrLoreId,
			LoreInfo loreInfoByLoreId, BuffetQueInfo buffetQueInfo) {
		this.edition = edition;
		this.loreInfoByCurrLoreId = loreInfoByCurrLoreId;
		this.loreInfoByLoreId = loreInfoByLoreId;
		this.buffetQueInfo = buffetQueInfo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Edition getEdition() {
		return this.edition;
	}

	public void setEdition(Edition edition) {
		this.edition = edition;
	}

	public LoreInfo getLoreInfoByCurrLoreId() {
		return this.loreInfoByCurrLoreId;
	}

	public void setLoreInfoByCurrLoreId(LoreInfo loreInfoByCurrLoreId) {
		this.loreInfoByCurrLoreId = loreInfoByCurrLoreId;
	}

	public LoreInfo getLoreInfoByLoreId() {
		return this.loreInfoByLoreId;
	}

	public void setLoreInfoByLoreId(LoreInfo loreInfoByLoreId) {
		this.loreInfoByLoreId = loreInfoByLoreId;
	}

	public BuffetQueInfo getBuffetQueInfo() {
		return this.buffetQueInfo;
	}

	public void setBuffetQueInfo(BuffetQueInfo buffetQueInfo) {
		this.buffetQueInfo = buffetQueInfo;
	}

}