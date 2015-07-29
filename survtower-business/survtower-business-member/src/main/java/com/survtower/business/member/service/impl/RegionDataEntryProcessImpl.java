package com.survtower.business.member.service.impl;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.ProgramService;
import com.survtower.business.common.service.SurveillanceService;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceAudit;
import com.survtower.business.member.domain.RegionSurveillanceData;
import com.survtower.business.member.service.RegionDataEntryProcess;
import com.survtower.business.member.service.RegionService;
import com.survtower.business.member.service.RegionSurveillanceAuditService;
import com.survtower.business.member.service.RegionSurveillanceDataService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel Nkhoma
 */
@Service("regionDataEntryProcess")
@Transactional(readOnly = true)
public class RegionDataEntryProcessImpl implements RegionDataEntryProcess {

    @Autowired
    private ProgramService programService;
    @Autowired
    private PeriodService periodService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private SurveillanceService surveillanceService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private RegionSurveillanceAuditService regionSurveillanceAuditService;
    @Autowired
    private RegionSurveillanceDataService regionSurveillanceDataService;

    private final List<RegionSurveillanceData> regionSurveillanceDataList = new ArrayList<>();
    private Program program;
    private Period period;
    private Region region;
    private Surveillance surveillance;
    private RegionSurveillanceAudit regionSurveillanceAudit;

    @Override
    public List<RegionSurveillanceData> loadRegionData(String programUuid, String periodUuid, String regionUuid) {

        program = programService.findByUuid(programUuid);
        period = periodService.findByUuid(periodUuid);
        region = regionService.findByUuid(regionUuid);

        surveillance = surveillanceService.get(program, period, memberService.getCurrentMember());

        if (surveillance == null) {
            surveillance = surveillanceService.createSurveillanceData(program, period, memberService.getCurrentMember());
        }

        regionSurveillanceAudit = regionSurveillanceAuditService.get(program, period, region);

        if (regionSurveillanceAudit == null) {
            regionSurveillanceAudit = new RegionSurveillanceAudit();
            for (SurveillanceData surveillanceData : surveillance.getSurveillanceDataSet()) {
                RegionSurveillanceData regionSurveillanceData;
                regionSurveillanceData = regionSurveillanceDataService.find(surveillanceData, region);
                if (regionSurveillanceData == null) {
                    regionSurveillanceData = new RegionSurveillanceData();
                    regionSurveillanceData.setSurveillanceData(surveillanceData);
                    regionSurveillanceData.setCreateDate(new Date());
                    regionSurveillanceData.setRegion(region);
                }
                regionSurveillanceDataList.clear();
                regionSurveillanceDataList.add(regionSurveillanceData);
            }
        } else {
            regionSurveillanceDataList.clear();
            regionSurveillanceDataList.addAll(regionSurveillanceDataService.findAll(surveillance, region));
        }
        return regionSurveillanceDataList;
    }
}
