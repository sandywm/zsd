package com.zsd.module;

/**
 * StudyStuQfTjInfo entity. @author MyEclipse Persistence Tools
 */

public class StudyStuQfTjInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Subject subject;
	private School school;
	private User user;
	private ClassInfo classInfo;
	private String studyDate;
	private Integer oneZdSuccNum;
	private Integer oneZdFailNum;
	private Integer againXxSuccNum;
	private Integer againXxFailNum;
	private Integer noRelateNum;
	private Integer relateZdFailNum;
	private Integer relateXxSuccNum;
	private Integer relateXxFailNum;
	private String rate;
	private String prov;
	private String city;
	private String county;
	private String town;
	private Integer schoolType;
	private String gradeName;
//	private String className;

	// Constructors

	/** default constructor */
	public StudyStuQfTjInfo() {
	}

	/** full constructor */
	public StudyStuQfTjInfo(Subject subject, School school, User user,
			String studyDate, Integer oneZdSuccNum, Integer oneZdFailNum,
			Integer againXxSuccNum, Integer againXxFailNum,
			Integer noRelateNum, Integer relateZdFailNum,
			Integer relateXxSuccNum, Integer relateXxFailNum, String rate,
			String prov, String city, String county, String town,
			Integer schoolType, String gradeName, ClassInfo classInfo) {
		this.subject = subject;
		this.school = school;
		this.user = user;
		this.studyDate = studyDate;
		this.oneZdSuccNum = oneZdSuccNum;
		this.oneZdFailNum = oneZdFailNum;
		this.againXxSuccNum = againXxSuccNum;
		this.againXxFailNum = againXxFailNum;
		this.noRelateNum = noRelateNum;
		this.relateZdFailNum = relateZdFailNum;
		this.relateXxSuccNum = relateXxSuccNum;
		this.relateXxFailNum = relateXxFailNum;
		this.rate = rate;
		this.prov = prov;
		this.city = city;
		this.county = county;
		this.town = town;
		this.schoolType = schoolType;
		this.gradeName = gradeName;
		this.classInfo = classInfo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public School getSchool() {
		return this.school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStudyDate() {
		return this.studyDate;
	}

	public void setStudyDate(String studyDate) {
		this.studyDate = studyDate;
	}

	public Integer getOneZdSuccNum() {
		return this.oneZdSuccNum;
	}

	public void setOneZdSuccNum(Integer oneZdSuccNum) {
		this.oneZdSuccNum = oneZdSuccNum;
	}

	public Integer getOneZdFailNum() {
		return this.oneZdFailNum;
	}

	public void setOneZdFailNum(Integer oneZdFailNum) {
		this.oneZdFailNum = oneZdFailNum;
	}

	public Integer getAgainXxSuccNum() {
		return this.againXxSuccNum;
	}

	public void setAgainXxSuccNum(Integer againXxSuccNum) {
		this.againXxSuccNum = againXxSuccNum;
	}

	public Integer getAgainXxFailNum() {
		return this.againXxFailNum;
	}

	public void setAgainXxFailNum(Integer againXxFailNum) {
		this.againXxFailNum = againXxFailNum;
	}

	public Integer getNoRelateNum() {
		return this.noRelateNum;
	}

	public void setNoRelateNum(Integer noRelateNum) {
		this.noRelateNum = noRelateNum;
	}

	public Integer getRelateZdFailNum() {
		return this.relateZdFailNum;
	}

	public void setRelateZdFailNum(Integer relateZdFailNum) {
		this.relateZdFailNum = relateZdFailNum;
	}

	public Integer getRelateXxSuccNum() {
		return this.relateXxSuccNum;
	}

	public void setRelateXxSuccNum(Integer relateXxSuccNum) {
		this.relateXxSuccNum = relateXxSuccNum;
	}

	public Integer getRelateXxFailNum() {
		return this.relateXxFailNum;
	}

	public void setRelateXxFailNum(Integer relateXxFailNum) {
		this.relateXxFailNum = relateXxFailNum;
	}

	public String getRate() {
		return this.rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
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

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public Integer getSchoolType() {
		return this.schoolType;
	}

	public void setSchoolType(Integer schoolType) {
		this.schoolType = schoolType;
	}

	public String getGradeName() {
		return this.gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public ClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}


}