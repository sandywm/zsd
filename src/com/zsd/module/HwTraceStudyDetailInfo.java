package com.zsd.module;

/**
 * HwTraceStudyDetailInfo entity. @author MyEclipse Persistence Tools
 */

public class HwTraceStudyDetailInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private HwTraceStudyLogInfo hwTraceStudyLogInfo;
	private LoreQuestion loreQuestion;
	private User user;
	private Integer result;
	private String addTime;
	private Integer queStep;
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
	public HwTraceStudyDetailInfo() {
	}
	
	/** full constructor */
	public HwTraceStudyDetailInfo(HwTraceStudyLogInfo hwTraceStudyLogInfo,
			LoreQuestion loreQuestion, User user, 
			Integer result, String addTime, Integer queStep, String myAnswer,
			String realAnswer, String a, String b, String c, String d,
			String e, String f, Integer completeTimes) {
		this.hwTraceStudyLogInfo = hwTraceStudyLogInfo;
		this.loreQuestion = loreQuestion;
		this.user = user;
		this.result = result;
		this.addTime = addTime;
		this.queStep = queStep;
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

	public HwTraceStudyLogInfo getHwTraceStudyLogInfo() {
		return this.hwTraceStudyLogInfo;
	}

	public void setHwTraceStudyLogInfo(HwTraceStudyLogInfo hwTraceStudyLogInfo) {
		this.hwTraceStudyLogInfo = hwTraceStudyLogInfo;
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

	public Integer getQueStep() {
		return this.queStep;
	}

	public void setQueStep(Integer queStep) {
		this.queStep = queStep;
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