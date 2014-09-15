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
 */
public interface SurveillanceRepository extends GenericRepository<Surveillance, Long> {

    @Query("select distinct s from Surveillance s where s.program=:program and s.period=:period and s.member=:member")
    public Surveillance get(@Param("program") Program program, @Param("period") Period period, @Param("member") Member member);

    @Query("select distinct s from Surveillance s where s.program=:program and s.period=:period")
    public List<Surveillance> getSurviellances(@Param("program") Program program, @Param("period") Period period);

}
