package com.zsd.module;


/**
 * LoreQuestion entity. @author MyEclipse Persistence Tools
 */

public class LoreQuestion implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LoreInfo loreInfo;
	private String loreTypeName;
	private String loreTypeCode;
	private Integer queNum;
	private String queTitle;
	private String queSub;
	private String queAnswer;
	private Integer queTips;
	private Integer lexId;
	private String queResolution;
	private String queType;
	private Integer queOrder;
	private String queType2;
	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private Integer inUse;
	private String operateUserName;
	private String operateDate;
	private Integer queClassTeaId;

	// Constructors

	/** default constructor */
	public LoreQuestion() {
	}

	/** minimal constructor */
	public LoreQuestion(LoreInfo loreInfo, String loreTypeName,
			String loreTypeCode, Integer queNum, String queTitle,
			Integer queOrder, Integer inUse, Integer queClassTeaId) {
		this.loreInfo = loreInfo;
		this.loreTypeName = loreTypeName;
		this.loreTypeCode = loreTypeCode;
		this.queNum = queNum;
		this.queTitle = queTitle;
		this.queOrder = queOrder;
		this.inUse = inUse;
		this.queClassTeaId = queClassTeaId;
	}

	/** full constructor */
	public LoreQuestion(LoreInfo loreInfo, String loreTypeName,
			String loreTypeCode, Integer queNum, String queTitle,
			String queSub, String queAnswer, Integer queTips, Integer lexId,
			String queResolution, String queType, Integer queOrder,
			String queType2, String a, String b, String c, String d, String e,
			String f, Integer inUse, String operateUserName,
			String operateDate, Integer queClassTeaId) {
		this.loreInfo = loreInfo;
		this.loreTypeName = loreTypeName;
		this.loreTypeCode = loreTypeCode;
		this.queNum = queNum;
		this.queTitle = queTitle;
		this.queSub = queSub;
		this.queAnswer = queAnswer;
		this.queTips = queTips;
		this.lexId = lexId;
		this.queResolution = queResolution;
		this.queType = queType;
		this.queOrder = queOrder;
		this.queType2 = queType2;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.inUse = inUse;
		this.operateUserName = operateUserName;
		this.operateDate = operateDate;
		this.queClassTeaId = queClassTeaId;
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

	public String getLoreTypeName() {
		return this.loreTypeName;
	}

	public void setLoreTypeName(String loreTypeName) {
		this.loreTypeName = loreTypeName;
	}

	public String getLoreTypeCode() {
		return this.loreTypeCode;
	}

	public void setLoreTypeCode(String loreTypeCode) {
		this.loreTypeCode = loreTypeCode;
	}

	public Integer getQueNum() {
		return this.queNum;
	}

	public void setQueNum(Integer queNum) {
		this.queNum = queNum;
	}

	public String getQueTitle() {
		return this.queTitle;
	}

	public void setQueTitle(String queTitle) {
		this.queTitle = queTitle;
	}

	public String getQueSub() {
		return this.queSub;
	}

	public void setQueSub(String queSub) {
		this.queSub = queSub;
	}

	public String getQueAnswer() {
		return this.queAnswer;
	}

	public void setQueAnswer(String queAnswer) {
		this.queAnswer = queAnswer;
	}

	public Integer getQueTips() {
		return this.queTips;
	}

	public void setQueTips(Integer queTips) {
		this.queTips = queTips;
	}

	public Integer getLexId() {
		return this.lexId;
	}

	public void setLexId(Integer lexId) {
		this.lexId = lexId;
	}

	public String getQueResolution() {
		return this.queResolution;
	}

	public void setQueResolution(String queResolution) {
		this.queResolution = queResolution;
	}

	public String getQueType() {
		return this.queType;
	}

	public void setQueType(String queType) {
		this.queType = queType;
	}

	public Integer getQueOrder() {
		return this.queOrder;
	}

	public void setQueOrder(Integer queOrder) {
		this.queOrder = queOrder;
	}

	public String getQueType2() {
		return this.queType2;
	}

	public void setQueType2(String queType2) {
		this.queType2 = queType2;
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

	public Integer getQueClassTeaId() {
		return this.queClassTeaId;
	}

	public void setQueClassTeaId(Integer queClassTeaId) {
		this.queClassTeaId = queClassTeaId;
	}

}