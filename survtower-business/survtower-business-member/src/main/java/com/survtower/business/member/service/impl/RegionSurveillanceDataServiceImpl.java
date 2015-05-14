package com.survtower.business.member.service.impl;

import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.member.dao.RegionSurveillanceDataDao;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceData;
import com.survtower.business.member.service.RegionSurveillanceDataService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Takunda Dhlakama
 */
@Service("regionSurveillanceDataService")
@Transactional(readOnly = true)
public class RegionSurveillanceDataServiceImpl implements RegionSurveillanceDataService {

    @Autowired
    private RegionSurveillanceDataDao regionSurveillanceDataDao;

    @Transactional
    @Override
    public RegionSurveillanceData save(RegionSurveillanceData region) {
        region.setUpdateDate(new Date());
        return regionSurveillanceDataDao.save(region);
    }

    @Override
    public List<RegionSurveillanceData> findAll() {
        return regionSurveillanceDataDao.findAll();
    }

    @Override
    public RegionSurveillanceData find(Long id) {
        return regionSurveillanceDataDao.find(id);
    }

    @Override
    public RegionSurveillanceData findByUuid(String uuid) {
        return regionSurveillanceDataDao.findByUuid(uuid);
    }

    @Override
    public List<RegionSurveillanceData> findAll(SurveillanceData surveillanceData) {
        return regionSurveillanceDataDao.findAll(surveillanceData);
    }

    @Override
    public List<RegionSurveillanceData> findAll(Program program, Region region) {
        return regionSurveillanceDataDao.findAll(program, region);
    }

    @Override
    public List<RegionSurveillanceData> findAll(Period period, Program program, Region region){
        return regionSurveillanceDataDao.findAll(period, program, region);
    }
    @Override
    public RegionSurveillanceData find(SurveillanceData surveillanceData, Region region) {
        return regionSurveillanceDataDao.find(surveillanceData, region);
    }

    @Override
    public Double getNumeratorCalculatedValue(SurveillanceData surveillanceData) {
        return regionSurveillanceDataDao.getNumeratorCalculatedValue(surveillanceData);
    }

    @Override
    public Double getDenominatedCalculatedValue(SurveillanceData surveillanceData) {
        return regionSurveillanceDataDao.getDenominatedCalculatedValue(surveillanceData);
    }

    @Override
    public List<RegionSurveillanceData> findAll(Surveillance surveillance, Region region) {
        return regionSurveillanceDataDao.findAll(surveillance, region);
    }

    @Override
    public List<RegionSurveillanceData> findAll(Period period, Indicator indicator, Region region) {
        return regionSurveillanceDataDao.findAll(period, indicator, region);
    }
}
