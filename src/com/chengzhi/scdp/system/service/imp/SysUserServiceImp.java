package com.chengzhi.scdp.system.service.imp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chengzhi.scdp.database.dao.IBaseDao;
import com.chengzhi.scdp.database.service.imp.BaseServiceImp;
import com.chengzhi.scdp.system.dao.ISysUserDao;
import com.chengzhi.scdp.system.dao.IUserRoleDao;
import com.chengzhi.scdp.system.dao.SysUsers;
import com.chengzhi.scdp.system.dao.UserRole;
import com.chengzhi.scdp.system.service.ISysUserService;

/**
 * 系统用户服务类
 * @author beisi
 *
 */
@Service
public class SysUserServiceImp extends BaseServiceImp<SysUsers, Long> implements ISysUserService {
	protected Logger logger = Logger.getLogger(this.getClass());

	private ISysUserDao sysUserDao;
	@Autowired
	private IUserRoleDao userRoleDao;
	
	@Autowired
	@Qualifier("sysUserDao")
	@Override
	public void setBaseDao(IBaseDao<SysUsers, Long> baseDao) {
		this.baseDao = baseDao;
		sysUserDao = (ISysUserDao)baseDao;
	}

	@Override
	public SysUsers findUserById(Long userId) {
		return sysUserDao.findUserById(userId);
	}

	/**
	 * 获取当前用户下所包含的角色与用户关系
	 */
	@Override
	public List<UserRole> getUserRolesByUserId(Long userId, Long organizationId) {
		return userRoleDao.getUserRolesByUserId(userId, organizationId);
	}
	
}
