package com.zsd.module;

/**
 * ParentClubRelationInfo entity. @author MyEclipse Persistence Tools
 */

public class ParentClubRelationInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private ParentClubInfo parentClubInfo;
	private String addTime;
	private String outTime;

	// Constructors

	/** default constructor */
	public ParentClubRelationInfo() {
	}

	/** full constructor */
	public ParentClubRelationInfo(User user, ParentClubInfo parentClubInfo,
			String addTime, String outTime) {
		this.user = user;
		this.parentClubInfo = parentClubInfo;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ParentClubInfo getParentClubInfo() {
		return this.parentClubInfo;
	}

	public void setParentClubInfo(ParentClubInfo parentClubInfo) {
		this.parentClubInfo = parentClubInfo;
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