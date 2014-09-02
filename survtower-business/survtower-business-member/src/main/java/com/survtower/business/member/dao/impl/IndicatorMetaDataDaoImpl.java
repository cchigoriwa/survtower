package com.survtower.business.member.dao.impl;

import com.survtower.business.member.dao.IndicatorMetaDataDao;
import com.survtower.business.member.domain.IndicatorMetaData;
import com.survtower.business.member.repository.IndicatorMetaDataRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Charles Chigoriwa
 */
@Repository
public class IndicatorMetaDataDaoImpl implements IndicatorMetaDataDao{
    
    @Autowired
    private IndicatorMetaDataRepository indicatorMetaDataRepository;

    public IndicatorMetaData save(IndicatorMetaData indicatorMetaData) {
       return indicatorMetaDataRepository.save(indicatorMetaData);
    }

    public List<IndicatorMetaData> findAll() {
       return indicatorMetaDataRepository.findAll();
    }

    public IndicatorMetaData find(Long id) {
       return indicatorMetaDataRepository.findOne(id);
    }

    public IndicatorMetaData findByUuid(String uuid) {
       return indicatorMetaDataRepository.findByUuid(uuid);
    }
    
}
