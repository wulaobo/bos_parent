package com.itheima.bos.service.impl;

import com.itheima.CustomerService;
import com.itheima.bos.dao.base.INoticeBillDao;
import com.itheima.bos.domain.NoticeBill;
import com.itheima.bos.service.INoticeBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class INoticeBillServiceImpl implements INoticeBillService {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private INoticeBillDao noticeBillDao;
    @Override
    public String findDecidedzoneIdByAddress(String address) {
        String decidedzone_id = customerService.findDecidedzoneIdByAddress(address);
        return decidedzone_id;
    }

    @Override
    public void save(NoticeBill noticeBill) {
        noticeBillDao.save(noticeBill);
    }
}
