package com.chengzhi.scdp.system.dao;

import java.util.List;

import com.chengzhi.scdp.database.dao.IBaseDao;

public interface ISysUserDao extends IBaseDao<SysUsers, Long>{

	abstract SysUsers findUserById(Long userId);
	
	abstract List<SysUsers> findSysUsersByRoleId(Long roleId);
}
