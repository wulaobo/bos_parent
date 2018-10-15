package com.itheima.bos.action.impl;

import com.itheima.bos.domain.WorkOrderManager;
import com.itheima.bos.service.IWorkOrderManagerService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Scope("prototype")
public class WorkOrderManagerAction extends ActionSupport implements ModelDriven<WorkOrderManager> {
    WorkOrderManager workOrderManager = new WorkOrderManager();

    @Autowired
    private IWorkOrderManagerService workOrderManagerService;

    public String save() throws IOException {

        String f = "1";
        try{
            workOrderManagerService.save(workOrderManager);
        }catch (Exception e){
            f = "0";
            e.printStackTrace();
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(f);
        return NONE;
    }

    @Override
    public WorkOrderManager getModel() {
        return workOrderManager;
    }
}
