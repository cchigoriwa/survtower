package com.survtower.business.common.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.DataElement;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface DataElementService extends GenericService<DataElement>,ILookupService<DataElement>{
    
    public List<DataElement> findDataElementsUpdatedAfter(Date afterDate);

    public Date findMaximumUpdateDate(Date afterDate);

    public List<DataElement> findDataElementsUpdatedAfter(Date afterDate, Date maxDate);
    
    public Date findMaximumUpdateDate();
    
    public List<DataElement> findDataElementsUpdatedBefore(Date maxDate);
         
    public List<DataElement> getDataElementAutoComplete(String searchTerm);
        
}
