package com.survtower.business.member.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.member.domain.SurveillanceAudit;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tdhlakama
 */
public interface SurveillanceAuditService extends GenericService<SurveillanceAudit> {

    public SurveillanceAudit get(Program program, Period period);

    public List<SurveillanceAudit> getSurviellanceAudits(Program program, Period period);

    public SurveillanceAudit findByProgramAndPeriod(Program program,Period period);
    
    public List<SurveillanceAudit> findSurveillanceAuditsUpdatedAfter(Date afterDate);

    public Date findMaximumUpdateDate(Date afterDate);

    public List<SurveillanceAudit> findSurveillanceAuditsUpdatedAfter(Date afterDate, Date maxDate);

    public Date findMaximumUpdateDate();
    
    public List<SurveillanceAudit> findSurveillanceAuditsUpdatedBefore(Date maxDate);

}
