package com.itheima.bos.dao.base.impl;

import com.itheima.bos.dao.base.IUserDao;
import com.itheima.bos.domain.User;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("iUserDaoImpl")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{

    //根据用户名和密码来登录
    public User findUserByUsernameAndPassword(String username, String password) {
        String hql = "from User u where u.username = ? and u.password = ?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql,username,password);
        if(list!=null &&list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public User findUserByUsername(String username) {
        String hql = "from User u where u.username = ? ";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql,username);
        if(list!=null &&list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
}
