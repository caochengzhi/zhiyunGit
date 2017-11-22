package com.chengzhi.scdp.database.service;

import java.io.Serializable;
import java.util.List;

import com.chengzhi.scdp.database.dao.AbstractModel;
import com.chengzhi.scdp.database.dao.PageList;

public interface IBaseService<T extends AbstractModel, PK extends Serializable> {

     T save(T model);

     void saveOrUpdate(T model);
    
     void update(T model);
    
     void merge(T model);

     void delete(Class<T> entityClass, PK id);

     void deleteObject(T model);

     List<T> findByProperty(Class<T> entityClass, String propertyName, Object value);
     
     T get(Class<T> entityClass, PK id);
    
     int count(T cond);
    
     List<T> findByCond(T cond);
    
     List<T> findByCond(T cond, String sortName, String sortOrder, int pageNum);
    
     /**
      * 分页查询
      * @param cond 查询对象
      * @param sortName 排序字段
      * @param sortOrder 排序顺序
      * @param pageNum 页数
      * @param pageSize 每页大小
      * @return 自定义分页对象
      */
     PageList<T> findByCond(T cond,String sortName, String sortOrder, int pageNum, int pageSize);
}
