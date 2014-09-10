package com.survtower.business.member.integration.impl;

import com.survtower.business.common.domain.IndicatorGroup;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.service.IndicatorGroupService;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.IndicatorGroupIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.LookupMetaService;
import com.survtower.ws.api.IndicatorGroupWebservice;
import com.survtower.ws.api.domain.IndicatorGroupCollectionPayload;
import com.survtower.ws.api.domain.RequestMetaData;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("indicatorGroupIntegrator")
public class IndicatorGroupIntegratorImpl implements IndicatorGroupIntegrator {

    @Autowired
    private LookupMetaService lookupMetaService;
    @Autowired
    private IndicatorGroupService indicatorGroupService;
    @Autowired
    private IntegrationService integrationService;

    @Override
    public synchronized LookupMeta pull() {

        LookupMeta indicatorGroupLookupMeta=lookupMetaService.findByLookup(Lookup.INDICATOR_GROUP);

        Date startDate = null;

        if (indicatorGroupLookupMeta != null) {
            startDate = indicatorGroupLookupMeta.getLastServerTimestamp();
        }

        RequestMetaData requestMetaData = new RequestMetaData();
        requestMetaData.setMinimumDate(startDate);
        IndicatorGroupWebservice indicatorGroupWebservice = integrationService.getIndicatorGroupWebservice();

        IndicatorGroupCollectionPayload indicatorGroupCollectionPayload = indicatorGroupWebservice.getIndicatorGroups(requestMetaData);

        List<IndicatorGroup> indicatorGroups = indicatorGroupCollectionPayload.getIndicatorGroups();
        if (indicatorGroups != null && !indicatorGroups.isEmpty()) {
            for (IndicatorGroup indicatorGroup : indicatorGroups) {
                IndicatorGroup existing = indicatorGroupService.findByUuid(indicatorGroup.getUuid());
                if (existing != null) {
                    //ID is not send
                    indicatorGroup.setId(existing.getId());
                }else{
                    //it must be a new object
                    indicatorGroup.setId(null);
                }
                indicatorGroupService.save(indicatorGroup);
            }
        }

        if (indicatorGroupLookupMeta == null) {
            indicatorGroupLookupMeta = new LookupMeta(Lookup.INDICATOR_GROUP);
        }

        if (indicatorGroupCollectionPayload.getPayloadMetaData() != null && indicatorGroupCollectionPayload.getPayloadMetaData().getMaximumDate() != null) {
            indicatorGroupLookupMeta.setLastServerTimestamp(indicatorGroupCollectionPayload.getPayloadMetaData().getMaximumDate());
        }
        
        indicatorGroupLookupMeta.setUpdateDate(new Date());
        lookupMetaService.save(indicatorGroupLookupMeta);

        return indicatorGroupLookupMeta;
    }
}
