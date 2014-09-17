package com.survtower.business.common.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface SurveillanceDataService extends GenericService<SurveillanceData> {

    public List<SurveillanceData> findSurveillanceDataItems(Surveillance surveillance);

    public SurveillanceData findSurveillanceData(Program program, Period period, Member member, Indicator indicator);

    public List<SurveillanceData> findSurveillanceDataItems(Program program, Member member, Indicator indicator);
    
    public List<SurveillanceData> findSurveillanceDataItems(Period period, Member member, Indicator indicator);

    public List<SurveillanceData> findSurveillanceDataItems(Program program, Period period, Member member);

    public List<SurveillanceData> findSurveillanceDataItems(Period period);

    public List<SurveillanceData> findSurveillanceDataItems(Program program);

    public List<SurveillanceData> findSurveillanceDataItems(Indicator indicator);

    public List<SurveillanceData> findSurveillanceDataItems(Member member, Indicator indicator);
}
