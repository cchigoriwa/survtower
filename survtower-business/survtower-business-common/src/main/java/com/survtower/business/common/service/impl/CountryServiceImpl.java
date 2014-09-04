package com.survtower.business.common.service.impl;

import com.survtower.business.common.dao.CountryDao;
import com.survtower.business.common.domain.Country;
import com.survtower.business.common.service.CountryService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service("countryService")
@Transactional(readOnly = true)
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;

    @Transactional
    @Override
    public Country save(Country country) {
        country.setUpdateDate(new Date());
        return countryDao.save(country);
    }

    @Override
    public List<Country> findAll() {
        return countryDao.findAll();
    }

    @Override
    public Country find(Long id) {
        return countryDao.find(id);
    }

    @Override
    public Country findByUuid(String uuid) {
        return countryDao.findByUuid(uuid);
    }

    @Override
    public List<Country> findCountrysUpdatedAfter(Date afterDate) {
        return countryDao.findCountrysUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return countryDao.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<Country> findCountrysUpdatedAfter(Date afterDate, Date maxDate) {
        return countryDao.findCountrysUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return countryDao.findMaximumUpdateDate();
    }

    @Override
    public List<Country> findCountrysUpdatedBefore(Date maxDate) {
        return countryDao.findCountrysUpdatedBefore(maxDate);
    }
}
