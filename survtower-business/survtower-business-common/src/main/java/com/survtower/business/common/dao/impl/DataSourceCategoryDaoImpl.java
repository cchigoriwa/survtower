package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.DataSourceCategoryDao;
import com.survtower.business.common.domain.DataSourceCategory;
import com.survtower.business.common.repository.DataSourceCategoryRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Takunda Dhlakama
 */
@Repository
public class DataSourceCategoryDaoImpl implements DataSourceCategoryDao{
    
    @Autowired
    private DataSourceCategoryRepository dataSourceCategoryRepository;

    @Override
    public DataSourceCategory save(DataSourceCategory dataSourceCategory) {
       return dataSourceCategoryRepository.save(dataSourceCategory);
    }

    @Override
    public List<DataSourceCategory> findAll() {
       return dataSourceCategoryRepository.findAll();
    }

    @Override
    public DataSourceCategory find(Long id) {
        return dataSourceCategoryRepository.findOne(id);
    }

    @Override
    public DataSourceCategory findByUuid(String uuid) {
       return dataSourceCategoryRepository.findByUuid(uuid);
    }

    @Override
    public List<DataSourceCategory> findDataSourceCategorysUpdatedAfter(Date afterDate) {
        return dataSourceCategoryRepository.findDataSourceCategorysUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
       return dataSourceCategoryRepository.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<DataSourceCategory> findDataSourceCategorysUpdatedAfter(Date afterDate, Date maxDate) {
        return dataSourceCategoryRepository.findDataSourceCategorysUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return dataSourceCategoryRepository.findMaximumUpdateDate();
    }

    @Override
    public List<DataSourceCategory> findDataSourceCategorysUpdatedBefore(Date maxDate) {
      return dataSourceCategoryRepository.findDataSourceCategorysUpdatedBefore(maxDate);
    }
    
}
