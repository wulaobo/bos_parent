package com.itheima.bos.action;

import Utils.PageBean;
import com.itheima.bos.domain.Staff;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    public final static String LIST="list";

    //分页查询抽取
    protected PageBean pageBean = new PageBean();
    protected DetachedCriteria detachedCriteria;

    public void setPage(int page) {
        pageBean.setCurrentPage(page);
    }
    public void setRows(int rows) {
        pageBean.setPageSize(rows);
    }


    protected T model;

    public BaseAction() {
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = superclass.getActualTypeArguments();
        Class<T> entityClass = (Class<T>) actualTypeArguments[0];
        detachedCriteria = DetachedCriteria.forClass(entityClass);
        pageBean.setDetachedCriteria(detachedCriteria);
        try {
            model = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public T getModel() {
        return model;
    }

    public void json2(Object o, String[] excludes) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        String json = JSONObject.fromObject(o,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void json2(List list, String[] excludes) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        String json = JSONArray.fromObject(list,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
