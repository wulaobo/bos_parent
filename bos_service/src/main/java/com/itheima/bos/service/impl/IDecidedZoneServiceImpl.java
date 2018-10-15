package com.itheima.bos.service.impl;

import Utils.PageBean;
import com.itheima.bos.dao.base.IDecidedZoneDao;
import com.itheima.bos.dao.base.ISubareaDao;
import com.itheima.bos.domain.DecidedZone;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.IDecidedZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("iDecidedZoneServiceImpl")
@Transactional
public class IDecidedZoneServiceImpl implements IDecidedZoneService {

    @Autowired
    private IDecidedZoneDao iDecidedZoneDao;
    @Autowired
    private ISubareaDao iSubareaDao;

    @Override
    public void save(DecidedZone decidedZone, String[] subareaid) {
        iDecidedZoneDao.save(decidedZone);
        List<Subarea> list = iSubareaDao.findById(subareaid);
        for (Subarea subarea:list) {
            subarea.setDecidedZone(decidedZone);
        }
    }

    @Override
    public void QueryPage(PageBean pageBean) {
        iDecidedZoneDao.QueryPage(pageBean);
    }

    @Override
    public DecidedZone findDecidedZoneById(String decidedzone_id) {
        DecidedZone decidedZone = iDecidedZoneDao.findDecidedZoneById(decidedzone_id);
        return decidedZone;
    }
}
