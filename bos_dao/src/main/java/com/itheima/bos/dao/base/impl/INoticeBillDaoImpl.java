package com.itheima.bos.dao.base.impl;

import com.itheima.bos.dao.base.INoticeBillDao;
import com.itheima.bos.domain.NoticeBill;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class INoticeBillDaoImpl extends HibernateDaoSupport implements INoticeBillDao{
    @Autowired
    public void mySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void save(NoticeBill noticeBill) {
        this.getHibernateTemplate().save(noticeBill);
    }
}
