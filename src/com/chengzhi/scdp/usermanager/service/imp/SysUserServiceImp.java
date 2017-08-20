package com.chengzhi.scdp.usermanager.service.imp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chengzhi.scdp.database.dao.IBaseDao;
import com.chengzhi.scdp.database.service.imp.BaseServiceImp;
import com.chengzhi.scdp.usermanager.dao.ISysUserDao;
import com.chengzhi.scdp.usermanager.dao.SysUser;
import com.chengzhi.scdp.usermanager.service.ISysUserService;

/**
 * 系统用户服务类
 * @author beisi
 *
 */
@Service
public class SysUserServiceImp extends BaseServiceImp<SysUser, String> implements ISysUserService {
	private Logger logger = Logger.getLogger(this.getClass());

	private ISysUserDao sysUserDao;
	
	@Autowired
	@Qualifier("sysUserDao")
	@Override
	public void setBaseDao(IBaseDao<SysUser, String> baseDao) {
		this.baseDao = baseDao;
		sysUserDao = (ISysUserDao)baseDao;
	}

}
