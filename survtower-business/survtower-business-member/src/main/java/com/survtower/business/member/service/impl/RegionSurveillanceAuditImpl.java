package com.survtower.business.member.service.impl;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.member.dao.RegionSurveillanceAuditDao;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceAudit;
import com.survtower.business.member.service.RegionSurveillanceAuditService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tdhlakama
 */
@Service("regionSurveillanceAuditService")
@Transactional(readOnly = true)
public class RegionSurveillanceAuditImpl implements RegionSurveillanceAuditService {

    @Autowired
    private RegionSurveillanceAuditDao surveillanceAuditDao;

    @Transactional
    @Override
    public synchronized RegionSurveillanceAudit save(RegionSurveillanceAudit surveillanceAudit) {
        surveillanceAudit.setUpdateDate(new Date());
        return surveillanceAuditDao.save(surveillanceAudit);
    }

    @Override
    public List<RegionSurveillanceAudit> findAll() {
        return surveillanceAuditDao.findAll();
    }

    @Override
    public RegionSurveillanceAudit find(Long id) {
        return surveillanceAuditDao.find(id);
    }

    @Override
    public RegionSurveillanceAudit findByUuid(String uuid) {
        return surveillanceAuditDao.findByUuid(uuid);
    }

    @Override
    public RegionSurveillanceAudit get(Program program, Period period, Region region) {
        return surveillanceAuditDao.get(program, period, region);
    }

    @Override
    public List<RegionSurveillanceAudit> getSurviellanceAudits(Program program, Period period, Region region) {
        return surveillanceAuditDao.getSurviellanceAudits(program, period, region);
    }

    @Override
    public RegionSurveillanceAudit findByProgramAndPeriodAndRegion(Program program, Period period, Region region) {
        return surveillanceAuditDao.findByProgramAndPeriodAndRegion(program, period, region);
    }

    @Override
    public List<RegionSurveillanceAudit> findPendingApproval(Program program, Region region) {
        return surveillanceAuditDao.findPendingApproval(program, region);
    }

    @Override
    public List<RegionSurveillanceAudit> findApproved(Program program, Region region) {
        return surveillanceAuditDao.findApproved(program, region);
    }

}
