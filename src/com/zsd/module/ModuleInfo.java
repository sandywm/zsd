package com.zsd.module;


/**
 * ModuleInfo entity. @author MyEclipse Persistence Tools
 */

public class ModuleInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String modName;
	private String modUrl;
	private Integer mainStatus;
	private Integer modOrder;
	private String modSmallImgPc;
	private String modBigImgPc;
	private String modImgApp;

	// Constructors

	/** default constructor */
	public ModuleInfo() {
	}

	/** minimal constructor */
	public ModuleInfo(String modName, String modUrl, Integer mainStatus,
			Integer modOrder) {
		this.modName = modName;
		this.modUrl = modUrl;
		this.mainStatus = mainStatus;
		this.modOrder = modOrder;
	}

	/** full constructor */
	public ModuleInfo(String modName, String modUrl, Integer mainStatus,
			Integer modOrder, String modSmallImgPc, String modBigImgPc,
			String modImgApp) {
		this.modName = modName;
		this.modUrl = modUrl;
		this.mainStatus = mainStatus;
		this.modOrder = modOrder;
		this.modSmallImgPc = modSmallImgPc;
		this.modBigImgPc = modBigImgPc;
		this.modImgApp = modImgApp;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModName() {
		return this.modName;
	}

	public void setModName(String modName) {
		this.modName = modName;
	}

	public String getModUrl() {
		return this.modUrl;
	}

	public void setModUrl(String modUrl) {
		this.modUrl = modUrl;
	}

	public Integer getMainStatus() {
		return this.mainStatus;
	}

	public void setMainStatus(Integer mainStatus) {
		this.mainStatus = mainStatus;
	}

	public Integer getModOrder() {
		return this.modOrder;
	}

	public void setModOrder(Integer modOrder) {
		this.modOrder = modOrder;
	}

	public String getModSmallImgPc() {
		return this.modSmallImgPc;
	}

	public void setModSmallImgPc(String modSmallImgPc) {
		this.modSmallImgPc = modSmallImgPc;
	}

	public String getModBigImgPc() {
		return this.modBigImgPc;
	}

	public void setModBigImgPc(String modBigImgPc) {
		this.modBigImgPc = modBigImgPc;
	}

	public String getModImgApp() {
		return this.modImgApp;
	}

	public void setModImgApp(String modImgApp) {
		this.modImgApp = modImgApp;
	}

}