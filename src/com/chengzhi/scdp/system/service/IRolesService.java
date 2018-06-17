package com.chengzhi.scdp.system.service;

import java.util.List;

import com.chengzhi.scdp.common.Exceptions.CustomException;
import com.chengzhi.scdp.database.service.IBaseService;
import com.chengzhi.scdp.system.dao.RoleResource;
import com.chengzhi.scdp.system.dao.Roles;

public interface IRolesService extends IBaseService<Roles, Long>{

	abstract Roles findRoleById(Long roleId);
	
	abstract List<Roles> findRolesByIds(Long[] roleIds, Long organizationId);
	
	abstract List<Roles> findRolesByIds(Long[] roleIds);
	
	abstract List<RoleResource> findRoleResourcesByRoleIds(Long[] roleIds, Long organizationId);
	
	/**
	 * 根据用户id查询对应的角色
	 * @param userId
	 * @return
	 */
	abstract List<Roles> findRolesByUserId(Long userId, Long organizationId);
	
	abstract List<Roles> findRolesByUserId(Long userId);
	
	/**
	 * 获取角色对应的权限编码
	 * @return
	 */
	abstract List<String> getRolesOfCodes(Long[] roleIds);
	
	abstract void saveRoleWithPermission(Roles role,String operatorType)throws CustomException;
	
	abstract void updateRoleOfUsers(Long roleId, Long[] userIds)throws CustomException;
	
}
