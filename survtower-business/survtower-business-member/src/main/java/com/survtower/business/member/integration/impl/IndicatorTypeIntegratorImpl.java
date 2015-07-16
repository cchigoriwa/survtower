package com.survtower.business.member.integration.impl;

import com.survtower.business.common.domain.IndicatorType;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.service.IndicatorTypeService;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.IndicatorTypeIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.LookupMetaService;
import com.survtower.ws.api.IndicatorTypeWebService;
import com.survtower.ws.api.domain.IndicatorTypePayload;
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

        Long lastUpdateNo = null;

        if (indicatorTypeLookupMeta != null) {
            lastUpdateNo = indicatorTypeLookupMeta.getLastServerUpdateNo();
        }

       
        IndicatorTypeWebService indicatorTypeWebService = integrationService.getIndicatorTypeWebService();

        IndicatorTypePayload indicatorTypePayload = indicatorTypeWebService.getData(lastUpdateNo);

        List<IndicatorType> indicatorTypes = indicatorTypePayload.getIndicatorTypeBody().getIndicatorTypes();
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

        if (indicatorTypePayload.getResponseHead() != null && indicatorTypePayload.getResponseHead().getLastUpdateNo() != null) {
            indicatorTypeLookupMeta.setLastServerUpdateNo(indicatorTypePayload.getResponseHead().getLastUpdateNo());
        }
        
        indicatorTypeLookupMeta.setUpdateDate(new Date());
        lookupMetaService.save(indicatorTypeLookupMeta);

        return indicatorTypeLookupMeta;
    }
}
