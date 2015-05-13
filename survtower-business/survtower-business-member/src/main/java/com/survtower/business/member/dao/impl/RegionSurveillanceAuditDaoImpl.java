package com.survtower.business.member.dao.impl;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.member.dao.RegionSurveillanceAuditDao;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceAudit;
import com.survtower.business.member.repository.RegionSurveillanceAuditRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tdhlakama
 */
@Repository
public class RegionSurveillanceAuditDaoImpl implements RegionSurveillanceAuditDao {

    @Autowired
    private RegionSurveillanceAuditRepository surveillanceAuditRepository;

    @Override
    public RegionSurveillanceAudit save(RegionSurveillanceAudit surveillanceAudit) {
        return surveillanceAuditRepository.save(surveillanceAudit);
    }

    @Override
    public List<RegionSurveillanceAudit> findAll() {
        return surveillanceAuditRepository.findAll();
    }

    @Override
    public RegionSurveillanceAudit find(Long id) {
        return surveillanceAuditRepository.findOne(id);
    }

    @Override
    public RegionSurveillanceAudit findByUuid(String uuid) {
        return surveillanceAuditRepository.findByUuid(uuid);
    }

    @Override
    public RegionSurveillanceAudit get(Program program, Period period, Region region) {
        return surveillanceAuditRepository.get(program, period, region);
    }

    @Override
    public List<RegionSurveillanceAudit> getSurviellanceAudits(Program program, Period period, Region region) {
        return surveillanceAuditRepository.getSurviellanceAudits(program, period, region);
    }

    @Override
    public RegionSurveillanceAudit findByProgramAndPeriodAndRegion(Program program, Period period, Region region) {
        return surveillanceAuditRepository.findByProgramAndPeriodAndRegion(program, period, region);
    }

}
