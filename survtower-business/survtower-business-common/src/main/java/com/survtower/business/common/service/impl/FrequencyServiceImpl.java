package com.survtower.business.common.service.impl;

import com.survtower.business.common.dao.FrequencyDao;
import com.survtower.business.common.domain.Frequency;
import com.survtower.business.common.service.FrequencyService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service("frequencyService")
@Transactional(readOnly = true)
public class FrequencyServiceImpl implements FrequencyService {

    @Autowired
    private FrequencyDao frequencyDao;

    @Transactional
    @Override
    public Frequency save(Frequency frequency) {
        frequency.setUpdateDate(new Date());
        return frequencyDao.save(frequency);
    }

    @Override
    public List<Frequency> findAll() {
        return frequencyDao.findAll();
    }

    @Override
    public Frequency find(Long id) {
        return frequencyDao.find(id);
    }

    @Override
    public Frequency findByUuid(String uuid) {
        return frequencyDao.findByUuid(uuid);
    }

    @Override
    public List<Frequency> findFrequencysUpdatedAfter(Date afterDate) {
        return frequencyDao.findFrequencysUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return frequencyDao.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<Frequency> findFrequencysUpdatedAfter(Date afterDate, Date maxDate) {
        return frequencyDao.findFrequencysUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return frequencyDao.findMaximumUpdateDate();
    }

    @Override
    public List<Frequency> findFrequencysUpdatedBefore(Date maxDate) {
        return frequencyDao.findFrequencysUpdatedBefore(maxDate);
    }
}
