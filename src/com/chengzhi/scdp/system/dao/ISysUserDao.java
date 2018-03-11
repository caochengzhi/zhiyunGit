package com.chengzhi.scdp.system.dao;

import com.chengzhi.scdp.database.dao.IBaseDao;

public interface ISysUserDao extends IBaseDao<SysUsers, Long>{

	abstract SysUsers findUserById(Long userId);
}
