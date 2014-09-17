package com.survtower.business.common.dao;

import com.survtower.business.common.GenericDao;
import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.IndicatorGroup;
import com.survtower.business.common.domain.Program;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Charles Chigoriwa
 */
public interface IndicatorDao extends GenericDao<Indicator> {

    public List<Indicator> findIndicatorsUpdatedAfter(Date afterDate);

    public Date findMaximumUpdateDate(Date afterDate);

    public List<Indicator> findIndicatorsUpdatedAfter(Date afterDate, Date maxDate);

    public Date findMaximumUpdateDate();

    public List<Indicator> findIndicatorsUpdatedBefore(Date maxDate);

    public List<Indicator> findIndicatorsInProgram(Program program);

    public List<Indicator> findIndicatorsIndicatorGroup(IndicatorGroup indicatorGroup);
    
    public List<Indicator> getIndicatorAutoComplete(String searchTerm);

}
