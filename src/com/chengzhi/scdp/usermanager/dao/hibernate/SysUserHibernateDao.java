package com.chengzhi.scdp.usermanager.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.chengzhi.scdp.database.dao.hibernate.BaseHibernateDao;
import com.chengzhi.scdp.usermanager.dao.ISysUserDao;
import com.chengzhi.scdp.usermanager.dao.SysUser;

@Repository("sysUserDao")
public class SysUserHibernateDao extends BaseHibernateDao<SysUser, String> implements ISysUserDao{

}
