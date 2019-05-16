package com.zsd.module;

/**
 * BuffetAbilityTypeInfo entity. @author MyEclipse Persistence Tools
 */

public class BuffetAbilityTypeInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String ability;

	// Constructors

	/** default constructor */
	public BuffetAbilityTypeInfo() {
	}

	/** full constructor */
	public BuffetAbilityTypeInfo(String ability) {
		this.ability = ability;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAbility() {
		return this.ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}

}