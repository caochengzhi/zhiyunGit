package com.chengzhi.scdp.database.service;

import java.io.Serializable;
import java.util.List;

import com.chengzhi.scdp.database.dao.AbstractModel;
import com.chengzhi.scdp.database.dao.PageList;

public interface IBaseService<T extends AbstractModel, PK extends Serializable> {

    public T save(T model);

    public void saveOrUpdate(T model);
    
    public void update(T model);
    
    public void merge(T model);

    public void delete(Class<T> entityClass, PK id);

    public void deleteObject(T model);

    public T get(Class<T> entityClass, PK id);
    
    public int count(Class<T> entityClass);
    
    public List<T> listAll(Class<T> entityClass);
    
    public List<T> listAll(Class<T> entityClass, int pageNum);
    
    public PageList<T> listAll(Class<T> entityClass, int pageNum, int pageSize);

}
