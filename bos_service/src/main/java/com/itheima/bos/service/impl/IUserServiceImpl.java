package com.itheima.bos.service.impl;

import Utils.MD5Utils;
import Utils.PageBean;
import com.itheima.bos.dao.base.IUserDao;
import com.itheima.bos.domain.Role;
import com.itheima.bos.domain.User;
import com.itheima.bos.service.IUserService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("iUserServiceImpl")
@Transactional
public class IUserServiceImpl implements IUserService {

    @Resource(name="iUserDaoImpl")
    private IUserDao iUserDao;

    //用户登录功能
    public User login(User user) {
         String password = MD5Utils.md5(user.getPassword());
         User u = iUserDao.findUserByUsernameAndPassword(user.getUsername(),password);
         return u;
    }

    //用户修改密码
    public void editPassword(String password, String id) {
           iUserDao.excuteUpdate("user.editPassword",password,id);
    }

    @Override
    //添加用户
    public void save(User user, String[] roleIds) {
        String password = MD5Utils.md5(user.getPassword());
        user.setPassword(password);
        iUserDao.save(user);
        for(int i= 0;i<roleIds.length;i++){
            Role role = new Role();
            role.setId(roleIds[i]);
            user.getRoles().add(role);
        }
    }

    @Override
    public void QueryPage(PageBean pageBean) {
        iUserDao.QueryPage(pageBean);
    }


}
