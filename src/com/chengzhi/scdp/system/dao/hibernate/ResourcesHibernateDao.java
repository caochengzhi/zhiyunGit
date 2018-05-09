package com.chengzhi.scdp.system.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.chengzhi.scdp.database.dao.hibernate.BaseHibernateDao;
import com.chengzhi.scdp.system.dao.IResourcesDao;
import com.chengzhi.scdp.system.dao.Resources;

@Repository("resourceDao")
public class ResourcesHibernateDao extends BaseHibernateDao<Resources, Long> implements IResourcesDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Resources> getPermissionsByIds(Long[] resourceIds, Long organizationId) {
		Criteria c = this.getSession().createCriteria(Resources.class);
		c.add(Restrictions.eq("organizationId", organizationId != null?organizationId : getUser().getOrganizationId()));
		c.add(Restrictions.in("resourceId", resourceIds));
		return c.list();
	}
	
}
