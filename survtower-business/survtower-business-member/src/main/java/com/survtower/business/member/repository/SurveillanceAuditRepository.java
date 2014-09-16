package com.survtower.business.member.repository;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.member.domain.SurveillanceAudit;
import java.util.Date;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Takunda Dhlakama
 */
public interface SurveillanceAuditRepository extends GenericRepository<SurveillanceAudit, Long> {

    @Query("select distinct s from SurveillanceAudit s where s.program=:program and s.period=:period")
    public SurveillanceAudit get(@Param("program") Program program, @Param("period") Period period);

    @Query("select distinct s from SurveillanceAudit s where s.program=:program and s.period=:period")
    public List<SurveillanceAudit> getSurviellanceAudits(Program program, Period period);

    public SurveillanceAudit findByProgramAndPeriod(Program program,Period period);
    
    @Query("select i from SurveillanceAudit i where i.updateDate>:afterDate")
    public List<SurveillanceAudit> findSurveillanceAuditsUpdatedAfter(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from SurveillanceAudit i where i.updateDate>:afterDate")
    public Date findMaximumUpdateDate(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from SurveillanceAudit i")
    public Date findMaximumUpdateDate();
    
    @Query("select i from SurveillanceAudit i where i.updateDate>:afterDate and i.updateDate<=:maxDate")
    public List<SurveillanceAudit> findSurveillanceAuditsUpdatedAfter(@Param("afterDate") Date afterDate,@Param("maxDate") Date maxDate);
    
    @Query("select i from SurveillanceAudit i where i.updateDate<=:maxDate")
    public List<SurveillanceAudit> findSurveillanceAuditsUpdatedBefore(@Param("maxDate") Date maxDate);

}
