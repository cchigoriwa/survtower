package com.survtower.business.common.dao;

import com.survtower.business.common.GenericDao;
import com.survtower.business.common.domain.DataSource;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface DataSourceDao extends GenericDao<DataSource> {

    public List<DataSource> findDataSourcesUpdatedAfter(Date afterDate);

    public Date findMaximumUpdateDate(Date afterDate);

    public List<DataSource> findDataSourcesUpdatedAfter(Date afterDate, Date maxDate);

    public Date findMaximumUpdateDate();
    
    public List<DataSource> findDataSourcesUpdatedBefore(Date maxDate);

}
