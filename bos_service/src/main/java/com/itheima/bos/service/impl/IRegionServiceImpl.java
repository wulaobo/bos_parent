package com.itheima.bos.service.impl;

import Utils.PageBean;
import com.itheima.bos.dao.base.IRegionDao;
import com.itheima.bos.domain.Region;
import com.itheima.bos.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class IRegionServiceImpl implements IRegionService {

    @Autowired
    private IRegionDao iRegionDao;

    @Override
    public void upload(List<Region> regionList) {
        for (Region region:regionList) {
            iRegionDao.saveOrUpdate(region);
        }
    }

    @Override
    //分页查询
    public void QueryPage(PageBean pageBean) {
        iRegionDao.QueryPage(pageBean);
    }

    @Override
    public List<Region> findAll() {
        return iRegionDao.findAll();
    }

    @Override
    //模糊查询
    public List<Region> findListByQ(String q) {
        return iRegionDao.findListByQ(q);
    }
}
