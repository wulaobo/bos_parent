package com.itheima.bos.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/*
  取派员
 */
public class Staff implements Serializable {

    private String id;
    private String name;
    private String telephone;
    private String haspda="0";  //是否有PDA 1 有 ,0 没有
    private String deltag="0";  //是否删除  1 删除,0正常使用
    private String station;
    private String standard;

    //一对多
    private Set<DecidedZone> decidedZones = new HashSet<DecidedZone>();

    public Set<DecidedZone> getDecidedZones() {
        return decidedZones;
    }

    public void setDecidedZones(Set<DecidedZone> decidedZones) {
        this.decidedZones = decidedZones;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getStation() {

        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getDeltag() {
        return deltag;
    }

    public void setDeltag(String deltag) {
        this.deltag = deltag;
    }

    public String getHaspda() {

        return haspda;
    }

    public void setHaspda(String haspda) {
        this.haspda = haspda;
    }

    public String getTelephone() {

        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", haspda='" + haspda + '\'' +
                ", deltag='" + deltag + '\'' +
                ", station='" + station + '\'' +
                ", standard='" + standard + '\'' +
                ", decidedZones=" + decidedZones +
                '}';
    }
}
