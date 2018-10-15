package com.itheima.bos.action.impl;

import Utils.BosUtils;
import Utils.MD5Utils;
import Utils.PageBean;
import com.itheima.Customer;
import com.itheima.CustomerService;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.domain.User;
import java.util.List;
import com.itheima.bos.service.IUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;

@Controller("userAction")
@Scope("prototype")
public class UserAction  extends ActionSupport implements ModelDriven<User> {
    User user = new User();

    @Resource(name = "iUserServiceImpl")
    private IUserService userService;


//    @Autowired
//    private CustomerService customerService;

    private String checkcode;
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    public User getModel() {
        return user;
    }

    //用户登录方法:使用shiro框架进行权限控制
    public String login() {

        //校验验证码是否正确
        //从session域中获取验证码
        String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");

        //校验验证码是否正确
        if (StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)) {
            //获取当前用户
            Subject subject = SecurityUtils.getSubject();
            String password = MD5Utils.md5(user.getPassword());
            user.setPassword(password);
            AuthenticationToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
            try{
                subject.login(token);
            }catch (Exception e){
                e.printStackTrace();
                return LOGIN;
            }
            User user = (User) subject.getPrincipal();
            if(user!=null){
                ActionContext.getContext().getSession().put("loginUser",user);
            }
            return "home";
        } else {
            //验证码输入错误,返回登录页面
            this.addActionError("您输入的验证码错误");
            return LOGIN;
        }

    }

    //用户登录方法
    public String login_abc() {

        //校验验证码是否正确
        //从session域中获取验证码
        String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");

        //校验验证码是否正确
        if (StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)) {
            //验证码正确,验证用户名和密码
            User user = userService.login(getModel());
            if (user != null) {
                ActionContext.getContext().getSession().put("loginUser", user);
                return "home";
            } else {
                this.addActionError("您输入的用户名或密码错误");
                return LOGIN;
            }

        } else {
            //验证码输入错误,返回登录页面
            this.addActionError("您输入的验证码错误");
            return LOGIN;
        }

    }

    //属性驱动
    private String[] RoleIds;
    public void setRoleIds(String[] roleIds) {
        RoleIds = roleIds;
    }

    //添加用户
    public String add() {
        userService.save(user,RoleIds);
        return "list";
    }

    //修改密码
    public String editPassword() throws IOException {
        String f="1";
        //获得用户
       // User user = (User) ActionContext.getContext().getSession().get("loginUser");
        User user = BosUtils.getUser();
        String password = (String) ServletActionContext.getRequest().getAttribute("password");
        String psd = MD5Utils.md5(password);
        user.setPassword(psd);
        try{
            userService.editPassword(user.getPassword(),user.getId());
        }catch(Exception e){
            f="0";
            e.printStackTrace();
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(f);
        return NONE;
    }

    //属性驱动
    private int page;
    private int rows;

    //分页查询方法
    public String QueryPage() throws IOException {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        pageBean.setDetachedCriteria(detachedCriteria);

        userService.QueryPage(pageBean);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"roles","currentPage","detachedCriteria","total"});
        String json = JSONObject.fromObject(pageBean,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    //用户注销
    public String logout() {
        //设置session失效
        ServletActionContext.getRequest().getSession().invalidate();
        return LOGIN;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }


}
