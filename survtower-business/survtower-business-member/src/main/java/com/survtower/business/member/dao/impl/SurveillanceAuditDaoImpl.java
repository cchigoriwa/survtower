package com.survtower.business.member.dao.impl;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.member.dao.SurveillanceAuditDao;
import com.survtower.business.member.domain.SurveillanceAudit;
import com.survtower.business.member.repository.SurveillanceAuditRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tdhlakama
 */
@Repository
public class SurveillanceAuditDaoImpl implements SurveillanceAuditDao {

    @Autowired
    private SurveillanceAuditRepository surveillanceAuditRepository;

    @Override
    public SurveillanceAudit save(SurveillanceAudit surveillanceAudit) {
        return surveillanceAuditRepository.save(surveillanceAudit);
    }

    @Override
    public List<SurveillanceAudit> findAll() {
        return surveillanceAuditRepository.findAll();
    }

    @Override
    public SurveillanceAudit find(Long id) {
        return surveillanceAuditRepository.findOne(id);
    }

    @Override
    public SurveillanceAudit findByUuid(String uuid) {
        return surveillanceAuditRepository.findByUuid(uuid);
    }

    @Override
    public SurveillanceAudit get(Program program, Period period) {
        return surveillanceAuditRepository.get(program, period);
    }

    @Override
    public List<SurveillanceAudit> getSurviellanceAudits(Program program, Period period) {
        return surveillanceAuditRepository.getSurviellanceAudits(program, period);
    }

    @Override
    public SurveillanceAudit findByProgramAndPeriod(Program program, Period period) {
        return surveillanceAuditRepository.findByProgramAndPeriod(program, period);
    }

    @Override
    public List<SurveillanceAudit> findSurveillanceAuditsUpdatedAfter(Date afterDate) {
       return surveillanceAuditRepository.findSurveillanceAuditsUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return surveillanceAuditRepository.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<SurveillanceAudit> findSurveillanceAuditsUpdatedAfter(Date afterDate, Date maxDate) {
        return surveillanceAuditRepository.findSurveillanceAuditsUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return surveillanceAuditRepository.findMaximumUpdateDate();
    }

    @Override
    public List<SurveillanceAudit> findSurveillanceAuditsUpdatedBefore(Date maxDate) {
       return surveillanceAuditRepository.findSurveillanceAuditsUpdatedBefore(maxDate);
    }

}
