package com.zsd.module;

import java.util.HashSet;
import java.util.Set;

/**
 * LexInfo entity. @author MyEclipse Persistence Tools
 */

public class LexInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String lexTitle;
	private String lexTitlePy;
	private String lexContent;

	// Constructors

	/** default constructor */
	public LexInfo() {
	}

	/** full constructor */
	public LexInfo(String lexTitle, String lexTitlePy, String lexContent) {
		this.lexTitle = lexTitle;
		this.lexTitlePy = lexTitlePy;
		this.lexContent = lexContent;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLexTitle() {
		return this.lexTitle;
	}

	public void setLexTitle(String lexTitle) {
		this.lexTitle = lexTitle;
	}

	public String getLexTitlePy() {
		return this.lexTitlePy;
	}

	public void setLexTitlePy(String lexTitlePy) {
		this.lexTitlePy = lexTitlePy;
	}

	public String getLexContent() {
		return this.lexContent;
	}

	public void setLexContent(String lexContent) {
		this.lexContent = lexContent;
	}
}