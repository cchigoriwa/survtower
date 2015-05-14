package com.survtower.business.member.dao;

import com.survtower.business.common.GenericDao;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceAudit;
import java.util.List;

/**
 *
 * @author tdhlakama
 */
public interface RegionSurveillanceAuditDao extends GenericDao<RegionSurveillanceAudit> {

    public RegionSurveillanceAudit get(Program program, Period period, Region region);

    public List<RegionSurveillanceAudit> getSurviellanceAudits(Program program, Period period, Region region);

    public RegionSurveillanceAudit findByProgramAndPeriodAndRegion(Program program, Period period, Region region);

    public List<RegionSurveillanceAudit> findPendingApproval(Program program, Region region);

    public List<RegionSurveillanceAudit> findApproved(Program program, Region region);
}
