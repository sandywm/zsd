package com.zsd.module;

/**
 * LoreQuestionSubInfo entity. @author MyEclipse Persistence Tools
 */

public class LoreQuestionSubInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LoreQuestion loreQuestion;
	private String loreTypeName;
	private String loreTypeCode;
	private String lqsTitle;
	private String lqsContent;
	private Integer lqsOrder;
	private String operateUserName;
	private String operateDate;

	// Constructors

	/** default constructor */
	public LoreQuestionSubInfo() {
	}

	/** minimal constructor */
	public LoreQuestionSubInfo(LoreQuestion loreQuestion, String loreTypeName,
			String loreTypeCode, String lqsTitle, String lqsContent) {
		this.loreQuestion = loreQuestion;
		this.loreTypeName = loreTypeName;
		this.loreTypeCode = loreTypeCode;
		this.lqsTitle = lqsTitle;
		this.lqsContent = lqsContent;
	}

	/** full constructor */
	public LoreQuestionSubInfo(LoreQuestion loreQuestion, String loreTypeName,
			String loreTypeCode, String lqsTitle, String lqsContent,
			Integer lqsOrder, String operateUserName, String operateDate) {
		this.loreQuestion = loreQuestion;
		this.loreTypeName = loreTypeName;
		this.loreTypeCode = loreTypeCode;
		this.lqsTitle = lqsTitle;
		this.lqsContent = lqsContent;
		this.lqsOrder = lqsOrder;
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

	public LoreQuestion getLoreQuestion() {
		return this.loreQuestion;
	}

	public void setLoreQuestion(LoreQuestion loreQuestion) {
		this.loreQuestion = loreQuestion;
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

	public String getLqsTitle() {
		return this.lqsTitle;
	}

	public void setLqsTitle(String lqsTitle) {
		this.lqsTitle = lqsTitle;
	}

	public String getLqsContent() {
		return this.lqsContent;
	}

	public void setLqsContent(String lqsContent) {
		this.lqsContent = lqsContent;
	}

	public Integer getLqsOrder() {
		return this.lqsOrder;
	}

	public void setLqsOrder(Integer lqsOrder) {
		this.lqsOrder = lqsOrder;
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