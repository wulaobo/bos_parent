package com.itheima.bos.service.impl;

import com.itheima.bos.dao.base.WorkbillDao;
import com.itheima.bos.domain.Workbill;
import com.itheima.bos.service.WorkbillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkbillServiceImpl implements WorkbillService {

    @Autowired
    private WorkbillDao workbillDao;

    @Override
    public void save(Workbill workbill) {
        workbillDao.save(workbill);
    }
}
