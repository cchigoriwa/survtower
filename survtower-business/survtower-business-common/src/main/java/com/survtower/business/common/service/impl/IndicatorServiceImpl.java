package com.survtower.business.common.service.impl;

import com.survtower.business.common.dao.IndicatorDao;
import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.service.IndicatorService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service("indicatorService")
@Transactional(readOnly = true)
public class IndicatorServiceImpl implements IndicatorService {

    @Autowired
    private IndicatorDao indicatorDao;

    @Transactional
    @Override
    public Indicator save(Indicator indicator) {
        return indicatorDao.save(indicator);
    }

    @Override
    public List<Indicator> findAll() {
        return indicatorDao.findAll();
    }

    @Override
    public Indicator find(Long id) {
        return indicatorDao.find(id);
    }

    @Override
    public Indicator findByUuid(String uuid) {
        return indicatorDao.findByUuid(uuid);
    }

    @Override
    public List<Indicator> findIndicatorsUpdatedAfter(Date afterDate) {
        return indicatorDao.findIndicatorsUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return indicatorDao.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<Indicator> findIndicatorsUpdatedAfter(Date afterDate, Date maxDate) {
        return indicatorDao.findIndicatorsUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return indicatorDao.findMaximumUpdateDate();
    }

    @Override
    public List<Indicator> findIndicatorsUpdatedBefore(Date maxDate) {
        return indicatorDao.findIndicatorsUpdatedBefore(maxDate);
    }
}
