package com.itheima.bos.dao.base;

import com.itheima.bos.domain.Region;

import java.util.List;

public interface IRegionDao extends IBaseDao<Region>{
    void saveOrUpdate(Region region);

    List<Region> findListByQ(String q);
}
