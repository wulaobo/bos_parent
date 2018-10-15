package com.itheima.bos.dao.base.impl;

import com.itheima.bos.dao.base.IWorkOrderManagerDao;
import com.itheima.bos.domain.WorkOrderManager;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class WorkOrderManagerDaoImpl extends HibernateDaoSupport implements IWorkOrderManagerDao {
    @Autowired
    public void mySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void save(WorkOrderManager workOrderManager) {
        this.getHibernateTemplate().save(workOrderManager);
    }
}
