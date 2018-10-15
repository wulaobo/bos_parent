package com.itheima.bos.dao.base.impl;

import Utils.PageBean;
import com.itheima.bos.dao.base.IBaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

    //代表的是某个实体的类型
    private Class<T> entityClass;

    @Resource(name="sessionFactory")
    public void mySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public BaseDaoImpl() {
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = superclass.getActualTypeArguments();
        entityClass = (Class<T>) actualTypeArguments[0];
    }

    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    public void update(T entity) {
       this.getHibernateTemplate().update(entity);
    }

    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass,id);
    }

    public List<T> findAll() {
        String hql = "from "+entityClass.getSimpleName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }

    @Override
    public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
        List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    public void excuteUpdate(String QueryName, Object... objects) {
         Session session = this.getSessionFactory().getCurrentSession();
         Query query = session.getNamedQuery(QueryName);
         int i = 0;
         for (Object object:objects) {
            query.setParameter(i++,object);
        }
        query.executeUpdate();  //执行更新和删除语句
    }

    @Override
    public void QueryPage(PageBean pageBean) {
        int currentPage = pageBean.getCurrentPage();
        int pageSize = pageBean.getPageSize();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();

        //total
        detachedCriteria.setProjection(Projections.rowCount()); //指定hibernate发出sql语句的格式 -> select count(*) from bc_staff;
        List<Long> countlist = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        Long total = countlist.get(0);
        pageBean.setTotal(total.intValue());

        //rows
        detachedCriteria.setProjection(null);   //指定hibernate发出sql语句的格式 -> select * from bc_staff;
        //指定hibernate封装对象的方式
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        int firstResult=(currentPage-1)*pageSize;
        int maxResults=pageSize;
        List rows= this.getHibernateTemplate().findByCriteria(detachedCriteria,firstResult,maxResults);
        pageBean.setRows(rows);
    }
}
