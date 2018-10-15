package com.itheima.bos.dao.base;

import Utils.PageBean;
import com.itheima.bos.domain.Subarea;
import org.hibernate.criterion.DetachedCriteria;
import java.util.List;
public interface ISubareaDao {
    void save(Subarea subarea);

    List<Subarea> findAll();

    void QueryPage(PageBean pageBean);

    List<Subarea> findByCriteria(DetachedCriteria detachedCriteria);

    List<Subarea> findById(String[] subareaid);

    List<Subarea> findListSubareaByDecidedzone_id(String decidedzone_id);

    List<Object> findSubareaByGroupProvince();
}
