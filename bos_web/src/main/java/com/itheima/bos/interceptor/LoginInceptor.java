package com.itheima.bos.interceptor;

import Utils.BosUtils;
import com.itheima.bos.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/*
  定义拦截器:如果用户没有登录,拦截到登录页面让用户先登录.
 */
public class LoginInceptor extends MethodFilterInterceptor {
    //拦截方法
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {

        ActionProxy proxy = actionInvocation.getProxy();
        String actionName = proxy.getActionName();
        String namespace = proxy.getNamespace();
        String url = actionName+"---"+namespace;
        System.out.println(url);

        //从session域中获取User对象
        User user = BosUtils.getUser();
        //System.out.println(user);
        //判断用户是否为空,如果为空,就没有登录
        if(user==null) {
            return "login";
        }
        //用户已登录,放行.
        return actionInvocation.invoke();

    }
}
