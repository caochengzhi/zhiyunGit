package com.chengzhi.scdp.system.dao;

import java.util.ArrayList;
import java.util.List;

import com.chengzhi.scdp.database.dao.AbstractModel;

/**
 * 角色表
 * @author beisi
 *
 */
public class Roles extends AbstractModel {

	private static final long serialVersionUID = -7217697417535576741L;
	private Long roleId;
	private Long[] roleIdIn;
	private String roleName;
	private String roleCode;//角色编码
	private Long roleType;//角色类型，比如公司员工，外部客户
	private String typeName;
	private String description;
	private Long organizationId;
	
	private String resourceCodes;
	
	private List<Resources> permissions = new ArrayList<Resources>();
	private List<RoleResource> roleResourceCodes = new ArrayList<RoleResource>();

	public Roles() {
	}

	public Roles(String roleName, String description, long organizationId) {
		this.roleName = roleName;
		this.description = description;
		this.organizationId = organizationId;
	}
	
	public Roles(Long roleId, String roleName, String description, long organizationId) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.description = description;
		this.organizationId = organizationId;
	}

	public List<RoleResource> getRoleResourceCodes() {
		return roleResourceCodes;
	}

	public void setRoleResourceCodes(List<RoleResource> roleResourceCodes) {
		this.roleResourceCodes = roleResourceCodes;
	}

	public Long[] getRoleIdIn() {
		return roleIdIn;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setRoleIdIn(Long[] roleIdIn) {
		this.roleIdIn = roleIdIn;
	}

	public List<Resources> getPermissions() {
		return permissions;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Long getRoleType() {
		return roleType;
	}

	public void setRoleType(Long roleType) {
		this.roleType = roleType;
	}

	public void setPermissions(List<Resources> permissions) {
		this.permissions = permissions;
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

	public String getResourceCodes() {
		return resourceCodes;
	}

	public void setResourceCodes(String resourceCodes) {
		this.resourceCodes = resourceCodes;
	}

}
