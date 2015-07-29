package com.survtower.business.member.service;

import com.survtower.business.member.domain.RegionSurveillanceData;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Daniel Nkhoma
 */
public interface RegionDataEntryProcess extends Serializable {

    public List<RegionSurveillanceData> loadRegionData(String programUuid, String periodUuid, String regionUuid);

}
