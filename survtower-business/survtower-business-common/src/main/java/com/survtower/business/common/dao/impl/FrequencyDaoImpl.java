package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.FrequencyDao;
import com.survtower.business.common.domain.Frequency;
import com.survtower.business.common.repository.FrequencyRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Takunda Dhlakama
 */
@Repository
public class FrequencyDaoImpl implements FrequencyDao{
    
    @Autowired
    private FrequencyRepository frequencyRepository;

    @Override
    public Frequency save(Frequency frequency) {
       return frequencyRepository.save(frequency);
    }

    @Override
    public List<Frequency> findAll() {
       return frequencyRepository.findAll();
    }

    @Override
    public Frequency find(Long id) {
        return frequencyRepository.findOne(id);
    }

    @Override
    public Frequency findByUuid(String uuid) {
       return frequencyRepository.findByUuid(uuid);
    }

    @Override
    public List<Frequency> findFrequencysUpdatedAfter(Date afterDate) {
        return frequencyRepository.findFrequencysUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
       return frequencyRepository.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<Frequency> findFrequencysUpdatedAfter(Date afterDate, Date maxDate) {
        return frequencyRepository.findFrequencysUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return frequencyRepository.findMaximumUpdateDate();
    }

    @Override
    public List<Frequency> findFrequencysUpdatedBefore(Date maxDate) {
      return frequencyRepository.findFrequencysUpdatedBefore(maxDate);
    }
    
}
