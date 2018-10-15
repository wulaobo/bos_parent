package com.itheima.bos.domain;

import java.io.Serializable;
import java.util.Date;

//工作单
public class WorkOrderManager implements Serializable {
    private String id;            //工作单号
    private String arrivecity;    //到达地
    private String product;//产品
    private Integer num;//件数
    private Double weight;//重量
    private String floadreqr;//配载要求
    private String prodtimelimit;//产品时限
    private String prodtype;//产品类型
    private String sendername;//寄件人姓名
    private String senderphone;//寄件人电话
    private String senderaddr;//寄件人地址
    private String receivername;//收件人姓名
    private String receiverphone;//收件人电话
    private String receiveraddr;//收件人地址
    private Integer feeitemnum;//计费件数
    private Double actlweit;//实际重量
    private String vol;//体积
    private String managerCheck;//是否审核配送
    private Date updatetime;//更新时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArrivecity() {
        return arrivecity;
    }

    public void setArrivecity(String arrivecity) {
        this.arrivecity = arrivecity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getManagerCheck() {

        return managerCheck;
    }

    public void setManagerCheck(String managerCheck) {
        this.managerCheck = managerCheck;
    }

    public String getVol() {

        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public Double getActlweit() {

        return actlweit;
    }

    public void setActlweit(Double actlweit) {
        this.actlweit = actlweit;
    }

    public Integer getFeeitemnum() {

        return feeitemnum;
    }

    public void setFeeitemnum(Integer feeitemnum) {
        this.feeitemnum = feeitemnum;
    }

    public String getReceiveraddr() {

        return receiveraddr;
    }

    public void setReceiveraddr(String receiveraddr) {
        this.receiveraddr = receiveraddr;
    }

    public String getReceiverphone() {

        return receiverphone;
    }

    public void setReceiverphone(String receiverphone) {
        this.receiverphone = receiverphone;
    }

    public String getReceivername() {

        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername;
    }

    public String getSenderaddr() {

        return senderaddr;
    }

    public void setSenderaddr(String senderaddr) {
        this.senderaddr = senderaddr;
    }

    public String getSenderphone() {

        return senderphone;
    }

    public void setSenderphone(String senderphone) {
        this.senderphone = senderphone;
    }

    public String getSendername() {

        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public String getProdtype() {

        return prodtype;
    }

    public void setProdtype(String prodtype) {
        this.prodtype = prodtype;
    }

    public String getProdtimelimit() {

        return prodtimelimit;
    }

    public void setProdtimelimit(String prodtimelimit) {
        this.prodtimelimit = prodtimelimit;
    }

    public String getFloadreqr() {

        return floadreqr;
    }

    public void setFloadreqr(String floadreqr) {
        this.floadreqr = floadreqr;
    }

    public Double getWeight() {

        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "WorkOrderManager{" +
                "id='" + id + '\'' +
                ", arrivecity='" + arrivecity + '\'' +
                ", product='" + product + '\'' +
                ", num=" + num +
                ", weight=" + weight +
                ", floadreqr='" + floadreqr + '\'' +
                ", prodtimelimit='" + prodtimelimit + '\'' +
                ", prodtype='" + prodtype + '\'' +
                ", sendername='" + sendername + '\'' +
                ", senderphone='" + senderphone + '\'' +
                ", senderaddr='" + senderaddr + '\'' +
                ", receivername='" + receivername + '\'' +
                ", receiverphone='" + receiverphone + '\'' +
                ", receiveraddr='" + receiveraddr + '\'' +
                ", feeitemnum=" + feeitemnum +
                ", actlweit=" + actlweit +
                ", vol='" + vol + '\'' +
                ", managerCheck='" + managerCheck + '\'' +
                ", updatetime=" + updatetime +
                '}';
    }


}
