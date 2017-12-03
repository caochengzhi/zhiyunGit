package com.chengzhi.scdp.system.service;

import com.chengzhi.scdp.database.service.IBaseService;
import com.chengzhi.scdp.system.dao.Roles;

public interface IRolesService extends IBaseService<Roles, Long>{

	abstract Roles findRoleById(Long roleId);
}
