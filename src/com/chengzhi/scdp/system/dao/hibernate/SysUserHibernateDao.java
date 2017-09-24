package com.chengzhi.scdp.system.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.chengzhi.scdp.database.dao.hibernate.BaseHibernateDao;
import com.chengzhi.scdp.system.dao.ISysUserDao;
import com.chengzhi.scdp.system.dao.Users;

@Repository("sysUserDao")
public class SysUserHibernateDao extends BaseHibernateDao<Users, String> implements ISysUserDao{

}
