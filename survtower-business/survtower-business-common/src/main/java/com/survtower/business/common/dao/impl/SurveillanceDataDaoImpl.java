package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.SurveillanceDataDao;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.repository.SurveillanceDataRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Takunda Dhlakama
 */
@Repository
public class SurveillanceDataDaoImpl implements SurveillanceDataDao{
    
    @Autowired
    private SurveillanceDataRepository surveillanceDataRepository;

    @Override
    public SurveillanceData save(SurveillanceData surveillanceData) {
       return surveillanceDataRepository.save(surveillanceData);
    }

    @Override
    public List<SurveillanceData> findAll() {
       return surveillanceDataRepository.findAll();
    }

    @Override
    public SurveillanceData find(Long id) {
        return surveillanceDataRepository.findOne(id);
    }

    @Override
    public SurveillanceData findByUuid(String uuid) {
       return surveillanceDataRepository.findByUuid(uuid);
    }

}
