package com.chengzhi.scdp.database.dao.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.database.dao.AbstractModel;
import com.chengzhi.scdp.database.dao.IBaseDao;

/**
 * DAO基础实现类
 * 
 * @author beisi
 *
 * @param <T>
 * @param <PK>
 */
public abstract class BaseHibernateDao<T extends AbstractModel, PK extends Serializable>implements IBaseDao<T, PK> {
	
	protected static Logger logger = Logger.getLogger(BaseHibernateDao.class);

//	@Resource(name="sessionFactory")
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if(session == null)
			session = sessionFactory.openSession();
		return session;
	}

	@Override
	public T save(T model) {
		getSession().save(model);
		return model;
	}

	@Override
	public void saveOrUpdate(T model) {
		getSession().saveOrUpdate(model);
	}

	@Override
	public void update(T model) {
		getSession().update(model);
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
		getSession().merge(model);
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

	public void deleteObject(T model) {
		getSession().delete(model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Class<T> entityClass, PK id) {
		return (T) getSession().get(entityClass, id);
	}

	@Override
	public int count(Class<T> entityClass) {
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.setProjection(Projections.rowCount());
		return ((Long) criteria.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listAll(Class<T> entityClass) {
		Criteria criteria = getSession().createCriteria(entityClass);
		return criteria.list();
	}

	@Override
	public List<T> listAll(Class<T> entityClass, int pn) {
		return listAll(entityClass, pn, Constants.DEFAULT_PAGE_SIZE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listAll(Class<T> entityClass, int pageNum, int pageSize) {
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.setFirstResult(pageNum);
		criteria.setMaxResults(pageSize);
		return criteria.list();
	}

}
