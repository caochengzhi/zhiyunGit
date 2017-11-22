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
    
     T save(T model);

     void saveOrUpdate(T model);
    
     void update(T model);
    
     void updateWithSql(String sql);
    
     void merge(T model);

     void delete(Class<T> entityClass, PK id);

     void deleteObject(T model);
     
     List<T> findByProperty(Class<T> entityClass, String propertyName, Object value);

     T get(Class<T> entityClass, PK id);
    
     int count(T cond);
    
     List<T> findByCond(T cond);
    
     List<T> findByCond(T cond, String sortName,String sortOrder,int pageNum, int pageSize);

}
