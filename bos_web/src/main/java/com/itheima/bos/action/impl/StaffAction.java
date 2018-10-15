package com.itheima.bos.action.impl;

import Utils.PageBean;
import com.itheima.bos.action.BaseAction;
import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IStaffService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import java.io.IOException;
import java.util.List;

/*
   取派员
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

    @Autowired
    private IStaffService iStaffService;

    //保存取派员
    //@RequiresPermissions("staff-save")
    public String saveStaff(){
        iStaffService.saveStaff(model);
        return "list";
    }

    public String findAllNotDeleteStaff() {
        List<Staff> list = iStaffService.findAllNotDeleteStaff();
        this.json2(list,new String[]{"decidedZones","telephone","haspda","deltag","station","standard"});
        return NONE;
    }

   //接收页面的分页参数
    //分页查询
    public String QueryPage() throws IOException {
//        PageBean pageBean = new PageBean();
//        pageBean.setCurrentPage(page);
//        pageBean.setPageSize(rows);
//        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
//        pageBean.setDetachedCriteria(detachedCriteria);

         iStaffService.QueryPage(pageBean);
         this.json2(pageBean, new String[]{"currentPage","pageSize","detachedCriteria","decidedZones"});
//        JsonConfig jsonConfig = new JsonConfig();
//        jsonConfig.setExcludes(new String[]{"currentPage","detachedCriteria","pageSize"});
//        JSONObject json = JSONObject.fromObject(pageBean);
//        //System.out.println(json);
//        ServletActionContext.getResponse().setContentType("html/json;charset=utf-8");
//        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    private String ids;

    //批量删除取派员
   @RequiresPermissions("staff-delete")
    public String deleteBatch() {

//        Subject subject = SecurityUtils.getSubject();
//        subject.checkPermission("staff-delete");
        //获取取派员的id
        if(StringUtils.isNotBlank(ids)){
            String[] idArray= ids.split(",");
            iStaffService.deleteBatch(idArray);
            //System.out.println(idArray.toString());
        }
        return "list";
    }

    //修改取派员
    public String editStaff() {
        //先从数据库中查询取派员
        Staff staff = iStaffService.findStaffById(model.getId());
        //System.out.println(staff);
        staff.setName(model.getName());
        staff.setTelephone(model.getTelephone());
        staff.setStation(model.getStation());
        staff.setHaspda(model.getHaspda());
        staff.setStandard(model.getStandard());
        iStaffService.updateStaff(staff);
        return LIST;
    }


//    public int getPage() {
//        return page;
//    }

//    public void setPage(int page) {
//        this.page = page;
//    }
//
////    public int getRows() {
////        return rows;
////    }
//
//    public void setRows(int rows) {
//        this.rows = rows;
//    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }


}
