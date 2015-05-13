package com.survtower.business.member.repository;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceAudit;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Takunda Dhlakama
 */
public interface RegionSurveillanceAuditRepository extends GenericRepository<RegionSurveillanceAudit, Long> {

    @Query("select distinct s from RegionSurveillanceAudit s where s.program=:program and s.period=:period and s.region=:region")
    public RegionSurveillanceAudit get(@Param("program") Program program, @Param("period") Period period,@Param("region") Region region);

    @Query("select distinct s from RegionSurveillanceAudit s where s.program=:program and s.period=:period and s.region=:region")
    public List<RegionSurveillanceAudit> getSurviellanceAudits(Program program, Period period, Region region);

    public RegionSurveillanceAudit findByProgramAndPeriodAndRegion(Program program,Period period, Region region);
    
   
}
