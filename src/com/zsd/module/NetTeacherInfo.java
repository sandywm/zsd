package com.zsd.module;

/**
 * NetTeacherInfo entity. @author MyEclipse Persistence Tools
 */

public class NetTeacherInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private Subject subject;
	private Integer schoolType;
	private Integer baseMoney;
	private String teacherIntro;
	private String honorPic;
	private String bankName;
	private String bankNumber;
	private String teaSign;
	private String teaEdu;
	private String graduateSchool;
	private String major;
	private Integer schoolAge;
	private Integer checkStatus;
	private Integer teaScore;

	// Constructors

	/** default constructor */
	public NetTeacherInfo() {
	}

	/** minimal constructor */
	public NetTeacherInfo(User user, Subject subject, Integer schoolType,
			Integer checkStatus, Integer teaScore) {
		this.user = user;
		this.subject = subject;
		this.schoolType = schoolType;
		this.checkStatus = checkStatus;
		this.teaScore = teaScore;
	}

	/** full constructor */
	public NetTeacherInfo(User user, Subject subject, Integer schoolType,
			Integer baseMoney, String teacherIntro, String honorPic,
			String bankName, String bankNumber, String teaSign, String teaEdu,
			String graduateSchool, String major, Integer schoolAge,
			Integer checkStatus, Integer teaScore) {
		this.user = user;
		this.subject = subject;
		this.schoolType = schoolType;
		this.baseMoney = baseMoney;
		this.teacherIntro = teacherIntro;
		this.honorPic = honorPic;
		this.bankName = bankName;
		this.bankNumber = bankNumber;
		this.teaSign = teaSign;
		this.teaEdu = teaEdu;
		this.graduateSchool = graduateSchool;
		this.major = major;
		this.schoolAge = schoolAge;
		this.checkStatus = checkStatus;
		this.teaScore = teaScore;
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

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Integer getSchoolType() {
		return this.schoolType;
	}

	public void setSchoolType(Integer schoolType) {
		this.schoolType = schoolType;
	}

	public Integer getBaseMoney() {
		return this.baseMoney;
	}

	public void setBaseMoney(Integer baseMoney) {
		this.baseMoney = baseMoney;
	}

	public String getTeacherIntro() {
		return this.teacherIntro;
	}

	public void setTeacherIntro(String teacherIntro) {
		this.teacherIntro = teacherIntro;
	}

	public String getHonorPic() {
		return this.honorPic;
	}

	public void setHonorPic(String honorPic) {
		this.honorPic = honorPic;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNumber() {
		return this.bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getTeaSign() {
		return this.teaSign;
	}

	public void setTeaSign(String teaSign) {
		this.teaSign = teaSign;
	}

	public String getTeaEdu() {
		return this.teaEdu;
	}

	public void setTeaEdu(String teaEdu) {
		this.teaEdu = teaEdu;
	}

	public String getGraduateSchool() {
		return this.graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Integer getSchoolAge() {
		return this.schoolAge;
	}

	public void setSchoolAge(Integer schoolAge) {
		this.schoolAge = schoolAge;
	}

	public Integer getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getTeaScore() {
		return this.teaScore;
	}

	public void setTeaScore(Integer teaScore) {
		this.teaScore = teaScore;
	}
}