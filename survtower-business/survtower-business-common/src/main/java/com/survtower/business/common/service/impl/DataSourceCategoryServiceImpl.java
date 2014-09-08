package com.survtower.business.common.service.impl;

import com.survtower.business.common.dao.DataSourceCategoryDao;
import com.survtower.business.common.domain.DataSourceCategory;
import com.survtower.business.common.service.DataSourceCategoryService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Takunda Dhlakama
 */
@Service("dataSourceCategoryService")
@Transactional(readOnly = true)
public class DataSourceCategoryServiceImpl implements DataSourceCategoryService {

    @Autowired
    private DataSourceCategoryDao dataSourceCategoryDao;

    @Transactional
    @Override
    public DataSourceCategory save(DataSourceCategory dataSourceCategory) {
        dataSourceCategory.setUpdateDate(new Date());
        return dataSourceCategoryDao.save(dataSourceCategory);
    }

    @Override
    public List<DataSourceCategory> findAll() {
        return dataSourceCategoryDao.findAll();
    }

    @Override
    public DataSourceCategory find(Long id) {
        return dataSourceCategoryDao.find(id);
    }

    @Override
    public DataSourceCategory findByUuid(String uuid) {
        return dataSourceCategoryDao.findByUuid(uuid);
    }

    @Override
    public List<DataSourceCategory> findDataSourceCategorysUpdatedAfter(Date afterDate) {
        return dataSourceCategoryDao.findDataSourceCategorysUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return dataSourceCategoryDao.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<DataSourceCategory> findDataSourceCategorysUpdatedAfter(Date afterDate, Date maxDate) {
        return dataSourceCategoryDao.findDataSourceCategorysUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return dataSourceCategoryDao.findMaximumUpdateDate();
    }

    @Override
    public List<DataSourceCategory> findDataSourceCategorysUpdatedBefore(Date maxDate) {
        return dataSourceCategoryDao.findDataSourceCategorysUpdatedBefore(maxDate);
    }
}
