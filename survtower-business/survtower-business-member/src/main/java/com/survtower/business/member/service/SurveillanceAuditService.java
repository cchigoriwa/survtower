/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.business.member.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.member.domain.SurveillanceAudit;
import java.util.List;

/**
 *
 * @author tdhlakama
 */
public interface SurveillanceAuditService extends GenericService<SurveillanceAudit> {

    public SurveillanceAudit get(Program program, Period period);

    public List<SurveillanceAudit> getSurviellanceAudits(Program program, Period period);
}
