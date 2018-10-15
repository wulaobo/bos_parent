package com.itheima.bos.dao.base.impl;

import com.itheima.bos.dao.base.WorkbillDao;
import com.itheima.bos.domain.Workbill;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkbillDaoImpl extends HibernateDaoSupport implements WorkbillDao {
    @Autowired
    public void mySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void save(Workbill workbill) {
        this.getHibernateTemplate().save(workbill);

    }

    @Override
    public List<Workbill> findAll() {
        String hql = "from Workbill";
        List<Workbill> list = (List<Workbill>) this.getHibernateTemplate().find(hql);
        if(list!=null){
            return list;
        }
        return null;
    }
}
