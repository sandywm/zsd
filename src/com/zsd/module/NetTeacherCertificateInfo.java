package com.zsd.module;

/**
 * NetTeacherCertificateInfo entity. @author MyEclipse Persistence Tools
 */

public class NetTeacherCertificateInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private NetTeacherInfo netTeacherInfo;
	private String icardImgFrontBig;
	private String icardImgBackBig;
	private String icardImgFrontSmall;
	private String icardImgBackSmall;
	private String icardName;
	private String icardNum;
	private String zgzImgBig;
	private String zgzImgSmall;
	private String xlzImgBig;
	private String xlzImgSmall;
	private Integer checkUserId;
	private String checkUserAccount;
	private Integer checkStatus;
	private String checkTime;
	private String checkReasonICard;
	private String checkReasonZgz;
	private String checkReasonXlz;

	// Constructors

	/** default constructor */
	public NetTeacherCertificateInfo() {
	}

	/** minimal constructor */
	public NetTeacherCertificateInfo(NetTeacherInfo netTeacherInfo,
			Integer checkUserId, Integer checkStatus) {
		this.netTeacherInfo = netTeacherInfo;
		this.checkUserId = checkUserId;
		this.checkStatus = checkStatus;
	}

	/** full constructor */
	public NetTeacherCertificateInfo(NetTeacherInfo netTeacherInfo,
			String icardImgFrontBig, String icardImgBackBig,
			String icardImgFrontSmall, String icardImgBackSmall,
			String icardName, String icardNum, String zgzImgBig,
			String zgzImgSmall, String xlzImgBig, String xlzImgSmall,
			Integer checkUserId, String checkUserAccount, Integer checkStatus,
			String checkTime, String checkReasonICard, String checkReasonZgz,
			String checkReasonXlz) {
		this.netTeacherInfo = netTeacherInfo;
		this.icardImgFrontBig = icardImgFrontBig;
		this.icardImgBackBig = icardImgBackBig;
		this.icardImgFrontSmall = icardImgFrontSmall;
		this.icardImgBackSmall = icardImgBackSmall;
		this.icardName = icardName;
		this.icardNum = icardNum;
		this.zgzImgBig = zgzImgBig;
		this.zgzImgSmall = zgzImgSmall;
		this.xlzImgBig = xlzImgBig;
		this.xlzImgSmall = xlzImgSmall;
		this.checkUserId = checkUserId;
		this.checkUserAccount = checkUserAccount;
		this.checkStatus = checkStatus;
		this.checkTime = checkTime;
		this.checkReasonICard = checkReasonICard;
		this.checkReasonZgz = checkReasonZgz;
		this.checkReasonXlz = checkReasonXlz;
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

	public String getIcardImgFrontBig() {
		return this.icardImgFrontBig;
	}

	public void setIcardImgFrontBig(String icardImgFrontBig) {
		this.icardImgFrontBig = icardImgFrontBig;
	}

	public String getIcardImgBackBig() {
		return this.icardImgBackBig;
	}

	public void setIcardImgBackBig(String icardImgBackBig) {
		this.icardImgBackBig = icardImgBackBig;
	}

	public String getIcardImgFrontSmall() {
		return this.icardImgFrontSmall;
	}

	public void setIcardImgFrontSmall(String icardImgFrontSmall) {
		this.icardImgFrontSmall = icardImgFrontSmall;
	}

	public String getIcardImgBackSmall() {
		return this.icardImgBackSmall;
	}

	public void setIcardImgBackSmall(String icardImgBackSmall) {
		this.icardImgBackSmall = icardImgBackSmall;
	}

	public String getIcardName() {
		return this.icardName;
	}

	public void setIcardName(String icardName) {
		this.icardName = icardName;
	}

	public String getIcardNum() {
		return this.icardNum;
	}

	public void setIcardNum(String icardNum) {
		this.icardNum = icardNum;
	}

	public String getZgzImgBig() {
		return this.zgzImgBig;
	}

	public void setZgzImgBig(String zgzImgBig) {
		this.zgzImgBig = zgzImgBig;
	}

	public String getZgzImgSmall() {
		return this.zgzImgSmall;
	}

	public void setZgzImgSmall(String zgzImgSmall) {
		this.zgzImgSmall = zgzImgSmall;
	}

	public String getXlzImgBig() {
		return this.xlzImgBig;
	}

	public void setXlzImgBig(String xlzImgBig) {
		this.xlzImgBig = xlzImgBig;
	}

	public String getXlzImgSmall() {
		return this.xlzImgSmall;
	}

	public void setXlzImgSmall(String xlzImgSmall) {
		this.xlzImgSmall = xlzImgSmall;
	}

	public Integer getCheckUserId() {
		return this.checkUserId;
	}

	public void setCheckUserId(Integer checkUserId) {
		this.checkUserId = checkUserId;
	}

	public String getCheckUserAccount() {
		return this.checkUserAccount;
	}

	public void setCheckUserAccount(String checkUserAccount) {
		this.checkUserAccount = checkUserAccount;
	}

	public Integer getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckReasonICard() {
		return this.checkReasonICard;
	}

	public void setCheckReasonICard(String checkReasonICard) {
		this.checkReasonICard = checkReasonICard;
	}

	public String getCheckReasonZgz() {
		return this.checkReasonZgz;
	}

	public void setCheckReasonZgz(String checkReasonZgz) {
		this.checkReasonZgz = checkReasonZgz;
	}

	public String getCheckReasonXlz() {
		return this.checkReasonXlz;
	}

	public void setCheckReasonXlz(String checkReasonXlz) {
		this.checkReasonXlz = checkReasonXlz;
	}

}