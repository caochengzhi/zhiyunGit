package com.chengzhi.scdp.system.dao;

import com.chengzhi.scdp.database.dao.AbstractModel;

/**
 * 用户与角色关系表
 * @author beisi
 *
 */
public class UserRole extends AbstractModel {

	private static final long serialVersionUID = 8729980846407783876L;
	private Long id;
	private Long userId;
	private Long roleId;
	private Long organizationId;

	public UserRole() {
	}

	public UserRole(Long userId, Long roleId, Long organizationId) {
		this.userId = userId;
		this.roleId = roleId;
		this.organizationId = organizationId;
	}
	
	public UserRole(Long userId, Long roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
