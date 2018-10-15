package com.itheima.bos.dao.base;

import Utils.PageBean;
import com.itheima.bos.domain.DecidedZone;

public interface IDecidedZoneDao {
    void save(DecidedZone decidedZone);

    void QueryPage(PageBean pageBean);

    DecidedZone findDecidedZoneById(String decidedzone_id);
}
