package com.itheima.bos.dao.base.impl;

import Utils.PageBean;
import com.itheima.bos.dao.base.IFunctionDao;
import com.itheima.bos.domain.function;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class FunctionDaoImpl extends HibernateDaoSupport implements IFunctionDao {

    @Autowired
    public void mySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<function> findAllFunction() {
        // f WHERE f.parentFunction IS NULL
        String hql = "FROM function";
        List<function> list  = (List<function>) this.getHibernateTemplate().find(hql);
        if(list != null && list.size()>0){
            return list;
        }
        return null;
    }
    public List<function> findAllByCriteria() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(function.class);
        List<function> list = (List<function>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if(list!=null){
            return list;
        }
        return null;
    }

    @Override
    public List<function> findListFunctionByUserId(String id) {
       // String hql = "select distinct f from function f left outer join f.roles_fun r left outer join r.users r where r.id = ?";
        String hql = "SELECT DISTINCT f FROM function f LEFT OUTER JOIN f.roles_fun"
                + " r LEFT OUTER JOIN r.users u WHERE u.id = ?";
        List<function> list = (List<function>) this.getHibernateTemplate().find(hql,id);
        if(list!=null) {
            return list;
        }
        return null;
    }

    @Override
    public List<function> findAllMenu() {
        String hql = "FROM function f where f.generatemenu ='1' order by f.zindex asc";
        List<function> list  = (List<function>) this.getHibernateTemplate().find(hql);
        if(list != null && list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public List<function> findListMenuByUserId(String id) {
        String hql = "SELECT DISTINCT f FROM function f LEFT OUTER JOIN f.roles_fun"
                + " r LEFT OUTER JOIN r.users u WHERE u.id = ?  and f.generatemenu ='1' order by f.zindex asc";
        List<function> list = (List<function>) this.getHibernateTemplate().find(hql,id);
        if(list!=null) {
            return list;
        }
        return null;
    }

    @Override
    public void save(function model) {
        this.getHibernateTemplate().save(model);
    }

    @Override
    public void QueryPage(PageBean pageBean) {
        int currentPage = pageBean.getCurrentPage();
        int pageSize = pageBean.getPageSize();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();

        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        Long total = list.get(0);
        pageBean.setTotal(total.intValue());

        detachedCriteria.setProjection(null);
        //指定hibernate封装对象的方式
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        int firstResults = (currentPage-1)*pageSize;
        int maxResults = pageSize;
        List rows =  this.getHibernateTemplate().findByCriteria(detachedCriteria,firstResults,maxResults);
        pageBean.setRows(rows);
    }

    @Override
    public function findFunctionById(String id) {
//        String hql = "FROM function f where f.id = ?";
//        List<function> list  = (List<function>) this.getHibernateTemplate().find(hql,id);
//        if(list != null && list.size()>0){
//            function fun = list.get(0);
//            return fun;
//        }
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(function.class);
        detachedCriteria.add(Restrictions.eq("id",id));
        List<function> list = (List<function>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if(list != null && list.size()>0){
            function fun = list.get(0);
          //  System.out.println(fun);
            return fun;
        }
        return null;

    }
}
