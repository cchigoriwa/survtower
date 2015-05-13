package com.survtower.business.member.service.impl;

import com.survtower.business.member.dao.RegionDao;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.service.RegionService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Takunda Dhlakama
 */
@Service("regionService")
@Transactional(readOnly = true)
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDao regionDao;

    @Transactional
    @Override
    public Region save(Region region) {
        region.setUpdateDate(new Date());
        return regionDao.save(region);
    }

    @Override
    public List<Region> findAll() {
        return regionDao.findAll();
    }

    @Override
    public Region find(Long id) {
        return regionDao.find(id);
    }

    @Override
    public Region findByUuid(String uuid) {
        return regionDao.findByUuid(uuid);
    }

}
