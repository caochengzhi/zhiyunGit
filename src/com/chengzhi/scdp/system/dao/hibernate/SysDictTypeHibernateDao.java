package com.chengzhi.scdp.system.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.chengzhi.scdp.database.dao.hibernate.BaseHibernateDao;
import com.chengzhi.scdp.system.dao.ISysDictTypeDao;
import com.chengzhi.scdp.system.dao.SysDictType;

@Repository("sysDictTypeDao")
public class SysDictTypeHibernateDao extends BaseHibernateDao<SysDictType, Long> implements ISysDictTypeDao{

}
