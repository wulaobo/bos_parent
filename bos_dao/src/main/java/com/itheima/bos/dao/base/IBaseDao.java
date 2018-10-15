package com.itheima.bos.dao.base;

import Utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {

     void save(T entity);
     void delete(T entity);
     void update(T entity);
     T findById(Serializable id);
     List<T> findAll();
     List<T> findByCriteria(DetachedCriteria detachedCriteria);
     void excuteUpdate(String QueryName,Object...objects);
     void QueryPage(PageBean pageBean);
}
