package com.chengzhi.scdp.database.service.imp;

import java.io.Serializable;
import java.util.List;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.database.dao.AbstractModel;
import com.chengzhi.scdp.database.dao.IBaseDao;
import com.chengzhi.scdp.database.dao.PageList;
import com.chengzhi.scdp.database.service.IBaseService;

/**
 * 基础服务类
 * @author beisi
 *
 * @param <T>
 * @param <PK>
 */
public abstract class BaseServiceImp<T extends AbstractModel, PK extends Serializable> implements IBaseService<T, PK> {
    
	protected IBaseDao<T, PK> baseDao;
    
    public abstract void setBaseDao(IBaseDao<T, PK> baseDao);

    @Override
    public T save(T model) {
        return baseDao.save(model);
    }

    @Override
    public void saveOrUpdate(T model) {
        baseDao.saveOrUpdate(model);
    }
    
    @Override
    public void update(T model) {
        baseDao.update(model);
    }
    
    @Override
	public void updateWithSql(String sql) {
    	baseDao.updateWithSql(sql);
	}

	@Override
    public void merge(T model) {
        baseDao.merge(model);
    }

    @Override
    public void delete(Class<T> entityClass, PK id) {
        baseDao.delete(entityClass, id);
    }

    @Override
    public void deleteObject(T model) {
        baseDao.deleteObject(model);
    }

    @Override
    public T get(Class<T> entityClass, PK id) {
        return baseDao.get(entityClass, id);
    }
    
    @Override
    public int count(T cond) {
        return baseDao.count(cond);
    }
    
    @Override
    public List<T> findByCond(T cond) {
        return baseDao.findByCond(cond);
    }
    
    @Override
	public List<T> findByProperty(Class<T> entityClass, String propertyName,Object value) {
		return baseDao.findByProperty(entityClass, propertyName, value);
	}

	/**
     * 分页查询，默认一次查询十条记录
     */
    @Override
    public List<T> findByCond(T cond,String sortName, String sortOrder, int pageNum) {
        return baseDao.findByCond(cond, sortName, sortOrder ,pageNum, Constants.DEFAULT_PAGE_SIZE);
    }
    
    /**
     * 分页查询
     * @param cond 查询对象
     * @param sortName 排序字段
     * @param sortOrder 排序顺序
     * @param pageNum 页数
     * @param pageSize 每页大小
     * @return 自定义分页对象
     */
    @Override
    public PageList<T> findByCond(T cond, String sortName, String sortOrder, int pageNum, int pageSize) {
    	int total = count(cond);
        List<T> list = baseDao.findByCond(cond, sortName, sortOrder, pageNum, pageSize);
        return new PageList<T>(pageNum,pageSize,total,list);   
    }

}
