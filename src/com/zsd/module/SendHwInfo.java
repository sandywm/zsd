package com.zsd.module;

/**
 * SendHwInfo entity. @author MyEclipse Persistence Tools
 */

public class SendHwInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private LoreInfo loreInfo;
	private ClassInfo classInfo;
	private String hwTitle;
	private String className;
	private Subject subject;
	private String sendDate;
	private String endDate;
	private Integer hwType;
	private String sysQueIdArr;
	private String hwQueIdArr;
	private String teaQueIdArr;
	private Integer coin;
	private Integer traceStatus;
	private Integer inUse;

	// Constructors

	/** default constructor */
	public SendHwInfo() {
	}

	/** full constructor */
	public SendHwInfo(User user, LoreInfo loreInfo, ClassInfo classInfo,
			String hwTitle, String className, Subject subject,
			String sendDate, String endDate, Integer hwType,
			String sysQueIdArr, String hwQueIdArr, String teaQueIdArr,
			Integer coin, Integer traceStatus,Integer inUse) {
		this.user = user;
		this.loreInfo = loreInfo;
		this.classInfo = classInfo;
		this.hwTitle = hwTitle;
		this.className = className;
		this.subject = subject;
		this.sendDate = sendDate;
		this.endDate = endDate;
		this.hwType = hwType;
		this.sysQueIdArr = sysQueIdArr;
		this.hwQueIdArr = hwQueIdArr;
		this.teaQueIdArr = teaQueIdArr;
		this.coin = coin;
		this.traceStatus = traceStatus;
		this.inUse = inUse;
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

	public LoreInfo getLoreInfo() {
		return this.loreInfo;
	}

	public void setLoreInfo(LoreInfo loreInfo) {
		this.loreInfo = loreInfo;
	}

	public ClassInfo getClassInfo() {
		return this.classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public String getHwTitle() {
		return this.hwTitle;
	}

	public void setHwTitle(String hwTitle) {
		this.hwTitle = hwTitle;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}


	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getHwType() {
		return this.hwType;
	}

	public void setHwType(Integer hwType) {
		this.hwType = hwType;
	}

	public String getSysQueIdArr() {
		return this.sysQueIdArr;
	}

	public void setSysQueIdArr(String sysQueIdArr) {
		this.sysQueIdArr = sysQueIdArr;
	}

	public String getHwQueIdArr() {
		return this.hwQueIdArr;
	}

	public void setHwQueIdArr(String hwQueIdArr) {
		this.hwQueIdArr = hwQueIdArr;
	}

	public String getTeaQueIdArr() {
		return this.teaQueIdArr;
	}

	public void setTeaQueIdArr(String teaQueIdArr) {
		this.teaQueIdArr = teaQueIdArr;
	}

	public Integer getCoin() {
		return this.coin;
	}

	public void setCoin(Integer coin) {
		this.coin = coin;
	}

	public Integer getTraceStatus() {
		return this.traceStatus;
	}

	public void setTraceStatus(Integer traceStatus) {
		this.traceStatus = traceStatus;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}
	
}