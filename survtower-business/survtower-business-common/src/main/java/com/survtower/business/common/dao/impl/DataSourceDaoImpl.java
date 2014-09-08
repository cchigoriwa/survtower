package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.DataSourceDao;
import com.survtower.business.common.domain.DataSource;
import com.survtower.business.common.repository.DataSourceRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Takunda Dhlakama
 */
@Repository
public class DataSourceDaoImpl implements DataSourceDao{
    
    @Autowired
    private DataSourceRepository dataSourceRepository;

    @Override
    public DataSource save(DataSource dataSource) {
       return dataSourceRepository.save(dataSource);
    }

    @Override
    public List<DataSource> findAll() {
       return dataSourceRepository.findAll();
    }

    @Override
    public DataSource find(Long id) {
        return dataSourceRepository.findOne(id);
    }

    @Override
    public DataSource findByUuid(String uuid) {
       return dataSourceRepository.findByUuid(uuid);
    }

    @Override
    public List<DataSource> findDataSourcesUpdatedAfter(Date afterDate) {
        return dataSourceRepository.findDataSourcesUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
       return dataSourceRepository.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<DataSource> findDataSourcesUpdatedAfter(Date afterDate, Date maxDate) {
        return dataSourceRepository.findDataSourcesUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return dataSourceRepository.findMaximumUpdateDate();
    }

    @Override
    public List<DataSource> findDataSourcesUpdatedBefore(Date maxDate) {
      return dataSourceRepository.findDataSourcesUpdatedBefore(maxDate);
    }
    
}
