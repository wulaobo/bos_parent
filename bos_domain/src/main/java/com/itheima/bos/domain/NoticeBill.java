package com.itheima.bos.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//业务通知单  qp_noticebill
public class NoticeBill implements Serializable {
    private String id;   //业务单主键
    //private String staff_id;//取派员_编号   外键
    private String customer_id;//客户编号   逻辑外键
    private String customer_name;//客户姓名
    private String delegater;//联系人
    private String telephone;//电话
    private String pickaddress;//取件地址
    private String arrivecity;//到达城市
    private String product;//产品
    private Timestamp pickdate;//预约取件时间
    private Integer num;//件数
    private Double weight;//重量
    private String volume;//体积
    private String remark;//备注
    private String ordertype;//分单类型  自动分单 人工分单
    //private String user_id;//受理人   外键

    //多对一
    private User user;
    private Staff staff;

    //一对多
    Set<Workbill> workbills = new HashSet<Workbill>();

    public static final String OrderType_AUTO = "自动分单";
    public static final String OrderType_MAN = "人工分单";

    public Set<Workbill> getWorkbills() {
        return workbills;
    }

    public void setWorkbills(Set<Workbill> workbills) {
        this.workbills = workbills;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVolume() {

        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Double getWeight() {

        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getNum() {

        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Timestamp getPickdate() {
        return pickdate;
    }

    public void setPickdate(Timestamp pickdate) {
        this.pickdate = pickdate;
    }

    public String getProduct() {

        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getArrivecity() {

        return arrivecity;
    }

    public void setArrivecity(String arrivecity) {
        this.arrivecity = arrivecity;
    }

    public String getPickaddress() {

        return pickaddress;
    }

    public void setPickaddress(String pickaddress) {
        this.pickaddress = pickaddress;
    }

    public String getTelephone() {

        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDelegater() {

        return delegater;
    }

    public void setDelegater(String delegater) {
        this.delegater = delegater;
    }

    public String getCustomer_name() {

        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_id() {

        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NoticeBill{" +
                "id='" + id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", delegater='" + delegater + '\'' +
                ", telephone='" + telephone + '\'' +
                ", pickaddress='" + pickaddress + '\'' +
                ", arrivecity='" + arrivecity + '\'' +
                ", product='" + product + '\'' +
                ", pickdate=" + pickdate +
                ", num=" + num +
                ", weight=" + weight +
                ", volume='" + volume + '\'' +
                ", remark='" + remark + '\'' +
                ", ordertype='" + ordertype + '\'' +
                ", user=" + user +
                ", staff=" + staff +
                ", workbills=" + workbills +
                '}';
    }
}
