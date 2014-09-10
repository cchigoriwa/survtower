package com.survtower.business.member.integration.impl;

import com.survtower.business.common.domain.Frequency;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.service.FrequencyService;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.FrequencyIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.LookupMetaService;
import com.survtower.ws.api.FrequencyWebservice;
import com.survtower.ws.api.domain.FrequencyCollectionPayload;
import com.survtower.ws.api.domain.RequestMetaData;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("frequencyIntegrator")
public class FrequencyIntegratorImpl implements FrequencyIntegrator {

    @Autowired
    private LookupMetaService lookupMetaService;
    @Autowired
    private FrequencyService frequencyService;
    @Autowired
    private IntegrationService integrationService;

    @Override
    public synchronized LookupMeta pull() {

        LookupMeta frequencyLookupMeta=lookupMetaService.findByLookup(Lookup.FREQUENCY);

        Date startDate = null;

        if (frequencyLookupMeta != null) {
            startDate = frequencyLookupMeta.getLastServerTimestamp();
        }

        RequestMetaData requestMetaData = new RequestMetaData();
        requestMetaData.setMinimumDate(startDate);
        FrequencyWebservice frequencyWebservice = integrationService.getFrequencyWebservice();

        FrequencyCollectionPayload frequencyCollectionPayload = frequencyWebservice.getFrequencys(requestMetaData);

        List<Frequency> frequencys = frequencyCollectionPayload.getFrequencys();
        if (frequencys != null && !frequencys.isEmpty()) {
            for (Frequency frequency : frequencys) {
                Frequency existing = frequencyService.findByUuid(frequency.getUuid());
                if (existing != null) {
                    //ID is not send
                    frequency.setId(existing.getId());
                }else{
                    //it must be a new object
                    frequency.setId(null);
                }
                frequencyService.save(frequency);
            }
        }

        if (frequencyLookupMeta == null) {
            frequencyLookupMeta = new LookupMeta(Lookup.FREQUENCY);
        }

        if (frequencyCollectionPayload.getPayloadMetaData() != null && frequencyCollectionPayload.getPayloadMetaData().getMaximumDate() != null) {
            frequencyLookupMeta.setLastServerTimestamp(frequencyCollectionPayload.getPayloadMetaData().getMaximumDate());
        }
        
        frequencyLookupMeta.setUpdateDate(new Date());
        lookupMetaService.save(frequencyLookupMeta);

        return frequencyLookupMeta;
    }
}
