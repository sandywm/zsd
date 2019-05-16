package com.zsd.module;

/**
 * BuffetMindTypeInfo entity. @author MyEclipse Persistence Tools
 */

public class BuffetMindTypeInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;
	private String mind;

	// Constructors

	/** default constructor */
	public BuffetMindTypeInfo() {
	}

	/** minimal constructor */
	public BuffetMindTypeInfo(String mind) {
		this.mind = mind;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMind() {
		return this.mind;
	}

	public void setMind(String mind) {
		this.mind = mind;
	}

}