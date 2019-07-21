package com.zsd.module;

/**
 * HwAbilityRelationInfo entity. @author MyEclipse Persistence Tools
 */

public class HwAbilityRelationInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private BuffetAbilityTypeInfo buffetAbilityTypeInfo;
	private HwQueInfo hwQueInfo;

	// Constructors

	/** default constructor */
	public HwAbilityRelationInfo() {
	}

	/** full constructor */
	public HwAbilityRelationInfo(BuffetAbilityTypeInfo buffetAbilityTypeInfo,
			HwQueInfo hwQueInfo) {
		this.buffetAbilityTypeInfo = buffetAbilityTypeInfo;
		this.hwQueInfo = hwQueInfo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BuffetAbilityTypeInfo getBuffetAbilityTypeInfo() {
		return this.buffetAbilityTypeInfo;
	}

	public void setBuffetAbilityTypeInfo(
			BuffetAbilityTypeInfo buffetAbilityTypeInfo) {
		this.buffetAbilityTypeInfo = buffetAbilityTypeInfo;
	}

	public HwQueInfo getHwQueInfo() {
		return this.hwQueInfo;
	}

	public void setHwQueInfo(HwQueInfo hwQueInfo) {
		this.hwQueInfo = hwQueInfo;
	}

}