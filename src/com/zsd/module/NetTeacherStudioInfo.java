package com.zsd.module;

/**
 * NetTeacherStudioInfo entity. @author MyEclipse Persistence Tools
 */

public class NetTeacherStudioInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;
	private NetTeacherInfo netTeacherInfo;
	private String studioName;
	private String studioCode;
	private Integer maxNum;
	private String studioProfile;

	// Constructors

	/** default constructor */
	public NetTeacherStudioInfo() {
	}


	/** full constructor */
	public NetTeacherStudioInfo(NetTeacherInfo netTeacherInfo,
			String studioName, String studioCode, Integer maxNum, String studioProfile) {
		this.netTeacherInfo = netTeacherInfo;
		this.studioName = studioName;
		this.studioCode = studioCode;
		this.maxNum = maxNum;
		this.studioProfile = studioProfile;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NetTeacherInfo getNetTeacherInfo() {
		return this.netTeacherInfo;
	}

	public void setNetTeacherInfo(NetTeacherInfo netTeacherInfo) {
		this.netTeacherInfo = netTeacherInfo;
	}

	public String getStudioName() {
		return this.studioName;
	}

	public void setStudioName(String studioName) {
		this.studioName = studioName;
	}

	public Integer getMaxNum() {
		return this.maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}

	public String getStudioProfile() {
		return this.studioProfile;
	}

	public void setStudioProfile(String studioProfile) {
		this.studioProfile = studioProfile;
	}


	public String getStudioCode() {
		return studioCode;
	}


	public void setStudioCode(String studioCode) {
		this.studioCode = studioCode;
	}

}