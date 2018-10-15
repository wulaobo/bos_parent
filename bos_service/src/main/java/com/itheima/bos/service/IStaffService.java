package com.itheima.bos.service;

import Utils.PageBean;
import com.itheima.bos.domain.Staff;

import java.util.List;

public interface IStaffService  {
    void saveStaff(Staff model);

    void QueryPage(PageBean pageBean);

    void deleteBatch(String[] idArray);

    Staff findStaffById(String id);

    void updateStaff(Staff staff);

    List<Staff> findAllNotDeleteStaff();
}
