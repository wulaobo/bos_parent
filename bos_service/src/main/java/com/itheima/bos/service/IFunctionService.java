package com.itheima.bos.service;

import Utils.PageBean;
import com.itheima.bos.domain.function;

import java.util.List;

public interface IFunctionService {
    List<function> findAllFunction();

    void save(function model);

    void QueryPage(PageBean pageBean);

    List<function> findMenu();
}
