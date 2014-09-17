package com.survtower.business.common.service.impl;

import com.survtower.business.common.dao.SurveillanceDataDao;
import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.service.SurveillanceDataService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Takunda Dhlakama
 */
@Service("surveillanceDataService")
@Transactional(readOnly = true)
public class SurveillanceDataServiceImpl implements SurveillanceDataService {

    @Autowired
    private SurveillanceDataDao surveillanceDataDao;

    @Transactional
    @Override
    public SurveillanceData save(SurveillanceData surveillanceData) {
        surveillanceData.setUpdateDate(new Date());
        return surveillanceDataDao.save(surveillanceData);
    }

    @Override
    public List<SurveillanceData> findAll() {
        return surveillanceDataDao.findAll();
    }

    @Override
    public SurveillanceData find(Long id) {
        return surveillanceDataDao.find(id);
    }

    @Override
    public SurveillanceData findByUuid(String uuid) {
        return surveillanceDataDao.findByUuid(uuid);
    }

    @Override
    public List<SurveillanceData> findSurveillanceDataItems(Surveillance surveillance) {
        return surveillanceDataDao.findSurveillanceDataItems(surveillance);
    }

    @Override
    public SurveillanceData findSurveillanceData(Program program, Period period, Member member, Indicator indicator) {
        return surveillanceDataDao.findSurveillanceData(program, period, member, indicator);
    }

    @Override
    public List<SurveillanceData> findSurveillanceDataItems(Period period, Member member, Indicator indicator) {
        return surveillanceDataDao.findSurveillanceDataItems(period, member, indicator);
    }

    @Override
    public List<SurveillanceData> findSurveillanceDataItems(Program program, Member member, Indicator indicator) {
        return surveillanceDataDao.findSurveillanceDataItems(program, member, indicator);
    }

    @Override
    public List<SurveillanceData> findSurveillanceDataItems(Program program, Period period, Member member) {
        return surveillanceDataDao.findSurveillanceDataItems(program, period, member);
    }

    @Override
    public List<SurveillanceData> findSurveillanceDataItems(Indicator indicator) {
        return surveillanceDataDao.findSurveillanceDataItems(indicator);
    }

    @Override
    public List<SurveillanceData> findSurveillanceDataItems(Member member, Indicator indicator) {
        return surveillanceDataDao.findSurveillanceDataItems(member, indicator);
    }

    @Override
    public List<SurveillanceData> findSurveillanceDataItems(Period period) {
        return surveillanceDataDao.findSurveillanceDataItems(period);
    }

    @Override
    public List<SurveillanceData> findSurveillanceDataItems(Program program) {
        return surveillanceDataDao.findSurveillanceDataItems(program);
    }
}
