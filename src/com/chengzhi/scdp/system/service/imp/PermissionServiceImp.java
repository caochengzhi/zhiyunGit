package com.chengzhi.scdp.system.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chengzhi.scdp.database.dao.IBaseDao;
import com.chengzhi.scdp.database.service.imp.BaseServiceImp;
import com.chengzhi.scdp.system.dao.IResourcesDao;
import com.chengzhi.scdp.system.dao.Resources;
import com.chengzhi.scdp.system.service.IPermissionService;

@Service
public class PermissionServiceImp extends BaseServiceImp<Resources, Long> implements IPermissionService{

	private IResourcesDao resourceDao;
	
	@Autowired
	@Qualifier("resourceDao")
	@Override
	public void setBaseDao(IBaseDao<Resources, Long> baseDao) {
		this.baseDao = baseDao;
		resourceDao = (IResourcesDao)baseDao;
	}
	
	/**
	 * 查询权限列表
	 */
	@Override
	public List<Resources> getPermissionsByIds(Long[] resourceIds, Long organizationId) {
		return resourceDao.getPermissionsByIds(resourceIds, organizationId);
	}
	
}
