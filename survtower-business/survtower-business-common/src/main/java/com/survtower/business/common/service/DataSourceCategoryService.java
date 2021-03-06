package com.survtower.business.common.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.DataSourceCategory;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface DataSourceCategoryService extends GenericService<DataSourceCategory>,ILookupService<DataSourceCategory>{
    
    public List<DataSourceCategory> findDataSourceCategorysUpdatedAfter(Date afterDate);

    public Date findMaximumUpdateDate(Date afterDate);

    public List<DataSourceCategory> findDataSourceCategorysUpdatedAfter(Date afterDate, Date maxDate);
    
    public Date findMaximumUpdateDate();
    
    public List<DataSourceCategory> findDataSourceCategorysUpdatedBefore(Date maxDate);
}
