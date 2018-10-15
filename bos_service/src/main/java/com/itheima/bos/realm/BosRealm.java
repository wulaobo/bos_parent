package com.itheima.bos.realm;

import com.itheima.bos.dao.base.IFunctionDao;
import com.itheima.bos.dao.base.IUserDao;
import com.itheima.bos.domain.User;
import com.itheima.bos.domain.function;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


public class BosRealm extends AuthorizingRealm{
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IFunctionDao functionDao;

    @Override
    //认证方法
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("自定义的认证方法执行了....");
        //对token进行强转,转成 UsernamePasswordToken类型 token中装着登录时的用户名和密码
        UsernamePasswordToken passwordToken = (UsernamePasswordToken)token;
        //获得登录时的用户名
        String username = passwordToken.getUsername();
        System.out.println(username);
        //根据登录时的用户名查询用户是否存在
        User user = userDao.findUserByUsername(username);
        //user.getPassword() 是框架自动查询数据库获得
        AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        System.out.println(user.getPassword());
        return info;
    }

    @Override
    //授权方法
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("staff-list");
        //info.addStringPermission("staff-save");

        //获取当前登录用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //如果用户名为admin就查询所有权限
        if(user.getUsername().equals("admin")){
            //查询所有权限
            List<function> list = functionDao.findAllByCriteria();
            for (function fun: list) {
                info.addStringPermission(fun.getCode());
            }

        }else{
            //如果用户名不是admin就根据id查询权限
            List<function> list = functionDao.findListFunctionByUserId(user.getId());
            for (function fun: list) {
                info.addStringPermission(fun.getCode());
            }
        }
        return info;

    }

}
