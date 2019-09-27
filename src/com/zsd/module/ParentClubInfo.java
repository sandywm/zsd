package com.zsd.module;

import java.util.HashSet;
import java.util.Set;

/**
 * ParentClubInfo entity. @author MyEclipse Persistence Tools
 */

public class ParentClubInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private String clubName;
	private String clubCode;
	private Integer maxNum;
	private String clubProfile;

	// Constructors

	/** default constructor */
	public ParentClubInfo() {
	}

	/** full constructor */
	public ParentClubInfo(User user, String clubName, String clubCode,
			Integer maxNum, String clubProfile) {
		this.user = user;
		this.clubName = clubName;
		this.clubCode = clubCode;
		this.maxNum = maxNum;
		this.clubProfile = clubProfile;
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

	public String getClubName() {
		return this.clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getClubCode() {
		return this.clubCode;
	}

	public void setClubCode(String clubCode) {
		this.clubCode = clubCode;
	}

	public Integer getMaxNum() {
		return this.maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}

	public String getClubProfile() {
		return this.clubProfile;
	}

	public void setClubProfile(String clubProfile) {
		this.clubProfile = clubProfile;
	}

}