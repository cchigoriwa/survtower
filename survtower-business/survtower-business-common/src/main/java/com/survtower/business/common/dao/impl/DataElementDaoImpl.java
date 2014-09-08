package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.DataElementDao;
import com.survtower.business.common.domain.DataElement;
import com.survtower.business.common.repository.DataElementRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Takunda Dhlakama
 */
@Repository
public class DataElementDaoImpl implements DataElementDao{
    
    @Autowired
    private DataElementRepository dataElementRepository;

    @Override
    public DataElement save(DataElement dataElement) {
       return dataElementRepository.save(dataElement);
    }

    @Override
    public List<DataElement> findAll() {
       return dataElementRepository.findAll();
    }

    @Override
    public DataElement find(Long id) {
        return dataElementRepository.findOne(id);
    }

    @Override
    public DataElement findByUuid(String uuid) {
       return dataElementRepository.findByUuid(uuid);
    }

    @Override
    public List<DataElement> findDataElementsUpdatedAfter(Date afterDate) {
        return dataElementRepository.findDataElementsUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
       return dataElementRepository.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<DataElement> findDataElementsUpdatedAfter(Date afterDate, Date maxDate) {
        return dataElementRepository.findDataElementsUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return dataElementRepository.findMaximumUpdateDate();
    }

    @Override
    public List<DataElement> findDataElementsUpdatedBefore(Date maxDate) {
      return dataElementRepository.findDataElementsUpdatedBefore(maxDate);
    }
    
}
