package com.chengzhi.scdp.system.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chengzhi.scdp.database.dao.hibernate.BaseHibernateDao;
import com.chengzhi.scdp.system.dao.IUserRoleDao;
import com.chengzhi.scdp.system.dao.UserRole;

@Repository("userRoleDao")
public class UserRoleHibernateDao extends BaseHibernateDao<UserRole, Long> implements IUserRoleDao{

	/**
	 * 查询用户与角色关系
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> getUserRolesByUserId(Long userId,Long organizationId) {
		List<UserRole> list = null;
		try {
			String queryString = "from UserRole as model where model.userId = :userId and model.organizationId = :organizationId";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("userId", userId);
			queryObject.setParameter("organizationId", organizationId != null?organizationId : getUser().getOrganizationId());
			list = queryObject.list();
		} catch (RuntimeException re) {
			logger.error("find by property name failed", re);
			throw re;
		}
		return list;
	}

}
