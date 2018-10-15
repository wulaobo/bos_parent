package com.itheima.bos.service;

import Utils.PageBean;
import com.itheima.bos.domain.User;

public interface IUserService {
    User login(User user);

    void editPassword(String password, String id);

    void save(User user, String[] roleIds);

    void QueryPage(PageBean pageBean);
}
