package com.survtower.business.member.service.impl;

import com.survtower.business.common.SurvtowerRuntimeException;
import com.survtower.business.member.dao.IndicatorMetaDataDao;
import com.survtower.business.member.domain.IndicatorMetaData;
import com.survtower.business.member.service.IndicatorMetaDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service
@Transactional(readOnly = true)
public class IndicatorMetaDataServiceImpl implements IndicatorMetaDataService {

    @Autowired
    private IndicatorMetaDataDao indicatorMetaDataDao;

    @Transactional
    public synchronized IndicatorMetaData save(IndicatorMetaData indicatorMetaData) {
        List<IndicatorMetaData> list = findAll();

        if (list.size() > 1) {
            throw new SurvtowerRuntimeException("Two or more IndicatorMetaDatas");
        }

        if (!list.isEmpty()) {
            IndicatorMetaData existing = list.get(0);
            if (!(existing.getId().equals(indicatorMetaData.getId()) && existing.getUuid().equals(indicatorMetaData.getUuid()))) {
                throw new SurvtowerRuntimeException("Existing MetaData and the record to be save are different");
            }
        }

        return indicatorMetaDataDao.save(indicatorMetaData);
    }

    public List<IndicatorMetaData> findAll() {
        return indicatorMetaDataDao.findAll();
    }

    public IndicatorMetaData find(Long id) {
        return indicatorMetaDataDao.find(id);
    }

    public IndicatorMetaData findByUuid(String uuid) {
        return indicatorMetaDataDao.findByUuid(uuid);
    }

    public IndicatorMetaData find() {
        List<IndicatorMetaData> list = findAll();

        if (list.size() > 1) {
            throw new SurvtowerRuntimeException("Two or more IndicatorMetaDatas");
        }

        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
