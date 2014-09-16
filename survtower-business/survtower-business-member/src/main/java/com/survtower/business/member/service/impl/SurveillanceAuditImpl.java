package com.survtower.business.member.service.impl;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.member.dao.SurveillanceAuditDao;
import com.survtower.business.member.domain.SurveillanceAudit;
import com.survtower.business.member.service.SurveillanceAuditService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tdhlakama
 */
@Service("surveillanceAuditService")
@Transactional(readOnly = true)
public class SurveillanceAuditImpl implements SurveillanceAuditService {

    @Autowired
    private SurveillanceAuditDao surveillanceAuditDao;

    @Transactional
    @Override
    public synchronized SurveillanceAudit save(SurveillanceAudit surveillanceAudit) {
        surveillanceAudit.setUpdateDate(new Date());
        return surveillanceAuditDao.save(surveillanceAudit);
    }

    @Override
    public List<SurveillanceAudit> findAll() {
        return surveillanceAuditDao.findAll();
    }

    @Override
    public SurveillanceAudit find(Long id) {
        return surveillanceAuditDao.find(id);
    }

    @Override
    public SurveillanceAudit findByUuid(String uuid) {
        return surveillanceAuditDao.findByUuid(uuid);
    }

    @Override
    public SurveillanceAudit get(Program program, Period period) {
        return surveillanceAuditDao.get(program, period);
    }

    @Override
    public List<SurveillanceAudit> getSurviellanceAudits(Program program, Period period) {
        return surveillanceAuditDao.getSurviellanceAudits(program, period);
    }

    @Override
    public SurveillanceAudit findByProgramAndPeriod(Program program, Period period) {
        return surveillanceAuditDao.findByProgramAndPeriod(program, period);
    }
 @Override
    public List<SurveillanceAudit> findSurveillanceAuditsUpdatedAfter(Date afterDate) {
       return surveillanceAuditDao.findSurveillanceAuditsUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return surveillanceAuditDao.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<SurveillanceAudit> findSurveillanceAuditsUpdatedAfter(Date afterDate, Date maxDate) {
        return surveillanceAuditDao.findSurveillanceAuditsUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return surveillanceAuditDao.findMaximumUpdateDate();
    }

    @Override
    public List<SurveillanceAudit> findSurveillanceAuditsUpdatedBefore(Date maxDate) {
       return surveillanceAuditDao.findSurveillanceAuditsUpdatedBefore(maxDate);
    }
}
