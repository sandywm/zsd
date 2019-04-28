package com.zsd.module;

/**
 * NetTeacherReturnRecord entity. @author MyEclipse Persistence Tools
 */

public class NetTeacherReturnRecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private NetTeacherInfo netTeacherInfo;
	private Integer returnMoney;
	private String returnDate;

	// Constructors

	/** default constructor */
	public NetTeacherReturnRecord() {
	}

	/** full constructor */
	public NetTeacherReturnRecord(User user, NetTeacherInfo netTeacherInfo,
			Integer returnMoney, String returnDate) {
		this.user = user;
		this.netTeacherInfo = netTeacherInfo;
		this.returnMoney = returnMoney;
		this.returnDate = returnDate;
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

	public NetTeacherInfo getNetTeacherInfo() {
		return this.netTeacherInfo;
	}

	public void setNetTeacherInfo(NetTeacherInfo netTeacherInfo) {
		this.netTeacherInfo = netTeacherInfo;
	}

	public Integer getReturnMoney() {
		return this.returnMoney;
	}

	public void setReturnMoney(Integer returnMoney) {
		this.returnMoney = returnMoney;
	}

	public String getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

}