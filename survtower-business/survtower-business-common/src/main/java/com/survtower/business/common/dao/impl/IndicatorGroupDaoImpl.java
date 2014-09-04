package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.IndicatorGroupDao;
import com.survtower.business.common.domain.IndicatorGroup;
import com.survtower.business.common.repository.IndicatorGroupRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Charles Chigoriwa
 */
@Repository
public class IndicatorGroupDaoImpl implements IndicatorGroupDao{
    
    @Autowired
    private IndicatorGroupRepository indicatorGroupRepository;

    @Override
    public IndicatorGroup save(IndicatorGroup indicatorGroup) {
       return indicatorGroupRepository.save(indicatorGroup);
    }

    @Override
    public List<IndicatorGroup> findAll() {
       return indicatorGroupRepository.findAll();
    }

    @Override
    public IndicatorGroup find(Long id) {
        return indicatorGroupRepository.findOne(id);
    }

    @Override
    public IndicatorGroup findByUuid(String uuid) {
       return indicatorGroupRepository.findByUuid(uuid);
    }

    @Override
    public List<IndicatorGroup> findIndicatorGroupsUpdatedAfter(Date afterDate) {
        return indicatorGroupRepository.findIndicatorGroupsUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
       return indicatorGroupRepository.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<IndicatorGroup> findIndicatorGroupsUpdatedAfter(Date afterDate, Date maxDate) {
        return indicatorGroupRepository.findIndicatorGroupsUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return indicatorGroupRepository.findMaximumUpdateDate();
    }

    @Override
    public List<IndicatorGroup> findIndicatorGroupsUpdatedBefore(Date maxDate) {
      return indicatorGroupRepository.findIndicatorGroupsUpdatedBefore(maxDate);
    }
    
}
