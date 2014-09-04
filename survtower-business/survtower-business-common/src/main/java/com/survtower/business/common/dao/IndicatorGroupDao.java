package com.survtower.business.common.dao;

import com.survtower.business.common.GenericDao;
import com.survtower.business.common.domain.IndicatorGroup;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Charles Chigoriwa
 */
public interface IndicatorGroupDao extends GenericDao<IndicatorGroup> {

    public List<IndicatorGroup> findIndicatorGroupsUpdatedAfter(Date afterDate);

    public Date findMaximumUpdateDate(Date afterDate);

    public List<IndicatorGroup> findIndicatorGroupsUpdatedAfter(Date afterDate, Date maxDate);

    public Date findMaximumUpdateDate();
    
    public List<IndicatorGroup> findIndicatorGroupsUpdatedBefore(Date maxDate);

}
