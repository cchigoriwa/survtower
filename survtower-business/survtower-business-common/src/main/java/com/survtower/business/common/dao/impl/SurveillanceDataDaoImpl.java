package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.SurveillanceDataDao;
import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.repository.SurveillanceDataRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Takunda Dhlakama
 */
@Repository
public class SurveillanceDataDaoImpl implements SurveillanceDataDao {

    @Autowired
    private SurveillanceDataRepository surveillanceDataRepository;

    @Override
    public SurveillanceData save(SurveillanceData surveillanceData) {
        return surveillanceDataRepository.save(surveillanceData);
    }

    @Override
    public List<SurveillanceData> findAll() {
        return surveillanceDataRepository.findAll();
    }

    @Override
    public SurveillanceData find(Long id) {
        return surveillanceDataRepository.findOne(id);
    }

    @Override
    public SurveillanceData findByUuid(String uuid) {
        return surveillanceDataRepository.findByUuid(uuid);
    }

    @Override
    public List<SurveillanceData> findSurveillanceDataItems(Surveillance surveillance) {
        return surveillanceDataRepository.findSurveillanceDataItems(surveillance);
    }

    @Override
    public SurveillanceData findSurveillanceData(Program program, Period period, Member member, Indicator indicator) {
        return surveillanceDataRepository.findSurveillanceData(program, period, member, indicator);
    }

    @Override
    public List<SurveillanceData> findSurveillanceDataItems(Program program, Member member, Indicator indicator) {
        return surveillanceDataRepository.findSurveillanceDataItems(program, member, indicator);
    }

    @Override
    public List<SurveillanceData> findSurveillanceDataItems(Program program, Period period, Member member) {
        return surveillanceDataRepository.findSurveillanceDataItems(program, period, member);
    }
    
    @Override
    public List<SurveillanceData> findSurveillanceDataItems(Period period, Member member, Indicator indicator){
    return surveillanceDataRepository.findSurveillanceDataItems(period, member, indicator);
    }
    
    @Override
    public List<SurveillanceData> findSurveillanceDataItems(Period period) {
        return surveillanceDataRepository.findSurveillanceDataItems(period);
    }

    @Override
    public List<SurveillanceData> findSurveillanceDataItems(Program program) {
        return surveillanceDataRepository.findSurveillanceDataItems(program);
    }

    @Override
    public List<SurveillanceData> findSurveillanceDataItems(Indicator indicator) {
        return surveillanceDataRepository.findSurveillanceDataItems(indicator);
    }

    @Override
    public List<SurveillanceData> findSurveillanceDataItems(Member member, Indicator indicator) {
        return surveillanceDataRepository.findSurveillanceDataItems(member, indicator);
    }

}
