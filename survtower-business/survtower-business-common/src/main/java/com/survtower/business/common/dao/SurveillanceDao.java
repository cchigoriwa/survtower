package com.survtower.business.common.dao;

import com.survtower.business.common.GenericDao;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface SurveillanceDao extends GenericDao<Surveillance> {

    public Surveillance get(Program program, Period period, Member member);
    
    public List<Surveillance> getSurviellances(Program program, Period period);
}
