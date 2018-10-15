package com.itheima.bos.action.impl;

import com.itheima.bos.action.BaseAction;
import com.itheima.bos.domain.Role;
import com.itheima.bos.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

    @Autowired
    private IRoleService roleService;

    //属性驱动
    private String functionIds;
    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

    //添加角色
    public String saveRole() {
        roleService.save(model,functionIds);
        return LIST;
    }

    //分页查询
    public String QueryPage() {
        roleService.QueryPage(pageBean);
        this.json2(pageBean,new String[]{"users","functions"});
        return NONE;
    }

    //查询所有的角色
    public String findAllRole() {
        List<Role> list = roleService.findAllRole();
        this.json2(list,new String[]{"users","functions"});
        return NONE;
    }

}
