package com.zsd.module;

import java.sql.Timestamp;

/**
 * InviteCodeInfo entity. @author MyEclipse Persistence Tools
 */

public class InviteCodeInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer inviteId;
	private String inviteType;
	private String inviteCode;
	private Timestamp createDate;

	// Constructors

	/** default constructor */
	public InviteCodeInfo() {
	}

	/** full constructor */
	public InviteCodeInfo(Integer inviteId, String inviteType,
			String inviteCode, Timestamp createDate) {
		this.inviteId = inviteId;
		this.inviteType = inviteType;
		this.inviteCode = inviteCode;
		this.createDate = createDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInviteId() {
		return this.inviteId;
	}

	public void setInviteId(Integer inviteId) {
		this.inviteId = inviteId;
	}

	public String getInviteType() {
		return this.inviteType;
	}

	public void setInviteType(String inviteType) {
		this.inviteType = inviteType;
	}

	public String getInviteCode() {
		return this.inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}