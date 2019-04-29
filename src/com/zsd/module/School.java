package com.zsd.module;


/**
 * School entity. @author MyEclipse Persistence Tools
 */

public class School implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String schoolName;
	private String prov;
	private String city;
	private String county;
	private String town;
	private Integer schoolType;
	private Integer yearSystem;
	private Integer showStatus;

	// Constructors

	/** default constructor */
	public School() {
	}

	/** minimal constructor */
	public School(String schoolName, Integer schoolType, Integer yearSystem) {
		this.schoolName = schoolName;
		this.schoolType = schoolType;
		this.yearSystem = yearSystem;
	}

	/** full constructor */
	public School(String schoolName, String prov, String city, String county,
			String town, Integer schoolType, Integer yearSystem,Integer showStatus) {
		this.schoolName = schoolName;
		this.prov = prov;
		this.city = city;
		this.county = county;
		this.town = town;
		this.schoolType = schoolType;
		this.yearSystem = yearSystem;
		this.showStatus = showStatus;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSchoolName() {
		return this.schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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

	public Integer getYearSystem() {
		return this.yearSystem;
	}

	public void setYearSystem(Integer yearSystem) {
		this.yearSystem = yearSystem;
	}

	public Integer getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Integer showStatus) {
		this.showStatus = showStatus;
	}

}