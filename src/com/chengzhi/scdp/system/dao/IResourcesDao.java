package com.chengzhi.scdp.system.dao;

import java.util.List;

import com.chengzhi.scdp.database.dao.IBaseDao;

public interface IResourcesDao extends IBaseDao<Resources, Long>{

	abstract List<Resources> getPermissionsByIds(Long[] resourceIds, Long organizationId);
}
