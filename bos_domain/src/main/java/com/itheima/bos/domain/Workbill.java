package com.itheima.bos.domain;

import java.io.Serializable;
import java.sql.Timestamp;

//工单
public class Workbill implements Serializable {
    private String id;   //工单号
   // private String noticebill_id;//业务通知单号   外键
    private String type;//工单类型   新单  追单 改单 销单
    private String pickstate;//取件状态   已取件 取件中 未取件
    private Timestamp buildtime;//工单生成时间
    private Integer attachbilltimes;//追单次数
    private String remark;//备注
    //private String staff_id;//取派员编号   外键

    //多对一
    private Staff staff;
    private NoticeBill noticeBill;

    public static final String PickState_NO = "未取件";
    public static final String PickState_RUNNING = "取件中";
    public static final String PickState_YES = "已取件";

    public static final String TYPE_1 = "新单";
    public static final String TYPE_2 = "追单";
    public static final String TYPE_3 = "改单";
    public static final String TYPE_4 = "销单";

    public NoticeBill getNoticeBill() {
        return noticeBill;
    }

    public void setNoticeBill(NoticeBill noticeBill) {
        this.noticeBill = noticeBill;
    }

    public Staff getStaff() {

        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAttachbilltimes() {

        return attachbilltimes;
    }

    public void setAttachbilltimes(Integer attachbilltimes) {
        this.attachbilltimes = attachbilltimes;
    }

    public Timestamp getBuildtime() {
        return buildtime;
    }

    public void setBuildtime(Timestamp buildtime) {
        this.buildtime = buildtime;
    }

    public String getPickstate() {

        return pickstate;
    }

    public void setPickstate(String pickstate) {
        this.pickstate = pickstate;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WorkBill{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", pickstate='" + pickstate + '\'' +
                ", buildtime=" + buildtime +
                ", attachbilltimes=" + attachbilltimes +
                ", remark='" + remark + '\'' +
                '}';
    }
}
