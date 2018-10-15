package com.itheima.bos.service;

import Utils.PageBean;
import com.itheima.bos.domain.Role;

import java.util.List;

public interface IRoleService {
    void save(Role model, String functionIds);

    void QueryPage(PageBean pageBean);

    List<Role> findAllRole();
}
