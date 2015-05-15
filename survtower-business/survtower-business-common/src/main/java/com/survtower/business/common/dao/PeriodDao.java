package com.survtower.business.common.dao;

import com.survtower.business.common.GenericDao;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Charles Chigoriwa
 * @author Takunda Dhlakama
 */
public interface PeriodDao extends GenericDao<Period> {

    public List<Period> findPeriodsUpdatedAfter(Date afterDate);

    public Date findMaximumUpdateDate(Date afterDate);

    public List<Period> findPeriodsUpdatedAfter(Date afterDate, Date maxDate);

    public Date findMaximumUpdateDate();

    public List<Period> findPeriodsUpdatedBefore(Date maxDate);

    public List<Period> fetchActive();
    
    public List<Period> fetchInActive();

    public List<Period> fetchActive(Program program);

    public List<Period> fetchAllAscending();

    public List<Period> fetchAllDescending();

    public List<Period> fetchInActive(Program program);

    public List<Period> fetchAll(Program program);

}
