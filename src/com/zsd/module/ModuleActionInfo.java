package com.zsd.module;

/**
 * ModuleActionInfo entity. @author MyEclipse Persistence Tools
 */

public class ModuleActionInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ModuleInfo moduleInfo;
	private String actNameChi;
	private String actNameEng;
	private Integer actOrder;

	// Constructors

	/** default constructor */
	public ModuleActionInfo() {
	}

	/** full constructor */
	public ModuleActionInfo(ModuleInfo moduleInfo, String actNameChi,
			String actNameEng, Integer actOrder) {
		this.moduleInfo = moduleInfo;
		this.actNameChi = actNameChi;
		this.actNameEng = actNameEng;
		this.actOrder = actOrder;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ModuleInfo getModuleInfo() {
		return this.moduleInfo;
	}

	public void setModuleInfo(ModuleInfo moduleInfo) {
		this.moduleInfo = moduleInfo;
	}

	public String getActNameChi() {
		return this.actNameChi;
	}

	public void setActNameChi(String actNameChi) {
		this.actNameChi = actNameChi;
	}

	public String getActNameEng() {
		return this.actNameEng;
	}

	public void setActNameEng(String actNameEng) {
		this.actNameEng = actNameEng;
	}

	public Integer getActOrder() {
		return this.actOrder;
	}

	public void setActOrder(Integer actOrder) {
		this.actOrder = actOrder;
	}

}