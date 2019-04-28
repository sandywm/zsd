package com.zsd.module;

/**
 * NetTeacherBasicInfo entity. @author MyEclipse Persistence Tools
 */

public class NetTeacherBasicInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private NetTeacherInfo netTeacherInfo;
	private String title;
	private String dataRange;
	private String description;
	private Integer type;
	private String addData;

	// Constructors

	/** default constructor */
	public NetTeacherBasicInfo() {
	}

	/** minimal constructor */
	public NetTeacherBasicInfo(NetTeacherInfo netTeacherInfo, String dataRange,
			String description, Integer type, String addData) {
		this.netTeacherInfo = netTeacherInfo;
		this.dataRange = dataRange;
		this.description = description;
		this.type = type;
		this.addData = addData;
	}

	/** full constructor */
	public NetTeacherBasicInfo(NetTeacherInfo netTeacherInfo, String title,
			String dataRange, String description, Integer type, String addData) {
		this.netTeacherInfo = netTeacherInfo;
		this.title = title;
		this.dataRange = dataRange;
		this.description = description;
		this.type = type;
		this.addData = addData;
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDataRange() {
		return this.dataRange;
	}

	public void setDataRange(String dataRange) {
		this.dataRange = dataRange;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAddData() {
		return this.addData;
	}

	public void setAddData(String addData) {
		this.addData = addData;
	}

}