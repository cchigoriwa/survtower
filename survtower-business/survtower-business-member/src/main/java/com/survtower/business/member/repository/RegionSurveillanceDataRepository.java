package com.survtower.business.member.repository;

import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceData;
import java.util.List;
import net.sadc.business.common.GenericRepository;

/**
 *
 * @author Takunda Dhlakama
 */
public interface RegionSurveillanceDataRepository extends GenericRepository<RegionSurveillanceData, Long> {

    public List<RegionSurveillanceData> findBySurveillanceData(SurveillanceData surveillanceData);

    public RegionSurveillanceData findBySurveillanceDataAndRegion(SurveillanceData surveillanceData, Region region);
}
