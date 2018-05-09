package com.chengzhi.scdp.system.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.chengzhi.scdp.database.dao.hibernate.BaseHibernateDao;
import com.chengzhi.scdp.system.dao.ISysDictionaryDao;
import com.chengzhi.scdp.system.dao.SysDictDatas;

@Repository("sysDictionaryDao")
public class SysDictionaryHibernateDao extends BaseHibernateDao<SysDictDatas, Long> implements ISysDictionaryDao{

}
