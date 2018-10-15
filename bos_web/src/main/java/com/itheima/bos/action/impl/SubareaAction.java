package com.itheima.bos.action.impl;

import Utils.FileUtils;
import Utils.PageBean;
import com.itheima.Customer;
import com.itheima.CustomerService;
import com.itheima.bos.domain.Region;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.ISubareaService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@Scope("prototype")
public class SubareaAction extends ActionSupport implements ModelDriven<Subarea> {

    @Autowired
    private ISubareaService iSubareaService;
    private Subarea subarea = new Subarea();

   public String save() {
      // System.out.println(subarea);
        iSubareaService.save(subarea);
       return "list";
   }

   //属性驱动
    private String decidedzone_id;
    public void setDecidedzone_id(String decidedzone_id) {
        this.decidedzone_id = decidedzone_id;
    }

    //通过定区id查询分区
    public String findListSubareaByDecidedzone_id() throws IOException {

        List<Subarea> list = iSubareaService.findListSubareaByDecidedzone_id(decidedzone_id);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[] {"decidedZone","subareas_Re"});
        String json = JSONArray.fromObject(list,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    //区域分区分布图
    public String findSubareaByGroupProvince() throws IOException {
        List<Object> list = iSubareaService.findSubareaByGroupProvince();
        String json = JSONArray.fromObject(list).toString();
        System.out.println(json);
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

   //分区文件导出
    public String download() throws IOException {
       //第一步:从数据库中查出所有分区数据
        List<Subarea> list = iSubareaService.findAll();
        //第二步:利用POI完成导出功能
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建标签页
        HSSFSheet sheet = workbook.createSheet("分区数据");
        //创建标题行
        HSSFRow headrow = sheet.createRow(0);
        headrow.createCell(0).setCellValue("分区编号");
        headrow.createCell(1).setCellValue("省市区");
        headrow.createCell(2).setCellValue("关键字");
        headrow.createCell(3).setCellValue("起始号");
        headrow.createCell(4).setCellValue("终止号");
        headrow.createCell(5).setCellValue("单双号");
        headrow.createCell(6).setCellValue("位置");

        for (Subarea subarea:list) {
            //遍历数据行
            HSSFRow datarow = sheet.createRow(sheet.getLastRowNum()+1);
            datarow.createCell(0).setCellValue(subarea.getId());
            datarow.createCell(1).setCellValue(subarea.getRegion().getName());
            datarow.createCell(2).setCellValue(subarea.getAddresskey());
            datarow.createCell(3).setCellValue(subarea.getStartnum());
            datarow.createCell(4).setCellValue(subarea.getEndnum());
            datarow.createCell(5).setCellValue(subarea.getSingle());
            datarow.createCell(6).setCellValue(subarea.getPosition());
        }
        //第三步:使用输出流进行文件下载(一个流,两个头)application/vnd.ms-excel
        ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
        HttpServletResponse response = ServletActionContext.getResponse();
        String filename="分区数据.xls";
        //动态获取xls的类型
        String contentType = ServletActionContext.getServletContext().getMimeType(filename);
        //System.out.println(contentType);
        response.setContentType(contentType);
        HttpServletRequest request = ServletActionContext.getRequest();
        String agent = request.getHeader("User-Agent");
       // System.out.println(agent);
        filename = FileUtils.encodeDownloadFilename(filename,agent);
        response.setHeader("Content-disposition","attachment;filename="+filename);
        workbook.write(outputStream);
        return NONE;
    }

   private int page;
   private int rows;
   //分区分页查询
   public String QueryPage() throws IOException {

       PageBean pageBean = new PageBean();
       pageBean.setCurrentPage(page);
       pageBean.setPageSize(rows);
       DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);

       System.out.println(subarea);
       String addresskey = subarea.getAddresskey();
       Region region = subarea.getRegion();

       if(StringUtils.isNotBlank(addresskey)) {
           detachedCriteria.add(Restrictions.like("addresskey","%"+addresskey+"%"));
       }
       if (region!=null){
           String province = region.getProvince();
           String city = region.getCity();
           String district = region.getDistrict();

           detachedCriteria.createAlias("region","r");
           if(StringUtils.isNotBlank(province)) {
               detachedCriteria.add(Restrictions.like("r.province","%"+province+"%"));
           }
           if(StringUtils.isNotBlank(city)) {
               detachedCriteria.add(Restrictions.like("r.city","%"+city+"%"));
           }
           if(StringUtils.isNotBlank(district)) {
               detachedCriteria.add(Restrictions.like("r.district","%"+district+"%"));
           }
       }

       pageBean.setDetachedCriteria(detachedCriteria);

       iSubareaService.QueryPage(pageBean);

       //System.out.println(pageBean);
       JsonConfig jsonConfig = new JsonConfig();
       jsonConfig.setExcludes(new String[]{"currentPage","pageSize","detachedCriteria","subareas_Re","decidedZone"});
       String json = JSONObject.fromObject(pageBean,jsonConfig).toString();
       ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
       ServletActionContext.getResponse().getWriter().print(json);
       return NONE;
   }

   //查询所有未被定区关联的分区
   public String findAllSubareaNotAssociated() throws IOException {
       List<Subarea> list = iSubareaService.findAllSubareaNotAssociated();
       JsonConfig jsonConfig = new JsonConfig();
       jsonConfig.setExcludes(new String[]{"startnum","endnum","single","region","decidedZone"});
       String json = JSONArray.fromObject(list,jsonConfig).toString();
       ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
       ServletActionContext.getResponse().getWriter().print(json);
       return NONE;
   }


    @Override
    public Subarea getModel() {
        return subarea;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {

        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
