package com.survtower.business.common.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.IndicatorType;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface IndicatorTypeService extends GenericService<IndicatorType>,ILookupService<IndicatorType>{
    
    public List<IndicatorType> findIndicatorTypesUpdatedAfter(Date afterDate);

    public Date findMaximumUpdateDate(Date afterDate);

    public List<IndicatorType> findIndicatorTypesUpdatedAfter(Date afterDate, Date maxDate);
    
    public Date findMaximumUpdateDate();
    
    public List<IndicatorType> findIndicatorTypesUpdatedBefore(Date maxDate);
}
