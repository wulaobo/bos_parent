package com.itheima.bos.service;

import Utils.PageBean;
import com.itheima.bos.domain.Subarea;
import java.util.List;

public interface ISubareaService {
    void save(Subarea subarea);

    List<Subarea> findAll();

    void QueryPage(PageBean pageBean);

    List<Subarea> findAllSubareaNotAssociated();

    List<Subarea> findById(String[] subareaid);

    List<Subarea> findListSubareaByDecidedzone_id(String decidedzone_id);

    List<Object> findSubareaByGroupProvince();
}
