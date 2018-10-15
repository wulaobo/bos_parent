package com.itheima.bos.dao.base.impl;

import Utils.PageBean;
import com.itheima.bos.dao.base.IDecidedZoneDao;
import com.itheima.bos.domain.DecidedZone;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("iDecidedZoneDaoImpl")
public class IDecidedZoneDaoImpl extends HibernateDaoSupport implements IDecidedZoneDao {

    @Autowired
    private void mySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    //保存定区
    public void save(DecidedZone decidedZone) {
        this.getHibernateTemplate().save(decidedZone);
    }

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
    public DecidedZone findDecidedZoneById(String decidedzone_id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DecidedZone.class);
        detachedCriteria.add(Restrictions.eq("id",decidedzone_id));
        List<DecidedZone> list = (List<DecidedZone>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        DecidedZone decidedZone = list.get(0);
        if(decidedZone!=null){
            return decidedZone;
        }else{
            return null;
        }

    }
}
