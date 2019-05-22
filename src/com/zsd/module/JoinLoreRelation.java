package com.zsd.module;

/**
 * JoinLoreRelation entity. @author MyEclipse Persistence Tools
 */

public class JoinLoreRelation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String loreIdArray;

	// Constructors

	/** default constructor */
	public JoinLoreRelation() {
	}

	/** full constructor */
	public JoinLoreRelation(String loreIdArray) {
		this.loreIdArray = loreIdArray;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoreIdArray() {
		return this.loreIdArray;
	}

	public void setLoreIdArray(String loreIdArray) {
		this.loreIdArray = loreIdArray;
	}

}