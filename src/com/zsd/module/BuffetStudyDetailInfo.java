package com.zsd.module;

/**
 * BuffetStudyDetailInfo entity. @author MyEclipse Persistence Tools
 */

public class BuffetStudyDetailInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private BuffetQueInfo buffetQueInfo;
	private BuffetSendInfo buffetSendInfo;
	private String realAnswer;
	private String myAnswer;
	private Integer result;
	private String addTime;
	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private Integer completeTimes;
	private Integer traceComStatus;
	private Integer currComStatus;

	// Constructors

	/** default constructor */
	public BuffetStudyDetailInfo() {
	}

	/** minimal constructor */
	public BuffetStudyDetailInfo(BuffetQueInfo buffetQueInfo,
			BuffetSendInfo buffetSendInfo, String realAnswer, Integer result,
			Integer completeTimes) {
		this.buffetQueInfo = buffetQueInfo;
		this.buffetSendInfo = buffetSendInfo;
		this.realAnswer = realAnswer;
		this.result = result;
		this.completeTimes = completeTimes;
	}

	/** full constructor */
	public BuffetStudyDetailInfo(BuffetQueInfo buffetQueInfo,
			BuffetSendInfo buffetSendInfo, String realAnswer, String myAnswer,
			Integer result, String addTime, String a, String b, String c,
			String d, String e, String f, Integer completeTimes,
			Integer traceComStatus, Integer currComStatus) {
		this.buffetQueInfo = buffetQueInfo;
		this.buffetSendInfo = buffetSendInfo;
		this.realAnswer = realAnswer;
		this.myAnswer = myAnswer;
		this.result = result;
		this.addTime = addTime;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.completeTimes = completeTimes;
		this.traceComStatus = traceComStatus;
		this.currComStatus = currComStatus;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BuffetQueInfo getBuffetQueInfo() {
		return this.buffetQueInfo;
	}

	public void setBuffetQueInfo(BuffetQueInfo buffetQueInfo) {
		this.buffetQueInfo = buffetQueInfo;
	}

	public BuffetSendInfo getBuffetSendInfo() {
		return this.buffetSendInfo;
	}

	public void setBuffetSendInfo(BuffetSendInfo buffetSendInfo) {
		this.buffetSendInfo = buffetSendInfo;
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

	public Integer getTraceComStatus() {
		return this.traceComStatus;
	}

	public void setTraceComStatus(Integer traceComStatus) {
		this.traceComStatus = traceComStatus;
	}

	public Integer getCurrComStatus() {
		return this.currComStatus;
	}

	public void setCurrComStatus(Integer currComStatus) {
		this.currComStatus = currComStatus;
	}
}