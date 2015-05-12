package com.survtower.business.common.service.impl;

import com.survtower.business.common.dao.PeriodDao;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.PeriodService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service("periodService")
@Transactional(readOnly = true)
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    private PeriodDao periodDao;

    @Transactional
    @Override
    public Period save(Period period) {
        period.setUpdateDate(new Date());
        return periodDao.save(period);
    }

    @Override
    public List<Period> findAll() {
        return periodDao.findAll();
    }

    @Override
    public Period find(Long id) {
        return periodDao.find(id);
    }

    @Override
    public Period findByUuid(String uuid) {
        return periodDao.findByUuid(uuid);
    }

    @Override
    public List<Period> findPeriodsUpdatedAfter(Date afterDate) {
        return periodDao.findPeriodsUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return periodDao.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<Period> findPeriodsUpdatedAfter(Date afterDate, Date maxDate) {
        return periodDao.findPeriodsUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return periodDao.findMaximumUpdateDate();
    }

    @Override
    public List<Period> findPeriodsUpdatedBefore(Date maxDate) {
        return periodDao.findPeriodsUpdatedBefore(maxDate);
    }

    @Override
    public List<Period> fetchActive() {
        return periodDao.fetchActive();
    }

    @Override
    public List<Period> fetchActive(Program program) {
        return periodDao.fetchActive(program);
    }

    @Override
    public List<Period> fetchAllAscending() {
        return periodDao.fetchAllAscending();
    }

    @Override
    public List<Period> fetchAllDescending() {
        return periodDao.fetchAllDescending();
    }
}
