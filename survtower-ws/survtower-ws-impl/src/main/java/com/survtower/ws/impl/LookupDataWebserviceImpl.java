package com.survtower.ws.impl;

import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.IndicatorService;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.ProgramService;
//import com.survtower.business.member.service.UserService;
import com.survtower.ws.api.LookupDataWebservice;
import com.survtower.ws.api.domain.LookupMetaDataCollectionPayload;
import org.springframework.beans.factory.annotation.Autowired;
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
    private MemberService countryService;
//    @Autowired
//    private UserService userService;

    public LookupMetaDataCollectionPayload getLookupMetaDataList() {
        LookupMetaDataCollectionPayload payload = new LookupMetaDataCollectionPayload();
        payload.add(Lookup.INDICATOR, indicatorService.findMaximumUpdateDate());
        payload.add(Lookup.PERIOD, periodService.findMaximumUpdateDate());
        payload.add(Lookup.PROGRAM, programService.findMaximumUpdateDate());
        payload.add(Lookup.COUNTRY, countryService.findMaximumUpdateDate());
        //payload.add(Lookup.USER, userService.findMaximumUpdateDate());
        return payload;
    }

}
