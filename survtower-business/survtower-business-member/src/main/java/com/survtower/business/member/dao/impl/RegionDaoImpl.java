package com.survtower.business.member.dao.impl;

import com.survtower.business.member.dao.RegionDao;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.repository.RegionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Takunda Dhlakama
 */
@Repository
public class RegionDaoImpl implements RegionDao {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public Region save(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    @Override
    public Region find(Long id) {
        return regionRepository.findOne(id);
    }

    @Override
    public Region findByUuid(String uuid) {
        return regionRepository.findByUuid(uuid);
    }

}
