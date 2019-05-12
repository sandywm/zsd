package com.zsd.module;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userAccount;
	private String nickName;
	private String realName;
	private String sex;
	private String password;
	private String email;
	private String mobile;
	private String portrait;
	private String birthday;
	private String qq;
	private String inSchoolDate;
	private String lastLoginDate;
	private String lastLoginIp;
	private String signDate;
	private Integer loginTimes;
	private Integer loginStatus;
	private Integer accountStatus;
	private Integer freeStatus;
	private Integer schoolId;
	private String endDate;
	private Integer coin;
	private Integer experience;
	private Integer yearSystem;
	private String dateFlag;
	private Integer coinZsd;
	private Integer accountMoney;
	private String prov;
	private String city;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userAccount, String password, String mobile) {
		this.userAccount = userAccount;
		this.password = password;
		this.mobile = mobile;
	}

	/** full constructor */
	public User(String userAccount, String nickName, String realName,
			String sex, String password, String email, String mobile,
			String portrait, String birthday, String qq, String inSchoolDate,
			String lastLoginDate, String lastLoginIp, String signDate,
			Integer loginTimes, Integer loginStatus, Integer accountStatus,
			Integer freeStatus, Integer schoolId, String endDate, Integer coin,
			Integer experience, Integer yearSystem, String dateFlag,
			Integer coinZsd, Integer accountMoney, String prov, String city) {
		this.userAccount = userAccount;
		this.nickName = nickName;
		this.realName = realName;
		this.sex = sex;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.portrait = portrait;
		this.birthday = birthday;
		this.qq = qq;
		this.inSchoolDate = inSchoolDate;
		this.lastLoginDate = lastLoginDate;
		this.lastLoginIp = lastLoginIp;
		this.signDate = signDate;
		this.loginTimes = loginTimes;
		this.loginStatus = loginStatus;
		this.accountStatus = accountStatus;
		this.freeStatus = freeStatus;
		this.schoolId = schoolId;
		this.endDate = endDate;
		this.coin = coin;
		this.experience = experience;
		this.yearSystem = yearSystem;
		this.dateFlag = dateFlag;
		this.coinZsd = coinZsd;
		this.accountMoney = accountMoney;
		this.prov = prov;
		this.city = city;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPortrait() {
		return this.portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getInSchoolDate() {
		return this.inSchoolDate;
	}

	public void setInSchoolDate(String inSchoolDate) {
		this.inSchoolDate = inSchoolDate;
	}

	public String getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getSignDate() {
		return this.signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public Integer getLoginTimes() {
		return this.loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public Integer getLoginStatus() {
		return this.loginStatus;
	}

	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Integer getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Integer getFreeStatus() {
		return this.freeStatus;
	}

	public void setFreeStatus(Integer freeStatus) {
		this.freeStatus = freeStatus;
	}

	public Integer getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getCoin() {
		return this.coin;
	}

	public void setCoin(Integer coin) {
		this.coin = coin;
	}

	public Integer getExperience() {
		return this.experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Integer getYearSystem() {
		return this.yearSystem;
	}

	public void setYearSystem(Integer yearSystem) {
		this.yearSystem = yearSystem;
	}

	public String getDateFlag() {
		return this.dateFlag;
	}

	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}

	public Integer getCoinZsd() {
		return this.coinZsd;
	}

	public void setCoinZsd(Integer coinZsd) {
		this.coinZsd = coinZsd;
	}

	public Integer getAccountMoney() {
		return this.accountMoney;
	}

	public void setAccountMoney(Integer accountMoney) {
		this.accountMoney = accountMoney;
	}

	public String getProv() {
		return this.prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}