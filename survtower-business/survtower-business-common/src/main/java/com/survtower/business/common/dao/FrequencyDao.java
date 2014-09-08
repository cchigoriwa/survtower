package com.survtower.business.common.dao;

import com.survtower.business.common.GenericDao;
import com.survtower.business.common.domain.Frequency;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface FrequencyDao extends GenericDao<Frequency> {

    public List<Frequency> findFrequencysUpdatedAfter(Date afterDate);

    public Date findMaximumUpdateDate(Date afterDate);

    public List<Frequency> findFrequencysUpdatedAfter(Date afterDate, Date maxDate);

    public Date findMaximumUpdateDate();
    
    public List<Frequency> findFrequencysUpdatedBefore(Date maxDate);

}
