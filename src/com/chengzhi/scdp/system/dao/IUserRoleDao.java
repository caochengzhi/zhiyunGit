package com.chengzhi.scdp.system.dao;

import java.util.List;

import com.chengzhi.scdp.database.dao.IBaseDao;

public interface IUserRoleDao extends IBaseDao<UserRole, Long>{
	
	abstract List<UserRole> getUserRolesByUserId(Long useId, Long organizationId);

}
