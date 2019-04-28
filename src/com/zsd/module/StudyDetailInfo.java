package com.zsd.module;

/**
 * StudyDetailInfo entity. @author MyEclipse Persistence Tools
 */

public class StudyDetailInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private StudyLogInfo studyLogInfo;
	private LoreQuestion loreQuestion;
	private User user;
	private LoreInfo loreInfo;
	private Integer queStep;
	private Integer result;
	private String addTime;
	private String myAnswer;
	private String realAnswer;
	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private Integer completeTimes;

	// Constructors

	/** default constructor */
	public StudyDetailInfo() {
	}

	/** minimal constructor */
	public StudyDetailInfo(StudyLogInfo studyLogInfo,
			LoreQuestion loreQuestion, User user, LoreInfo loreInfo,
			Integer queStep, Integer result, String addTime, String myAnswer,
			String realAnswer, Integer completeTimes) {
		this.studyLogInfo = studyLogInfo;
		this.loreQuestion = loreQuestion;
		this.user = user;
		this.loreInfo = loreInfo;
		this.queStep = queStep;
		this.result = result;
		this.addTime = addTime;
		this.myAnswer = myAnswer;
		this.realAnswer = realAnswer;
		this.completeTimes = completeTimes;
	}

	/** full constructor */
	public StudyDetailInfo(StudyLogInfo studyLogInfo,
			LoreQuestion loreQuestion, User user, LoreInfo loreInfo,
			Integer queStep, Integer result, String addTime, String myAnswer,
			String realAnswer, String a, String b, String c, String d,
			String e, String f, Integer completeTimes) {
		this.studyLogInfo = studyLogInfo;
		this.loreQuestion = loreQuestion;
		this.user = user;
		this.loreInfo = loreInfo;
		this.queStep = queStep;
		this.result = result;
		this.addTime = addTime;
		this.myAnswer = myAnswer;
		this.realAnswer = realAnswer;
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

	public StudyLogInfo getStudyLogInfo() {
		return this.studyLogInfo;
	}

	public void setStudyLogInfo(StudyLogInfo studyLogInfo) {
		this.studyLogInfo = studyLogInfo;
	}

	public LoreQuestion getLoreQuestion() {
		return this.loreQuestion;
	}

	public void setLoreQuestion(LoreQuestion loreQuestion) {
		this.loreQuestion = loreQuestion;
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

	public String getMyAnswer() {
		return this.myAnswer;
	}

	public void setMyAnswer(String myAnswer) {
		this.myAnswer = myAnswer;
	}

	public String getRealAnswer() {
		return this.realAnswer;
	}

	public void setRealAnswer(String realAnswer) {
		this.realAnswer = realAnswer;
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