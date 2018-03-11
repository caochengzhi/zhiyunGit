package com.chengzhi.scdp.system.service;

import java.util.List;

import com.chengzhi.scdp.database.service.IBaseService;
import com.chengzhi.scdp.system.dao.SysUsers;
import com.chengzhi.scdp.system.dao.UserRole;

public interface ISysUserService extends IBaseService<SysUsers, Long>{

	abstract SysUsers findUserById(Long userId);
	
	abstract List<UserRole> getUserRolesByUserId(Long userId,Long organizationId);
	
}
