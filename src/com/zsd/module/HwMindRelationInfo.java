package com.zsd.module;

/**
 * HwMindRelationInfo entity. @author MyEclipse Persistence Tools
 */

public class HwMindRelationInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private BuffetMindTypeInfo buffetMindTypeInfo;
	private HwQueInfo hwQueInfo;

	// Constructors

	/** default constructor */
	public HwMindRelationInfo() {
	}

	/** full constructor */
	public HwMindRelationInfo(BuffetMindTypeInfo buffetMindTypeInfo,
			HwQueInfo hwQueInfo) {
		this.buffetMindTypeInfo = buffetMindTypeInfo;
		this.hwQueInfo = hwQueInfo;
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

	public HwQueInfo getHwQueInfo() {
		return this.hwQueInfo;
	}

	public void setHwQueInfo(HwQueInfo hwQueInfo) {
		this.hwQueInfo = hwQueInfo;
	}

}