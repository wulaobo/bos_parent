package com.itheima.bos.action.impl;

import java.util.ArrayList;
import java.util.List;

import Utils.PageBean;
import Utils.PinYin4jUtils;
import com.itheima.bos.action.BaseAction;
import com.itheima.bos.domain.Region;
import com.itheima.bos.service.IRegionService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@Scope("prototype")
public class  RegionAction extends BaseAction<Region>{
    //属性驱动
    private File myFile;
    @Autowired
    private IRegionService iRegionService;

    //区域分页查询
    public String QueryPage() throws IOException {
//        PageBean pageBean = new PageBean();
//        pageBean.setCurrentPage(page);
//        pageBean.setPageSize(rows);
//        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Region.class);
//        pageBean.setDetachedCriteria(detachedCriteria);

        iRegionService.QueryPage(pageBean);
        this.json2(pageBean,new String[]{"currentPage","pageSize","detachedCriteria","subareas_Re","name"});
//        System.out.println(pageBean);
//        JsonConfig jsonConfig = new JsonConfig();
//        jsonConfig.setExcludes(new String[]{"currentPage","pageSize","detachedCriteria"});
//        String json = JSONObject.fromObject(pageBean).toString();
//        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
//        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    private String q;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String findAll() {
        List<Region> list = null;
        if(StringUtils.isNotBlank(q)){
            list = iRegionService.findListByQ(q);
        }else{
            list=iRegionService.findAll();
        }
        this.json2(list,new String[]{"postcode","citycode","subareas_Re"});
        return NONE;
    }

    private List<Region> regionList = new ArrayList<Region>();
    //分区文件上传
    public String upload() throws IOException {
        // System.out.println(myFile);
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(myFile));
        Sheet sheet = workbook.getSheet("Sheet1");
        for (Row row:sheet) {
            //System.out.println(row);
            int i = row.getRowNum();
            if(i==0) {
                continue;
            }
           //System.out.println(i);
            //System.out.println(row.getCell(1).toString());
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).toString();
            String district = row.getCell(3).toString();
            String postcode = row.getCell(4).toString();

            Region region = new Region();
            region.setId(id);
            region.setProvince(province);
            region.setCity(city);
            region.setDistrict(district);
            region.setPostcode(postcode);
            //System.out.println(region);

            province = province.substring(0,province.length()-1);
            city = city.substring(0,city.length()-1);
            district = district.substring(0,district.length()-1);
            //System.out.println(province);

            String info = province+city+district;
            String[] infos = PinYin4jUtils.getHeadByString(info);
            String shortcode = PinYin4jUtils.stringArrayToString(infos);
            //System.out.println(info);
             region.setShortcode(shortcode);
            String citycode = PinYin4jUtils.hanziToPinyin(city,"");
            //System.out.println(citycode);
            region.setCitycode(citycode);

            regionList.add(region);
        }
        iRegionService.upload(regionList);

        return NONE;
    }

    public File getMyFile() {
        return myFile;
    }

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }
//
////    public int getRows() {
////        return rows;
////    }
//
//    public void setRows(int rows) {
//        this.rows = rows;
//    }

//    public int getPage() {
//
//        return page;
//    }

//    public void setPage(int page) {
//        this.page = page;
//    }


}
