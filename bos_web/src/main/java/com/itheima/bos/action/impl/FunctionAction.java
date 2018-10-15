package com.itheima.bos.action.impl;

import Utils.PageBean;
import com.itheima.bos.domain.function;
import com.itheima.bos.service.IFunctionService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class FunctionAction extends ActionSupport implements ModelDriven<function> {
    function model = new function();

    @Autowired
    private IFunctionService functionService;
   //下拉列表查询所有权限
    public String findAllFunction() throws IOException {
        List<function> list = functionService.findAllFunction();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"parentFunction","roles_fun"});
        String json = JSONArray.fromObject(list,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    //添加权限
    public String add(){
        functionService.save(model);
        return "list";
    }

    //查询菜单的方法
    public String findMenu() throws IOException {
        List<function> list = functionService.findMenu();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"parentFunction","roles_fun","sonFunctions"});
        String json = JSONArray.fromObject(list,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    private int page;
    private int rows;

    //分页查询方法
    public String QueryPage() throws IOException {

        //如果实体类中有page属性 那么与分页查询的page属性冲突,此时struts2会将页面传过来的page属性值赋给模型驱动实体类的page属性
        String page1 = model.getPage();
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(Integer.parseInt(page1));

        pageBean.setPageSize(rows);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(function.class);
        pageBean.setDetachedCriteria(detachedCriteria);

        functionService.QueryPage(pageBean);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"parentFunction","roles_fun"});
        String json = JSONObject.fromObject(pageBean,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    @Override
    public function getModel() {
        return model;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
