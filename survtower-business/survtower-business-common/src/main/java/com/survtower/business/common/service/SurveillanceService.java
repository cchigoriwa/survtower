package com.survtower.business.common.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface SurveillanceService extends GenericService<Surveillance> {

    public Surveillance get(Program program, Period period, Member member);
    
    public List<Surveillance> getSurviellances(Program program, Period period);
    
     //To be used on the member side only otherwise a non single result exception will be thrown
    public Surveillance findByProgramAndPeriod(Program program,Period period);
    
    //Can be used by both but is suited on the central side
    public Surveillance findByProgramAndPeriodAndMember(Program program,Period period,Member member);
    
    public Surveillance createSurveillanceData(Program program,Period period, Member member);
}
