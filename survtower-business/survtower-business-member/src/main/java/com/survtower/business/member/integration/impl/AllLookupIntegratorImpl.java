package com.survtower.business.member.integration.impl;

import com.survtower.business.common.domain.Lookup;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.AllLookupIntegrator;
import com.survtower.business.member.integration.DataElementIntegrator;
import com.survtower.business.member.integration.DataSourceCategoryIntegrator;
import com.survtower.business.member.integration.DataSourceIntegrator;
import com.survtower.business.member.integration.FrequencyIntegrator;
import com.survtower.business.member.integration.IndicatorGroupIntegrator;
import com.survtower.business.member.integration.IndicatorIntegrator;
import com.survtower.business.member.integration.IndicatorTypeIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.integration.MemberIntegrator;
import com.survtower.business.member.integration.PeriodIntegrator;
import com.survtower.business.member.integration.ProgramIntegrator;
import com.survtower.business.member.service.LookupMetaService;
import com.survtower.ws.api.domain.ServerLookupMetaData;
import com.survtower.ws.api.domain.LookupMetaDataCollectionPayload;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Charles Chigoriwa
 */
@Service("allLookupIntegrator")
public class AllLookupIntegratorImpl implements AllLookupIntegrator {

    @Autowired
    private IntegrationService integrationService;
    @Autowired
    private IndicatorIntegrator indicatorIntegrator;
    @Autowired
    private LookupMetaService lookupMetaService;
    @Autowired
    private MemberIntegrator memberIntegrator;
    @Autowired
    private DataSourceCategoryIntegrator dataSourceCategoryIntegrator;
    @Autowired
    private PeriodIntegrator periodIntegrator;
    @Autowired
    private FrequencyIntegrator frequencyIntegrator;
    @Autowired
    private IndicatorTypeIntegrator indicatorTypeIntegrator;
    @Autowired
    private ProgramIntegrator programIntegrator;
    @Autowired
    private IndicatorGroupIntegrator indicatorGroupIntegrator;
    @Autowired
    private DataSourceIntegrator dataSourceIntegrator;
    @Autowired
    private DataElementIntegrator dataElementIntegrator;

    @Override
    public synchronized void pull() {
        System.out.println("GLOBAL LOOKUP POOLING");

        try {
            LookupMetaDataCollectionPayload payload = integrationService.getLookupDataWebService().getLookupMetaDataList();
            if (payload != null) {
                List<ServerLookupMetaData> lookupMetaDataList = payload.getLookupMetaDataList();
                if (lookupMetaDataList != null && !lookupMetaDataList.isEmpty()) {
                    for (ServerLookupMetaData serverLookupMetaData : lookupMetaDataList) {
                        if (serverLookupMetaData.getLastUpdateNo() != null) {
                            LookupMeta localLookupMeta = lookupMetaService.findByLookup(serverLookupMetaData.getLookup());
                            if (localLookupMeta == null || serverLookupMetaData.getLastUpdateNo()>localLookupMeta.getLastServerUpdateNo()) {
                                if (Lookup.INDICATOR.equals(serverLookupMetaData.getLookup())) {
                                    indicatorIntegrator.pull();
                                } else if (Lookup.MEMBER.equals(serverLookupMetaData.getLookup())) {
                                    memberIntegrator.pull();
                                } else if (Lookup.DATA_SOURCE_CATEGORY.equals(serverLookupMetaData.getLookup())) {
                                    dataSourceCategoryIntegrator.pull();
                                } else if (Lookup.PERIOD.equals(serverLookupMetaData.getLookup())) {
                                    periodIntegrator.pull();
                                } else if (Lookup.FREQUENCY.equals(serverLookupMetaData.getLookup())) {
                                    frequencyIntegrator.pull();
                                } else if (Lookup.INDICATOR_TYPE.equals(serverLookupMetaData.getLookup())) {
                                    indicatorTypeIntegrator.pull();
                                } else if (Lookup.PROGRAM.equals(serverLookupMetaData.getLookup())) {
                                    programIntegrator.pull();
                                } else if (Lookup.INDICATOR_GROUP.equals(serverLookupMetaData.getLookup())) {
                                    indicatorGroupIntegrator.pull();
                                } else if (Lookup.DATA_SOURCE.equals(serverLookupMetaData.getLookup())) {
                                    dataSourceIntegrator.pull();
                                } else if (Lookup.DATA_ELEMENT.equals(serverLookupMetaData.getLookup())) {
                                    dataElementIntegrator.pull();
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("------ Failed to Download From Server");
        }

    }

}
