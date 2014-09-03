package com.survtower.business.member.integration.impl;

import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.service.IndicatorService;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.IndicatorIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.LookupMetaService;
import com.survtower.ws.api.IndicatorWebservice;
import com.survtower.ws.api.domain.IndicatorCollectionPayload;
import com.survtower.ws.api.domain.RequestMetaData;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("indicatorIntegrator")
public class IndicatorIntegratorImpl implements IndicatorIntegrator {

    @Autowired
    private LookupMetaService lookupMetaService;
    @Autowired
    private IndicatorService indicatorService;
    @Autowired
    private IntegrationService integrationService;

    @Override
    public synchronized LookupMeta pull() {

        LookupMeta indicatorLookupMeta=lookupMetaService.findByLookup(Lookup.INDICATOR);

        Date startDate = null;

        if (indicatorLookupMeta != null) {
            startDate = indicatorLookupMeta.getLastServerTimestamp();
        }

        RequestMetaData requestMetaData = new RequestMetaData();
        requestMetaData.setMinimumDate(startDate);
        IndicatorWebservice indicatorWebservice = integrationService.getIndicatorWebservice();

        IndicatorCollectionPayload indicatorCollectionPayload = indicatorWebservice.getIndicators(requestMetaData);

        List<Indicator> indicators = indicatorCollectionPayload.getIndicators();
        if (indicators != null && !indicators.isEmpty()) {
            for (Indicator indicator : indicators) {
                Indicator existing = indicatorService.findByUuid(indicator.getUuid());
                if (existing != null) {
                    //ID is not send
                    indicator.setId(existing.getId());
                }else{
                    //it must be a new object
                    indicator.setId(null);
                }
                indicatorService.save(indicator);
            }
        }

        if (indicatorLookupMeta == null) {
            indicatorLookupMeta = new LookupMeta(Lookup.INDICATOR);
        }

        if (indicatorCollectionPayload.getPayloadMetaData() != null && indicatorCollectionPayload.getPayloadMetaData().getMaximumDate() != null) {
            indicatorLookupMeta.setLastServerTimestamp(indicatorCollectionPayload.getPayloadMetaData().getMaximumDate());
        }
        
        indicatorLookupMeta.setUpdateDate(new Date());
        lookupMetaService.save(indicatorLookupMeta);

        return indicatorLookupMeta;
    }
}
