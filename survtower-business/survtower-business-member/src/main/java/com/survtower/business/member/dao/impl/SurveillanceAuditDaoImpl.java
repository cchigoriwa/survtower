/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.business.member.dao.impl;

import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.member.dao.SurveillanceAuditDao;
import com.survtower.business.member.domain.SurveillanceAudit;
import com.survtower.business.member.repository.SurveillanceAuditRepository;
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

}
