package Utils;

import org.hibernate.criterion.DetachedCriteria;
import java.util.List;

public class PageBean {
    private int currentPage;   //当前页
    private int pageSize;    //每页显示条数
    private int total;      //总记录数
    private DetachedCriteria detachedCriteria;   //离线查询
    private List rows;   //每页中显示从数据库中查询的数据集合

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public DetachedCriteria getDetachedCriteria() {

        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }

    public int getTotal() {

        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {

        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {

        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", detachedCriteria=" + detachedCriteria +
                ", rows=" + rows +
                '}';
    }
}
