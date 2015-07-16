package com.survtower.business.common.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.Frequency;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface FrequencyService extends GenericService<Frequency>,ILookupService<Frequency>{
    
    public List<Frequency> findFrequencysUpdatedAfter(Date afterDate);

    public Date findMaximumUpdateDate(Date afterDate);

    public List<Frequency> findFrequencysUpdatedAfter(Date afterDate, Date maxDate);
    
    public Date findMaximumUpdateDate();
    
    public List<Frequency> findFrequencysUpdatedBefore(Date maxDate);
}
