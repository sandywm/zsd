package com.zsd.module;

/**
 * BuffetLoreStudyDetailInfo entity. @author MyEclipse Persistence Tools
 */

public class BuffetLoreStudyDetailInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private BuffetLoreStudyLogInfo buffetLoreStudyLogInfo;
	private LoreQuestion loreQuestion;
	private LoreInfo loreInfo;
	private Integer queStep;
	private Integer result;
	private String addTime;
	private String realAnswer;
	private String myAnswer;
	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private Integer completeTimes;

	// Constructors

	/** default constructor */
	public BuffetLoreStudyDetailInfo() {
	}

	/** minimal constructor */
	public BuffetLoreStudyDetailInfo(
			BuffetLoreStudyLogInfo buffetLoreStudyLogInfo,
			LoreQuestion loreQuestion, LoreInfo loreInfo, Integer queStep,
			Integer result, String addTime, String realAnswer, String myAnswer,
			Integer completeTimes) {
		this.buffetLoreStudyLogInfo = buffetLoreStudyLogInfo;
		this.loreQuestion = loreQuestion;
		this.loreInfo = loreInfo;
		this.queStep = queStep;
		this.result = result;
		this.addTime = addTime;
		this.realAnswer = realAnswer;
		this.myAnswer = myAnswer;
		this.completeTimes = completeTimes;
	}

	/** full constructor */
	public BuffetLoreStudyDetailInfo(
			BuffetLoreStudyLogInfo buffetLoreStudyLogInfo,
			LoreQuestion loreQuestion, LoreInfo loreInfo, Integer queStep,
			Integer result, String addTime, String realAnswer, String myAnswer,
			String a, String b, String c, String d, String e, String f,
			Integer completeTimes) {
		this.buffetLoreStudyLogInfo = buffetLoreStudyLogInfo;
		this.loreQuestion = loreQuestion;
		this.loreInfo = loreInfo;
		this.queStep = queStep;
		this.result = result;
		this.addTime = addTime;
		this.realAnswer = realAnswer;
		this.myAnswer = myAnswer;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.completeTimes = completeTimes;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BuffetLoreStudyLogInfo getBuffetLoreStudyLogInfo() {
		return this.buffetLoreStudyLogInfo;
	}

	public void setBuffetLoreStudyLogInfo(
			BuffetLoreStudyLogInfo buffetLoreStudyLogInfo) {
		this.buffetLoreStudyLogInfo = buffetLoreStudyLogInfo;
	}

	public LoreQuestion getLoreQuestion() {
		return this.loreQuestion;
	}

	public void setLoreQuestion(LoreQuestion loreQuestion) {
		this.loreQuestion = loreQuestion;
	}

	public LoreInfo getLoreInfo() {
		return this.loreInfo;
	}

	public void setLoreInfo(LoreInfo loreInfo) {
		this.loreInfo = loreInfo;
	}

	public Integer getQueStep() {
		return this.queStep;
	}

	public void setQueStep(Integer queStep) {
		this.queStep = queStep;
	}

	public Integer getResult() {
		return this.result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getRealAnswer() {
		return this.realAnswer;
	}

	public void setRealAnswer(String realAnswer) {
		this.realAnswer = realAnswer;
	}

	public String getMyAnswer() {
		return this.myAnswer;
	}

	public void setMyAnswer(String myAnswer) {
		this.myAnswer = myAnswer;
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

	public Integer getCompleteTimes() {
		return this.completeTimes;
	}

	public void setCompleteTimes(Integer completeTimes) {
		this.completeTimes = completeTimes;
	}

}