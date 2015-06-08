package com.survtower.business.common.repository;

import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Takunda Dhlakama
 * @author Charles Chigoriwa
 */
public interface SurveillanceRepository extends GenericRepository<Surveillance, Long> {

    @Query("select distinct s from Surveillance s where s.program=:program and s.period=:period and s.member=:member")
    public Surveillance get(@Param("program") Program program, @Param("period") Period period, @Param("member") Member member);

    @Query("select distinct s from Surveillance s where s.program=:program and s.period=:period")
    public List<Surveillance> getSurviellances(@Param("program") Program program, @Param("period") Period period);
    
    //To be used on the member side otherwise a non single result exception will be thrown
    public Surveillance findByProgramAndPeriod(Program program,Period period);
    
    //Can be used by both but is suited on the central side
    public Surveillance findByProgramAndPeriodAndMember(Program program,Period period,Member member);
    
    public List<Surveillance> findByMember(Member member);

}
