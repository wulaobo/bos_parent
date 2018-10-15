package com.itheima.bos.service;

import Utils.PageBean;
import com.itheima.bos.domain.Region;

import java.util.List;

public interface IRegionService {
    void upload(List<Region> regionList);

    void QueryPage(PageBean pageBean);

    List<Region> findAll();

    List<Region> findListByQ(String q);
}
