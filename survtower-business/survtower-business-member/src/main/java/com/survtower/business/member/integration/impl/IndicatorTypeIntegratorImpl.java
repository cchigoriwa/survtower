package com.survtower.business.member.integration.impl;

import com.survtower.business.common.domain.IndicatorType;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.service.IndicatorTypeService;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.IndicatorTypeIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.LookupMetaService;
import com.survtower.ws.api.IndicatorTypeWebservice;
import com.survtower.ws.api.domain.IndicatorTypeCollectionPayload;
import com.survtower.ws.api.domain.RequestMetaData;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("indicatorTypeIntegrator")
public class IndicatorTypeIntegratorImpl implements IndicatorTypeIntegrator {

    @Autowired
    private LookupMetaService lookupMetaService;
    @Autowired
    private IndicatorTypeService indicatorTypeService;
    @Autowired
    private IntegrationService integrationService;

    @Override
    public synchronized LookupMeta pull() {

        LookupMeta indicatorTypeLookupMeta=lookupMetaService.findByLookup(Lookup.INDICATOR_TYPE);

        Date startDate = null;

        if (indicatorTypeLookupMeta != null) {
            startDate = indicatorTypeLookupMeta.getLastServerTimestamp();
        }

        RequestMetaData requestMetaData = new RequestMetaData();
        requestMetaData.setMinimumDate(startDate);
        IndicatorTypeWebservice indicatorTypeWebservice = integrationService.getIndicatorTypeWebservice();

        IndicatorTypeCollectionPayload indicatorTypeCollectionPayload = indicatorTypeWebservice.getIndicatorTypes(requestMetaData);

        List<IndicatorType> indicatorTypes = indicatorTypeCollectionPayload.getIndicatorTypes();
        if (indicatorTypes != null && !indicatorTypes.isEmpty()) {
            for (IndicatorType indicatorType : indicatorTypes) {
                IndicatorType existing = indicatorTypeService.findByUuid(indicatorType.getUuid());
                if (existing != null) {
                    //ID is not send
                    indicatorType.setId(existing.getId());
                }else{
                    //it must be a new object
                    indicatorType.setId(null);
                }
                indicatorTypeService.save(indicatorType);
            }
        }

        if (indicatorTypeLookupMeta == null) {
            indicatorTypeLookupMeta = new LookupMeta(Lookup.INDICATOR_TYPE);
        }

        if (indicatorTypeCollectionPayload.getPayloadMetaData() != null && indicatorTypeCollectionPayload.getPayloadMetaData().getMaximumDate() != null) {
            indicatorTypeLookupMeta.setLastServerTimestamp(indicatorTypeCollectionPayload.getPayloadMetaData().getMaximumDate());
        }
        
        indicatorTypeLookupMeta.setUpdateDate(new Date());
        lookupMetaService.save(indicatorTypeLookupMeta);

        return indicatorTypeLookupMeta;
    }
}
