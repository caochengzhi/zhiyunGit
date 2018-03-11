package com.chengzhi.scdp.system.service.imp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chengzhi.scdp.database.dao.IBaseDao;
import com.chengzhi.scdp.database.service.imp.BaseServiceImp;
import com.chengzhi.scdp.system.dao.IRoleResourceDao;
import com.chengzhi.scdp.system.dao.IRolesDao;
import com.chengzhi.scdp.system.dao.RoleResource;
import com.chengzhi.scdp.system.dao.Roles;
import com.chengzhi.scdp.system.service.IRolesService;

/**
 * 系统用户服务类
 * @author beisi
 *
 */
@Service
public class RolesServiceImp extends BaseServiceImp<Roles, Long> implements IRolesService {
	protected Logger logger = Logger.getLogger(this.getClass());

	private IRolesDao rolesDao;
	@Autowired
	private IRoleResourceDao userResourceDao;
	
	@Autowired
	@Qualifier("rolesDao")
	@Override
	public void setBaseDao(IBaseDao<Roles, Long> baseDao) {
		this.baseDao = baseDao;
		rolesDao = (IRolesDao)baseDao;
	}

	/**
	 * 根据id查询角色
	 */
	@Override
	public Roles findRoleById(Long roleId){
		return rolesDao.findRoleById(roleId);
	}

	@Override
	public List<Roles> findRolesByIds(Long[] roleIds, Long organizationId) {
		return rolesDao.findRolesByIds(roleIds, organizationId);
	}

	@Override
	public List<RoleResource> findRoleResourcesByRoleIds(Long[] roleIds, Long organizationId) {
		RoleResource cond = new RoleResource();
		cond.setRoleIdIn(roleIds);
		cond.setOrganizationId(organizationId);
		return userResourceDao.findByCond(cond);
	}

}
