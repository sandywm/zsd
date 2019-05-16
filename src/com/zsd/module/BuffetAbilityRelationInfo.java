package com.zsd.module;

/**
 * BuffetAbilityRelationInfo entity. @author MyEclipse Persistence Tools
 */

public class BuffetAbilityRelationInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private BuffetAbilityTypeInfo buffetAbilityTypeInfo;
	private BuffetQueInfo buffetQueInfo;

	// Constructors

	/** default constructor */
	public BuffetAbilityRelationInfo() {
	}

	/** full constructor */
	public BuffetAbilityRelationInfo(
			BuffetAbilityTypeInfo buffetAbilityTypeInfo,BuffetQueInfo buffetQueInfo) {
		this.buffetAbilityTypeInfo = buffetAbilityTypeInfo;
		this.buffetQueInfo = buffetQueInfo;
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

	public BuffetQueInfo getBuffetQueInfo() {
		return this.buffetQueInfo;
	}

	public void setBuffetQueInfo(BuffetQueInfo buffetQueInfo) {
		this.buffetQueInfo = buffetQueInfo;
	}

}