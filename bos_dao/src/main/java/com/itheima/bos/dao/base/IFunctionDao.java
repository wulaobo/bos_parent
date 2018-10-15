package com.itheima.bos.dao.base;

import Utils.PageBean;
import com.itheima.bos.domain.function;

import java.util.List;

public interface IFunctionDao {
    List<function> findAllFunction();

    void save(function model);

    void QueryPage(PageBean pageBean);

    function findFunctionById(String id);

    List<function> findAllByCriteria();

    List<function> findListFunctionByUserId(String id);

    List<function> findAllMenu();

    List<function> findListMenuByUserId(String id);
}
