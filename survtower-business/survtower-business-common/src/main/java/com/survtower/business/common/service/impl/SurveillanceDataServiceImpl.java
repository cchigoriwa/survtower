package com.survtower.business.common.service.impl;

import com.survtower.business.common.dao.SurveillanceDataDao;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.service.SurveillanceDataService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Takunda Dhlakama
 */
@Service("surveillanceDataService")
@Transactional(readOnly = true)
public class SurveillanceDataServiceImpl implements SurveillanceDataService {

    @Autowired
    private SurveillanceDataDao surveillanceDataDao;

    @Transactional
    @Override
    public SurveillanceData save(SurveillanceData surveillanceData) {
        surveillanceData.setUpdateDate(new Date());
        return surveillanceDataDao.save(surveillanceData);
    }

    @Override
    public List<SurveillanceData> findAll() {
        return surveillanceDataDao.findAll();
    }

    @Override
    public SurveillanceData find(Long id) {
        return surveillanceDataDao.find(id);
    }

    @Override
    public SurveillanceData findByUuid(String uuid) {
        return surveillanceDataDao.findByUuid(uuid);
    }
  
}
