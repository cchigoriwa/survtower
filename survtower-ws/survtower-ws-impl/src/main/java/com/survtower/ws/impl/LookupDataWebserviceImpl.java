package com.survtower.ws.impl;

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

    public LookupMetaDataCollectionPayload getLookupMetaDataList() {
        LookupMetaDataCollectionPayload payload = new LookupMetaDataCollectionPayload();
        payload.add(Lookup.INDICATOR, indicatorService.findMaximumUpdateDate());
        payload.add(Lookup.PERIOD, periodService.findMaximumUpdateDate());
        payload.add(Lookup.PROGRAM, programService.findMaximumUpdateDate());
        payload.add(Lookup.COUNTRY, countryService.findMaximumUpdateDate());
        payload.add(Lookup.DATASOURCE, dataSourceService.findMaximumUpdateDate());
        payload.add(Lookup.DATASOURCECATEGORY, dataSourceCategoryService.findMaximumUpdateDate());
        payload.add(Lookup.INDICATORTYPE, indicatorTypeService.findMaximumUpdateDate());
        payload.add(Lookup.DATAELEMENT, dataElementService.findMaximumUpdateDate());
        payload.add(Lookup.INDICATORGROUP, indicatorGroupService.findMaximumUpdateDate());
         payload.add(Lookup.FREQUENCY, frequencyService.findMaximumUpdateDate());        
        return payload;
    }

}
