package com.itheima.bos.service.impl;

import Utils.PageBean;
import com.itheima.bos.dao.base.IStaffDao;
import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IStaffService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IStaffServiceImpl implements IStaffService{

    @Autowired
    private IStaffDao iStaffDao;

    @Override
    public void saveStaff(Staff model) {
        iStaffDao.save(model);
    }

    @Override
    //分页查询
    public void QueryPage(PageBean pageBean) {
        iStaffDao.QueryPage(pageBean);
    }

    @Override
    //批量删除
    public void deleteBatch(String[] idArray) {

        for(String id:idArray){
            iStaffDao.excuteUpdate("staff.deleteBatch",id);
        }

    }

    @Override
    public Staff findStaffById(String id) {
       return iStaffDao.findById(id);
    }

    @Override
    public void updateStaff(Staff staff) {
        iStaffDao.update(staff);
    }

    @Override
    public List<Staff> findAllNotDeleteStaff() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        detachedCriteria.add(Restrictions.eq("deltag","0"));
       //detachedCriteria.add(Restrictions.ne("deltag","100"));
        List<Staff> list = iStaffDao.findByCriteria(detachedCriteria);
        return list;
    }
}
