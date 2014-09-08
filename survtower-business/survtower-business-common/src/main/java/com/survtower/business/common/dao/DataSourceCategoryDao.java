package com.survtower.business.common.dao;

import com.survtower.business.common.GenericDao;
import com.survtower.business.common.domain.DataSourceCategory;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface DataSourceCategoryDao extends GenericDao<DataSourceCategory> {

    public List<DataSourceCategory> findDataSourceCategorysUpdatedAfter(Date afterDate);

    public Date findMaximumUpdateDate(Date afterDate);

    public List<DataSourceCategory> findDataSourceCategorysUpdatedAfter(Date afterDate, Date maxDate);

    public Date findMaximumUpdateDate();
    
    public List<DataSourceCategory> findDataSourceCategorysUpdatedBefore(Date maxDate);

}
