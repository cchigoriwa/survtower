package com.survtower.business.member.dao.impl;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.member.dao.RegionSurveillanceAuditDao;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceAudit;
import com.survtower.business.member.domain.RegionSurveillanceData;
import com.survtower.business.member.repository.RegionSurveillanceAuditRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tdhlakama
 */
@Repository
public class RegionSurveillanceAuditDaoImpl implements RegionSurveillanceAuditDao {

    @PersistenceContext
    EntityManager entityManager;

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
   
    @Override
    public List<RegionSurveillanceAudit> findPendingApproval(Program program, Region region) {
        return entityManager.createQuery("select DISTINCT r from RegionSurveillanceAudit r where r.uploadedOn IS NOT NULL and r.approvedBy IS NULL and r.program=:program and r.region=:region").setParameter("program", program).setParameter("region", region).getResultList();
    }
    
    @Override
    public List<RegionSurveillanceAudit> findApproved(Program program, Region region) {
        return entityManager.createQuery("select DISTINCT r from RegionSurveillanceAudit r where r.uploadedOn IS NOT NULL and r.approvedBy IS NOT NULL and r.program=:program and r.region=:region").setParameter("program", program).setParameter("region", region).getResultList();
    }

}
