package com.chengzhi.scdp.system.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.chengzhi.scdp.database.dao.hibernate.BaseHibernateDao;
import com.chengzhi.scdp.system.dao.IRolesDao;
import com.chengzhi.scdp.system.dao.Roles;

@Repository("rolesDao")
public class RolesHibernateDao extends BaseHibernateDao<Roles, Long> implements IRolesDao{

	@Override
	public Roles findRoleById(Long roleId) {
		return get(Roles.class, roleId);
	}

	
}
