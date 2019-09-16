package com.zsd.module;

/**
 * NetTeacherTxRecord entity. @author MyEclipse Persistence Tools
 */

public class NetTeacherTxRecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private NetTeacherInfo netTeacherInfo;
	private Integer stuId;
	private String txDate;
	private Integer txMoney;
	private Integer operateUserId;
	private String operateDate;
	private String bankName;
	private String bankNo;
	private String remark;

	// Constructors

	/** default constructor */
	public NetTeacherTxRecord() {
	}

	/** minimal constructor */
	public NetTeacherTxRecord(NetTeacherInfo netTeacherInfo, String txDate,
			Integer txMoney, Integer operateUserId, String bankName,
			String bankNo) {
		this.netTeacherInfo = netTeacherInfo;
		this.txDate = txDate;
		this.txMoney = txMoney;
		this.operateUserId = operateUserId;
		this.bankName = bankName;
		this.bankNo = bankNo;
	}

	/** full constructor */
	public NetTeacherTxRecord(NetTeacherInfo netTeacherInfo,Integer stuId, String txDate,
			Integer txMoney, Integer operateUserId, String operateDate,
			String bankName, String bankNo,String remark) {
		this.netTeacherInfo = netTeacherInfo;
		this.stuId = stuId;
		this.txDate = txDate;
		this.txMoney = txMoney;
		this.operateUserId = operateUserId;
		this.operateDate = operateDate;
		this.bankName = bankName;
		this.bankNo = bankNo;
		this.remark = remark;
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

	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public String getTxDate() {
		return this.txDate;
	}

	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}

	public Integer getTxMoney() {
		return this.txMoney;
	}

	public void setTxMoney(Integer txMoney) {
		this.txMoney = txMoney;
	}

	public Integer getOperateUserId() {
		return this.operateUserId;
	}

	public void setOperateUserId(Integer operateUserId) {
		this.operateUserId = operateUserId;
	}

	public String getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}