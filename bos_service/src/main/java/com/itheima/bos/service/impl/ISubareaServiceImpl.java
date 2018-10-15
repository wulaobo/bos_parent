package com.itheima.bos.service.impl;

import Utils.PageBean;
import com.itheima.bos.dao.base.IDecidedZoneDao;
import com.itheima.bos.dao.base.ISubareaDao;
import com.itheima.bos.domain.DecidedZone;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.ISubareaService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ISubareaServiceImpl implements ISubareaService {
    @Autowired
    private ISubareaDao iSubareaDao;

    @Override
    public void save(Subarea subarea) {
        iSubareaDao.save(subarea);
    }

    @Override
    public List<Subarea> findAll() {
        return iSubareaDao.findAll();
    }

    @Override
    public void QueryPage(PageBean pageBean) {
        iSubareaDao.QueryPage(pageBean);
    }

    @Override
    public List<Subarea> findAllSubareaNotAssociated() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
        detachedCriteria.add(Restrictions.isNull("decidedZone"));
        return iSubareaDao.findByCriteria(detachedCriteria);
    }

    @Override
    public List<Subarea> findById(String[] subareaid) {
         List<Subarea> list = iSubareaDao.findById(subareaid);
         return list;
    }

    @Override
    //select * from bc_subarea s where s.decidedzone_id = 'sdf';
    //通过定区id查询分区
    public List<Subarea> findListSubareaByDecidedzone_id(String decidedzone_id) {
        List<Subarea> list = iSubareaDao.findListSubareaByDecidedzone_id(decidedzone_id);
        return list;
    }

    @Override
    public List<Object> findSubareaByGroupProvince() {
        return iSubareaDao.findSubareaByGroupProvince();
    }

//    @Autowired
//    private IDecidedZoneDao decidedZoneDao;
//    @Override
//    public List<Subarea> findListSubareaByDecidedzone_id(String decidedzone_id) {
//        DecidedZone decidedZone = decidedZoneDao.findDecidedZoneById(decidedzone_id);
//        Set<Subarea> set = decidedZone.getSubareas_De();
//        //set集合转为list集合
//        List<Subarea> list = new ArrayList<Subarea>(set);
//        return list;
//    }


}
