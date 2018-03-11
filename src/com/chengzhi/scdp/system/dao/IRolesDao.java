package com.chengzhi.scdp.system.dao;

import java.util.List;

import com.chengzhi.scdp.database.dao.IBaseDao;

public interface IRolesDao extends IBaseDao<Roles, Long>{

	abstract Roles findRoleById(Long roleId);
	
	abstract List<Roles> findRolesByIds(Long[] roleIds, Long organizationId);
	
}
