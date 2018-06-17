package com.chengzhi.scdp.system.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<SysUsers> findSysUsersByRoleId(Long roleId) {
		String hql =" from SysUsers as model where model.organizationId = :organizationId and model.userId in (select userId from UserRole where roleId = :roleId and organizationId = model.organizationId)";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("roleId", roleId);
		query.setParameter("organizationId", getUser().getOrganizationId());
		return query.list();
	}

}
