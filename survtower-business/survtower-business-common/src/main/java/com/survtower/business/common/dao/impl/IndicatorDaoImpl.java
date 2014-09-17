package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.IndicatorDao;
import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.IndicatorGroup;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.repository.IndicatorRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Charles Chigoriwa
 */
@Repository
public class IndicatorDaoImpl implements IndicatorDao {

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Override
    public Indicator save(Indicator indicator) {
        return indicatorRepository.save(indicator);
    }

    @Override
    public List<Indicator> findAll() {
        return indicatorRepository.findAll();
    }

    @Override
    public Indicator find(Long id) {
        return indicatorRepository.findOne(id);
    }

    @Override
    public Indicator findByUuid(String uuid) {
        return indicatorRepository.findByUuid(uuid);
    }

    @Override
    public List<Indicator> findIndicatorsUpdatedAfter(Date afterDate) {
        return indicatorRepository.findIndicatorsUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return indicatorRepository.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<Indicator> findIndicatorsUpdatedAfter(Date afterDate, Date maxDate) {
        return indicatorRepository.findIndicatorsUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return indicatorRepository.findMaximumUpdateDate();
    }

    @Override
    public List<Indicator> findIndicatorsUpdatedBefore(Date maxDate) {
        return indicatorRepository.findIndicatorsUpdatedBefore(maxDate);
    }

    @Override
    public List<Indicator> findIndicatorsInProgram(Program program) {
        return indicatorRepository.findIndicatorsInProgram(program);
    }

    @Override
    public List<Indicator> findIndicatorsIndicatorGroup(IndicatorGroup indicatorGroup) {
        return indicatorRepository.findIndicatorsIndicatorGroup(indicatorGroup);
    }

    @Override
    public List<Indicator> getIndicatorAutoComplete(String searchTerm) {
        return indicatorRepository.getIndicatorAutoComplete(searchTerm);
    }
}
