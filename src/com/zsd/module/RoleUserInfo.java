package com.zsd.module;

/**
 * RoleUserInfo entity. @author MyEclipse Persistence Tools
 */

public class RoleUserInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private RoleInfo roleInfo;
	private String prov;
	private String city;
	private String county;
	private String town;
	private Integer schoolType;
	private Integer schoolId;
	private Integer gradeNo;
	private Integer classId;

	// Constructors

	/** default constructor */
	public RoleUserInfo() {
	}

	/** full constructor */
	
	public RoleUserInfo(User user, RoleInfo roleInfo, String prov, String city,
			String county, String town, Integer schoolType, Integer schoolId,
			Integer gradeNo, Integer classId) {
		this.user = user;
		this.roleInfo = roleInfo;
		this.prov = prov;
		this.city = city;
		this.county = county;
		this.town = town;
		this.schoolType = schoolType;
		this.schoolId = schoolId;
		this.gradeNo = gradeNo;
		this.classId = classId;
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

	public RoleInfo getRoleInfo() {
		return this.roleInfo;
	}

	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo = roleInfo;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public Integer getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(Integer schoolType) {
		this.schoolType = schoolType;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(Integer gradeNo) {
		this.gradeNo = gradeNo;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

}