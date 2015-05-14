package com.survtower.business.member.dao;

import com.survtower.business.common.GenericDao;
import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceData;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface RegionSurveillanceDataDao extends GenericDao<RegionSurveillanceData> {

    public List<RegionSurveillanceData> findAll(SurveillanceData surveillanceData);

    public RegionSurveillanceData find(SurveillanceData surveillanceData, Region region);

    public Double getNumeratorCalculatedValue(SurveillanceData surveillanceData);

    public Double getDenominatedCalculatedValue(SurveillanceData surveillanceData);
    
    public List<RegionSurveillanceData> findAll(Surveillance surveillance , Region region);
    
    public List<RegionSurveillanceData> findAll(Period period, Indicator indicator, Region region);
    
    public List<RegionSurveillanceData> findAll(Program program, Region region);
    
    public List<RegionSurveillanceData> findAll(Period period, Program program, Region region);
}
