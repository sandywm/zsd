package com.zsd.module;

/**
 * BuffetTypeInfo entity. @author MyEclipse Persistence Tools
 */

public class BuffetTypeInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String types;

	// Constructors

	/** default constructor */
	public BuffetTypeInfo() {
	}

	/** full constructor */
	public BuffetTypeInfo(String types) {
		this.types = types;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypes() {
		return this.types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

}