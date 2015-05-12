package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.PeriodDao;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.repository.PeriodRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Charles Chigoriwa
 */
@Repository
public class PeriodDaoImpl implements PeriodDao {

    @Autowired
    private PeriodRepository periodRepository;

    @Override
    public Period save(Period period) {
        return periodRepository.save(period);
    }

    @Override
    public List<Period> findAll() {
        return periodRepository.findAll();
    }

    @Override
    public Period find(Long id) {
        return periodRepository.findOne(id);
    }

    @Override
    public Period findByUuid(String uuid) {
        return periodRepository.findByUuid(uuid);
    }

    @Override
    public List<Period> findPeriodsUpdatedAfter(Date afterDate) {
        return periodRepository.findPeriodsUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return periodRepository.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<Period> findPeriodsUpdatedAfter(Date afterDate, Date maxDate) {
        return periodRepository.findPeriodsUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return periodRepository.findMaximumUpdateDate();
    }

    @Override
    public List<Period> findPeriodsUpdatedBefore(Date maxDate) {
        return periodRepository.findPeriodsUpdatedBefore(maxDate);
    }

    @Override
    public List<Period> fetchActive() {
        return periodRepository.fetchActive();
    }

    @Override
    public List<Period> fetchActive(Program program) {
        return periodRepository.fetchActive(program);
    }

    @Override
    public List<Period> fetchAllAscending() {
        return periodRepository.fetchAllAscending();
    }

    @Override
    public List<Period> fetchAllDescending() {
        return periodRepository.fetchAllDescending();
    }

}
