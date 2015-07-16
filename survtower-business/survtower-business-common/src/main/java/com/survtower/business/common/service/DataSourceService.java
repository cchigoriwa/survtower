package com.survtower.business.common.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.DataSource;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface DataSourceService extends GenericService<DataSource>,ILookupService<DataSource>{
    
    public List<DataSource> findDataSourcesUpdatedAfter(Date afterDate);

    public Date findMaximumUpdateDate(Date afterDate);

    public List<DataSource> findDataSourcesUpdatedAfter(Date afterDate, Date maxDate);
    
    public Date findMaximumUpdateDate();
    
    public List<DataSource> findDataSourcesUpdatedBefore(Date maxDate);
}
