package com.zsd.module;

/**
 * ActRoleInfo entity. @author MyEclipse Persistence Tools
 */

public class ActRoleInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private RoleInfo roleInfo;
	private ModuleActionInfo moduleActionInfo;

	// Constructors

	/** default constructor */
	public ActRoleInfo() {
	}

	/** full constructor */
	public ActRoleInfo(RoleInfo roleInfo, ModuleActionInfo moduleActionInfo) {
		this.roleInfo = roleInfo;
		this.moduleActionInfo = moduleActionInfo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleInfo getRoleInfo() {
		return this.roleInfo;
	}

	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo = roleInfo;
	}

	public ModuleActionInfo getModuleActionInfo() {
		return this.moduleActionInfo;
	}

	public void setModuleActionInfo(ModuleActionInfo moduleActionInfo) {
		this.moduleActionInfo = moduleActionInfo;
	}

}