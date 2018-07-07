package com.chengzhi.scdp.database.dao.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.jdbc.Work;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.database.dao.AbstractModel;
import com.chengzhi.scdp.database.dao.IBaseDao;
import com.chengzhi.scdp.system.dao.SysUsers;
import com.chengzhi.scdp.tools.ObjectUtil;

/**
 * DAO基础实现类
 * 
 * @author beisi
 * @param <T>
 * @param <PK>
 */
public abstract class BaseHibernateDao<T extends AbstractModel, PK extends Serializable>implements IBaseDao<T, PK> {
	
	protected static Logger logger = Logger.getLogger(BaseHibernateDao.class);
	private SysUsers currentUser;
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if(session == null)
			sessionFactory.openSession();
		return session;
	}

	public SysUsers getUser(){
		currentUser = Constants.getCurrentSysUser();
		return currentUser;
	}
	
	@Override
	public T save(T model) {
		model.setOrganizationId(getUser().getOrganizationId());
		getSession().save(model);
		return model;
	}

	@Override
	public void saveOrUpdate(T model) {
		model.setOrganizationId(getUser().getOrganizationId());;
		getSession().saveOrUpdate(model);
	}

	@Override
	public void update(T model) {
		model.setOrganizationId(getUser().getOrganizationId());;
		getSession().update(model);
	}
	
	/**
	 * 执行jdbc sql更新操作
	 */
	@Override
	public void updateWithSql(String sql){
		getSession().doWork(
				new Work(){
					@Override
					public void execute(Connection connect) throws SQLException {
						connect.prepareStatement(sql).execute();
					}
				}
			);
	}
	
	@Override
	public void delete(Class<T> entityClass, PK id) {
		deleteObject(get(entityClass, id));
	}

	@Override
	public void deleteObject(T model) {
		getSession().delete(model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {
		logger.debug("finding SysUsers instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from "+entityClass.getName()+" as model where model." + propertyName + "= :propertyName and model.organizationId = :organizationId";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("propertyName", value);
			queryObject.setParameter("organizationId", getUser().getOrganizationId());
			return queryObject.list();
		} catch (RuntimeException re) {
			logger.error("find by property name failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T get(Class<T> entityClass, PK id) {
		return (T) getSession().get(entityClass, id);
	}
	
	@Override
	public int count(T cond) {
		if(cond.getOrganizationId() == null)
			cond.setOrganizationId(getUser().getOrganizationId());
		Criteria criteria = getSession().createCriteria(cond.getClass());
		ObjectUtil.contructCond(criteria, cond);
		criteria.setProjection(Projections.rowCount());
		return ((Long) criteria.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCond(T cond) {
		if(cond.getOrganizationId() == null)
			cond.setOrganizationId(getUser().getOrganizationId());
		
		Criteria criteria = getSession().createCriteria(cond.getClass());
		ObjectUtil.contructCond(criteria, cond);
		return criteria.list();
	}

	/**
	 * 分页查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCond(T cond, String sortName,String sortOrder, int pageNum, int pageSize) {
		if(cond.getOrganizationId() == null)
			cond.setOrganizationId(getUser().getOrganizationId());
		
		Criteria criteria = getSession().createCriteria(cond.getClass());
		ObjectUtil.contructCond(criteria, cond);
		if(sortName != null && sortOrder != null){
			if("asc".equalsIgnoreCase(sortOrder))
				criteria.addOrder(Order.asc(sortName));
			else
				criteria.addOrder(Order.desc(sortName));
		}
			
		criteria.setFirstResult(pageNum);
		criteria.setMaxResults(pageSize);
		return criteria.list();
	}
	
	/**
	 * merge的作用是：新new一个对象， 如果该对象设置了ID，则这个对象就当作游离态处理：
	 * 当ID在数据库中不能找到时，用update的话肯定会报异常，然而用merge的话，就会insert。
	 * 当ID在数据库中能找到的时候，update与merge的执行效果都是更新数据，发出update语句；
	 * 如果没有设置ID的话，则这个对象就当作瞬态处理：
	 * 用update的话，由于没有ID，所以会报异常，merge此时则会保存数据，根据ID生产策略生成一条数据
	 */
	@Override
	public void merge(T model) {
		model.setOrganizationId(getUser().getOrganizationId());
		getSession().merge(model);
	}

}
