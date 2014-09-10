package com.survtower.ws.impl;

import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.central.service.MemberSecurityService;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.service.DataElementService;
import com.survtower.business.common.service.DataSourceCategoryService;
import com.survtower.business.common.service.DataSourceService;
import com.survtower.business.common.service.FrequencyService;
import com.survtower.business.common.service.IndicatorGroupService;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.IndicatorService;
import com.survtower.business.common.service.IndicatorTypeService;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.ProgramService;
import com.survtower.ws.api.LookupDataWebservice;
import com.survtower.ws.api.domain.LookupMetaDataCollectionPayload;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component
public class LookupDataWebserviceImpl implements LookupDataWebservice {

    @Autowired
    private IndicatorService indicatorService;
    @Autowired
    private PeriodService periodService;
    @Autowired
    private ProgramService programService;
    @Autowired
    private DataSourceService dataSourceService;
    @Autowired
    private DataElementService dataElementService;
    @Autowired
    private DataSourceCategoryService dataSourceCategoryService;
    @Autowired
    private FrequencyService frequencyService;
    @Autowired
    private IndicatorTypeService indicatorTypeService;
    @Autowired
    private IndicatorGroupService indicatorGroupService;
    @Autowired
    private MemberSecurityService memberSecurityService;

    public LookupMetaDataCollectionPayload getLookupMetaDataList() {
        LookupMetaDataCollectionPayload payload = new LookupMetaDataCollectionPayload();
        payload.add(Lookup.MEMBER, findMaximumUpdateForMember());
        payload.add(Lookup.PERIOD, periodService.findMaximumUpdateDate());
        payload.add(Lookup.PROGRAM, programService.findMaximumUpdateDate());
        payload.add(Lookup.FREQUENCY, frequencyService.findMaximumUpdateDate());
        payload.add(Lookup.DATA_SOURCE_CATEGORY, dataSourceCategoryService.findMaximumUpdateDate());
        payload.add(Lookup.DATA_SOURCE, dataSourceService.findMaximumUpdateDate());
        payload.add(Lookup.INDICATOR_TYPE, indicatorTypeService.findMaximumUpdateDate());
        payload.add(Lookup.DATA_ELEMENT, dataElementService.findMaximumUpdateDate());
        payload.add(Lookup.INDICATOR_GROUP, indicatorGroupService.findMaximumUpdateDate());
        payload.add(Lookup.INDICATOR, indicatorService.findMaximumUpdateDate());
        return payload;
    }

    private Date findMaximumUpdateForMember() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String memberID = auth.getName();
        MemberSecurity memberSecurity = memberSecurityService.findByMemberID(memberID);
        return memberSecurity.getMember().getUpdateDate();
    }

}
