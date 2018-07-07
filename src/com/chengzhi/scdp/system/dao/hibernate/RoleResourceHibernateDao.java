package com.chengzhi.scdp.system.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chengzhi.scdp.database.dao.hibernate.BaseHibernateDao;
import com.chengzhi.scdp.system.dao.IRoleResourceDao;
import com.chengzhi.scdp.system.dao.RoleResource;

@Repository("userResourceDao")
public class RoleResourceHibernateDao extends BaseHibernateDao<RoleResource, Long> implements IRoleResourceDao{

	@Override
	public void saveList(List<RoleResource> list) {
		for(RoleResource obj : list){
			save(obj);
		}
	}
	
}
