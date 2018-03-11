package com.chengzhi.scdp.system.service;

import java.util.List;

import com.chengzhi.scdp.database.service.IBaseService;
import com.chengzhi.scdp.system.dao.Resources;

public interface IPermissionService extends IBaseService<Resources, Long>{
	
	abstract List<Resources> getPermissionsByIds(Long[] resourceIds, Long organizationId);

}
