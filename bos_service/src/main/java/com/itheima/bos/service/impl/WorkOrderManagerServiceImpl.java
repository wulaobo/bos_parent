package com.itheima.bos.service.impl;

import com.itheima.bos.dao.base.IWorkOrderManagerDao;
import com.itheima.bos.domain.WorkOrderManager;
import com.itheima.bos.service.IWorkOrderManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkOrderManagerServiceImpl implements IWorkOrderManagerService {

    @Autowired
    private IWorkOrderManagerDao workOrderManagerDao;

    @Override
    public void save(WorkOrderManager workOrderManager) {
        workOrderManagerDao.save(workOrderManager);
    }
}
