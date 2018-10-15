package com.itheima.bos.service;

import com.itheima.bos.domain.NoticeBill;

public interface INoticeBillService {
    String findDecidedzoneIdByAddress(String address);

    void save(NoticeBill noticeBill);
}
