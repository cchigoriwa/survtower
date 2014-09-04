package com.survtower.business.common.service.impl;

import com.survtower.business.common.dao.IndicatorGroupDao;
import com.survtower.business.common.domain.IndicatorGroup;
import com.survtower.business.common.service.IndicatorGroupService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service("indicatorGroupService")
@Transactional(readOnly = true)
public class IndicatorGroupServiceImpl implements IndicatorGroupService {

    @Autowired
    private IndicatorGroupDao indicatorGroupDao;

    @Transactional
    @Override
    public IndicatorGroup save(IndicatorGroup indicatorGroup) {
        indicatorGroup.setUpdateDate(new Date());
        return indicatorGroupDao.save(indicatorGroup);
    }

    @Override
    public List<IndicatorGroup> findAll() {
        return indicatorGroupDao.findAll();
    }

    @Override
    public IndicatorGroup find(Long id) {
        return indicatorGroupDao.find(id);
    }

    @Override
    public IndicatorGroup findByUuid(String uuid) {
        return indicatorGroupDao.findByUuid(uuid);
    }

    @Override
    public List<IndicatorGroup> findIndicatorGroupsUpdatedAfter(Date afterDate) {
        return indicatorGroupDao.findIndicatorGroupsUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return indicatorGroupDao.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<IndicatorGroup> findIndicatorGroupsUpdatedAfter(Date afterDate, Date maxDate) {
        return indicatorGroupDao.findIndicatorGroupsUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return indicatorGroupDao.findMaximumUpdateDate();
    }

    @Override
    public List<IndicatorGroup> findIndicatorGroupsUpdatedBefore(Date maxDate) {
        return indicatorGroupDao.findIndicatorGroupsUpdatedBefore(maxDate);
    }
}
