package com.itheima.bos.dao.base.impl;

import com.itheima.bos.dao.base.IRegionDao;
import com.itheima.bos.domain.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {
    @Override
    public void saveOrUpdate(Region region) {
        this.getHibernateTemplate().saveOrUpdate(region);
    }

    @Override
    //模糊查询
    public List<Region> findListByQ(String q) {
        String hql = "from Region r where r.province like ? or r.city like ? or r.district like ? or r.shortcode like ? or r.citycode like ?";
       List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql,"%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
        return list;
    }
}
