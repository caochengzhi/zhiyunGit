package com.chengzhi.scdp.system.service;

import java.util.List;

import com.chengzhi.scdp.common.Exceptions.CustomException;
import com.chengzhi.scdp.database.service.IBaseService;
import com.chengzhi.scdp.system.dao.SysUsers;
import com.chengzhi.scdp.system.dao.UserRole;

public interface ISysUserService extends IBaseService<SysUsers, Long>{

	abstract void saveOrUpdateSysUser(SysUsers user,Long[] roleIds)throws CustomException;
	
	abstract SysUsers findUserById(Long userId);
	
	abstract List<UserRole> getUserRolesByUserId(Long userId,Long organizationId);
	
	abstract void saveUserRoles(List<UserRole> userRoles);
	
	abstract void updateUserPassword(SysUsers user,String newPassword)throws CustomException;
	
	abstract List<SysUsers> findSysUsersByRoleId(Long roleId);
	
}
