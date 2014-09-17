package com.survtower.business.common.repository;

import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Takunda Dhlakama
 */
public interface SurveillanceDataRepository extends GenericRepository<SurveillanceData, Long> {

    @Query("select distinct s from SurveillanceData s where s.surveillance=:surveillanceData")
    public List<SurveillanceData> findSurveillanceDataItems(@Param("surveillance") Surveillance surveillance);

    @Query("select distinct s from SurveillanceData s where s.surveillance.program=:program and s.surveillance.period=:period and s.surveillance.member=:member and s.indicator=:indicator")
    public SurveillanceData findSurveillanceData(@Param("program") Program program, @Param("period") Period period, @Param("member") Member member, @Param("indicator") Indicator indicator);

    @Query("select distinct s from SurveillanceData s where s.surveillance.program=:program and s.surveillance.member=:member and s.indicator=:indicator")
    public List<SurveillanceData> findSurveillanceDataItems(@Param("program") Program program, @Param("member") Member member, @Param("indicator") Indicator indicator);

    @Query("select distinct s from SurveillanceData s where s.surveillance.program=:program and s.surveillance.period=:period and s.surveillance.member=:member")
    public List<SurveillanceData> findSurveillanceDataItems(@Param("program") Program program, @Param("period") Period period, @Param("member") Member member);

    @Query("select distinct s from SurveillanceData s where s.surveillance.period=:period and s.surveillance.member=:member and s.indicator=:indicator")
    public List<SurveillanceData> findSurveillanceDataItems(@Param("period") Period period, @Param("member") Member member, @Param("indicator") Indicator indicator);

    @Query("select distinct s from SurveillanceData s where s.surveillance.period=:period")
    public List<SurveillanceData> findSurveillanceDataItems(@Param("period") Period period);

    @Query("select distinct s from SurveillanceData s where s.surveillance.program=:program")
    public List<SurveillanceData> findSurveillanceDataItems(@Param("program") Program program);

    @Query("select distinct s from SurveillanceData s where s.indicator=:indicator")
    public List<SurveillanceData> findSurveillanceDataItems(@Param("indicator") Indicator indicator);

    @Query("select distinct s from SurveillanceData s where s.surveillance.member=:member and s.indicator=:indicator")
    public List<SurveillanceData> findSurveillanceDataItems(@Param("member") Member member, @Param("indicator") Indicator indicator);
}
