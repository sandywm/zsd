package com.zsd.module;

/**
 * BuffetQueInfo entity. @author MyEclipse Persistence Tools
 */

public class BuffetQueInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LoreInfo loreInfo;
	private Integer typeNo;
	private Integer buffetNum;
	private String title;
	private String subject;
	private String answer;
	private Integer lexId;
	private Integer tips;
	private String resolution;
	private String queType;
	private String abilityNo;
	private String mindNo;
	private Integer order;
	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private Integer inUse;
	private String operateUserName;
	private String operateDate;

	// Constructors

	/** default constructor */
	public BuffetQueInfo() {
	}

	/** minimal constructor */
	public BuffetQueInfo(LoreInfo loreInfo, Integer typeNo, Integer buffetNum,
			String title, String abilityNo, String mindNo, Integer order,
			Integer inUse) {
		this.loreInfo = loreInfo;
		this.typeNo = typeNo;
		this.buffetNum = buffetNum;
		this.title = title;
		this.abilityNo = abilityNo;
		this.mindNo = mindNo;
		this.order = order;
		this.inUse = inUse;
	}

	/** full constructor */
	public BuffetQueInfo(LoreInfo loreInfo, Integer typeNo, Integer buffetNum,
			String title, String subject, String answer, Integer lexId,
			Integer tips, String resolution, String queType, String abilityNo,
			String mindNo, Integer order, String a, String b, String c,
			String d, String e, String f, Integer inUse,
			String operateUserName, String operateDate) {
		this.loreInfo = loreInfo;
		this.typeNo = typeNo;
		this.buffetNum = buffetNum;
		this.title = title;
		this.subject = subject;
		this.answer = answer;
		this.lexId = lexId;
		this.tips = tips;
		this.resolution = resolution;
		this.queType = queType;
		this.abilityNo = abilityNo;
		this.mindNo = mindNo;
		this.order = order;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.inUse = inUse;
		this.operateUserName = operateUserName;
		this.operateDate = operateDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LoreInfo getLoreInfo() {
		return this.loreInfo;
	}

	public void setLoreInfo(LoreInfo loreInfo) {
		this.loreInfo = loreInfo;
	}

	public Integer getTypeNo() {
		return this.typeNo;
	}

	public void setTypeNo(Integer typeNo) {
		this.typeNo = typeNo;
	}


	public Integer getBuffetNum() {
		return buffetNum;
	}

	public void setBuffetNum(Integer buffetNum) {
		this.buffetNum = buffetNum;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getLexId() {
		return this.lexId;
	}

	public void setLexId(Integer lexId) {
		this.lexId = lexId;
	}

	public Integer getTips() {
		return this.tips;
	}

	public void setTips(Integer tips) {
		this.tips = tips;
	}

	public String getResolution() {
		return this.resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getQueType() {
		return this.queType;
	}

	public void setQueType(String queType) {
		this.queType = queType;
	}

	public String getAbilityNo() {
		return this.abilityNo;
	}

	public void setAbilityNo(String abilityNo) {
		this.abilityNo = abilityNo;
	}

	public String getMindNo() {
		return this.mindNo;
	}

	public void setMindNo(String mindNo) {
		this.mindNo = mindNo;
	}

	public Integer getOrder() {
		return this.order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getA() {
		return this.a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return this.b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return this.c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return this.d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getE() {
		return this.e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getF() {
		return this.f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public Integer getInUse() {
		return this.inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public String getOperateUserName() {
		return this.operateUserName;
	}

	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}

	public String getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

}