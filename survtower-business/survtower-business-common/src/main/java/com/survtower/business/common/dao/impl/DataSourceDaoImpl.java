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
    private DataSourceRepository indicatorRepository;

    @Override
    public DataSource save(DataSource indicator) {
       return indicatorRepository.save(indicator);
    }

    @Override
    public List<DataSource> findAll() {
       return indicatorRepository.findAll();
    }

    @Override
    public DataSource find(Long id) {
        return indicatorRepository.findOne(id);
    }

    @Override
    public DataSource findByUuid(String uuid) {
       return indicatorRepository.findByUuid(uuid);
    }

    @Override
    public List<DataSource> findDataSourcesUpdatedAfter(Date afterDate) {
        return indicatorRepository.findDataSourcesUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
       return indicatorRepository.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<DataSource> findDataSourcesUpdatedAfter(Date afterDate, Date maxDate) {
        return indicatorRepository.findDataSourcesUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return indicatorRepository.findMaximumUpdateDate();
    }

    @Override
    public List<DataSource> findDataSourcesUpdatedBefore(Date maxDate) {
      return indicatorRepository.findDataSourcesUpdatedBefore(maxDate);
    }
    
}
