package com.chengzhi.scdp.system.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Roles> findRolesByIds(Long[] roleIds, Long organizationId) {
		Criteria c = this.getSession().createCriteria(Roles.class);
		c.add(Restrictions.eq("organizationId", organizationId));
		c.add(Restrictions.in("roleId", roleIds));
		return c.list();
	}

}
