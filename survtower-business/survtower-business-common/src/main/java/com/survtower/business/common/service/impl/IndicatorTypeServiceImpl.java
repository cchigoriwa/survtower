package com.survtower.business.common.service.impl;

import com.survtower.business.common.dao.IndicatorTypeDao;
import com.survtower.business.common.domain.IndicatorType;
import com.survtower.business.common.service.IndicatorTypeService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Takunda Dhlakama
 */
@Service("indicatorTypeService")
@Transactional(readOnly = true)
public class IndicatorTypeServiceImpl implements IndicatorTypeService {

    @Autowired
    private IndicatorTypeDao indicatorTypeDao;

    @Transactional
    @Override
    public IndicatorType save(IndicatorType indicatorType) {
        indicatorType.setUpdateDate(new Date());
        return indicatorTypeDao.save(indicatorType);
    }

    @Override
    public List<IndicatorType> findAll() {
        return indicatorTypeDao.findAll();
    }

    @Override
    public IndicatorType find(Long id) {
        return indicatorTypeDao.find(id);
    }

    @Override
    public IndicatorType findByUuid(String uuid) {
        return indicatorTypeDao.findByUuid(uuid);
    }

    @Override
    public List<IndicatorType> findIndicatorTypesUpdatedAfter(Date afterDate) {
        return indicatorTypeDao.findIndicatorTypesUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return indicatorTypeDao.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<IndicatorType> findIndicatorTypesUpdatedAfter(Date afterDate, Date maxDate) {
        return indicatorTypeDao.findIndicatorTypesUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return indicatorTypeDao.findMaximumUpdateDate();
    }

    @Override
    public List<IndicatorType> findIndicatorTypesUpdatedBefore(Date maxDate) {
        return indicatorTypeDao.findIndicatorTypesUpdatedBefore(maxDate);
    }
}
