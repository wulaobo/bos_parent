package com.itheima.bos.service;

import Utils.PageBean;
import com.itheima.bos.domain.DecidedZone;

public interface IDecidedZoneService {
    void save(DecidedZone decidedZone, String[] subareaid);

    void QueryPage(PageBean pageBean);

    DecidedZone findDecidedZoneById(String decidedzone_id);
}
