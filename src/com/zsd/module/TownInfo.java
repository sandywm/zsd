package com.zsd.module;

/**
 * TownInfo entity. @author MyEclipse Persistence Tools
 */

public class TownInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String countyCode;
	private String countyName;
	private String townCode;
	private String townName;

	// Constructors

	/** default constructor */
	public TownInfo() {
	}

	/** full constructor */
	public TownInfo(String countyCode, String countyName, String townCode,
			String townName) {
		this.countyCode = countyCode;
		this.countyName = countyName;
		this.townCode = townCode;
		this.townName = townName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountyCode() {
		return this.countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getCountyName() {
		return this.countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getTownCode() {
		return this.townCode;
	}

	public void setTownCode(String townCode) {
		this.townCode = townCode;
	}

	public String getTownName() {
		return this.townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

}