package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.CountryDao;
import com.survtower.business.common.domain.Country;
import com.survtower.business.common.repository.CountryRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Charles Chigoriwa
 */
@Repository
public class CountryDaoImpl implements CountryDao{
    
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country save(Country country) {
       return countryRepository.save(country);
    }

    @Override
    public List<Country> findAll() {
       return countryRepository.findAll();
    }

    @Override
    public Country find(Long id) {
        return countryRepository.findOne(id);
    }

    @Override
    public Country findByUuid(String uuid) {
       return countryRepository.findByUuid(uuid);
    }

    @Override
    public List<Country> findCountrysUpdatedAfter(Date afterDate) {
        return countryRepository.findCountrysUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
       return countryRepository.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<Country> findCountrysUpdatedAfter(Date afterDate, Date maxDate) {
        return countryRepository.findCountrysUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return countryRepository.findMaximumUpdateDate();
    }

    @Override
    public List<Country> findCountrysUpdatedBefore(Date maxDate) {
      return countryRepository.findCountrysUpdatedBefore(maxDate);
    }
    
}
