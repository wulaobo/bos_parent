package com.itheima.bos.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/*
   定区表
 */
public class DecidedZone implements Serializable {

    private String id;
    private String name;

    //一对多
    private Set<Subarea> subareas_De = new HashSet<Subarea>();

    //多对一
    private Staff staff;

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Set<Subarea> getSubareas_De() {
        return subareas_De;
    }

    public void setSubareas_De(Set<Subarea> subareas_De) {
        this.subareas_De = subareas_De;
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
        return "DecidedZone{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
