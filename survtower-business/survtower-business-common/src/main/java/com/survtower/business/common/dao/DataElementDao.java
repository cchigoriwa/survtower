package com.survtower.business.common.dao;

import com.survtower.business.common.GenericDao;
import com.survtower.business.common.domain.DataElement;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface DataElementDao extends GenericDao<DataElement> {

    public List<DataElement> findDataElementsUpdatedAfter(Date afterDate);

    public Date findMaximumUpdateDate(Date afterDate);

    public List<DataElement> findDataElementsUpdatedAfter(Date afterDate, Date maxDate);

    public Date findMaximumUpdateDate();
    
    public List<DataElement> findDataElementsUpdatedBefore(Date maxDate);
    
    public List<DataElement> getDataElementAutoComplete(String searchTerm);

}
