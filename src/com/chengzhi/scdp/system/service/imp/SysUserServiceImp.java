package com.chengzhi.scdp.system.service.imp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chengzhi.scdp.database.dao.IBaseDao;
import com.chengzhi.scdp.database.service.imp.BaseServiceImp;
import com.chengzhi.scdp.system.dao.ISysUserDao;
import com.chengzhi.scdp.system.dao.Users;
import com.chengzhi.scdp.system.service.ISysUserService;

/**
 * 系统用户服务类
 * @author beisi
 *
 */
@Service
public class SysUserServiceImp extends BaseServiceImp<Users, String> implements ISysUserService {
	private Logger logger = Logger.getLogger(this.getClass());

	private ISysUserDao sysUserDao;
	
	@Autowired
	@Qualifier("sysUserDao")
	@Override
	public void setBaseDao(IBaseDao<Users, String> baseDao) {
		this.baseDao = baseDao;
		sysUserDao = (ISysUserDao)baseDao;
	}

}
