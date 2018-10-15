package com.itheima.bos.action.impl;

import Utils.PageBean;
import com.itheima.Customer;
import com.itheima.CustomerService;
import com.itheima.bos.dao.base.ISubareaDao;
import com.itheima.bos.domain.DecidedZone;
import com.itheima.bos.domain.Region;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.IDecidedZoneService;
import com.itheima.bos.service.ISubareaService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;

@Controller("decidedZoneAction")
public class DecidedZoneAction extends ActionSupport implements ModelDriven<DecidedZone> {

    DecidedZone decidedZone = new DecidedZone();

    @Resource(name="iDecidedZoneServiceImpl")
    private IDecidedZoneService iDecidedZoneService;

    //属性驱动
    private String[] subareaid;
    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }

    //保存定区
    public String add() {
       // System.out.println(decidedZone);
        iDecidedZoneService.save(decidedZone,subareaid);
        return "list";
    }

    //远程访问未关联的定区
    @Autowired
    private CustomerService proxy;
    public String findAllNotAssociation() throws IOException {
        List<Customer> list  =proxy.findAllNotAssociation();
        //JsonConfig jsonConfig = new JsonConfig();
        String json = JSONArray.fromObject(list).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    //远程访问关联的定区
    public String findAllHasAssociation() throws IOException {
        String id = decidedZone.getId();
        List<Customer> list = proxy.findAllHasAssociation(id);
        String json = JSONArray.fromObject(list).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    //属性注入客户的id数组
    private Integer[] customerIds;
    public void setCustomerIds(Integer[] customerIds) {
        this.customerIds = customerIds;
    }

    //远程访问服务定区关联客户
    public String assigncustomerstodecidedzone() {
        String id= decidedZone.getId();
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0;i<customerIds.length;i++){
            list.add(customerIds[i]);
        }
        proxy.assigncustomerstodecidedzone(id,list);
        return "list";
    }

    //定区分页
    private int page;
    private int rows;
    //分区分页查询
    public String QueryPage() throws IOException {

        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DecidedZone.class);
        pageBean.setDetachedCriteria(detachedCriteria);
        iDecidedZoneService.QueryPage(pageBean);

        //System.out.println(pageBean);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"subareas_De","decidedZones","currentPage","detachedCriteria","pageSize"});
        String json = JSONObject.fromObject(pageBean,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    @Override
    public DecidedZone getModel() {
        return decidedZone;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
