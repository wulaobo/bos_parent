package com.itheima.bos.dao.base.impl;

import Utils.PageBean;
import com.itheima.bos.dao.base.ISubareaDao;
import com.itheima.bos.domain.Subarea;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ISubareaDaoImpl extends HibernateDaoSupport implements ISubareaDao {

    @Autowired
    private void mySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void save(Subarea subarea) {
        this.getHibernateTemplate().save(subarea);
    }

    @Override
    public List<Subarea> findAll() {
        String hql="from Subarea";
        List<Subarea> list = (List<Subarea>) this.getHibernateTemplate().find(hql);
        return list;
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
    public List<Subarea> findByCriteria(DetachedCriteria detachedCriteria) {
        return (List<Subarea>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }

    @Override
    public List<Subarea> findById(String[] subareaid) {
        List<Subarea> list = new ArrayList<Subarea>();
        for (String id:subareaid) {
            Subarea subarea = this.getHibernateTemplate().get(Subarea.class,id);
            list.add(subarea);
        }
        return list;
    }

    @Override
    public List<Subarea> findListSubareaByDecidedzone_id(String decidedzone_id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
        detachedCriteria.add(Restrictions.eq("decidedZone.id",decidedzone_id));
        List<Subarea> list = (List<Subarea>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if(list.size()>0) {
            return list;
        }else{
            return null;
        }
    }

    @Override
    public List<Object> findSubareaByGroupProvince() {
        //sql: select r.province,count(*) from bc_subarea s left outer join bc_region r on s.region_id = r.id group by r.province;
        String hql = "SELECT r.province,count(*) FROM Subarea s LEFT OUTER JOIN s.region r GROUP BY r.province";
        List<Object> list = (List<Object>) this.getHibernateTemplate().find(hql);
        if(list!=null &&list.size()>0){
            return list;
        }
        return null;
    }

}