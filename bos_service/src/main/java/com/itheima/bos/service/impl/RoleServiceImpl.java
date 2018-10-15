package com.itheima.bos.service.impl;

import Utils.PageBean;
import com.itheima.bos.dao.base.IFunctionDao;
import com.itheima.bos.dao.base.IRoleDao;
import com.itheima.bos.domain.Role;
import com.itheima.bos.domain.function;
import com.itheima.bos.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IFunctionDao functionDao;

    @Override
    public void save(Role model, String functionIds) {
        roleDao.save(model);
        //解析functionIds
        String[] split = functionIds.split(",");
        //角色关联权限
        for(int i = 0;i<split.length;i++) {
//            function fun = new function();
//            fun.setId(split[i]);
            String id= split[i];
            function fun = functionDao.findFunctionById(id);
            model.getFunctions().add(fun);
        }

    }

    @Override
    public void QueryPage(PageBean pageBean) {
        roleDao.QueryPage(pageBean);
    }

    @Override
    public List<Role> findAllRole() {
        List<Role> list = roleDao.findAll();
        if(list!=null){
            return list;
        }
        return null;
    }
}
