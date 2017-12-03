package com.chengzhi.scdp.system.dao;

import com.chengzhi.scdp.database.dao.IBaseDao;

public interface IRolesDao extends IBaseDao<Roles, Long>{

	abstract Roles findRoleById(Long roleId);
}
