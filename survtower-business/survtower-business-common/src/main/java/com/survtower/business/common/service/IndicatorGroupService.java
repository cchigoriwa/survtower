package com.survtower.business.common.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.IndicatorGroup;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Charles Chigoriwa
 */
public interface IndicatorGroupService extends GenericService<IndicatorGroup>,ILookupService<IndicatorGroup>{
    
    public List<IndicatorGroup> findIndicatorGroupsUpdatedAfter(Date afterDate);

    public Date findMaximumUpdateDate(Date afterDate);

    public List<IndicatorGroup> findIndicatorGroupsUpdatedAfter(Date afterDate, Date maxDate);
    
    public Date findMaximumUpdateDate();
    
    public List<IndicatorGroup> findIndicatorGroupsUpdatedBefore(Date maxDate);
}
