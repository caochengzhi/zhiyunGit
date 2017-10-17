package com.chengzhi.scdp.system.dao;

import com.chengzhi.scdp.database.dao.AbstractModel;

/**
 * 角色权限关系表
 * @author beisi
 *
 */
public class RoleResource extends AbstractModel {

	private static final long serialVersionUID = -4056441708735397553L;
	private Long id;
	private Long roleId;
	private Long resourceId;
	private Long organizationId;

	public RoleResource() {
	}

	public RoleResource(long organizationId) {
		this.organizationId = organizationId;
	}

	public RoleResource(Long roleId, Long resourceId, long organizationId) {
		this.roleId = roleId;
		this.resourceId = resourceId;
		this.organizationId = organizationId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Long getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}