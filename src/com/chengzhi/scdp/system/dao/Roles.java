package com.chengzhi.scdp.system.dao;

import com.chengzhi.scdp.database.dao.AbstractModel;

/**
 * 角色表
 * @author beisi
 *
 */
public class Roles extends AbstractModel {

	private static final long serialVersionUID = -7217697417535576741L;
	private Long roleId;
	private String roleName;
	private String description;
	private Long organizationId;

	public Roles() {
	}

	public Roles(String roleName, long organizationId) {
		this.roleName = roleName;
		this.organizationId = organizationId;
	}

	public Roles(String roleName, String description, long organizationId) {
		this.roleName = roleName;
		this.description = description;
		this.organizationId = organizationId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
