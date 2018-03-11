package com.chengzhi.scdp.system.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.chengzhi.scdp.database.dao.hibernate.BaseHibernateDao;
import com.chengzhi.scdp.system.dao.ISysUserDao;
import com.chengzhi.scdp.system.dao.SysUsers;

@Repository("sysUserDao")
public class SysUserHibernateDao extends BaseHibernateDao<SysUsers, Long> implements ISysUserDao{

	@Override
	public SysUsers findUserById(Long userId) {
		return get(SysUsers.class, userId);
	}

}
