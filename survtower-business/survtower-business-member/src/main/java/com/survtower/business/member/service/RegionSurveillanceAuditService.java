package com.survtower.business.member.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceAudit;
import java.util.List;

/**
 *
 * @author tdhlakama
 */
public interface RegionSurveillanceAuditService extends GenericService<RegionSurveillanceAudit> {

    public RegionSurveillanceAudit get(Program program, Period period, Region region);

    public List<RegionSurveillanceAudit> getSurviellanceAudits(Program program, Period period, Region region);

    public RegionSurveillanceAudit findByProgramAndPeriodAndRegion(Program program, Period period, Region region);

    public List<RegionSurveillanceAudit> findPendingApproval(Program program, Region region);

    public List<RegionSurveillanceAudit> findApproved(Program program, Region region);

}
