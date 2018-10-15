package com.itheima.bos.action.impl;

import com.itheima.Customer;
import com.itheima.CustomerService;
import com.itheima.bos.domain.*;
import com.itheima.bos.service.IDecidedZoneService;
import com.itheima.bos.service.INoticeBillService;
import com.itheima.bos.service.WorkbillService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.sql.Timestamp;

@Controller
@Scope("prototype")
//业务通知单管理
public class NoticeBillAction extends ActionSupport implements ModelDriven<NoticeBill> {

    NoticeBill noticeBill = new NoticeBill();

    @Autowired
    private CustomerService customerService;

    //通过电话号码查询客户
    public String findCustomerByTelephone() throws IOException {
        Customer customer = customerService.findCustomerByTelephone(noticeBill.getTelephone());
        String json = JSONObject.fromObject(customer).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    @Autowired
    private INoticeBillService noticeBillService;
    @Autowired
    private IDecidedZoneService decidedZoneService;
    @Autowired
    private WorkbillService workbillService;

    //增加业务通知单
    public String add() {
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
        noticeBill.setUser(user);
        //获得取件地址
        String address = noticeBill.getPickaddress();
        //
        String decidedzone_id = noticeBillService.findDecidedzoneIdByAddress(address);
        if(decidedzone_id!=null){
            DecidedZone decidedZone = decidedZoneService.findDecidedZoneById(decidedzone_id);
            if(decidedZone!=null){
                Staff staff = decidedZone.getStaff();
                if(staff != null){
                    noticeBill.setOrdertype(NoticeBill.OrderType_AUTO);
                    noticeBill.setStaff(staff);

                    Workbill workbill = new Workbill();
                    workbill.setAttachbilltimes(0);
                    workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
                    workbill.setRemark(noticeBill.getRemark());
                    workbill.setType(Workbill.TYPE_1);
                    workbill.setStaff(staff);
                    workbill.setNoticeBill(noticeBill);
                    workbill.setPickstate(Workbill.PickState_NO);
                    workbillService.save(workbill);
                }
            }
        }else{
            noticeBill.setOrdertype(NoticeBill.OrderType_MAN);
        }

        noticeBillService.save(noticeBill);

        return "noticebill_add";
    }

    @Override
    public NoticeBill getModel() {
        return noticeBill;
    }
}
