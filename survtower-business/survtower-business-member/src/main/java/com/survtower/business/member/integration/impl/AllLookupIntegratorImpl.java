package com.survtower.business.member.integration.impl;

import com.survtower.business.common.domain.Lookup;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.AllLookupIntegrator;
import com.survtower.business.member.integration.IndicatorIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.integration.MemberIntegrator;
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

    @Override
    public synchronized void pull() {
        System.out.println("GLOBAL LOOKUP POOLING");
        LookupMetaDataCollectionPayload payload = integrationService.getLookupDataWebservice().getLookupMetaDataList();
        if (payload != null) {
            List<ServerLookupMetaData> lookupMetaDataList = payload.getLookupMetaDataList();
            if (lookupMetaDataList != null && !lookupMetaDataList.isEmpty()) {
                for (ServerLookupMetaData serverLookupMetaData : lookupMetaDataList) {
                    if (serverLookupMetaData.getLastUpdateTimestamp() != null) {
                        LookupMeta localLookupMeta = lookupMetaService.findByLookup(serverLookupMetaData.getLookup());
                        if (localLookupMeta == null || serverLookupMetaData.getLastUpdateTimestamp().after(localLookupMeta.getLastServerTimestamp())) {
                            if (Lookup.INDICATOR.equals(serverLookupMetaData.getLookup())) {
                                indicatorIntegrator.pull();
                            }else if (Lookup.MEMBER.equals(serverLookupMetaData.getLookup())) {
                                memberIntegrator.pull();
                            } //////PUT AS MANY ALTERNATIVES AS YOU CAN
                        }
                    }
                }
            }
        }
    }

}
