package com.survtower.business.common.service.impl;

import com.survtower.business.common.dao.DataSourceDao;
import com.survtower.business.common.domain.DataSource;
import com.survtower.business.common.service.DataSourceService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Takunda Dhlakama
 */
@Service("dataSourceService")
@Transactional(readOnly = true)
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataSourceDao dataSourceDao;

    @Transactional
    @Override
    public DataSource save(DataSource dataSource) {
        dataSource.setUpdateDate(new Date());
        return dataSourceDao.save(dataSource);
    }

    @Override
    public List<DataSource> findAll() {
        return dataSourceDao.findAll();
    }

    @Override
    public DataSource find(Long id) {
        return dataSourceDao.find(id);
    }

    @Override
    public DataSource findByUuid(String uuid) {
        return dataSourceDao.findByUuid(uuid);
    }

    @Override
    public List<DataSource> findDataSourcesUpdatedAfter(Date afterDate) {
        return dataSourceDao.findDataSourcesUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return dataSourceDao.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<DataSource> findDataSourcesUpdatedAfter(Date afterDate, Date maxDate) {
        return dataSourceDao.findDataSourcesUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return dataSourceDao.findMaximumUpdateDate();
    }

    @Override
    public List<DataSource> findDataSourcesUpdatedBefore(Date maxDate) {
        return dataSourceDao.findDataSourcesUpdatedBefore(maxDate);
    }
}
