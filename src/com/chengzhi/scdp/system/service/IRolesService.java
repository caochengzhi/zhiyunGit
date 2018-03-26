package com.chengzhi.scdp.system.service;

import java.util.List;

import com.chengzhi.scdp.common.Exceptions.CustomException;
import com.chengzhi.scdp.database.service.IBaseService;
import com.chengzhi.scdp.system.dao.RoleResource;
import com.chengzhi.scdp.system.dao.Roles;

public interface IRolesService extends IBaseService<Roles, Long>{

	abstract Roles findRoleById(Long roleId);
	
	abstract List<Roles> findRolesByIds(Long[] roleIds, Long organizationId);
	
	abstract List<RoleResource> findRoleResourcesByRoleIds(Long[] roleIds, Long organizationId);
	
	/**
	 * 获取角色对应的权限编码
	 * @return
	 */
	abstract List<String> getRolesOfCodes(Long[] roleIds);
	
	abstract void saveRoleWithPermission(Roles role,String operatorType)throws CustomException;
}
