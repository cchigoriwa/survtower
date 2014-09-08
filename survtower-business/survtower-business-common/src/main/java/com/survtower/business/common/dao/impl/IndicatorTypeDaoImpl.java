package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.IndicatorTypeDao;
import com.survtower.business.common.domain.IndicatorType;
import com.survtower.business.common.repository.IndicatorTypeRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Takunda Dhlakama
 */
@Repository
public class IndicatorTypeDaoImpl implements IndicatorTypeDao{
    
    @Autowired
    private IndicatorTypeRepository indicatorTypeRepository;

    @Override
    public IndicatorType save(IndicatorType indicatorType) {
       return indicatorTypeRepository.save(indicatorType);
    }

    @Override
    public List<IndicatorType> findAll() {
       return indicatorTypeRepository.findAll();
    }

    @Override
    public IndicatorType find(Long id) {
        return indicatorTypeRepository.findOne(id);
    }

    @Override
    public IndicatorType findByUuid(String uuid) {
       return indicatorTypeRepository.findByUuid(uuid);
    }

    @Override
    public List<IndicatorType> findIndicatorTypesUpdatedAfter(Date afterDate) {
        return indicatorTypeRepository.findIndicatorTypesUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
       return indicatorTypeRepository.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<IndicatorType> findIndicatorTypesUpdatedAfter(Date afterDate, Date maxDate) {
        return indicatorTypeRepository.findIndicatorTypesUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return indicatorTypeRepository.findMaximumUpdateDate();
    }

    @Override
    public List<IndicatorType> findIndicatorTypesUpdatedBefore(Date maxDate) {
      return indicatorTypeRepository.findIndicatorTypesUpdatedBefore(maxDate);
    }
    
}
