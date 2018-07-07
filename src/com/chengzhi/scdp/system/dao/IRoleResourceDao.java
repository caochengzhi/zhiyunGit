package com.chengzhi.scdp.system.dao;

import java.util.List;

import com.chengzhi.scdp.database.dao.IBaseDao;

public interface IRoleResourceDao extends IBaseDao<RoleResource, Long>{
	
	abstract void saveList(List<RoleResource> list);

}
