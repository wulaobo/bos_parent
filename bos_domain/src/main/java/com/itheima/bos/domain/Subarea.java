package com.itheima.bos.domain;

import java.io.Serializable;

public class Subarea implements Serializable {
    private String id;
    private String addresskey;
    private String startnum;
    private String endnum;
    private String single;
    private String position;

    //集合 多对一
    private Region region;
    //集合 多对一
    private DecidedZone decidedZone;

    public String getSubareaid() {
        return id;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public DecidedZone getDecidedZone() {
        return decidedZone;
    }

    public void setDecidedZone(DecidedZone decidedZone) {
        this.decidedZone = decidedZone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String getEndnum() {

        return endnum;
    }

    public void setEndnum(String endnum) {
        this.endnum = endnum;
    }

    public String getStartnum() {

        return startnum;
    }

    public void setStartnum(String startnum) {
        this.startnum = startnum;
    }

    public String getAddresskey() {

        return addresskey;
    }

    public void setAddresskey(String addresskey) {
        this.addresskey = addresskey;
    }

    @Override
    public String toString() {
        return "Subarea{" +
                "id='" + id + '\'' +
                ", addresskey='" + addresskey + '\'' +
                ", startnum='" + startnum + '\'' +
                ", endnum='" + endnum + '\'' +
                ", single='" + single + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
