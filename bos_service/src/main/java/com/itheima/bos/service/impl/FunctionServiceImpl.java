package com.itheima.bos.service.impl;

import Utils.BosUtils;
import Utils.PageBean;
import com.itheima.bos.dao.base.IFunctionDao;
import com.itheima.bos.domain.User;
import com.itheima.bos.domain.function;
import com.itheima.bos.service.IFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

    @Autowired
    private IFunctionDao functionDao;

    @Override
    public List<function> findAllFunction() {
        return functionDao.findAllFunction();
    }

    @Override
    public void save(function model) {
        function parentFunction = model.getParentFunction();
        if(parentFunction!=null &&parentFunction.getId().equals("") ){
            model.setParentFunction(null);
        }
        functionDao.save(model);
    }

    @Override
    public void QueryPage(PageBean pageBean) {
        functionDao.QueryPage(pageBean);
    }

    @Override
    public List<function> findMenu() {
        //获取登录用户
        User user = BosUtils.getUser();
        List<function> list = null;
        if(user.getUsername().equals("admin")){
            //判断登录用户是否为admin(超级管理员)
             list = functionDao.findAllMenu();
        }else{
            //登录用户不是admin.根据id查询菜单
            list= functionDao.findListMenuByUserId(user.getId());
        }
        return list;
    }
}
