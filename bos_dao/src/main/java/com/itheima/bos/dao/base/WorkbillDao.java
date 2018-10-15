package com.itheima.bos.dao.base;

import com.itheima.bos.domain.Workbill;

import java.util.List;

public interface WorkbillDao {
    void save(Workbill workbill);

    List<Workbill> findAll();
}
