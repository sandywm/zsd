package com.zsd.module;

/**
 * BuffetMindRelationInfo entity. @author MyEclipse Persistence Tools
 */

public class BuffetMindRelationInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private BuffetMindTypeInfo buffetMindTypeInfo;
	private BuffetQueInfo buffetQueInfo;

	// Constructors

	/** default constructor */
	public BuffetMindRelationInfo() {
	}

	/** full constructor */
	public BuffetMindRelationInfo(BuffetMindTypeInfo buffetMindTypeInfo,
			BuffetQueInfo buffetQueInfo) {
		this.buffetMindTypeInfo = buffetMindTypeInfo;
		this.buffetQueInfo = buffetQueInfo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BuffetMindTypeInfo getBuffetMindTypeInfo() {
		return this.buffetMindTypeInfo;
	}

	public void setBuffetMindTypeInfo(BuffetMindTypeInfo buffetMindTypeInfo) {
		this.buffetMindTypeInfo = buffetMindTypeInfo;
	}

	public BuffetQueInfo getBuffetQueInfo() {
		return this.buffetQueInfo;
	}

	public void setBuffetQueInfo(BuffetQueInfo buffetQueInfo) {
		this.buffetQueInfo = buffetQueInfo;
	}

}