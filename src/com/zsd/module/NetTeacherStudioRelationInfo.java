package com.zsd.module;

/**
 * NetTeacherStudioRelationInfo entity. @author MyEclipse Persistence Tools
 */

public class NetTeacherStudioRelationInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private NetTeacherStudioInfo netTeacherStudioInfo;
	private Integer teaId;
	private String addTime;
	private String outTime;

	// Constructors

	/** default constructor */
	public NetTeacherStudioRelationInfo() {
	}

	/** full constructor */
	public NetTeacherStudioRelationInfo(
			NetTeacherStudioInfo netTeacherStudioInfo, Integer teaId,
			String addTime, String outTime) {
		this.netTeacherStudioInfo = netTeacherStudioInfo;
		this.teaId = teaId;
		this.addTime = addTime;
		this.outTime = outTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NetTeacherStudioInfo getNetTeacherStudioInfo() {
		return this.netTeacherStudioInfo;
	}

	public void setNetTeacherStudioInfo(
			NetTeacherStudioInfo netTeacherStudioInfo) {
		this.netTeacherStudioInfo = netTeacherStudioInfo;
	}

	public Integer getTeaId() {
		return this.teaId;
	}

	public void setTeaId(Integer teaId) {
		this.teaId = teaId;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getOutTime() {
		return this.outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

}