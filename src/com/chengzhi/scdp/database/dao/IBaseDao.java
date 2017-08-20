package com.chengzhi.scdp.database.dao;

import java.io.Serializable;
import java.util.List;

/**
 * DAO基础类
 * @author beisi
 *
 * @param <T>
 * @param <PK>
 */
public interface IBaseDao<T extends AbstractModel, PK extends Serializable>{
    
    public T save(T model);

    public void saveOrUpdate(T model);
    
    public void update(T model);
    
    public void updateWithSql(String sql);
    
    public void merge(T model);

    public void delete(Class<T> entityClass, PK id);

    public void deleteObject(T model);

    public T get(Class<T> entityClass, PK id);
    
    public int count(Class<T> entityClass);
    
    public List<T> listAll(Class<T> entityClass);
    
    public List<T> listAll(Class<T> entityClass, int pn);
    
    public List<T> listAll(Class<T> entityClass, int pn, int pageSize);

}
