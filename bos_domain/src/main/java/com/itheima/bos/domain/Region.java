package com.itheima.bos.domain;

import com.google.common.annotations.VisibleForTesting;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/*
  区域表
 */
public class Region implements Serializable {

    private String id;
    private String province;
    private String city;
    private String district;
    private String postcode;
    private String shortcode;
    private String citycode;

    public String getName() {
        return province+" "+city+" "+district;
    }

    @Test
    public void test() {
        Region region = new Region();
        region.setId("11");
        region.setProvince("安徽省");
        region.setCity("阜阳市");
        region.setDistrict("临泉县");
        String json = JSONObject.fromObject(region).toString();
        System.out.println(json);
    }

    //一对多
    private Set<Subarea> subareas_Re = new HashSet<Subarea>();

    public Set<Subarea> getSubareas_Re() {
        return subareas_Re;
    }

    public void setSubareas_Re(Set<Subarea> subareas_Re) {
        this.subareas_Re = subareas_Re;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getShortcode() {

        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public String getPostcode() {

        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getDistrict() {

        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {

        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Region{" +
                "id='" + id + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", postcode='" + postcode + '\'' +
                ", shortcode='" + shortcode + '\'' +
                ", citycode='" + citycode + '\'' +
                '}';
    }
}
