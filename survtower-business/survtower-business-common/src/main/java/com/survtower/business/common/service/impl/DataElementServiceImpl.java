package com.survtower.business.common.service.impl;

import com.survtower.business.common.dao.DataElementDao;
import com.survtower.business.common.domain.DataElement;
import com.survtower.business.common.service.DataElementService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Takunda Dhlakama
 */
@Service("dataElementService")
@Transactional(readOnly = true)
public class DataElementServiceImpl implements DataElementService {

    @Autowired
    private DataElementDao dataElementDao;

    @Transactional
    @Override
    public DataElement save(DataElement dataElement) {
        dataElement.setUpdateDate(new Date());
        return dataElementDao.save(dataElement);
    }

    @Override
    public List<DataElement> findAll() {
        return dataElementDao.findAll();
    }

    @Override
    public DataElement find(Long id) {
        return dataElementDao.find(id);
    }

    @Override
    public DataElement findByUuid(String uuid) {
        return dataElementDao.findByUuid(uuid);
    }

    @Override
    public List<DataElement> findDataElementsUpdatedAfter(Date afterDate) {
        return dataElementDao.findDataElementsUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return dataElementDao.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<DataElement> findDataElementsUpdatedAfter(Date afterDate, Date maxDate) {
        return dataElementDao.findDataElementsUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return dataElementDao.findMaximumUpdateDate();
    }

    @Override
    public List<DataElement> findDataElementsUpdatedBefore(Date maxDate) {
        return dataElementDao.findDataElementsUpdatedBefore(maxDate);
    }
}
