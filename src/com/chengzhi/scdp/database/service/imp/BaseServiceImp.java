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
    public int count(Class<T> entityClass) {
        return baseDao.count(entityClass);
    }
    
    @Override
    public List<T> listAll(Class<T> entityClass) {
        return baseDao.listAll(entityClass);
    }
    
    /**
     * 分页查询，默认一次查询十条记录
     */
    @Override
    public List<T> listAll(Class<T> entityClass, int pageNum) {
        return baseDao.listAll(entityClass, pageNum, Constants.DEFAULT_PAGE_SIZE);
    }
    
    /**
     * 分页查询
     */
    @Override
    public PageList<T> listAll(Class<T> entityClass, int pageNum, int pageSize) {
    	int total = count(entityClass);
        List<T> list = baseDao.listAll(entityClass, pageNum, pageSize);
        return new PageList<T>(pageNum,pageSize,total,list);
    }

}
